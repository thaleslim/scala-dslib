package br.unb.cic.ed

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

class TestList extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A List"

  var list: br.unb.cic.ed.List[Int] = _ 

  before {
//    list = new br.unb.cic.ed.ArrayList()
    list = new br.unb.cic.ed.LinkedList[Int]()

  }

  it should "have size == 0 before inserting any element" in {
    list.size() should be (0)
  }

  it should "have size == 1 after inserting one element" in {
    list.insert(0, 5)

    list.size() should be (1)
    list.elementAt(0) should be (Some(5)) 
  }

  it should "have size == 3 after inserting three elements" in {
    list.insert(0, 5)
    list.insert(1, 6)
    list.insert(2, 7)

    list.size() should be (3)
  }

  it should "should keep the list of inserted values" in {
    list.insert(0, 1)
    list.insert(1, 2)
    list.insert(2, 3)

    list.size() should be (3)
    list.elementAt(0) should be (Some(1))
    list.elementAt(1) should be (Some(2))
    list.elementAt(2) should be (Some(3))
  }

  it should "return Some(1) when we call find(1000) in the list [100, 1000, 10000]" in {
    list.insert(0, 100)
    list.insert(1, 1000)
    list.insert(2, 10000)

    list.find(1000) should be (Some(1))
  }

  it should "throw InvalidArgument when we call insert(1, 1) on an empty list" in {
    intercept[InvalidArgument] {
      list.insert(1, 1)
    }
  }
  it should "throw InvalidArgument when we call insert(4, 4) on a list with values [1,3,4]" in {
    list.insert(0, 1)
    list.insert(1, 2)
    list.insert(2, 3)

    intercept[InvalidArgument] {
      list.insert(4,4)
    }

  }
  it should "shift the elements of a list. that is [10,30,40].insert(1,20) = [10,20,30,40]" in {
    list.insert(0, 10)
    list.insert(1, 30)
    list.insert(2, 40)

    list.size() should be (3)
    list.insert(1, 20)

    list.size() should be (4)
    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(20))
    list.elementAt(2) should be (Some(30))
    list.elementAt(3) should be (Some(40))
  }

  it should "have size == 2 and shift the elements after removing a element of a list. that is [1,2,88,3].remove(2) = [1,2,3]" in {
    list.insert(0,  1)
    list.insert(1,  2)
    list.insert(2, 88)
    list.insert(3,  3)

    list.size() should be (4)

    list.remove(2)

    list.elementAt(0) should be (Some(1))
    list.elementAt(1) should be (Some(2))
    list.elementAt(2) should be (Some(3))

    list.size() should be (3)
  }

  it should "have the value [10, 30] after removing the second element (idx = 1) in the list [10, 20, 30]" in {
    list.insert(0, 10)
    list.insert(1, 20)
    list.insert(2, 30)

    list.remove(1)

    list.size() should be (2)

    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(30)) 
  }

  it should "the value 30 should not appear after removing the third element (idx = 2) in the list [10, 20, 30]" in {
    list.insert(0, 10)
    list.insert(1, 20)
    list.insert(2, 30)

    intercept[InvalidArgument] {
       list.remove(3)
    }    
  }

}
