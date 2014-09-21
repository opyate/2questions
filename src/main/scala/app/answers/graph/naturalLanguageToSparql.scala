package app.answers.graph

/**
 * <code>NaturalLanguageToSparql</code> provides a contrived
 * question-to-sparql implementation.
 *
 * A more robust implementation is "beyond the scope", or
 * another micro-service (e.g. a simple Flask app powered by
 * http://quepy.machinalis.com/) could be wired in.
 *
 * Other considerations:
 * - canonical representations of literals, e.g.
 *   "London, United Kingdom" can be construed to be "London, England, U.K."
 * - spell checker  */
class NaturalLanguageToSparql {

  val varname = "x1"

  def convert(question: String) = if (question.contains("David")) {
    s"""
    PREFIX dbpprop: <http://dbpedia.org/property/>
    PREFIX dbres: <http://dbpedia.org/resource/>

    SELECT ?${varname} WHERE {
      dbres:David_Cameron
      dbpprop:birthPlace ?${varname}
    }
    """
  } else {
    s"""
      SELECT DISTINCT ?${varname} WHERE {
        ?x0 rdf:type foaf:Person.
        ?x0 rdfs:label "Tony Blair"@en.
        ?x0 dbpprop:birthDate ?${varname}.
      }
    """
  }
}
