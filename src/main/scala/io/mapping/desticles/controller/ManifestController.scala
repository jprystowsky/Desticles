package io.mapping.desticles.controller

import java.io.File

import io.mapping.desticles.http.BungieHttpProvider
import io.mapping.desticles.model.DestinyManifest
import org.apache.commons.io.FileUtils
import org.json4s.DefaultFormats

import org.json4s.native.Serialization.{read, writePretty}

object ManifestController extends BaseController {
	implicit private val formats = DefaultFormats

	// todo: change to Either[X, None.type] where X is the type of full Destiny manifest
	def getManifest: Either[DestinyManifest, None.type] = {
		ManifestLoader.getManifestDirectory match {
			case Left(d) => Left(updateManifest)
			case Right(n) => Right(None)
		}

		/**
		 * todo:
		 * fetch manifest from server;
		 * save it;
		 * compare versions of existing DLd and server version;
		 * download .content file;
		 * unzip content file;
		 * extract json from db into files
		 */
	}

	private def updateManifest: DestinyManifest = {
		var serverManifest = getServerManifest

		ManifestLoader.getLocalManifest match {
			case Left(m) => {
				if (isLocalManifestOutdated(m, serverManifest))
					writeAndReturnNewManifest(serverManifest)
				else
					m
			}
			case Right(n) => writeAndReturnNewManifest(serverManifest)
		}
	}

	private def writeAndReturnNewManifest(s: DestinyManifest): DestinyManifest = {
		FileUtils.write(new File(ManifestLoader.localManifestPath), writePretty(s))
		s
	}

	private def isLocalManifestOutdated(local: DestinyManifest, server: DestinyManifest): Boolean = {
		// Assume inequality means outdated -- the version shouldn't decrement over time
		local.version != server.version
	}

	/**
	 * Gets the Destiny manifest from Bungie servers
	 * @return an instance of DestinyManifest
	 */
	private def getServerManifest: DestinyManifest = BungieHttpProvider.getManifest

	private object ManifestLoader {
		private implicit val formats = DefaultFormats

		private val manifestDirectoryName = "manifest"
		private val localManifestName = "localManifest"

		// todo: don't hardcode directory sep
		// seems like it should be java.io.File.pathSeparator but this is `:' for me
		lazy val manifestDirectoryPath = Seq(new File(".").getCanonicalPath, manifestDirectoryName).mkString("/")
		lazy val localManifestPath = Seq(manifestDirectoryPath, localManifestName).mkString("/")

		private def manifestDirectoryExists: Boolean = {
			val x = new File(manifestDirectoryPath)

			x.exists && x.isDirectory
		}

		private def localManifestExists: Boolean = {
			val x = new File(localManifestPath)

			x.exists && x.isFile
		}

		private def createManifestDirectory: Boolean = new File(manifestDirectoryPath).mkdir

		def getManifestDirectory: Either[File, None.type] = {
			if (initManifestDirectory)
				Left(new File(manifestDirectoryPath))
			else
				Right(None)
		}

		def getLocalManifest: Either[DestinyManifest, None.type] = {
			if (localManifestExists)
				Left(read[DestinyManifest](FileUtils.readFileToString(new File(localManifestPath))))
			else
				Right(None)
		}

		def initManifestDirectory: Boolean = {
			if (!manifestDirectoryExists) {
				synchronized {
					if (!manifestDirectoryExists) {
						createManifestDirectory
					}
				}
			}

			true
		}
	}
}

