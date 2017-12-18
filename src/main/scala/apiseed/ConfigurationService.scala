package apiseed

import scala.concurrent.{Future, ExecutionContext}
import apiseed.model.Configuration

class ConfigurationService(repository: ConfigurationRepository)(implicit ec: ExecutionContext) {

  def readAll(): Future[Either[Throwable, List[Configuration]]] = Future {
    Right(repository.configurations)
  }
}
