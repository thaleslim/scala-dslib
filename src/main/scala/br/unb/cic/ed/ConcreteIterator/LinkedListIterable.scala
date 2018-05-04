package br.unb.cic.ed.ConcreteIterator

import br.unb.cic.ed.traits.Iterator
import br.unb.cic.ed.mutable.NodeList
import br.unb.cic.ed.mutable.LinkedList

/**
  * Uma implementação do tipo iterator baseado
  * em uma Lista encadeada e NodeList.
  * 
  * @author thaleslim
  */
//TODO: comentar
class LinkedListIterable[T <% Comparable[T]](private val head: NodeList[T]) extends Iterator[T]{

    private var cursor: NodeList[T] = null
    private var index: Int = -1
    private var temp: NodeList[T] = cursor
    private var finished: Boolean = false

    def currentItem(): T = this.cursor.value

    def currentIndex(): Int = this.index

    def first() = { 
        this.cursor = head
        if( this.cursor != null )
            this.index = 0
        else
            this.finished = true
    }

    def previous(): Unit = {
        if( this.index > -1 && this.temp != null ){
            this.cursor = this.temp
            index -= 1
        }else
            finished = true
    }

    def next(): Unit = {
        if( this.cursor != null && this.cursor.next != null && !this.isDone ){
            this.temp = this.cursor
            this.cursor = this.cursor.next
            index += 1
        }else
            finished = true
    }

    def isDone(): Boolean = this.finished
    
}