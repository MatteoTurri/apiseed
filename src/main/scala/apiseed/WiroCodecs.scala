package apiseed

import apiseed.error.ApiError

import wiro.server.akkaHttp._
import wiro.server.akkaHttp.FailSupport._

import akka.http.scaladsl.model._

import io.circe._
import io.circe.syntax._
import io.circe.generic.auto._
import io.buildo.enumero.circe._

trait WiroCodecs {
  implicit def apiErrorToResponse: ToHttpResponse[ApiError] = error =>
    error match { 
      case ApiError.ConfigNotFound => HttpResponse(
        status = StatusCodes.NotFound,
        entity = error
      )
      case ApiError.ConfigAlreadyExisting => HttpResponse(
        status = StatusCodes.BadRequest,
        entity = error
      )
    }

  private implicit def entityToJson[E](entity: E)(implicit encoder: Encoder[E]): HttpEntity.Strict = HttpEntity(
    ContentTypes.`application/json`, entity.asJson.noSpaces
  )
}

