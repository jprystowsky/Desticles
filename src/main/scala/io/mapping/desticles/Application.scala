package io.mapping.desticles

import javax.swing.{ImageIcon, JFrame, JLabel}

import com.typesafe.config.ConfigFactory
import io.mapping.desticles.controller._
import io.mapping.desticles.database.MobileWorldContentDb
import io.mapping.desticles.debug.DumpOutput
import io.mapping.desticles.http.BungieHttpProvider
import org.json4s.DefaultFormats

object Application extends App {
	val config = ConfigFactory.load()

	implicit val formats = DefaultFormats

	/**
	 * Initialize the manifest
	 */
	var manifest = ManifestController.getManifest
	manifest match {
		case Left(m) => {
			println("Manifest retrieved")
			println(m)
		}
		case Right(n) => println("Couldn't do it")
	}

	/**
	 * EXTREME DEV AREA
	 */

	println("RED DEATH IS")
	val redDeath = MobileWorldContentDb.getInventoryItemDefinitions.filter(x => x.json.contains("Red Death"))
	println(redDeath)
	println("RED DEATH WAS")

	/**
	 * END EXTREME DEV AREA
	 */

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

  showTopItemImg(topItem, topItemDetail)


  /**
	 * Get the player's Grimoire Cards
	 */
	val grimCards = GrimoireController.getGrimoireCards(playerAcct.player)
	println(s"The player has a Grimoire score of ${grimCards.data.score}")


  def showTopItemImg(t:Object, td:Object) = {
    val f = new JFrame("Img Test")
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    f.setSize(400,400)


    //This link returns item image.  Data in topItem and topItemDetail does not contain this value.
    //https://www.bungie.net//common/destiny_content/icons/80da6cbfde86ecd6a8bb720c3df54d0b.jpg

	  //println(org.json4s.native.Serialization.writePretty(topItem))
	  //println(org.json4s.native.Serialization.writePretty(topItemDetail))

    val i = new JLabel(new ImageIcon("https://www.bungie.net//common/destiny_content/icons/" + topItem.itemHash + ".jpg"))

    println("https://www.bungie.net//common/destiny_content/icons/" + topItem.itemHash + ".jpg")
    f.getContentPane().add(i);
    f.add(i);
    f.pack();
    f.setVisible(true);
  }
	/**
	 * Get the top character's activities
	 */
	val acts = CharacterController.getCharacterActivities(playerAcct.player, topChar)
	println(s"The character " + (if (acts.data.available.getOrElse(Array()).exists(ca => ca.activityHash == 1836893119 && ca.isCompleted)) "has completed" else "has not completed") + " Crota's end on hard mode")

	/**
	 * DEV AREA
	 */

	//[membershipType]/Account/[accountId]/Character/[characterId]/Activities/     definitions=[g]

	DumpOutput.dumpString(
		BungieHttpProvider.getString(
			BungieHttpProvider.destinyEndpoint,
			Seq(playerAcct.player.membershipType.toString, "Account", playerAcct.player.membershipId, "Character", topChar.characterBase.characterId, "Activities"),
			Some(Seq("definitions=True")),
			debugOutput = false
		),
		(x) => Unit)
}
