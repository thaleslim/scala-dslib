package br.unb.cic.ed.mutable

/**
  * Uma especifição do tipo Hash MAp
  * usando a construção trait da linguagem 
  * Scala. 
  * 
  * @author thaleslim
  */

trait HashMap[A,B]{
	def apply(newHash: A => Int): Unit		//Altera a função default de hash
	def apply(key: A): Option[B]        	//Busca por um valor no Hash Map baseado em uma chave
	def apply(pair: Tuple2[A,B]): Unit		//Insere um valor no Hash Map vinculando-o a uma chave, usar operador -> ou uma Tupla
  	def apply(values: Tuple2[A,B]*): Unit	//Insere uma sequência de Tuplas no Hash Map
	def remove(index: A): Unit				//Remove um valor do Hash Map
}
