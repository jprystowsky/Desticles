package io.mapping.desticles.model

case class CharacterStats(
	                         mergedDeletedCharacters: CharacterStat,
	                         mergedAllCharacters: CharacterStat,
	                         characters: Array[CharacterStatCharacter]
	                         )

case class CharacterStat(
	                        results: CharacterStatResults,
	                        merged: CharacterStatResultAllTime
	                        )

case class CharacterStatResults(
	                               allPvE: CharacterStatResultAllTime,
	                               allPvP: CharacterStatResultAllTime
	                               )

case class CharacterStatResultAllTime(
	                                     allTime: CharacterStatResultAllTimeValue
	                                     )

case class CharacterStatResultAllTimeValue(
	                                          activitiesCleared: Option[CharacterStatResult],
	                                          weaponKillsSuper: Option[CharacterStatResult],
	                                          activitiesEntered: Option[CharacterStatResult],
	                                          weaponKillsMelee: Option[CharacterStatResult],
	                                          weaponKillsGrenade: Option[CharacterStatResult],
	                                          assists: Option[CharacterStatResult],
	                                          totalDeathDistance: Option[CharacterStatResult],
	                                          totalKillDistance: Option[CharacterStatResult],
	                                          kills: Option[CharacterStatResult],
	                                          secondsPlayed: Option[CharacterStatResult],
	                                          deaths: Option[CharacterStatResult],
	                                          bestSingleGameKills: Option[CharacterStatResult],
	                                          objectivesCompleted: Option[CharacterStatResult],
	                                          precisionKills: Option[CharacterStatResult],
	                                          resurrectionsPerformed: Option[CharacterStatResult],
	                                          resurrectionsReceived: Option[CharacterStatResult],
	                                          suicides: Option[CharacterStatResult],
	                                          weaponKillsAutoRifle: Option[CharacterStatResult],
	                                          weaponKillsFusionRifle: Option[CharacterStatResult],
	                                          weaponKillsHandCannon: Option[CharacterStatResult],
	                                          weaponKillsMachinegun: Option[CharacterStatResult],
	                                          weaponKillsPulseRifle: Option[CharacterStatResult],
	                                          weaponKillsRocketLauncher: Option[CharacterStatResult],
	                                          weaponKillsScoutRifle: Option[CharacterStatResult],
	                                          weaponKillsShotgun: Option[CharacterStatResult],
	                                          weaponKillsSniper: Option[CharacterStatResult],
	                                          weaponKillsSubmachinegun: Option[CharacterStatResult],
	                                          allParticipantsCount: Option[CharacterStatResult],
	                                          allParticipantsTimePlayed: Option[CharacterStatResult],
	                                          longestKillSpree: Option[CharacterStatResult],
	                                          longestSingleLife: Option[CharacterStatResult],
	                                          mostPrecisionKills: Option[CharacterStatResult],
	                                          orbsDropped: Option[CharacterStatResult],
	                                          orbsGathered: Option[CharacterStatResult],
	                                          publicEventsCompleted: Option[CharacterStatResult],
	                                          publicEventsJoined: Option[CharacterStatResult],
	                                          remainingTimeAfterQuitSeconds: Option[CharacterStatResult],
	                                          totalActivityDurationSeconds: Option[CharacterStatResult],
	                                          fastestCompletion: Option[CharacterStatResult],
	                                          activitiesWon: Option[CharacterStatResult],
	                                          score: Option[CharacterStatResult],
	                                          bestSingleGameScore: Option[CharacterStatResult],
	                                          closeCalls: Option[CharacterStatResult],
	                                          dominationKills: Option[CharacterStatResult],
	                                          allParticipantsScore: Option[CharacterStatResult],
	                                          defensiveKills: Option[CharacterStatResult],
	                                          offensiveKills: Option[CharacterStatResult],
	                                          relicsCaptured: Option[CharacterStatResult],
	                                          teamScore: Option[CharacterStatResult],
	                                          zonesCaptured: Option[CharacterStatResult],
	                                          zonesNeutralized: Option[CharacterStatResult]
	                                          )

case class CharacterStatResult(
	                              statId: String,
	                              basic: CharacterStatResultValue,
	                              pga: Option[CharacterStatResultValue]
	                              )

case class CharacterStatResultValue(
	                                   value: BigDecimal,
	                                   displayValue: String
	                                   )

case class CharacterStatCharacter(
	                                 characterId: String,
	                                 deleted: Boolean,
	                                 results: CharacterStatResults,
	                                 merged: CharacterStatResultAllTime
	                                 )