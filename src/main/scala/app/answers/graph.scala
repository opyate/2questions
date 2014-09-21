package app.answers

import app.answers.graph._

/**
 * <code>GraphDBModule</code> is a graph-backed DB module to be
 * extended by the call-site (<code>Question</code>), upon which
 * the caller inherits the wired implementation (<code>GraphDB</code>)
 * by way of the cake pattern.
 */
trait GraphDBModule {
  lazy val db: DB = new GraphDB()
}
private class GraphDB extends DB {
  lazy val naturalLanguageToSparql = new NaturalLanguageToSparql()
  lazy val api = new DBPediaAPI()
  lazy val interpreter = new ResultInterpreter()

  override def get(question: String) = {
    val sparql = naturalLanguageToSparql.convert(question)
    val apiResult = api.getResult(sparql)
    val result = interpreter.get(question, apiResult)
    result
  }
}


