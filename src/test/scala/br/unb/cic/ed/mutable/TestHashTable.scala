package br.unb.cic.ed.mutable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

class TestHashTable extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Hash Table"

  var hashTable: br.unb.cic.ed.mutable.HashTable[Int,String] = _ 

  before {
    hashTable = new br.unb.cic.ed.mutable.ArrayHashTable[Int,String]()
  }

  it should "have size == 0 before inserting any element" in {
    hashTable.size() should be (0)
  }

  it should "have size == 1 after hashTable.insert(1,\"one\")(_*2) and hashTable.search(1)(_*2) return Some(\"one\")" in {
    hashTable.setHashin( _ * 2)
	hashTable.insert(1,"one")
    hashTable.size() should be (1)
	hashTable.search(1) should be (Some("one"))
  }

}