package io.mapping.desticles.controller

import io.mapping.desticles.model.{DestinyPlayer, GrimoireCards}

object GrimoireController extends BaseController {
	/**
	 * Get the Grimoire Cards for a player
	 * @param po an instance of DestinyPlayer
	 * @return an instance of GrimoireCards
	 */
	def getGrimoireCards(po: DestinyPlayer): GrimoireCards = bungie.getPlayerGrimoireCards(po)
}
