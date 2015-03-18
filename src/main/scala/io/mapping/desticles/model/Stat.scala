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