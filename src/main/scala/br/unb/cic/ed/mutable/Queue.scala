package br.unb.cic.ed.mutable

/**
  * Uma especificação do tipo fila
  * usando a construção trait da linguagem
  * Scala.
  * 
  * @author mekamdan
  */
  
trait Queue[T] {
    /**  */
    def enqueue(value:T) : Unit
    /**  */
    def dequeue (): Option[T]
    /**  */
    def First() : Option[T]
    /**  */
    def size(): Int
}
