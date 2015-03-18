package io.mapping.desticles.controller

import io.mapping.desticles.model.internal.PlayerAccount

object PlayerAccountController extends BaseController {
	/**
	 * Get a player account for a handle and membership type
	 * @param handle a system-specific player handle
	 * @param membershipType a Bungie membership type (defaults to "1" for XBL)
	 * @return an instance of PlayerAccount
	 */
	def getPlayerAccount(handle: String, membershipType: String = "1"): PlayerAccount = {
		val player = bungie.searchDestinyPlayer(handle, membershipType)
		val account = bungie.getAccount(player)

		new PlayerAccount(player, account)
	}
}
