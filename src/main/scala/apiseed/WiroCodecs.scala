package apiseed

import wiro.server.akkaHttp._

trait WiroCodecs {
  implicit val throwableToResponse: ToHttpResponse[Throwable] = null
}
