package io.mapping.desticles.model

case class GrimoireCards(
	                        data: GrimoireCardsData,
	                        cardDefinitions: Option[Map[String, GrimoireCardDefinition]]
	                        )

case class GrimoireCardsData(
	                            score: Long,
	                            cardCollection: Option[Array[GrimoireCard]],
	                            cardsToHide: Option[Array[Long]],
	                            bonuses: Option[Array[GrimoireBonusCard]]
	                            )

case class GrimoireCard(
	                       cardId: Long,
	                       score: Long,
	                       points: Long,
	                       statisticCollection: Option[Array[GrimoireCardStatistic]]
	                       )

case class GrimoireCardStatistic(
	                                statNumber: Int,
	                                value: BigDecimal,
	                                displayValue: String,
	                                rankCollection: Option[Array[GrimoireCardStatisticRank]]
	                                )

case class GrimoireCardStatisticRank(
	                                    rank: Int,
	                                    points: Long
	                                    )

case class GrimoireBonusCard(
	                            cardId: Long,
	                            cardName: String,
	                            statName: String,
	                            bonusName: String,
	                            bonusDescription: String,
	                            bonusRank: GrimoireBonusCardRank,
	                            value: BigDecimal,
	                            threshold: BigDecimal,
	                            smallImage: GrimoireCardImageWithSheet,
	                            sheetPath: Option[String],
	                            sheetSize: Option[GrimoireCardOffsetRect]
	                            )

case class GrimoireBonusCardRank(
	                                statId: Int,
	                                rank: Long
	                                )

case class GrimoireCardImageWithSheet(
	                                     rect: GrimoireCardOffsetRect,
	                                     sheetPath: String,
	                                     sheetSize: GrimoireCardOffsetRect
	                                     )

case class GrimoireCardOffsetRect(
	                                 x: Long,
	                                 y: Long,
	                                 height: Long,
	                                 width: Long
	                                 )

case class GrimoireCardDefinition(
	                                 cardId: Long,
	                                 cardName: String,
	                                 cardIntro: Option[String],
	                                 cardDescription: String,
	                                 unlockHowToText: Option[String],
	                                 rarity: Long,
	                                 unlockFlagHash: Long,
	                                 points: Long,
	                                 normalResolution: GrimoireCardImageResolution,
	                                 highResolution: GrimoireCardImageResolution
	                                 )

case class GrimoireCardImageResolution(
	                                      image: GrimoireCardImageWithSheet,
	                                      smallImage: GrimoireCardImageWithSheet
	                                      )