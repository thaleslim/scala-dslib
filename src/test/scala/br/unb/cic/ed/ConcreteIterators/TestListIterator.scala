package br.unb.cic.ed.ConcreteIterators

import br.unb.cic.ed.mutable._

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestListIterator extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A List's Iterator"

  var list: br.unb.cic.ed.mutable.LinkedList[Int] = _ 

  before {
    list = new br.unb.cic.ed.mutable.LinkedList[Int]()
  }

  it should "have currentItem == null after initializing the iterator" in {
    var cursor = list.createIterator()
    cursor.currentItem should be (null)
  }

  it should "have currentItem == null after trying to manipulate the iterator with a empty List" in {
    var cursor = list.createIterator()
    cursor.currentItem should be (null)
    cursor.first
    cursor.currentItem should be (null)
    cursor.next
    cursor.currentItem should be (null)
    cursor.previous
    cursor.currentItem should be (null)
  }

  it should "have currentItem != null after manipulating the iterator with a non-empty List" in {
    list.insert(0,1)
    var cursor = list.createIterator()
    cursor.currentItem should be (null)
    cursor.first
    cursor.currentItem should not be (null)
    cursor.currentItem.value should be (1)
  }
  
}
