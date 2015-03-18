package io.mapping.desticles.model

case class PlayerCharacter(characterBase: PlayerCharacterBase,
                           levelProgression: PlayerCharacterLevelProgression,
                           emblemPath: String,
                           backgroundPath: String,
                           emblemHash: Long,
                           characterLevel: Long,
                           baseCharacterLevel: Long,
                           isPrestigeLevel: Boolean,
                           percentToNextLevel: Long)

case class PlayerCharacterBase(membershipId: String,
                               membershipType: Int,
                               characterId: String,
                               dateLastPlayed: java.util.Date,
                               minutesPlayedThisSession: String,
                               minutesPlayedTotal: String,
                               powerLevel: Long,
                               raceHash: Long,
                               genderHash: Long,
                               classHash: Long,
                               currentActivityHash: Long,
                               lastCompletedStoryHash: Long,
                               stats: PlayerCharacterStats,
                               customization: PlayerCharacterCustomization,
                               grimoireScore: Long,
                               peerView: PlayerCharacterPeerView,
                               genderType: Int,
                               classType: Int,
                               buildStatGroupHash: Long)

case class PlayerCharacterLevelProgression(dailyProgress: Long,
                            weeklyProgress: Long,
                            currentProgress: Long,
                            level: Long,
                            stop: Option[Long],
                            progresToNextLevel: Option[Long],
                            nextLevelAt: Long,
                            progressionHash: Long)

case class PlayerCharacterStats(STAT_DEFENSE: PlayerCharacterStat,
                          STAT_INTELLECT: PlayerCharacterStat,
                          STAT_DISCIPLINE: PlayerCharacterStat,
                          STAT_STRENGTH: PlayerCharacterStat,
                          STAT_ARMOR: PlayerCharacterStat,
                          STAT_AGILITY: PlayerCharacterStat,
                          STAT_RECOVERY: PlayerCharacterStat,
                          STAT_OPTICS: PlayerCharacterStat)

case class PlayerCharacterStat(statHash: Long, value: Long, maximumValue: Long)

case class PlayerCharacterCustomization(personality: Long,
                                  face: Long,
                                  skinColor: Long,
                                  lipColor: Long,
                                  eyeColor: Long,
                                  hairColor: Long,
                                  featureColor: Long,
                                  decalColor: Long,
                                  wearHelmet: Boolean,
                                  hairIndex: Int,
                                  featureIndex: Int,
                                  decalIndex: Int)

case class PlayerCharacterPeerView(equipment: Array[PlayerCharacterPeerViewEquipment])

case class PlayerCharacterPeerViewEquipment(itemHash: Long,
	                                           dyes: Array[EquipmentDye])

case class EquipmentDye(channelHash: Long,
	                       dyeHash: Long)