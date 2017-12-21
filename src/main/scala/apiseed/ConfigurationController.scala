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

  @command
  def delete(id: String): Future[Either[ApiError, Unit]]

  @command
  def create(conf: Configuration): Future[Either[ApiError, Unit]]

  @command
  def update(conf: Configuration): Future[Either[ApiError, Unit]]
}

class ConfigurationApiImpl(service: ConfigurationService)(implicit ec: ExecutionContext) extends ConfigurationApi {

  override def readAll(): Future[Either[ApiError, List[Configuration]]] = service.readAll()

  override def readById(id: String): Future[Either[ApiError, Configuration]] = service.readById(id)

  override def delete(id: String): Future[Either[ApiError, Unit]] = service.delete(id)

  override def create(conf: Configuration): Future[Either[ApiError, Unit]] = service.create(conf)

  override def update(conf: Configuration): Future[Either[ApiError, Unit]] = service.update(conf)
}

