package apiseed

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import wiro.Config
import wiro.server.akkaHttp._
import wiro.server.akkaHttp.FailSupport._

object Router extends App with RouterDerivationModule {
  implicit val system = ActorSystem("apiseed")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  implicit val throwableToResponse: ToHttpResponse[Throwable] = null

  val helloworldRouter = deriveRouter[HelloWorldApi](new HelloWorldApiImpl)

  val rpcServer = new HttpRPCServer(
    config = Config("localhost", 8080),
    routers = List(helloworldRouter)
  )
}
