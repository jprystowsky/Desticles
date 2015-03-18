package io.mapping.desticles

import com.typesafe.config.ConfigFactory
import io.mapping.desticles.http.BungieHttpProvider
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.writePretty

object Application extends App {
	val config = ConfigFactory.load()

	implicit val formats = DefaultFormats

	val playerObject = BungieHttpProvider.searchDestinyPlayer(config.getString("playerName"))
	//println(writePretty(playerObject))

	val account = BungieHttpProvider.getAccount(playerObject)
	//println(writePretty(account))

	val topChar = account.data.characters.filter(pc => pc.characterLevel == 32)

	for (c <- account.data.characters) {
		println("For character:")
		println(writePretty(c))

		val i = BungieHttpProvider.getInventory(playerObject, c)

		println("Inventory:")
		println(writePretty(i))
	}

	//println("The top char is")
	//println(writePretty(topChar))

//	println(
//		writePretty(
//			BungieHttpProvider.stringGet(
//				Seq(playerObject.membershipType.toString, "Account", playerObject.membershipId, "Character", topChar.head.characterBase.characterId, "Inventory"),
//				Some(Seq("definitions=True"))
//			)
//		)
//	)
}
