Notes on the implementation:

- there's a hard-coded implementation for TDD's sake
- use of [spray-client](http://spray.io/documentation/1.2.1/spray-client/) for making requests to the RDF store.
- contrived use of the thin "cake pattern" to wire dependencies.
- [natural-language-to-sparql engine](src/main/scala/app/answers/graph/naturalLanguageToSparql.scala), with implementation details in the javadoc.
- when the question is semantically correct, but not supported: see javadoc in [the custom result interpreter](src/main/scala/app/answers/graph/resultInterpreter.scala)
- when the answer is semantically correct, but not supported (in the requirements, i.e. tests): see the custom equality matcher (```localityEq```) in [the test](src/test/scala/app/TestApp.scala) using [scalactic](http://www.scalactic.org/)
