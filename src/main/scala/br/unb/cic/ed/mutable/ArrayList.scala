package br.unb.cic.ed.mutable

import br.unb.cic.ed.traits._

/**
  * Uma implementacao do tipo lista usando
  * alocacao sequencial (um array de elementos).
  *
  * @author: rbonifacio
  */

class ArrayList[T <% Comparable[T]: Manifest](private val max: Int = 10) extends List[T]/* with Aggregate[T]*/{

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

  def remove(idx: Int): Unit = {
    if(idx >= 0 && idx < _size) {
      if(idx != _size-1){
        for(index <- idx until (_size-1)){
          elements(index) = elements(index+1)
        }
      }
      _size -= 1
    }
    else throw InvalidArgument("the first argument must be between 0 and size")
  }

  def size(): Int = _size

  def addAll(values: List[T]): Unit = {
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

}
