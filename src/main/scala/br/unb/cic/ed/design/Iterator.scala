package br.unb.cic.ed.design

/**
  *um iterador se refere tanto ao objeto que permite ao programador percorrer um container, (uma coleção de elementos) *particularmente listas, quanto ao padrão de projetos Iterator, no qual um iterador é usado para percorrer um container *e acessar seus elementos
  * Uma especifição do design Iterator, ou cursor,
  * usando a construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */

trait Iterator[T]{
    /** Gets the value inside this */
    def currentItem(): T          
    /** Gets this current position */
    def currentIndex(): Int
    /** Sets this to the Container' beginning */
    def first(): Unit
    /** Sets this to the element previously acessed */
    def previous(): Unit
    /** Sets this to the next element in the container */
    def next(): Unit
    /** Checks if has already reached the end or if the container is empty */
    def isDone(): Boolean
    /** Alternative to this.isDone */
    def apply(): Boolean = this.isDone()
    /** Alternative to !(this.isDone) */
    def unary_!(): Boolean = !this.isDone()
    /** Alternative to this.next */
    def ++(): Unit = this.next()
    /** Alternative to this.previous */
    def --(): Unit = this.previous()
}
