package br.unb.cic.ed.design

import br.unb.cic.ed.mutable.List

/**
  * Uma especificação do tipo traversable usando
  * a construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */
//TODO: comentar
trait Traversable[NodeType]{
    def foreach[U](fun: NodeType => U): Unit

    def size(): Int = {
        var size = 0
        this.foreach[Int]{ x: NodeType => size += 1; size }
        return size
    }

    def map(fun: NodeType => NodeType): List[NodeType]
    def reduce[A](fun: (A,NodeType) => A)(start: A): A
    def filter(fun: NodeType => Boolean): List[NodeType]

}