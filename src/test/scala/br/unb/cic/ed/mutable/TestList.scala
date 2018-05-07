package br.unb.cic.ed.mutable

import br.unb.cic.ed.design.Aggregate
import br.unb.cic.ed.concreteIterator._

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestList extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A List"

  var list: List[Int] = _
  var listAux: List[Int] = _

  before {
    /**/
    list = new br.unb.cic.ed.mutable.ArrayList[Int]()
    listAux = new br.unb.cic.ed.mutable.ArrayList[Int](3)
    /*
    list = new br.unb.cic.ed.mutable.LinkedList[Int]
    listAux = new br.unb.cic.ed.mutable.LinkedList[Int]
    */
  }

  it should "have size == 0 before inserting any element" in {
    list.size should be (0)
  }

  it should "have size == 1 after inserting one element" in {
    list(5)

    list.size should be (1)
    list.elementAt(0) should be (Some(5))
  }

  it should "have size == 3 after inserting three elements" in {
    list( 5, 6, 7 )

    list.size should be (3)
    list.elementAt(0) should be (Some(5))
    list.elementAt(1) should be (Some(6))
    list.elementAt(2) should be (Some(7))
  }

  it should "should keep the list of inserted values" in {
    list( 1, 2, 3 )

    list.size should be (3)
    list.elementAt(0) should be (Some(1))
    list.elementAt(1) should be (Some(2))
    list.elementAt(2) should be (Some(3))
  }

  it should "return Some(1) when we call find(1000) in the list [100, 1000, 10000]" in {
    list(100, 1000, 10000)

    list.find(1000) should be (Some(1))
  }

  it should "throw InvalidArgument when we call insert(1, 1) on an empty list" in {
    intercept[InvalidArgument] {
      list( (1,1) )
    }
  }

  it should "throw InvalidArgument when we call insert(4, 4) on a list with values [1,2,3]" in {
    list( 1, 2, 3 )

    intercept[InvalidArgument] {
      list( (4,4) )
    }

  }

  it should "shift the elements of a list. That is [10,30,40].insert(1,20) = [10,20,30,40]" in {
    list( 10, 30, 40 )

    list.size() should be (3)

    list( (1,20) )

    list.size should be (4)
    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(20))
    list.elementAt(2) should be (Some(30))
    list.elementAt(3) should be (Some(40))
  }

  it should "have size == 2 and shift the elements after removing a element of a list. That is [1,2,88,3].remove(2) = [1,2,3]" in {
    list( 1, 2, 88, 3 )
    
    list.size should be (4)

    list.remove(2)

    list.elementAt(0) should be (Some(1))
    list.elementAt(1) should be (Some(2))
    list.elementAt(2) should be (Some(3))

    list.size should be (3)
  }

  it should "have the list [10, 30] after removing the second element (idx = 1) in the list [10, 20, 30]" in {
    list( 10, 20, 30 )

    list.remove(1)

    list.size should be (2)

    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(30)) 
  }

  it should "not accept [10,20,30].remove(3)" in {
    list( 10, 20, 30 )

    intercept[InvalidArgument] {
       list.remove(3)
    }
  }

  it should "remove the value 30, in other words, it should not appear, even if requested, after it's removal [10, 20, 30].remove(2)" in {
    list( 10, 20, 30 )

    list.size should be (3)

    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(20)) 
    list.elementAt(2) should be (Some(30))
    list.find(30)     should be (Some(2))

    list.remove(2)

    list.size should be (2)

    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(20))
    list.elementAt(2) should be (None)
    list.find(30)     should be (None)
    
  }

  it should "remove all the elements after list.clear" in {
    list(1,2,3,4,5,6,7,8,9)

    list.size should be (9)
    list.elementAt(4) should be (Some(5))

    list.clear

    list.size should be (0)
    list.elementAt(0) should be (None)
  }

  it should "trade the list stored inside the class by the new one passed as a argument" in {
    list(1,2,3,4,5,6,7,8,9)
    list.size should be (9)

    listAux(10,20,30)
    listAux.size should be (3)

    list.subst(listAux)
    
    list.size should be (3)
    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(20))
    list.elementAt(2) should be (Some(30))
  }

  it should "lead to a list [3,4,5,6,7,8] when we call [3, 4, 5].addAll([6, 7, 8])" in {
    list( 3, 4, 5 )

    list.size should be (3)

    listAux( 6, 7, 8 )

    listAux.size should be (3)

    list.addAll(listAux)

    list.size should be (6)
    listAux.size should be (3)

    list.elementAt(5) should be (Some(8)) 
  }

  it should "lead to list [2,4,6] when we call [1,2,3].map(_*2)" in {
    listAux( 1, 2, 3 )
    listAux.map(_*2)

    listAux.elementAt(0) should be (Some(2))
    listAux.elementAt(1) should be (Some(4))
    listAux.elementAt(2) should be (Some(6))
  }

  it should "return the string \"1 -> 2 -> 3 -> \" from [1,2,3].reduce[String]{ _ + _ + \" -> \"}(\"\")" in {
    listAux( 1, 2, 3 )

    listAux.reduce[String]{ _ + _ + " -> " }("") should be ("1 -> 2 -> 3 -> ")
  }

  it should "return remove all values greater than 2 after the list.filter{if(_ < 3) true else false}" in {
    list( 88, 10, 20, 77, 1, 2, 30, 3, 44, 55)
    list.filter{x: Int => if( x < 3) true else false }
    
    list.size should be (2)
    list.elementAt(0) should be (Some(1))
    list.elementAt(1) should be (Some(2))
  }

}
