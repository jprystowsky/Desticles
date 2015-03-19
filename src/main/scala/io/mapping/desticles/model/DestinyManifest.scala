package io.mapping.desticles.model

case class DestinyManifest(
	                          version: String,
	                          mobileAssetContentPath: String,
	                          mobileGearAssetDataBases: Array[GearAssetDatabase],
	                          mobileWorldContentPaths: ContentPaths
	                          )

case class GearAssetDatabase(
	                            version: Int,
	                            path: String
	                            )

// todo: worry about other langs later; pt-br key name is icky for scala and haven't researched fix
case class ContentPaths(
	                       en: String
	                       )