package io.mapping.desticles.controller

import java.io.File
import java.net.URL

import io.mapping.desticles.http.BungieHttpProvider
import io.mapping.desticles.model.DestinyManifest
import net.lingala.zip4j.core.ZipFile
import org.apache.commons.io.FileUtils
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.{read, writePretty}

object ManifestController extends BaseController {
	implicit private val formats = DefaultFormats

	// todo: change to Either[X, None.type] where X is the type of full Destiny manifest
	def getManifest: Either[DestinyManifest, None.type] = {
		ManifestLoader.getManifestDirectory match {
			case Left(d) => {
				val manifest = updateManifest

				val mobileWorldContentZip = downloadMobileWorldContentZip(manifest)
				val mobileWorldContent = unzipMobileWorldContentZip(mobileWorldContentZip, manifest)

				Left(manifest)
			}
			case Right(n) => Right(None)
		}

		/**
		 * todo:
		 * download .content file;
		 * unzip content file;
		 * extract json from db into files
		 */
	}

	/**
	 * Gets the path to the mobile world content database
	 * @return a string
	 */
	def getMobileWorldContentDatabasePath: String = ManifestLoader.getMobileWorldContent.getCanonicalPath

	private def downloadMobileWorldContentZip(m: DestinyManifest): File = {
		FileUtils.copyURLToFile(
			new URL(Seq(BungieHttpProvider.bungieServer, m.mobileWorldContentPaths.en).mkString("/")),
			ManifestLoader.getMobileWorldContentZip
		)

		ManifestLoader.getMobileWorldContentZip
	}

	private def unzipMobileWorldContentZip(f: File, m: DestinyManifest): File = {
		val zip = new ZipFile(f)

		val sqliteFileName = m.mobileWorldContentPaths.en.split("/").takeRight(1).head

		zip.extractFile(
			sqliteFileName,
			ManifestLoader.manifestDirectoryPath
		)


		val mwc = ManifestLoader.getMobileWorldContent
		val sqlFile = new File(Seq(ManifestLoader.manifestDirectoryPath, sqliteFileName).mkString("/"))
		sqlFile.renameTo(mwc)

		mwc
	}

	/**
	 * Get and update the local DestinyManifest from Bungie servers
	 * @return
	 */
	private def updateManifest: DestinyManifest = {
		val serverManifest = getServerManifest

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

	/**
	 * Write an instance of DestinyManifest to local store
	 * @param s an instance of DestinyManifest
	 * @return s
	 */
	private def writeAndReturnNewManifest(s: DestinyManifest): DestinyManifest = {
		FileUtils.write(new File(ManifestLoader.localManifestPath), writePretty(s))
		s
	}

	/**
	 * Test to see if the local DestinyManifest is outdated
	 * @param local an instance of DestinyManifest (from local)
	 * @param server an instance of DestinyManifest (from server)
	 * @return a boolean
	 */
	private def isLocalManifestOutdated(local: DestinyManifest, server: DestinyManifest): Boolean = {
		// Assume inequality means outdated -- the version shouldn't decrement over time
		local.version != server.version
	}

	/**
	 * Gets the Destiny manifest from Bungie servers
	 * @return an instance of DestinyManifest
	 */
	private def getServerManifest: DestinyManifest = BungieHttpProvider.getManifest

	/**
	 * The object responsible for core Manifest IO and logic
	 */
	private object ManifestLoader {
		private implicit val formats = DefaultFormats

		private val manifestDirectoryName = "manifest"
		private val localManifestName = "localManifest"
		private val mobileWorldContentName = "mobileWorldContent"
		private val mobileWorldContentZipName = Seq(mobileWorldContentName, "zip").mkString(".")


		/**
		 * Paths to manifest-related objects
		 *
		 * todo: don't hardcode directory sep; seems like it should be java.io.File.pathSeparator but this is `:' for me
		 */
		lazy val manifestDirectoryPath = Seq(new File(".").getCanonicalPath, manifestDirectoryName).mkString("/")
		lazy val localManifestPath = Seq(manifestDirectoryPath, localManifestName).mkString("/")
		lazy val mobileWorldContentPath = Seq(manifestDirectoryPath, mobileWorldContentName).mkString("/")
		lazy val mobileWorldContentZipPath = Seq(manifestDirectoryPath, mobileWorldContentZipName).mkString("/")

		/**
		 * Test to see if the local manifest directory exists
		 * @return a boolean
		 */
		private def manifestDirectoryExists: Boolean = {
			val x = new File(manifestDirectoryPath)

			x.exists && x.isDirectory
		}

		/**
		 * Test to see if the local manifest file exists
		 * @return a boolean
		 */
		private def localManifestExists: Boolean = {
			val x = new File(localManifestPath)

			x.exists && x.isFile
		}

		/**
		 * Create the local manifest directory
		 * @return a boolean indicating success
		 */
		private def createManifestDirectory: Boolean = new File(manifestDirectoryPath).mkdir

		/**
		 * Get the local manifest directory
		 * @return either a File or None
		 */
		def getManifestDirectory: Either[File, None.type] = {
			if (initManifestDirectory)
				Left(new File(manifestDirectoryPath))
			else
				Right(None)
		}

		/**
		 * Get the local manifest file
		 * @return either an instance of DestinyManifest or None
		 */
		def getLocalManifest: Either[DestinyManifest, None.type] = {
			if (localManifestExists)
				Left(read[DestinyManifest](FileUtils.readFileToString(new File(localManifestPath))))
			else
				Right(None)
		}

		/**
		 * Gets the mobile world content zip file
		 * @return a File
		 */
		def getMobileWorldContentZip: File = new File(mobileWorldContentZipPath)

		/**
		 * Gets the mobile world content sqlite file
		 * @return a File
		 */
		def getMobileWorldContent: File = new File(mobileWorldContentPath)

		/**
		 * Create the local manifest directory if it doesn't already exist
		 * @return a boolean indicating success
		 */
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

