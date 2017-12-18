package apiseed

import scala.concurrent.{Future, ExecutionContext}
import wiro.annotation._

@path("apiseed")
trait HelloWorldApi {

  @query
  def helloworld(): Future[Either[Throwable, String]]
}

class HelloWorldApiImpl(implicit ec: ExecutionContext) extends HelloWorldApi {

  override def helloworld(): Future[Either[Throwable, String]] = Future {
    Right("Hello world!")
  }
}

