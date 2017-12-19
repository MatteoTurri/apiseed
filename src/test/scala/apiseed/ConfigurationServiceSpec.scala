package apiseed

import scala.concurrent.Future
import org.scalatest._
import apiseed.error.ApiError

class ConfigurationServiceSpec extends AsyncFlatSpec with Matchers {

  val foo = "foo"
  val errorMessage = "Api should return an error"
  val repo = new ConfigurationRepository()
  val service = new ConfigurationService(repo)
  
  "The service" should "return an empty list when the readAll method is invoked" in {
    service.readAll().map(_.fold(
      error => fail(error.toString),
      result => result shouldBe empty
    ))
  }

  "The service" should "return ConfigNotFoundError when the readById method is invoked passing the id of a non-existing configuration" in {
    service.readById(foo).map(_.fold(
      error => error shouldBe ApiError.ConfigNotFoundError,
      result => fail(errorMessage)
    ))
  }

  "The service" should "return ConfigNotFoundError when the delete method is invoked passing the id of a non-existing configuration" in {
    service.delete(foo).map(_.fold(
      error => error shouldBe ApiError.ConfigNotFoundError,
      result => fail(errorMessage)
    ))
  }
}
