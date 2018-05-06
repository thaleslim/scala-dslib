package br.unb.cic.ed.mutable

/**
  * Uma especificação do tipo pilha
  * usando a construção trait da linguagem
  * Scala.
  *
  * @author thaleslim
  */

trait Stack[T] {
  /** Pushes a value in this */
  def push(value: T): Unit
  /** Pops a value from this */
  def pop(): Option[T]
  /** Returns the value at this' top */
  def top(): Option[T] 
  /** Returns the amount of values stacked */
  def size(): Int
}
