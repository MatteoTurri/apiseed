package apiseed

import scala.concurrent.{Future, ExecutionContext}
import wiro.annotation._
import apiseed.model.Configuration

@path("conf")
trait ConfigurationApi {

  @query
  def readAll(): Future[Either[Throwable, List[Configuration]]]
}

class ConfigurationApiImpl(service: ConfigurationService)(implicit ec: ExecutionContext) extends ConfigurationApi {

  override def readAll(): Future[Either[Throwable, List[Configuration]]] = {
    service.readAll()
  }
}

