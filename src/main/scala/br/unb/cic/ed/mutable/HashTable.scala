package br.unb.cic.ed.mutable

/**
  * Uma especificao do tipo lista 
  * usando a constru\c c\~{a}o trait da linguagem 
  * Scala. 
  * 
  * @author thaleslim
  */

trait HashTable[A,B]{
  def setHashin(hash: A => Int): Unit
  def insert(key: A, value: B): Unit
  def search(key: A): Option[B]
  def remove(index: A): Unit
  def size(): Int
}
