package br.unb.cic.ed.design

/**
  * Uma especifição do design Iterator, ou cursor,
  * usando a construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */

trait Iterator[T]{
    /** O nó apontado do cursor */
    def currentItem(): T          
    /** A posição atual do cursor */
    def currentIndex(): Int
    /** Move o cursor para o início do Transversable */
    def first(): Unit
    /** Move o cursor para o elemento anterior */
    def previous(): Unit
    /** Move o cursor para o elemento seguinte */
    def next(): Unit
    /** Verifica se chegou ao final do Transversable ou se está vazia */
    def isDone(): Boolean
    /**  */
    def apply(): Boolean = this.isDone()
    /**  */
    def unary_!(): Boolean = !this.isDone()
    /**  */
    def ++(): Unit = this.next()
    /**  */
    def --(): Unit = this.previous()
}
