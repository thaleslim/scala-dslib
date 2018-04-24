package br.unb.cic.ed.mutable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

class TestHashMap extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Hash Map"

  var hashMap: br.unb.cic.ed.mutable.HashMap[String,String] = _ 

  before {
    hashMap = new br.unb.cic.ed.mutable.ArrayHashMap[String,String]()
  }

  it should "have hashMap(\"one\") = Some(\"um\") if hashMap(\"one\" -> \"um\") has been executed before" in {
	hashMap(_.length())
	hashMap("one" -> "um")
    hashMap("one") should be (Some("um"))
  }

  it should "return None after calling hashMap(\"three\") na estrutura hashMap(\"one\" -> \"um\", \"2two\" -> \"dois\")" in {
	hashMap(_.length())
	hashMap("one" -> "um", "two2" -> "dois")
	hashMap("three") should be (None)
  }

}