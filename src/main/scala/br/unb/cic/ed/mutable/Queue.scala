package br.unb.cic.ed.mutable

/**
  * Queue é uma estrutura linear de dados
  * onde o primeiro elemento adicionado à
  * fila será o primeiro a ser removido (First in First Out)
  * Uma especificação do tipo fila
  * usando a construção trait da linguagem
  * Scala.
  * 
  * @author mekamdan
  */
  
trait Queue[T] {
    /**Insert a value in the end of the queue **/
    def enqueue(value:T) : Unit
    /** Remove a value a value from the begining of the queue **/
    def dequeue (): Option[T]
    /** Check the first element of the queue without removing it **/
    def First() : Option[T]
    /** Return the size of the queue **/
    def size(): Int
}
