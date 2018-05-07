package br.unb.cic.ed.mutable

/**
  * A Queue is a linear data structure that follows a FIFO 
  * (First In First Out) priority to handle its elements.
  * 
  * @author mekamdan
  */
  
trait Queue[T] {
    /** Inserts a value at this' tail */
    def enqueue(value:T) : Unit
    /** Removes a value at this' head */
    def dequeue (): Option[T]
    /** Checks the value at this' head without removing it */
    def First() : Option[T]
    /** Returns the amount of values enqueued */
    def size(): Int
}
