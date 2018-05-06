package br.unb.cic.ed.design

/**
  * The aggregate method aggregates results by first applying a sequence operation which is its first parameter and then * uses a combine operator to combine the results produced by the sequence operation
  * Uma especifição de interface para interação
  * com design Iterator, ou cursor, usando a
  * construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */

trait Aggregate[ConcreteIterator]{
    def createIterator(): ConcreteIterator      //Interface para inicialização de um Iterator
}
