package apiseed

import apiseed.model.Configuration
import apiseed.error.ApiError

class ConfigurationRepository {
  var configurations: List[Configuration] = List()

  def findById(id: String): Option[Configuration] = configurations.find(_.id == id)

  def delete(id: String): Either[ApiError, Unit] = 
    findById(id) match {
      case Some(_) => {
        configurations = configurations.filterNot(_.id == id)
        Right(Unit)
      }
      case None => Left(ApiError.ConfigNotFoundError)
    }
}
