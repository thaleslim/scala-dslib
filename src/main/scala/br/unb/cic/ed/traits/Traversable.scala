package br.unb.cic.ed.traits

/**
  * Uma especificação do tipo traversable usando
  * a construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */

trait Traversable[NodeType, IterableType] extends List[NodeType] with Aggregate[IterableType]{
    def foreach[U](fun: NodeType => U): Unit
    def map(fun: NodeType => NodeType): Any
    def reduce[A](fun: (A,NodeType) => A)(start: A): Any
    def filter(fun: NodeType => Boolean): Any
}