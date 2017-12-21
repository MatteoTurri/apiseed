package apiseed.error

import io.buildo.enumero.annotations.enum

@enum trait ApiError{
  object ConfigNotFound
  object ConfigAlreadyExisting
}
