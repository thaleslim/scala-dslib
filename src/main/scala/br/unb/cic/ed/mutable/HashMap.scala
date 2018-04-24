package br.unb.cic.ed.mutable

/**
  * Uma especifição do tipo Hash MAp
  * usando a construção trait da linguagem 
  * Scala. 
  * 
  * @author thaleslim
  */

trait HashMap[A,B]{
	def apply(newHash: A => Int): Unit
	def apply(key: A): Option[B]
  def apply(init: Tuple2[A,B]): Unit
  def apply(values: Tuple2[A,B]*): Unit
	def remove(index: A): Unit
}
