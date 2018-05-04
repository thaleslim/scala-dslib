package br.unb.cic.ed.ConcreteIterators

import br.unb.cic.ed.traits.Iterator
import br.unb.cic.ed.mutable.ArrayList

/**
  * Uma implementação do tipo iterator baseado
  * em uma Lista sequencial.
  * 
  * @author thaleslim
  */

class ArrayListIterable[T <% Comparable[T]](private val list: ArrayList[T]) extends Iterator[T]{

    private var cursor: T = _
    private var index: Int = -1
    private var temp: Int = index
    private var finished: Boolean = false

    def currentItem(): T = this.cursor

    def currentValue() = this.currentItem

    def first() = {
        list.elementAt(0) match {
            case Some(value) => this.cursor = value
            case None => {}
        }
        this.index = 0
    }

    def previous(): Unit = {
        if( this.temp > 0 ) 
            list.elementAt(this.temp) match {
                case Some(value) => this.cursor = value
                case None => {}
            }
        else
            finished = true
    }

    def next(): Unit = {
        if( this.index + 1 < list.size && !this.isDone ){
            this.temp = this.index
            this.index += 1
            list.elementAt(this.index) match {
                case Some(value) => this.cursor = value
                case None => {}
            }
        }else
            finished = true
    }

    def isDone(): Boolean = this.finished
    
}