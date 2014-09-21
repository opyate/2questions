package app.answers.graph

import spray.json.{JsonFormat, DefaultJsonProtocol}

/*
Classes in this file maps onto result sets returned from Graph DB Web APIs (e.g. dbpedia)

Example response, with 'x1' arbitrarily
chosen as the first return value:

{
  head: {
    link: [ ],
    vars: [
    "x1"
    ]
  },
  results: {
    distinct: false,
    ordered: true,
    bindings: [
    {
      x1: {
        type: "typed-literal",
        datatype: "http://www.w3.org/2001/XMLSchema#date", // optional
        value: "1953-05-06+02:00"
      }
    }
    ]
  }
}
*/

case class ApiResult(head: Head, results: Results)
case class Head(link: List[String], vars: List[String])
case class Results(distinct: Boolean, ordered: Boolean, bindings: List[Binding])
case class Binding(x1: BindingData)
case class BindingData(`type`: String, datatype: Option[String], value: String)

object ApiResultJsonProtocol extends DefaultJsonProtocol {
  implicit val bindingDataFormat = jsonFormat3(BindingData)
  implicit val bindingFormat = jsonFormat1(Binding)
  implicit val headFormat = jsonFormat2(Head)
  implicit val resultsFormat = jsonFormat3(Results)
  implicit val apiResultFormat = jsonFormat2(ApiResult)
}


