package io.mapping.desticles.model

case class BaseResponse[T](Response: T, ErrorCode: Int, ThrottleSeconds: Int, ErrorStatus: String, Message: String)