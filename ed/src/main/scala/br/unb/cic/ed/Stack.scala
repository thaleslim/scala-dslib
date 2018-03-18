package br.unb.cic.ed

/**
  * Uma especifica\c c\~{a}o do tipo pilha
  * usando a constru\c c\~{a}o trait da linguagem
  * Scala.
  *
  * @author thaleslim
  */
trait Stack {
  def push(value: Int): Unit
  def pop(): Option[Int]
  def size() : Int
}
