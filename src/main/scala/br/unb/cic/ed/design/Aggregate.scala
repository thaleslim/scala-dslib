package br.unb.cic.ed.design

/**
  * A Interface to allow the client interact with the Iterator.
  * 
    @see [[br.unb.cic.ed.design.Iterator]]
  * @author thaleslim
  */

trait Aggregate[ConcreteIterator]{
    /** Interface to initialize a Iterator 
      *
        @see [[br.unb.cic.ed.design.Iterator]]
      */
    def createIterator(): ConcreteIterator
}
