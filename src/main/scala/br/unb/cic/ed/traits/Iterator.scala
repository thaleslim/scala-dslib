package br.unb.cic.ed.traits

/**
  * Uma especifição do design Iterator, ou cursor,
  * usando a construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */

trait Iterator[NodeType]{
    def currentItem(): NodeType   //O nó apontado do cursor
    def currentIndex(): Int       //A posição atual do cursor
    def currentValue(): Any       //O valor encapsulado na posição atual
    def first(): Unit             //Move o cursor para o início do Transversable
    def previous(): Unit          //Move o cursor para o elemento anterior
    def next(): Unit              //Move o cursor para o elemento seguinte
    //TODO: ampliar comportamento de this.isDone()
    def isDone(): Boolean         //Verifica se chegou ao final do Transversable ou se está vazia

    def apply(): Boolean = this.isDone()
    def unary_!(): Boolean = !this.isDone()
    def ++(): Unit = this.next()
    def --(): Unit = this.previous()
}
