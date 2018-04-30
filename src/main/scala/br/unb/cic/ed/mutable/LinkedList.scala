package br.unb.cic.ed.mutable

import br.unb.cic.ed.traits._

case class NodeList[T](val value: T, var next: NodeList[T])

//TODO: Criar um metódo de lista que retorne Some(o endereço do seu nó ou a cabeça da lista) ou None
//Solução provisória: restringi o Iterable para LinkedList e tornei publico o método nodeAtPosition
class ListIterable[T](private val list: LinkedList[T]) extends Iterator[NodeList[T]]{

    private var cursor: NodeList[T] = null
    private var temp: NodeList[T] = cursor

    def currentItem(): NodeList[T] = this.cursor

    def first() = { this.cursor = list.nodeAtPosition(0) }

    def previous(): Unit = { this.cursor = this.temp }

    def next(): Unit = {
        if( !this.isDone ){
            this.temp = this.cursor
            this.cursor = this.cursor.next
        }
    }

    def isDone(): Boolean = if( this.cursor == null && this.temp != null ) true else false
    
}

class LinkedList[T] extends List[T] with Aggregate[ListIterable[T]]{

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
    if(pos >=0 && pos <= _size) {
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

  def addAll[B <: T](values: List[B]) : Unit = {
    throw InvalidArgument("not implemented yet") 
  }
}