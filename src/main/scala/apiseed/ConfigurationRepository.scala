package apiseed

import apiseed.model.Configuration

class ConfigurationRepository {
  val configurations: List[Configuration] = List()

  def findById(id: String): Option[Configuration] = configurations.find(conf => conf.id == id)
}
