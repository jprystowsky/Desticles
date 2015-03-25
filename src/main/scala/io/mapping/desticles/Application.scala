package io.mapping.desticles

import javax.swing._

import com.typesafe.config.ConfigFactory
import io.mapping.desticles.controller._
import io.mapping.desticles.database.MobileWorldContentDb
import io.mapping.desticles.debug.DumpOutput
import io.mapping.desticles.http.BungieHttpProvider
import io.mapping.desticles.model.InventoryDefinitionItem
import org.json4s.DefaultFormats

object Application extends App {
	val config = ConfigFactory.load()

	implicit val formats = DefaultFormats

	/**
	 * Initialize the manifest
	 */
	ManifestController.getManifest match {
		case Left(m) => {
			println("Manifest retrieved")
		}
		case Right(n) => println("Couldn't retrieve manifest; this is bad")
	}

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

	/**
	 * Get readable info about all the character's equippable items
	 */
	val readableEquippableItems = for (
		i <- inv.buckets.Equippable.flatMap(idb =>idb.items);
		j <- MobileWorldContentDb.getInventoryItemDefinitions.filter(_.itemHash == i.itemHash)
		if j.itemName.isDefined
	) yield j

	println("Here's all the equippable items for this top character:")
	for (rEI <- readableEquippableItems) {
		println(s"\t${rEI.itemName.get}: ${rEI.itemDescription.getOrElse("no db description")}")
	}

	/**
	 * Fetch human-readable info about the top item from the database
	 */
	val topItemDb = MobileWorldContentDb.getInventoryItemDefinitions.filter(x => x.itemHash == topItem.itemHash).head
	println(s"The top quality item is in actuality ${topItemDb.itemName.getOrElse("no name found!?")}")

	println("Here are its perks by name:")
	val topItemPerks = for (
		i <- topItem.perks;
		j <- MobileWorldContentDb.getPerkDefinitions.filter(_.perkHash == i.perkHash)
		if j.displayName.isDefined
	) yield j
	for (perk <- topItemPerks) {
		println(s"\t${perk.displayName.get}: ${perk.displayDescription.getOrElse("no db description")}")
	}

	println("Here are its stats:")
	val topItemStats = for (
		s <- topItem.stats;
		x <- MobileWorldContentDb.getStatDefinitions.filter(_.statHash == s.statHash)
		if x.statName.isDefined
	) yield (x, s)
	for (stat <- topItemStats) {
		println(s"\t${stat._1.statName.get}: ${stat._1.statDescription.getOrElse("no db description")} (value ${stat._2.value}/${stat._2.maximumValue}})")
	}

  val f = new JFrame("Main Frame")
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  f.setSize(400, 400)

  BuildPanes(f);

  f.pack()
  f.setVisible(true)

  //todo: use this to populate in a pane
	//showTopItemImg(topItemDb)


  /**
	 * Get the player's Grimoire Cards
	 */
	val grimCards = GrimoireController.getGrimoireCards(playerAcct.player)
	println(s"The player has a Grimoire score of ${grimCards.data.score}")


  def BuildPanes(f: JFrame) = {
    val jtp = new JTabbedPane();
    f.getContentPane().add(jtp);
    val jp1 = new JPanel();
    val jp2 = new JPanel();
    val label1 = new JLabel();
    label1.setText("panel 1");
    val label2 = new JLabel();
    label2.setText("panel 2");
    jp1.add(label1);
    jp2.add(label2);
    jtp.addTab("Tab1", jp1);
    jtp.addTab("Tab2", jp2);
  }


	def showTopItemImg(item: InventoryDefinitionItem) = {
		val imageUrlPath = Seq(BungieHttpProvider.bungieServer, item.icon).mkString("/")

		ManifestController.getSaveWebImageItemAsset(imageUrlPath) match {
			case Left(imgFile) => {
				val f = new JFrame("Img Test")
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
				f.setSize(400, 400)

				val i = new JLabel(new ImageIcon(imgFile.getCanonicalPath))

				println(imageUrlPath)
				f.getContentPane().add(i)
				f.add(i)
				f.pack()
				f.setVisible(true)
			}

			case Right(n) => println("Couldn't get/save web image; aborting this part")
		}
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
