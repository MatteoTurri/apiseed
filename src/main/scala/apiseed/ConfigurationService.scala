package apiseed

import scala.concurrent.{Future, ExecutionContext}
import apiseed.model.Configuration
import apiseed.error.ApiError

class ConfigurationService(repository: ConfigurationRepository)(implicit ec: ExecutionContext) {

  def readAll(): Future[Either[ApiError, List[Configuration]]] = Future {
    Right(repository.readAll())
  }

  def readById(id: String): Future[Either[ApiError, Configuration]] = Future {
    repository.readById(id).someToRight(ApiError.ConfigNotFound)
  }

  def delete(id: String): Future[Either[ApiError, Unit]] = Future {
    repository.delete(id).map(_ => ()).someToRight(ApiError.ConfigNotFound)
  }

  def create(conf: Configuration): Future[Either[ApiError, Unit]] = Future {
    repository.create(conf).someToLeft(ApiError.ConfigAlreadyExisting)
  }

  def update(conf: Configuration): Future[Either[ApiError, Unit]] = Future {
    repository.update(conf).map(_ => ()).someToRight(ApiError.ConfigNotFound)
  }

  implicit class OptionToEitherConverter[A](option: Option[A]) {
    
    def someToRight(error: ApiError): Either[ApiError, A] =
      option match {
        case Some(s) => Right(s)
        case None => Left(error)
      }

    def someToLeft(error: ApiError): Either[ApiError, Unit] =
      option match {
        case Some(_) => Left(ApiError.ConfigAlreadyExisting)
        case None => Right(())
      }
  }

}
