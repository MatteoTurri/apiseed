package apiseed

import apiseed.model.Configuration
import scala.collection.concurrent.TrieMap

class ConfigurationRepository {
  val configurations: TrieMap[String, Configuration] = new TrieMap()

  def readAll(): List[Configuration] = configurations.values.toList

  def readById(id: String): Option[Configuration] = configurations.get(id)

  def delete(id: String): Option[Configuration] = configurations.remove(id)

  def create(conf: Configuration): Option[Configuration] = configurations.putIfAbsent(conf.id, conf)
}
