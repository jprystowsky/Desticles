package io.mapping.desticles.http

import com.typesafe.config.ConfigFactory
import io.mapping.desticles.model._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.{read, writePretty}

import scalaj.http.Http

trait BungieEndpoints {
	val destinyEndpoint = "http://www.bungie.net/Platform/Destiny/"
}

object BungieHttpProvider extends BungieEndpoints {
	val config = ConfigFactory.load()

	implicit val formats = DefaultFormats

	/**
	 * Gets the activities for a player's character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter
	 * @return an instance of CharacterActivities
	 */
	def getCharacterActivities(po: DestinyPlayer, c: PlayerCharacter) = getResponse[CharacterActivities](
		destinyEndpoint,
		createControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId, "Character", c.characterBase.characterId, "Activities")
		) + "?definitions=True"
	)

	/**
	 * Get the Grimoire cards for a player
	 * @param po an instance of DestinyPlayer
	 * @return an instance of GrimoireCards
	 */
	def getPlayerGrimoireCards(po: DestinyPlayer) = getResponse[GrimoireCards](
		destinyEndpoint,
		createControllerPath(
			Seq("Vanguard", "Grimoire", po.membershipType.toString, po.membershipId)
		) + "?definitions=True&flavour=True"
	)

	/**
	 * Get the progression of a player's character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter corresponding to po
	 * @return an instance of CharacterProgression
	 */
	def getCharacterProgression(po: DestinyPlayer, c: PlayerCharacter) = getResponse[CharacterProgression](
		destinyEndpoint,
		createControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId, "Character", c.characterBase.characterId, "Progression")
		)
	)

	/**
	 * Get the stats for a player's characters
	 * @param po an instance of DestinyPlayer
	 * @return an instance of CharacterStats
	 */
	def getCharacterStats(po: DestinyPlayer) = getResponse[CharacterStats](
		destinyEndpoint,
		createControllerPath(
			Seq("Stats", "Account", po.membershipType.toString, po.membershipId)
		)
	)

	/**
	 * Get the detail for a specific character's inventory item
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter corresponding to po
	 * @param itemInstanceId an item instance id corresponding to c
	 * @return an instance of InventoryItemDetail
	 */
	def getInventoryItemDetail(po: DestinyPlayer, c: PlayerCharacter, itemInstanceId: String) = getResponse[InventoryItemDetail](
		destinyEndpoint,
		createControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId, "Character", c.characterBase.characterId, "Inventory", itemInstanceId)
		) + "?definitions=True"
	)

	/**
	 * Get the inventory for a player's character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter corresponding to po
	 * @return an instance of Inventory
	 */
	def getInventory(po: DestinyPlayer, c: PlayerCharacter) = getResponse[Inventory](
		destinyEndpoint,
		createControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId, "Character", c.characterBase.characterId, "Inventory")
		) + "?definitions=True"
	)

	/**
	 * Get the account for a DestinyPlayer
	 * @param po an instance of DestinyPlayer
	 * @return an instance of Account
	 */
	def getAccount(po: DestinyPlayer) = getResponse[Account](
		destinyEndpoint,
		createControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId)
		)
	)

	/**
	 * Search for a Destiny player
	 * @param handle the player's system-specific name
	 * @param membershipType the Bungie membership type
	 * @return an instance of DestinyPlayer
	 */
	def searchDestinyPlayer(handle: String, membershipType: String = "1") = getResponse[DestinyPlayer](
		destinyEndpoint,
		createControllerPath(
			Seq("SearchDestinyPlayer", membershipType, handle)
		)
	)

	/**
	 * Get a strong-typed object from Bungie servers
	 * @param e the base controller endpoint
	 * @param s the controller path
	 * @param m the implicit manifest of T for type deserialization
	 * @tparam T the response type
	 * @return an instance of T
	 */
	def getResponse[T](e: String, s: String)(implicit m: Manifest[T]): T = get[T](e, s).Response

	/**
	 * Get a strongly-typed object from Bungie servers wrapped in BaseResponse
	 * @param e the base controller endpoint
	 * @param s the controller path
	 * @param m the implicit manifest of T for type deserialization
	 * @tparam T the response type
	 * @return an instance of BaseResponse[T]
	 */
	def get[T](e: String, s: String)(implicit m: Manifest[T]): BaseResponse[T] = {
		val r = read[BaseResponse[T]](getString(e, s, None, debugOutput = false))

		if (r.ThrottleSeconds > 0) {
			println("***THROTTLE WARNING***")
			println(writePretty(r))
		}

		r
	}

	/**
	 * GET a string response from Bungie servers
	 * @param e the controller base endpoint
	 * @param s the controller path
	 * @param q optional sequence of query parameters
	 * @param debugOutput whether to print debugging output
	 * @return a string
	 */
	def getString(e: String, s: Seq[String], q: Option[Seq[String]], debugOutput: Boolean = false): String = {
		q match {
			case Some(x) => getString(e, createControllerPath(s), Some(x.mkString("&")), debugOutput)
			case None => getString(e, createControllerPath(s), None, debugOutput)
		}
	}

	/**
	 * GET a string response from Bungie servers
	 * @param e the controller base endpoint
	 * @param s the controller path
	 * @param q optional query parameter string
	 * @param debugOutput whether to print debugging output
	 * @return a string
	 */
	def getString(e: String, s: String, q: Option[String], debugOutput: Boolean): String = {
		var query = e + s

		q match {
			case Some(x) => query ++= "?" + x
			case None =>
		}

		if (debugOutput) println("Querying " + query)
		val resp = Http(query).header("X-API-Key", config.getString("bungieApiKey")).asString.body

		if (debugOutput) {
			println("*** GOT RESP ***")
			println(resp)
		}

		resp
	}

	/**
	 * Joins a sequence of arguments into a controller path
	 * @param x a sequence of controller path components
	 * @return a string
	 */
	private def createControllerPath(x: Seq[String]): String = x.mkString("/") + "/"
}
