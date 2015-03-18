package io.mapping.desticles.controller

import io.mapping.desticles.model.internal.PlayerAccount

object PlayerAccountController extends BaseController {
	def getPlayerAccount(handle: String, membershipType: String = "1"): PlayerAccount = {
		val player = bungie.searchDestinyPlayer(handle, membershipType)
		val account = bungie.getAccount(player)

		new PlayerAccount(player, account)
	}
}
