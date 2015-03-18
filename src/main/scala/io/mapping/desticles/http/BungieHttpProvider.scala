package io.mapping.desticles.http

import com.typesafe.config.ConfigFactory
import io.mapping.desticles.model._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.read

import scalaj.http.Http

object BungieHttpProvider {
	val config = ConfigFactory.load()

	implicit val formats = DefaultFormats

	def getInventoryItemDetail(po: SearchDestinyPlayer, c: PlayerCharacter, itemInstanceId: String) = get[InventoryItemDetail](
		formControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId, "Character", c.characterBase.characterId, "Inventory", "6917529045166408798")
		) + "?definitions=True"
	).Response

	def getInventory(po: SearchDestinyPlayer, c: PlayerCharacter) = get[Inventory](
		formControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId, "Character", c.characterBase.characterId, "Inventory")
		) + "?definitions=True"
	).Response

	def getAccount(po: SearchDestinyPlayer) = get[Account](
		formControllerPath(
			Seq(po.membershipType.toString, "Account", po.membershipId)
		)
	).Response

	def searchDestinyPlayer(handle: String, membershipType: String = "1") = get[SearchDestinyPlayer](
		formControllerPath(
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
		read[BaseResponse[T]](stringGet(s, None, false))
	}

	/**
	 * GET a string response from Bungie servers
	 * @param s the controller path
	 * @param q optional sequence of query parameters
	 * @return a string
	 */
	def stringGet(s: Seq[String], q: Option[Seq[String]], debugOutput: Boolean = false): String = {
		q match {
			case Some(x) => stringGet(formControllerPath(s), Some(x.mkString("&")), debugOutput)
			case None => stringGet(formControllerPath(s), None, debugOutput)
		}
	}

	/**
	 * GET a string response from Bungie servers
	 * @param s the controller path
	 * @param q optional query parameter string
	 * @return a string
	 */
	def stringGet(s: String, q: Option[String], debugOutput: Boolean): String = {
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
	private def formControllerPath(x: Seq[String]): String = x.mkString("/") + "/"
}
