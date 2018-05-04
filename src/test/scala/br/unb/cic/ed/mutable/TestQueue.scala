package br.unb.cic.ed.mutable

import br.unb.cic.ed.mutable._
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

class TestQueue extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

    behavior of "A Queue"

    var queue : br.unb.cic.ed.mutable.QueueImp[Int] = _
    
    
    before {
        queue = new br.unb.cic.ed.mutable.QueueImp[Int] (new ArrayList[Int]())
    }
    
    it should "have size==0 before enqueue elements" in{
        queue.size() should be (0)
    }
    
    it should "have size==4 after enqueue four elements" in{
        queue.size() should be (0)
        
        queue.enqueue(6)
        queue.enqueue(3)
        queue.enqueue(9)
        queue.enqueue(1)
        
        queue.size() should be (4)
    
    }
    
    it should "return Some(6) after enqueue the following elements:6,3,9,1; and dequeue once" in{
        queue.size() should be (0)
        
        queue.enqueue(6)
        queue.enqueue(3)
        queue.enqueue(9)
        queue.enqueue(1)
        
        queue.size() should be (4)
        
        queue.dequeue() should be (Some(6))
        
        queue.size() should be (3)
        queue.dequeue() should be (Some(3))
        queue.dequeue() should be (Some(9))
        queue.dequeue() should be (Some(1))
        
        queue.size() should be (0)
    }
    
    it should "return None when dequeue an empty queue" in{
    
        queue.dequeue() should be (None)
    
    }
    
    it should "be able to retrive every value from queue: 1,3,6,9,12,15; returning in the order: 1,3,6,9,12,15 " in{
        queue.size() should be (0)
        
        queue.enqueue(1)
        queue.enqueue(3)
        queue.enqueue(6)
        queue.enqueue(9)
        queue.enqueue(12)
        queue.enqueue(15)
        
        queue.size() should be (6)
        
        queue.dequeue() should be (Some(1))
        
        queue.size() should be (5)
        
        queue.dequeue() should be (Some(3))
        queue.dequeue() should be (Some(6))
        queue.dequeue() should be (Some(9))
        queue.dequeue() should be (Some(12))
        queue.dequeue() should be (Some(15))
        
        queue.size() should be (0)
    
    }
    
    it should "throw InvalidArgument when we call enqueue(6) on an full queue" in{
    val smallqueue = new br.unb.cic.ed.mutable.QueueImp[Int](new ArrayList[Int](1))
    
        smallqueue.enqueue(6)
        
        intercept[InvalidArgument] {
            smallqueue.enqueue(6)
        }
    }
    
    it should "show the first element of the queue: 1,2,3,5,7; without removing " in{
        queue.size() should be (0)
        
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        queue.enqueue(5)
        queue.enqueue(7)
        
        queue.size() should be (5)
        
        queue.First() should be (Some(1))
        
        queue.size() should be (5)
        
        queue.dequeue() should be (Some(1))
        queue.dequeue() should be (Some(2))
        queue.dequeue() should be (Some(3))
        queue.dequeue() should be (Some(5))
        queue.dequeue() should be (Some(7))
    
        queue.size() should be (0)
    }
}