package com.github.andyglow.jsonschema

import json._
import json.Schema._
import json.Schema.`object`.Field

import org.scalatest.matchers.should.Matchers._
import org.scalatest.funsuite._


class CRLFSourceSpec extends AnyFunSuite {
  import CRLFSourceSpec._

  test("support windows CRLF") {
    CC.ccSchema shouldEqual `object`(
      Field("set"     , `set`(`integer`), required = false, default = Set(1, 5, 9)),
      Field("list"    , `array`(`boolean`), required = false, default = List(true, false)),
      Field("vector"  , `array`(`number`[Long]), required = false, default = Vector(9, 7)),
      Field("strMap"  , `string-map`(`number`[Double]), required = false, default = Map("foo" -> .12)),
      Field("intMap"  , `int-map`(`string`[String](None, None)), required = false, default = Map(1 -> "1", 2 -> "2")))
  }

}

object CRLFSourceSpec {

  case class CC(
    set: Set[Int] = Set(1, 5, 9),
    list: List[Boolean] = List(true, false),
    vector: Vector[Long] = Vector(9L, 7L),
    strMap: Map[String, Double] = Map("foo" -> .12),
    intMap: Map[Int, String] = Map(1 -> "1", 2 -> "2"))

  object CC {
    implicit val ccSchema: Schema[CC] = Json.schema[CC]
  }
}
