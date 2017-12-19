package apiseed

import scala.concurrent.{Future, ExecutionContext}
import apiseed.model.Configuration
import apiseed.error.ApiError

class ConfigurationService(repository: ConfigurationRepository)(implicit ec: ExecutionContext) {

  def readAll(): Future[Either[ApiError, List[Configuration]]] = Future {
    Right(repository.configurations)
  }

  def readById(id: String): Future[Either[ApiError, Configuration]] = Future {
    val result: Option[Configuration] = repository.findById(id)
    result match {
      case Some(conf) => Right(conf)
      case None => Left(ApiError.ConfigNotFoundError)
    }
  }
}
