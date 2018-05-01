package br.unb.cic.ed.mutable

/**
  * Uma especificação do tipo lista usando
  * a construção trait da linguagem Scala. 
  * 
  * @author rbonifacio
  */

trait List[T <: Comparable[T]] {
    def find(value: T): Option[Int]
    def elementAt(pos: Int): Option[T]
    def insert(pos: Int, value: T) : Unit
    def remove(pos: Int) : Unit
    def size() : Int
//    def addAll[T :> B ](values: List[B]): Unit // TODO: Fix This
/*
    def + [B <: T](values: List[B])
    def + (pair: Tuple2[Int,T])
    def - (index: T): Unit					//Busca e remove um valor da Lista
    def apply(pair: Tuple2[Int,T]): Unit	//Insere o valor pair._2 na posição pair._1, usar operador -> ou uma Tupla
    def apply(values: Tuple2[Int,T]*): Unit	//Insere uma sequência de Tuplas no Hash Map

*/
}
