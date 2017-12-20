package apiseed

import scala.concurrent.Future
import org.scalatest._
import apiseed.error.ApiError

class ConfigurationServiceSpec extends AsyncFlatSpec with Matchers {

  val repo = new ConfigurationRepository()
  val service = new ConfigurationService(repo)
  
  "The service" should "return an empty list when the readAll method is invoked" in {
    service.readAll().checkFutureResult(_ shouldBe empty)
  }

  "The service" should "return ConfigNotFoundError when the readById method is invoked passing the id of a non-existing configuration" in {
    service.readById("foo").checkFutureError(_ shouldBe ApiError.ConfigNotFoundError)
  }

  "The service" should "return ConfigNotFoundError when the delete method is invoked passing the id of a non-existing configuration" in {
    service.delete("foo").checkFutureError(_ shouldBe ApiError.ConfigNotFoundError)
  }

  implicit private class Check[E,R](fut: Future[Either[E,R]]) {
    def checkFutureResult(g: R => Assertion): Future[Assertion] = 
      fut.map(_.fold(
        error => fail(error.toString),
        result => g(result)
      ))

    def checkFutureError(h: E => Assertion): Future[Assertion] = 
      fut.map(_.fold(
        error => h(error),
        result => fail("Unexpected result")
      ))
  }
}
