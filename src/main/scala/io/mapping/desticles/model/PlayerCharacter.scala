package io.mapping.desticles.model

case class PlayerCharacter(
	                          characterBase: PlayerCharacterBase,
	                          levelProgression: ItemProgression,
	                          emblemPath: String,
	                          backgroundPath: String,
	                          emblemHash: Long,
	                          characterLevel: Long,
	                          baseCharacterLevel: Long,
	                          isPrestigeLevel: Boolean,
	                          percentToNextLevel: Long
	                          )

case class PlayerCharacterBase(
	                              membershipId: String,
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
	                              buildStatGroupHash: Long
	                              )

case class PlayerCharacterStats(
	                               STAT_DEFENSE: UpperBoundedStat,
	                               STAT_INTELLECT: UpperBoundedStat,
	                               STAT_DISCIPLINE: UpperBoundedStat,
	                               STAT_STRENGTH: UpperBoundedStat,
	                               STAT_ARMOR: UpperBoundedStat,
	                               STAT_AGILITY: UpperBoundedStat,
	                               STAT_RECOVERY: UpperBoundedStat,
	                               STAT_OPTICS: UpperBoundedStat
	                               )

case class PlayerCharacterCustomization(
	                                       personality: Long,
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
	                                       decalIndex: Int
	                                       )

case class PlayerCharacterPeerView(
	                                  equipment: Array[PlayerCharacterPeerViewEquipment]
	                                  )

case class PlayerCharacterPeerViewEquipment(
	                                           itemHash: Long,
	                                           dyes: Array[ItemDye]
	                                           )