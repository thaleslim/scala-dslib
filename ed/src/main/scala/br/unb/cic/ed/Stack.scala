package br.unb.cic.ed

/**
  * Uma especificação do tipo pilha
  * usando a construção trait da linguagem
  * Scala.
  *
  * @author thaleslim
  */
trait Stack {
  def push(value: Int): Unit
  def pop(): Option[Int]
  def top(): Option[Int] 
  def size(): Int
}
