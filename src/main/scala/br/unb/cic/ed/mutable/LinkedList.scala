package br.unb.cic.ed.mutable

import br.unb.cic.ed.traits._
import br.unb.cic.ed.ConcreteIterators._

case class NodeList[T](val value: T, var next: NodeList[T])

class LinkedList[T <: Comparable[T]] extends List[T] with Aggregate[ListIterable[T]]{

  private var _size: Int = 0 
  private var head: NodeList[T] = null

  def createIterator(): ListIterable[T] = return new ListIterable[T](this)

  def size(): Int = _size

  def nodeAtPosition(pos: Int): NodeList[T] = {
    var it = head
    for(i <- 0 until pos) {
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
      if(it.value == value) {
        return Some(idx); 
      }
      it = it.next
      idx += 1
    }
    return None
  }

  def elementAt(pos: Int): Option[T] = {
    if(pos < 0 || pos > _size) {
      return None
    }
    val node = nodeAtPosition(pos)
    return Some(node.value)
  }

  def insert(pos: Int, value: T){
    if(pos >= 0 && pos <= _size) {
      if(pos == 0) {
        head = NodeList(value, head) 
      }
      else {
        val node = nodeAtPosition(pos-1)
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
    //TODO> Implementar addAll and foreach
/*
  def addAll[B <: T](values: List[B]) : Unit = {
    throw InvalidArgument("not implemented yet") 
  }

  def foreach[U](f: T => U): LinkedList[U] = {
    var cursor = this.createIterator
    cursor.first
    while( cursor.currentItem != null && !cursor.isDone ) cursor.next
  }
*/
}