package br.unb.cic.ed.mutable

import br.unb.cic.ed.traits.List
import br.unb.cic.ed.traits.Aggregate
import br.unb.cic.ed.ConcreteIterators.LinkedListIterable

/**
  * Uma implementação do tipo lista usando
  * encadeamento.
  * 
  * @author rbonifacio / thaleslim
  */

case class NodeList[T](val value: T, var next: NodeList[T])

class LinkedList[T <% Comparable[T]] extends List[T] with Aggregate[LinkedListIterable[T]]{

  private var _size: Int = 0 
  private var head: NodeList[T] = null

  def insert(idx: Int, value: T){
    if(idx >= 0 && idx <= _size) {
      if(idx == 0) {
        head = NodeList(value, head) 
      }
      else {
        val node = nodeAtPosition(idx-1)
        node.next = NodeList(value, node.next)
      }
      _size += 1
    }
    else {
      throw br.unb.cic.ed.mutable.InvalidArgument()
    }
  }

  def remove(pos: Int): Unit = {
    if(pos < 0 || pos >= size) {
      throw br.unb.cic.ed.mutable.InvalidArgument()
    }
    if(pos == 0) {
      head = head.next
    }
    else {
      val node = nodeAtPosition(pos-1)
      node.next = node.next.next
    }
    _size -= 1
  }

  def elementAt(idx: Int): Option[T] = {
    if(idx < 0 || idx > _size) {
      return None
    }
    val node = nodeAtPosition(idx)

    if( node != null )
        return Some(node.value)
    else
        return None
  }

  def find(value: T): Option[Int] = {
    if( size == 0 ) return None
    var it = head
    var idx = 0
    while( it != null ) {
        if( value.compareTo(it.value) == 0 )
            return Some(idx)
        it = it.next
        idx += 1
    }
    return None
  }

  def addAll[B<:T](values: List[B]) : Unit = {
    for(index <- 0 until values.size){
        values.elementAt(index) match{
            case Some(value) => this.insert(this.size,value)
            case _ => throw new InvalidArgument("ooops..... unexpected")
        }
    }
  }

  def size(): Int = _size
    //TODO: Stress Test nodeAtPosition
  def nodeAtPosition(idx: Int): NodeList[T] = {
    if(idx < 0 || idx > this.size) null
    var it = head
    for(index <- 0 until idx) {
      it = it.next
    }
    return it
  }
  
  def createIterator(): LinkedListIterable[T] = return new LinkedListIterable[T](this)

/* TODO: Implementar foreach
  def foreach[U](f: T => U): LinkedList[U] = {
    var cursor = this.createIterator
    cursor.first
    while( cursor.currentItem != null && !cursor.isDone ) cursor.next
  }
*/
}