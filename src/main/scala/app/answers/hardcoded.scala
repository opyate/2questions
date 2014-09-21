package app.answers

trait HardcodedDBModule {
  lazy val db: DB = new Hardcoded()
}

/**
 * Hard-coded answers initially implemented because TDD.
 */
private class Hardcoded extends DB {
  override def get(q: String) = if (q.contains("David")) {
    "London, United Kingdom"
  } else {
    "61"
  }
}


