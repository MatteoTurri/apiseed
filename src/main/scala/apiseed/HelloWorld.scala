package apiseed

import scala.concurrent.Future
import wiro.annotation._

@path("apiseed")
trait HelloWorldApi {

  @query
  def helloworld(): Future[Either[Throwable, String]]
}

class HelloWorldApiImpl() extends HelloWorldApi {
  import scala.concurrent.ExecutionContext.Implicits.global

  override def helloworld(): Future[Either[Throwable, String]] = {
    Future {
      Right("Hello world!")
    }
  }
}

