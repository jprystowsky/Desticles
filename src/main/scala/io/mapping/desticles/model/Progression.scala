package io.mapping.desticles.model

case class Progression(
	                          dailyProgress: Option[Long],
	                          weeklyProgress: Option[Long],
	                          currentProgress: Option[Long],
	                          level: Option[Long],
	                          step: Option[Long],
	                          progressToNextLevel: Option[Long],
	                          nextLevelAt: Option[Long],
	                          progressionHash: Option[Long]
	                          )