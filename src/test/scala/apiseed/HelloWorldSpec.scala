package apiseed

import scala.concurrent.Future
import org.scalatest._

class HelloWorldSpec extends AsyncFlatSpec with Matchers {
  "The controller" should "return \"Hello world!\" when the helloworld method is invoked" in {
    val apiImpl = new HelloWorldApiImpl()
    apiImpl.helloworld().map(_.fold(
      error => fail(error.toString),
      result => result shouldBe "Hello world!"
    ))
  }
}
