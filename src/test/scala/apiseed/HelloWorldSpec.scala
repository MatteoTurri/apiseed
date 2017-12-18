package apiseed

import scala.concurrent.Future
import org.scalatest._

class ConfigurationServiceSpec extends AsyncFlatSpec with Matchers {
  "The service" should "return an empty list when the readAll method is invoked" in {
    val repo = new ConfigurationRepository()
    val service = new ConfigurationService(repo)
    service.readAll().map(_.fold(
      error => fail(error.toString),
      result => result shouldBe List()
    ))
  }
}
