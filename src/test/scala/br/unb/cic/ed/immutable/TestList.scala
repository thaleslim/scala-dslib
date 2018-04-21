package br.unb.cic.ed.immutable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

class TestImmutableList extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {
  behavior of "An immutable list"

  it should "return 5 when we call List.size(l1) and l1 =  List(1,2,3,4,5)" in {
    val l1 = List(1, 2, 3, 4, 5) 
    List.size(l1) should be (5)
  }

  it should "return 0 when we call List.size(l1) and l1 = Nil" in {
    val l1 = Nil
    List.size(l1) should be (0) 
  }

  it should "return 7 when we call List.size(l3) and l3 = List.append(List(1,2,3,4,5), List(10, 20))" in {
    val l1 = List(1, 2, 3, 4, 5)
    val l2 = List(20, 30)
    val l3 = List.append(l1, l2)

    List.size(l3) should be (7) 
  }

  it should "return the list l3 = (1, 2, 3, 4, 5, 20, 30) when l3 = List.append(List(1,2,3,4,5), List(10, 20))" in {
    val l1 = List(1, 2, 3, 4, 5)
    val l2 = List(20, 30)
    val l3 = List.append(l1, l2)
///    val l4 = List(1, 2, 3, 4, 5, 20, 30) 
    l3 should be (List(1, 2, 3, 4, 5, 20, 30)) 
  }

  
}
