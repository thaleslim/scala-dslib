package br.unb.cic.ed.mutable

import br.unb.cic.ed.traits.List
import br.unb.cic.ed.traits.Aggregate
import br.unb.cic.ed.traits.Traversable
import br.unb.cic.ed.ConcreteIterators.ArrayListIterable

/**
  * Uma implementação do tipo lista usando
  * alocação sequencial (um array de elementos).
  *
  * @author: rbonifacio
  */

class ArrayList[T <% Comparable[T]: Manifest](private val max: Int = 10) extends List[T]
    with Aggregate[ArrayListIterable[T]] with Traversable[T,ArrayListIterable[T]]{

  private var _size = 0;
  private var elements = Array.ofDim[T](max)

  def insert(idx: Int, value: T): Unit = {
    if(idx >= 0 && idx <= _size && idx < max) {
      if(idx == _size) {
        elements(idx) = value
      }
      else {
        for(index <- (_size-1) to idx by -1){
          elements(index + 1) = elements(index)
        }
        elements(idx) = value
      }
      _size += 1
    }
    else throw InvalidArgument("the first argument must be between 0 and size")
  }

  def remove(pos: Int): Unit = {
    if(pos >= 0 && pos < _size) {
      if(pos != _size-1){
        for(index <- pos until (_size-1)){
          elements(index) = elements(index+1)
        }
      }
      _size -= 1
    }
    else throw InvalidArgument("the first argument must be between 0 and size")
  }

  def elementAt(idx: Int): Option[T] = {
    if(idx >= 0 && idx < _size) {
      return Some(elements(idx))
    }
    return None
  }

  def find(value: T): Option[Int] = {
    for(idx <- 0 until _size) {
      if( value.compareTo(elements(idx)) == 0 ) {
        return Some(idx)
      }
    }
    return None
  }

  def addAll[B<:T](values: List[B]): Unit = {
    if(values.size() + _size > max) {
      throw new InvalidArgument("overflow!!!")
    }

    for(i <- 0 until values.size()) {
      values.elementAt(i) match {
        case Some(v) => insert(_size, v)
        case _       => throw new InvalidArgument("ooops..... unexpected")
      }
    }
  }

  def clear(): Unit = {
    var cursor = this.createIterator
    
    cursor.first

    while( !cursor ){
        this.remove(cursor.currentIndex)
        cursor.first
    }
  }

  def size(): Int = _size

  def createIterator(): ArrayListIterable[T] = return new ArrayListIterable[T](this)

  def foreach[U](fun: T => U): Unit = {
    var cursor = this.createIterator

    cursor.first
    
    while( !cursor )
        cursor.next

  }

  def map(fun: T => T): ArrayList[T] = {
    var cursor = this.createIterator
    var newlist = new ArrayList[T](this.max)

    cursor.first
    
    while( !cursor ){
        newlist.insert( newlist.size, fun(cursor.currentValue) )
        cursor.next
    }

    this.subst(newlist)

    return newlist

  }

  def reduce[A](fun: (A,T) => A)(start: A): A = {
    var cursor  = this.createIterator
    var result: A = start

    cursor.first
    
    while( !cursor ){
        result = fun( result, cursor.currentValue )
        cursor.next
    }

    return result
  }


  def filter(fun: T => Boolean): ArrayList[T] = {
    var cursor = this.createIterator
    var newlist = new ArrayList[T](this.max)

    cursor.first
    
    while( !cursor ){
        if( fun(cursor.currentValue) )
            newlist.insert(newlist.size,cursor.currentValue)
        cursor.next
    }

    this.subst(newlist)

    return newlist
  }

}
