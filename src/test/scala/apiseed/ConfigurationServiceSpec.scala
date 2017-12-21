package apiseed

import scala.concurrent.Future
import org.scalatest._
import org.scalatest.Matchers._
import apiseed.error.ApiError
import apiseed.model.Configuration

class ConfigurationServiceSpec extends fixture.AsyncFlatSpec with EitherValues {

  val foo = new Configuration("foo", "My name is foo", "The value is foo")
  val bar = new Configuration("bar", "My name is bar", "The value is bar")

  type FixtureParam = ConfigurationService
  
  def withFixture(test: OneArgAsyncTest): FutureOutcome = {
    val service = new ConfigurationService(new ConfigurationRepository())
    withFixture(test.toNoArgAsyncTest(service))
  }

  "The service" should "return an empty list when the readAll method is invoked and no configuration is stored" in { service =>
    service.readAll().checkFutureResult(_ shouldBe empty)
  }

  "The service" should "return ConfigNotFound error when the readById method is invoked passing the id of a non-existing configuration" in { service =>
    service.readById(foo.id).checkFutureError(_ shouldBe ApiError.ConfigNotFound)
  }

  "The service" should "return ConfigNotFound error when the delete method is invoked passing the id of a non-existing configuration" in { service =>
    service.delete(foo.id).checkFutureError(_ shouldBe ApiError.ConfigNotFound)
  }

  "The service" should "return Unit when the create method is invoked for a configuration that is not already stored" in { service =>
    service.create(foo).checkFutureResult(_ => succeed)
  }

  "The service" should "return ConfigAlreadyExisting error when invoking the create method passing the id of a configuration that is already existing" in { service =>
    for {
      _ <- service.create(foo)
      x <- service.create(foo)
    } yield {
      x.left.get shouldBe ApiError.ConfigAlreadyExisting
    }
  }

  "The service" should "return the list of stored configurations when the readAll method is invoked" in { service =>
    for {
      _ <- service.create(foo)
      _ <- service.create(bar)
      x <- service.readAll()
    } yield {
      x.right.get should contain only (foo, bar)
    }
  }

  "The service" should "return Unit when the delete method is invoked passing the id of an existing configuration" in { service =>
    for {
      _ <- service.create(bar)
      x <- service.delete(bar.id)
    } yield {
      x shouldBe Right(()) 
    }
  }

  "The service" should "delete an existing configuration successfully when the delete method is invoked passing the corresponding id" in { service =>
    for {
      _ <- service.create(bar)
      _ <- service.create(foo)
      x <- service.delete(bar.id)
      y <- service.readAll()
    } yield {
      x shouldBe Right(())
      y.right.get should contain only (foo)
    }
  }

  implicit private class Check[E,R](fut: Future[Either[E,R]]) {
    def checkFutureResult(g: R => Assertion): Future[Assertion] = 
      fut.map(_.fold(
        error => fail(error.toString),
        g
      ))

    def checkFutureError(h: E => Assertion): Future[Assertion] = 
      fut.map(_.fold(
        h,
        result => fail("Unexpected result")
      ))
  }
}
