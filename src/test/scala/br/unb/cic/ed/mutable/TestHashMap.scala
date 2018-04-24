package br.unb.cic.ed.mutable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

class TestHashMap extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Hash Map"

  var hashMap: br.unb.cic.ed.mutable.HashMap[Int,String] = _ 

  before {
    hashMap = new br.unb.cic.ed.mutable.ArrayHashMap[Int,String]()
  }

  it should "have hashMap(1) = Some(\"one\") if hashMap.insert(1,\"one\") has been executed before" in {
    hashMap.insert(1,"one")
	//hashMap(1 -> "one")
    hashMap(1) should be (Some("one"))
  }

}