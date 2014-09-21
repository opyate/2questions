package app.answers.graph

import spray.httpx.unmarshalling.Unmarshaller
import spray.json._
import spray.http._

/**
 * A Spray plugin which handles content-type "application/sparql-results+json"
 * as if it's JSON.
 */
trait SparqlJsonSupport {
  val accept = "application/sparql-results+json"

  implicit def sparqlJsonUnmarshaller[T: RootJsonReader] =
    Unmarshaller[T](MediaType.custom(accept)) {
      case x: HttpEntity.NonEmpty ⇒
        val json = JsonParser(x.asString(defaultCharset = HttpCharsets.`UTF-8`))
        jsonReader[T].read(json)
    }
  implicit def sparqlJsonUnmarshallerConverter[T](reader: RootJsonReader[T]) =
    sparqlJsonUnmarshaller(reader)
}

object SparqlJsonSupport extends SparqlJsonSupport
