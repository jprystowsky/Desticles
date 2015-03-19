package io.mapping.desticles.controller

import io.mapping.desticles.model._
import io.mapping.desticles.model.internal.PlayerAccount

object CharacterController extends BaseController {
	/**
	 * Get a player account's characters without making an API call
	 * @param po an instance of PlayerAccount
	 * @return an array of PlayerCharacter
	 */
	def getPlayerAccountCharacters(po: PlayerAccount): Array[PlayerCharacter] = getAccountCharacters(po.account)

	/**
	 * Get an account's characters without making an API call
	 * @param a an instance of Account
	 * @return an array of PlayerCharacter
	 */
	def getAccountCharacters(a: Account): Array[PlayerCharacter] = a.data.characters

	/**
	 * Get the stats for a player's characters
	 * @param po an instance of DestinyPlayer
	 * @return an instance of CharacterStats
	 */
	def getCharacterStats(po: DestinyPlayer): CharacterStats = bungie.getCharacterStats(po)

	/**
	 * Get the stats for a player's particular character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter
	 * @return an instance of CharacterStatCharacter
	 */
	def getCharacterStats(po: DestinyPlayer, c: PlayerCharacter): CharacterStatCharacter = getCharacterStats(po).characters.filter(csc => csc.characterId == c.characterBase.characterId).head

	/**
	 * Get the progression of a particular player's character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter
	 * @return an instance of CharacterProgression
	 */
	def getCharacterProgression(po: DestinyPlayer, c: PlayerCharacter): CharacterProgression = bungie.getCharacterProgression(po, c)

	/**
	 * Get the activities for a player's particular character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter corresponding to po
	 * @return an instance of CharacterActivities
	 */
	def getCharacterActivities(po: DestinyPlayer, c: PlayerCharacter): CharacterActivities = bungie.getCharacterActivities(po, c)
}
