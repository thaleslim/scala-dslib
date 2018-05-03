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
class ArrayListIterable[T <% Comparable[T]](private val elements: Array[T]) extends Iterator[T]{

    private var cursor: T = _
    private var index: Int = -1
    private var temp: Int = index
    private var finished: Boolean = false

    def currentItem(): T = this.cursor

    def currentIndex(): Int = this.index

    def first() = { 
        if( this.elements.length > 0){
            this.cursor = this.elements(0)
            this.index = 0
        }else 
            this.finished = true
    }

    def previous(): Unit = {
        if( this.temp > 0 ) {
            this.cursor = elements(this.temp); this.index = this.temp; this.temp -= 1
        }else
            this.finished = true
    }

    def next(): Unit = {
        if( !this.isDone && this.index + 1 < this.elements.length ){
            this.temp = this.index
            this.index += 1
            this.cursor = this.elements(this.index)
        } else 
            this.finished = true
    }

    //ATENÇÃO: Só dá por terminado o acesso à coleção, neste casso Array, quando chega ao último espaço livre
    def isDone(): Boolean = this.finished 

}