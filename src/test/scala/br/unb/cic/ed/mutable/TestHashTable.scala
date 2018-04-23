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

}