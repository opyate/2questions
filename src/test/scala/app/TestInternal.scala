package app

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import scala.concurrent._
import scala.concurrent.duration._

import java.net.URLEncoder

@RunWith(classOf[JUnitRunner])
class TestInternal extends FunSuite {

  val api = new answers.graph.DBPediaAPI()
  val sparql = s"""
      SELECT DISTINCT ?x1 WHERE {
        ?x0 rdf:type foaf:Person.
        ?x0 rdfs:label "Tony Blair"@en.
        ?x0 dbpprop:birthDate ?x1.
      }
    """

  test("Future result") {

    val future = api.get(sparql)
    val result = Await.result(future, 2 seconds)

    assert(result.results.bindings.head.x1.value === "1953-05-06+02:00")
  }

  test("Blocking result") {

    val result = api.getResult(sparql)

    assert(result === "1953-05-06+02:00")
  }
}
