package apiseed

import apiseed.model.Configuration

class ConfigurationRepository {
  var configurations: List[Configuration] = List()

  def findById(id: String): Option[Configuration] = configurations.find(_.id == id)

  def delete(id: String): Option[Unit] =
    findById(id) match {
      case Some(_) => {
        configurations = configurations.filterNot(_.id == id)
        Some(Unit)
      }
      case None => None
    }
}
