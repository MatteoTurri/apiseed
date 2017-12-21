package apiseed

import scala.concurrent.Future
import org.scalatest._
import Matchers._
import apiseed.error.ApiError
import apiseed.model.Configuration

class ConfigurationServiceSpec extends AsyncFlatSpec {

  val foo = new Configuration("foo", "My name is foo", "The value is foo")
  val bar = new Configuration("bar", "My name is bar", "The value is bar")
  
  "The service" should "return an empty list when the readAll method is invoked and no configuration is stored" in {
    val service = setUp()
    service.readAll().checkFutureResult(_ shouldBe empty)
  }

  "The service" should "return ConfigNotFoundError when the readById method is invoked passing the id of a non-existing configuration" in {
    val service = setUp()
    service.readById(foo.id).checkFutureError(_ shouldBe ApiError.ConfigNotFound)
  }

  "The service" should "return ConfigNotFoundError when the delete method is invoked passing the id of a non-existing configuration" in {
    val service = setUp()
    service.delete(foo.id).checkFutureError(_ shouldBe ApiError.ConfigNotFound)
  }

  "The service" should "return Unit when the create method is invoked for a configuration that is not already stored" in {
    val service = setUp()
    service.create(foo).checkFutureResult(_ => succeed)
  }

  "The service" should "return the list of stored configurations when the readAll method is invoked" in {
    val service = setUp()
    service.create(foo)
    service.create(bar)
    service.readAll().checkFutureResult(_ should contain only (foo, bar))
  }

  "The service"should "return Unit when the delete method is invoked passing the id of an existing configuration" in {
    val service = setUp()
    service.create(bar)
    service.delete(bar.id).checkFutureResult(_ => succeed)
  }

  private def setUp(): ConfigurationService = {
    val repo = new ConfigurationRepository()
    new ConfigurationService(repo)
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
