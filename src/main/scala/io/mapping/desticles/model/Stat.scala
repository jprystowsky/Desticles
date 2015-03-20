package io.mapping.desticles.model

class Stat(
	          statHash: Long
	          )

class StatWithValue(
	                   statHash: Long,
	                   value: Long
	                   ) extends Stat(statHash)

case class UpperBoundedStat(
	                           statHash: Long,
	                           value: Long,
	                           maximumValue: Long
	                           ) extends StatWithValue(statHash, value)

case class FullyBoundedStat(
	                           statHash: Long,
	                           value: Long,
	                           minimum: Long,
	                           maximum: Long
	                           ) extends StatWithValue(statHash, value)

case class DisplayableStat(
	                          statHash: Long,
	                          displayName: String,
	                          displayDescription: String,
	                          displayIcon: String
	                          ) extends Stat(statHash)

case class DbStat(
	                 statHash: Long,
	                 statName: Option[String],
	                 statDescription: Option[String],
	                 icon: String,
	                 statIdentifier: String,
	                 interpolate: Boolean
	                 ) extends Stat(statHash)