package app.answers

/**
 * <code>Anwers</code> is the main trait implemented by the call-site (Question), which
 * uses a backing <code>DB</code> in turn.
 */
trait Answers {
  
  def ask(question: String): String = db.get(question)
  def db: DB
}

/**
 * <code>DB</code> defines a database which accepts a
 * natural-language question, and provides an answer.
 */
trait DB {
  def get(question: String): String
}

