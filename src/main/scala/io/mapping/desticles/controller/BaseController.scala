package io.mapping.desticles.controller

import io.mapping.desticles.http.BungieHttpProvider

abstract class BaseController {
	protected def bungie = BungieHttpProvider
}
