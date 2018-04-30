package br.unb.cic.ed.mutable

/**
  * Uma especificação do tipo lista usando
  * a construção trait da linguagem Scala. 
  * 
  * @author rbonifacio
  */

trait List[T] {
  def find(value: T): Option[Int]
  def elementAt(pos: Int): Option[T]
  def insert(pos: Int, value: T) : Unit
  def remove(pos: Int) : Unit
  def size() : Int
  def addAll[B <: T](values: List[B]): Unit
}
