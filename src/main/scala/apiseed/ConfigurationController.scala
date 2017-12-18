package apiseed

import scala.concurrent.{Future, ExecutionContext}
import wiro.annotation._
import apiseed.model.Configuration
import apiseed.error.ApiError

@path("conf")
trait ConfigurationApi {

  @query
  def readAll(): Future[Either[ApiError, List[Configuration]]]

  @query
  def readById(id: String): Future[Either[ApiError, Configuration]]
}

class ConfigurationApiImpl(service: ConfigurationService)(implicit ec: ExecutionContext) extends ConfigurationApi {

  override def readAll(): Future[Either[ApiError, List[Configuration]]] = service.readAll()

  override def readById(id: String): Future[Either[ApiError, Configuration]] = service.readById(id)
}
