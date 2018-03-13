package br.unb.cic.ed

/**
  * Uma especifica\c c\~{a}o do tipo lista 
  * usando a constru\c c\~{a}o trait da linguagem 
  * Scala. 
  * 
  * @author rbonifacio
  */ 
trait List {
  def find(value: Int): Option[Int]
  def elementAt(pos: Int): Option[Int]
  def insert(pos: Int, value: Int) : Unit
  def remove(pos: Int) : Unit
  def size() : Int
}
