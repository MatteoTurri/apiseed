package apiseed

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import wiro.Config
import wiro.server.akkaHttp._
import wiro.server.akkaHttp.FailSupport._

import com.typesafe.config.ConfigFactory
import io.circe.generic.auto._
import apiseed.model.Configuration

object Boot extends App with WiroCodecs with RouterDerivationModule {
  implicit val system = ActorSystem("apiseed")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val repo = new ConfigurationRepository()
  val service = new ConfigurationService(repo)

  val configurationsRouter = deriveRouter[ConfigurationApi](new ConfigurationApiImpl(service))

  val conf = ConfigFactory.load()

  val rpcServer = new HttpRPCServer(
    config = Config(conf.getString("apiseed.host"), conf.getInt("apiseed.port")),
    routers = List(configurationsRouter)
  )
}
