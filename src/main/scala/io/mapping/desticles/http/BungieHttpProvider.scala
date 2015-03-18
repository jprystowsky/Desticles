package io.mapping.desticles.http

import com.typesafe.config.ConfigFactory
import io.mapping.desticles.model._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.{read, writePretty}

import scalaj.http.Http

object BungieHttpProvider {
	val config = ConfigFactory.load()

	implicit val formats = DefaultFormats

	def getCharacterStats(po: SearchDestinyPlayer) = get[CharacterStats](
		createControllerPath(
			Seq("Stats", "Account", po.membershipType.toString, po.membershipId)
		)
	).Response

	def getInventoryItemDetail(po: SearchDestinyPlayer, c: PlayerCharacter, itemInstanceId: String) = get[InventoryItemDetail](
		createControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId, "Character", c.characterBase.characterId, "Inventory", itemInstanceId)
		) + "?definitions=True"
	).Response

	def getInventory(po: SearchDestinyPlayer, c: PlayerCharacter) = get[Inventory](
		createControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId, "Character", c.characterBase.characterId, "Inventory")
		) + "?definitions=True"
	).Response

	def getAccount(po: SearchDestinyPlayer) = get[Account](
		createControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId)
		)
	).Response

	def searchDestinyPlayer(handle: String, membershipType: String = "1") = get[SearchDestinyPlayer](
		createControllerPath(
			Seq("SearchDestinyPlayer", membershipType, handle)
		)
	).Response

	/**
	 * Get a strongly-typed object from Bungie servers
	 * @param s the controller path
	 * @param m the implicit manifest of T for type deserialization
	 * @tparam T the response type
	 * @return an instance of T
	 */
	def get[T](s: String)(implicit m: Manifest[T]): BaseResponse[T] = {
		val r = read[BaseResponse[T]](getString(s, None, false))

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
