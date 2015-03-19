package io.mapping.desticles.debug

import org.json4s.DefaultFormats

object DumpOutput {
	implicit val formats = DefaultFormats

	def dumpString(s: AnyRef, f: (Any) => Unit) = dump[AnyRef, Unit](s, f)

	private def dump[X, Y](x: X, f: (X) => Y): Y = f(x)
}
