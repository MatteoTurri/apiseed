package apiseed

import apiseed.error.ApiError

import wiro.server.akkaHttp._
import wiro.server.akkaHttp.FailSupport._

import akka.http.scaladsl.model.{HttpResponse, StatusCodes, ContentType, HttpEntity}
import akka.http.scaladsl.model.MediaTypes

import io.circe.syntax._
import io.circe.generic.auto._
import io.buildo.enumero.circe._

trait WiroCodecs {
  implicit def apiErrorToResponse: ToHttpResponse[ApiError] =
    error => HttpResponse(
      status = StatusCodes.NotFound,
      entity = HttpEntity(ContentType(MediaTypes.`application/json`), error.asJson.noSpaces)
    )
}

