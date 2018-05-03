package br.unb.cic.ed.ConcreteIterator

import br.unb.cic.ed.traits.Iterator
import br.unb.cic.ed.mutable.ArrayList

/**
  * Uma implementação do tipo iterator baseado
  * em uma Lista sequencial.
  * 
  * @author thaleslim
  */
//TODO: comentar
class ArrayListIterable[T <% Comparable[T]](private val list: ArrayList[T]) extends Iterator[T]{

    private var cursor: T = _
    private var index: Int = -1
    private var temp: Int = index
    private var finished: Boolean = false

    def currentItem(): T = this.cursor

    def currentIndex(): Int = index

    def currentValue() = this.currentItem

    def first() = list.elementAt(0) match {
            case Some(value) => { this.cursor = value; this.index = 0 }
            case None => finished = true
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
        if( !this.isDone ){
            list.elementAt( this.index + 1 ) match {
                case Some(value) => {
                    this.temp = this.index
                    this.index += 1
                    this.cursor = value
                }
                case None => finished = true
            }
        }
    }

    def isDone(): Boolean = this.finished
    
}