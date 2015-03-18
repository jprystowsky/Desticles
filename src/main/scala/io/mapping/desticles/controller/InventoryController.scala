package io.mapping.desticles.controller

import io.mapping.desticles.model.{Inventory, InventoryItemDetail, PlayerCharacter, DestinyPlayer}

object InventoryController extends BaseController {
	/**
	 * Get the inventory for a player's character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter corresponding to po
	 * @return an instance of Inventory
	 */
	def getCharacterInventory(po: DestinyPlayer, c: PlayerCharacter): Inventory = bungie.getInventory(po, c)

	/**
	 * Get the inventory item detail for a player's character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter
	 * @param itemInstanceId an item instance id from po's c's inventory
	 * @return an instance of InventoryItemDetail
	 */
	def getInventoryItemDetail(po: DestinyPlayer, c: PlayerCharacter, itemInstanceId: String): InventoryItemDetail = bungie.getInventoryItemDetail(po, c, itemInstanceId)
}
