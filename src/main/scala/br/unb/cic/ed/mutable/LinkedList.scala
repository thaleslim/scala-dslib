package br.unb.cic.ed.mutable

import br.unb.cic.ed.traits._
import br.unb.cic.ed.ConcreteIterators._

case class NodeList[T](val value: T, var next: NodeList[T])

class LinkedList[T <: Ordering[T]] extends List[T] with Aggregate[ListIterable[T]]{

  private var _size: Int = 0 
  private var head: NodeList[T] = null

  def createIterator(): ListIterable[T] = return new ListIterable[T](this)

  def size(): Int = _size
    //TODO: Stress Test nodeAtPosition
  def nodeAtPosition(idx: Int): NodeList[T] = {
    var it = head
    for(index <- 0 until idx) {
      it = it.next
    }
    return it
  }
    //TODO: Padronizar função find para um tipo T <: Comparable
  def find(value: T): Option[Int] = {
    if(size == 0) { return None }
    var it = head
    var idx = 0
    while(it != null) {
      if( value.equals(it.value) ) {
        return Some(idx); 
      }
      it = it.next
      idx += 1
    }
    return None
  }

  def elementAt(idx: Int): Option[T] = {
    if(idx < 0 || idx > _size) {
      return None
    }
    val node = nodeAtPosition(idx)
    return Some(node.value)
  }

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

  def remove(idx: Int): Unit = {
    if(idx < 0 || idx >= size) {
      throw br.unb.cic.ed.mutable.InvalidArgument()
    }
    if(idx == 0) {
      head = head.next
    }
    else {
      val node = nodeAtPosition(idx-1)
      node.next = node.next.next
    }
    _size -= 1
  }

    //TODO: Implementar addAll and foreach
  def addAll(values: List[T]) : Unit = {
    throw InvalidArgument("not implemented yet") 
  }

/*
  def foreach[U](f: T => U): LinkedList[U] = {
    var cursor = this.createIterator
    cursor.first
    while( cursor.currentItem != null && !cursor.isDone ) cursor.next
  }
*/
}