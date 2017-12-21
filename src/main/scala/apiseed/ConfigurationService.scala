package apiseed

import scala.concurrent.{Future, ExecutionContext}
import apiseed.model.Configuration
import apiseed.error.ApiError

class ConfigurationService(repository: ConfigurationRepository)(implicit ec: ExecutionContext) {

  def readAll(): Future[Either[ApiError, List[Configuration]]] = Future {
    Right(repository.readAll())
  }

  def readById(id: String): Future[Either[ApiError, Configuration]] = Future {
    repository.readById(id) match {
      case Some(conf) => Right(conf)
      case None => Left(ApiError.ConfigNotFound)
    }
  }

  def delete(id: String): Future[Either[ApiError, Unit]] = Future {
    repository.delete(id) match {
      case Some(_) => Right(())
      case None => Left(ApiError.ConfigNotFound)
    }  
  }

  def create(conf: Configuration): Future[Either[ApiError, Unit]] = Future {
    repository.create(conf) match {
      case Some(_) => Left(ApiError.ConfigAlreadyExisting)
      case None => Right(())
    }
  }
}
