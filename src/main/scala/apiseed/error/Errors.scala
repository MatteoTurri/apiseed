package apiseed.error

import io.buildo.enumero.annotations.enum

@enum trait ApiError{
  object ConfigNotFoundError
  object FatalError
}
