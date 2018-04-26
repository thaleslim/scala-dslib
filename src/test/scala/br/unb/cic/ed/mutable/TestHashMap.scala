package br.unb.cic.ed.mutable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

class TestHashMap extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Hash Map"

  var hashMap: br.unb.cic.ed.mutable.HashMap[String,String] = _ 

  before {
    hashMap = new br.unb.cic.ed.mutable.ArrayHashMap[String,String](4)
	hashMap(_.length())
  }

  it should "have hashMap(\"one\") = Some(\"um\") if hashMap(\"one\" -> \"um\") has been executed before" in {
	hashMap("one" -> "um")
    hashMap("one") should be (Some("um"))
  }

  it should "return None after calling hashMap(\"two\") na estrutura hashMap(\"one\" -> \"um\")" in {
	hashMap("one" -> "um")
	hashMap("two") should be (None)
  }

  it should "return None after calling hashMap(\"three\") na estrutura hashMap(\"one\" -> \"um\", \"two\" -> \"dois\")" in {
	hashMap("one" -> "um", "two" -> "dois")
	hashMap("three") should be (None)
  }

  it should "be able to retrive every value from hashMap(\"1one\" -> \"um\", \"2two\" -> \"dois\", \"three\" -> \"três\", \"four\" -> \"quatro\")" in {
	hashMap("one" -> "um", "two" -> "dois", "three" -> "três", "four" -> "quatro")
	hashMap("one") should be (Some("um"))
	hashMap("two") should be (Some("dois"))
	hashMap("three") should be (Some("três"))
	hashMap("four") should be (Some("quatro"))
  }

  it should "remove the element (\"um\") after executing hashMap - (\"one\")" in {
	hashMap("one" -> "um")
	hashMap - ("one")
	hashMap("one") should be (None)
  }

  it should "explode if you try to insert more elements than previously informed" in {
	hashMap("one" -> "um", "two" -> "dois", "three" -> "três", "four" -> "quatro")
	
	hashMap("one") should be (Some("um"))
	hashMap("two") should be (Some("dois"))
	hashMap("three") should be (Some("três"))
	hashMap("four") should be (Some("quatro"))

	intercept[InvalidArgument] {
		hashMap("five" -> "cinco")
	}

  }

}