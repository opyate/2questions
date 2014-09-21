package app.answers.graph

import akka.actor.ActorSystem
import akka.event.Logging
import akka.io.IO
import akka.pattern.ask
import scala.concurrent._
import scala.concurrent.duration._ 
import scala.util.{Success, Failure}
import spray.can.Http
import spray.client.pipelining._
import spray.httpx.SprayJsonSupport
import spray.util._
import spray.json.RootJsonReader
import spray.httpx.unmarshalling.Unmarshaller
import spray.http.MediaType
import java.net.URLEncoder

// API onto graph DB, where the DB can be one of DBPedia or Freebase.
class Query(sparql_query: String, graph_uri: String = "http%3A%2F%2Fdbpedia.org") {
  def get() = {
    s"http://dbpedia.org/sparql?default-graph-uri=${graph_uri}&query=${sparql_query}&format=application%2Fsparql-results%2Bjson&timeout=30000"
  }
}


class DBPediaAPI {

  implicit val system = ActorSystem("graphdb-spray-client")
  import system.dispatcher // execution context for futures below
  val log = Logging(system, getClass)
  
  import ApiResultJsonProtocol._
  import SparqlJsonSupport._

  val pipeline = addHeader("Accept", accept) ~> sendReceive ~> unmarshal[ApiResult]

  def get(sparql: String) = pipeline {

    // sparql isn't URIEncoded
    val encodedSparql = URLEncoder.encode(sparql, "UTF8")
    Get(new Query(encodedSparql).get())
  }

  /**
   * A blocking getter for use in tests.
   */
  def getResult(sparql: String) = {
    val resultFuture = get(sparql)
    resultFuture onFailure {
      case t: Throwable => {
        log.error("An error has occurred", t)
        shutdown()
      }
    }

    val apiResult = Await.result(resultFuture, 2 seconds)
    apiResult.results.bindings.head.x1.value
  }

  private[this] def shutdown(): Unit = {
    IO(Http).ask(Http.CloseAll)(1.second).await
    system.shutdown()
  }
}


