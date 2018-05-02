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
    cursor.currentIndex should be (-1)
  }

  it should "have currentItem == null after trying to manipulate the iterator with a empty List" in {
    var cursor = list.createIterator()

    cursor.currentIndex should be (-1)

    cursor.first

    cursor.currentIndex should be (-1)

    cursor++

    cursor.currentIndex should be (-1)

    cursor--

    cursor.currentIndex should be (-1)
  }

  it should "have currentItem != null after manipulating the iterator with a non-empty List" in {
    list(1)
    var cursor = list.createIterator()

    cursor.currentIndex should be (-1)

    cursor.first

    cursor.currentIndex should not be (-1)

    cursor.currentValue should be (1)
  }
  
}
