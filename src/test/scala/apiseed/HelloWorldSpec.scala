package apiseed
package test

import scala.concurrent.Future
import org.scalatest._

class HelloWorldSpec extends AsyncFlatSpec {
  "The controller" should "return \"Hello world!\" when the helloworld method is invoked" in {
    val apiImpl = new HelloWorldApiImpl()
    apiImpl.helloworld().map(_.fold(
      error => fail(error.toString),
      result => assert(result === "Hello world!")
    ))
  }
}
