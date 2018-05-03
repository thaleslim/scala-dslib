package br.unb.cic.ed.mutable

import br.unb.cic.ed.traits.List
import br.unb.cic.ed.traits.Aggregate
import br.unb.cic.ed.traits.Traversable
import br.unb.cic.ed.ConcreteIterator.ArrayListIterable

/**
  * Uma implementação do tipo lista usando
  * alocação sequencial (um array de elementos).
  *
  * @author: rbonifacio
  */
//TODO: comentar & falar com professor sobre Iterator e ArrayList
class ArrayList[T <% Comparable[T]: Manifest](private val max: Int = 10) extends List[T] with Aggregate[ArrayListIterable[T]]{

  private var _size = 0;
  private var elements = Array.ofDim[T](max)

  def insert(idx: Int, value: T): Unit = {
    var size = this.size
    if(idx >= 0 && idx <= size && idx < max) {
      if(idx == size) {
        elements(idx) = value
      }
      else {
        for(index <- (size-1) to idx by -1){
          elements(index + 1) = elements(index)
        }
        elements(idx) = value
      }
      _size += 1
    }
    else throw InvalidArgument("the first argument must be between 0 and size")
  }

  def remove(pos: Int): Unit = {
    var size = this.size 
    if(pos >= 0 && pos < size) {
      if(pos != size-1){
        for(index <- pos until (size-1)){
          elements(index) = elements(index+1)
        }
      }
      _size -= 1
    }
    else throw InvalidArgument("the first argument must be between 0 and size")
  }

  def elementAt(idx: Int): Option[T] = {
    if(idx >= 0 && idx < _size) //Usar o this.size aqui dá erro
        return Some(elements(idx))
    else
        return None
  }

  def find(value: T): Option[Int] = {
    for(idx <- 0 until this.size) {
        if( value.compareTo(elements(idx)) == 0 )
            return Some(idx)
    }
    return None
  }

  def addAll[B<:T](that: List[B]): Unit = {
    if(that.size + this.size > max) {
      throw new InvalidArgument("overflow!!!")
    }

    for(i <- 0 until that.size) {
      that.elementAt(i) match {
        case Some(v) => this.insert(this.size, v)
        case _       => throw new InvalidArgument("ooops..... unexpected")
      }
    }
  }

  def clear(): Unit = while( this.size > 0 ) { this.remove( this.size - 1 ) }

  //override def size(): Int = _size

  def createIterator(): ArrayListIterable[T] = return new ArrayListIterable[T](this.elements)

  def foreach[U](fun: T => U): Unit = {
    var cursor = this.createIterator

    cursor.first

    while( !cursor ){
        fun(cursor.currentItem)
        cursor.next
    }

  }

  def map(fun: T => T): ArrayList[T] = {
    var cursor = this.createIterator
    var that = new ArrayList[T](this.max)

    cursor.first
    
    while( !cursor ){
        that.insert( that.size, fun(cursor.currentItem) )
        cursor.next
    }

    this.subst(that)

    return that

  }

  def reduce[A](fun: (A,T) => A)(start: A): A = {
    var cursor  = this.createIterator
    var result: A = start

    cursor.first
    
    while( !cursor ){
        result = fun( result, cursor.currentItem )
        cursor.next
    }

    return result
  }

  def filter(fun: T => Boolean): ArrayList[T] = {
    var cursor = this.createIterator
    var that = new ArrayList[T](this.max)

    cursor.first
    
    while( !cursor ){
        if( fun( cursor.currentItem ) )
            that.insert( that.size, cursor.currentItem )
        cursor.next
    }

    this.subst(that)

    return that
  }

}
