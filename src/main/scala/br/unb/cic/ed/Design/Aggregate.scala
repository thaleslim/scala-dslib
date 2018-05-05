package br.unb.cic.ed.design

/**
  * Uma especifição de interface para interação
  * com design Iterator, ou cursor, usando a
  * construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */

trait Aggregate[ConcreteIterator]{
    def createIterator(): ConcreteIterator      //Interface para inicialização de um Iterator
}
