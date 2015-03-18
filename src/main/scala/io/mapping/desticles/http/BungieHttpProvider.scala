package io.mapping.desticles.http

import com.typesafe.config.ConfigFactory
import io.mapping.desticles.model._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.{read, writePretty}

import scalaj.http.Http

object BungieHttpProvider {
	val config = ConfigFactory.load()

	implicit val formats = DefaultFormats

	/**
	 * Get the progression of a player's character
	 * @param po an instance of DestinyPlayer
	 * @param c an instance of PlayerCharacter corresponding to po
	 * @return an instance of CharacterProgression
	 */
	def getCharacterProgression(po: DestinyPlayer, c: PlayerCharacter) = getResponse[CharacterProgression](
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
		createControllerPath(
			Seq("SearchDestinyPlayer", membershipType, handle)
		)
	)

	/**
	 * Get a strong-typed object from Bungie servers
	 * @param s the controller path
	 * @param m the implicit manifest of T for type deserialization
	 * @tparam T the response type
	 * @return an instance of T
	 */
	def getResponse[T](s: String)(implicit m: Manifest[T]): T = get[T](s).Response

	/**
	 * Get a strongly-typed object from Bungie servers wrapped in BaseResponse
	 * @param s the controller path
	 * @param m the implicit manifest of T for type deserialization
	 * @tparam T the response type
	 * @return an instance of BaseResponse[T]
	 */
	def get[T](s: String)(implicit m: Manifest[T]): BaseResponse[T] = {
		val r = read[BaseResponse[T]](getString(s, None, debugOutput = false))

		if (r.ThrottleSeconds > 0) {
			println("***THROTTLE WARNING***")
			println(writePretty(r))
		}

		r
	}

	/**
	 * GET a string response from Bungie servers
	 * @param s the controller path
	 * @param q optional sequence of query parameters
	 * @param debugOutput whether to print debugging output
	 * @return a string
	 */
	def getString(s: Seq[String], q: Option[Seq[String]], debugOutput: Boolean = false): String = {
		q match {
			case Some(x) => getString(createControllerPath(s), Some(x.mkString("&")), debugOutput)
			case None => getString(createControllerPath(s), None, debugOutput)
		}
	}

	/**
	 * GET a string response from Bungie servers
	 * @param s the controller path
	 * @param q optional query parameter string
	 * @param debugOutput whether to print debugging output
	 * @return a string
	 */
	def getString(s: String, q: Option[String], debugOutput: Boolean): String = {
		var query = "http://www.bungie.net/Platform/Destiny/" + s

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
