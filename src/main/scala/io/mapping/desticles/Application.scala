package io.mapping.desticles

import com.typesafe.config.ConfigFactory
import io.mapping.desticles.controller.{CharacterController, InventoryController, PlayerAccountController}
import org.json4s.DefaultFormats

object Application extends App {
	val config = ConfigFactory.load()

	implicit val formats = DefaultFormats

	/**
	 * Get a player and their account
	 */
	val playerAcct = PlayerAccountController.getPlayerAccount(config.getString("playerName"))
	println(s"${playerAcct.player.displayName}'s membership id is ${playerAcct.account.data.membershipId}")

	/**
	 * Get the player's top-ranked character
	 */
	val topChar = playerAcct.account.data.characters.sortBy(- _.characterLevel).head
	println(s"The first top-ranked character, character id ${topChar.characterBase.characterId}, is level ${topChar.characterLevel}; their percent to next level is ${topChar.percentToNextLevel}")

	/**
	 * Get stats and progression on this character
	 */
	val topCharStats = CharacterController.getCharacterStats(playerAcct.player, topChar)
	println(s"This character has ${topCharStats.merged.allTime.kills.get.basic.displayValue} kills")

	val topCharProg = CharacterController.getCharacterProgression(playerAcct.player, topChar)
	val sumDailyProgs = topCharProg.data.progressions.getOrElse(Array()).foldLeft(0L)((b, p) => b + p.dailyProgress.getOrElse(0L))
	val avgProgs = sumDailyProgs / topCharProg.data.progressions.getOrElse(Array()).length
	println(s"Their average daily progress is $avgProgs")

	/**
	 * Get the character's currency, and the best item from the character's inventory and its detail
	 */
	val inv = InventoryController.getCharacterInventory(playerAcct.player, topChar).data
	var sumCurr = inv.currencies.foldLeft(0L)((b, idc) => b + idc.value)
	println(s"They have a total of $sumCurr currency")

	val topItem = inv.buckets.Equippable.flatMap(idb => idb.items).sortBy(- _.qualityLevel).head
	val topItemDetail = InventoryController.getInventoryItemDetail(playerAcct.player, topChar, topItem.itemInstanceId)
	println(s"The top quality item, instance id ${topItem.itemInstanceId}, is of quality level ${topItem.qualityLevel} and has the following perks:")
	for (p <- topItemDetail.data.item.perks) {
		println(s"\t${p.perkHash}")
	}
}
