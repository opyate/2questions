package app.answers.graph

import org.joda.time.{DateTime,Period} 
import org.joda.time.format.DateTimeFormat

/**
 * An API result intepreter for the case where there isn't sufficient
 * support for the question, e.g. "how old".
 *
 * This class can be extended with a whole array of rules, which
 * can be deprecated once the necessary backend support (in RDF)
 * is implemented.
 *
 * An analogue to this is where the API result is not quite to
 * expectations, e.g. "London, United Kingdom" VS "London, England, U.K.",
 * and for this special support is built into the equality matcher, where
 * it belongs (see TestApp.scala for more).
 */
class ResultInterpreter {

  val fmt = DateTimeFormat.forPattern("yyyy-MM-dd+HH:mm")

  def get(question: String, result: String): String = {
    if (question.contains("old")) {
      val birthDate: DateTime = fmt.parseDateTime(result)
      new Period(birthDate, DateTime.now()).getYears().toString()
    } else {
      result
    }
  }
}
