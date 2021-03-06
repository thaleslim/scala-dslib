package br.unb.cic.ed.concreteIterator

import br.unb.cic.ed.design.Iterator
import br.unb.cic.ed.mutable.ArrayList

/**
  * A Concrete Iterator implemented to a Sequencially Allocated(Array) List.
  * 
    @see [[br.unb.cic.ed.mutable.ArrayList]]
  * @author thaleslim
  */

class ArrayListIterable[T <% Comparable[T]](private val array: ArrayList[T]) extends Iterator[T]{

    private var cursor: T = _
    private var index: Int = -1
    private var temp: Int = index
    private var finished: Boolean = false

    def currentItem(): T = this.cursor

    def currentIndex(): Int = this.index

    def first() = {
        array.elementAt(0) match {
            case Some(value) => { this.cursor = value; this.index = 0}
            case None => this.finished = true
        }
    }
    
    // NOTE: implement previous another day
    // def previous(): Unit = {
    //     array.elementAt(this.temp) match {
    //         case Some(value) => { this.cursor = value; this.index = this.temp; this.temp -= 1 }
    //         case None => { this.finished = true }
    //     }
    // }

    def next(): Unit = {
        if( !this.isDone ) array.elementAt(this.index + 1) match {
            case Some(value) => { this.cursor = value; this.temp = this.index; this.index += 1 }
            case None => { this.finished = true }
        }
    }

    def isDone(): Boolean = this.finished 

}