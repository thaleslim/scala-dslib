package br.unb.cic.ed.design

/**
  * Uma especifição do design Iterator, ou cursor,
  * usando a construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */

trait Iterator[Type]{
    def currentItem(): Type   //O nó apontado do cursor
    def currentIndex(): Int       //A posição atual do cursor
    def first(): Unit             //Move o cursor para o início do Transversable
    def previous(): Unit          //Move o cursor para o elemento anterior
    def next(): Unit              //Move o cursor para o elemento seguinte
    def isDone(): Boolean         //Verifica se chegou ao final do Transversable ou se está vazia

    def apply(): Boolean = this.isDone()
    def unary_!(): Boolean = !this.isDone()
    def ++(): Unit = this.next()
    def --(): Unit = this.previous()
}
