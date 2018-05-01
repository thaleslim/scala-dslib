package br.unb.cic.ed.traits

/**
  * Uma especifição do design Iterator, ou cursor,
  * usando a construção trait da linguagem Scala. 
  * 
  * @author thaleslim
  */

trait Iterator[NodeType]{
    def currentItem(): NodeType             //A posição atual do cursor
    def first(): Unit                       //Move o cursor para o início do Transversable
    def previous(): Unit                    //Move o cursor para o elemento anterior
    def next(): Unit                        //Move o cursor para o elemento seguinte
    def isDone(): Boolean                   //Verifica se chegou ao final do Transversable
/*
    def apply(newHash: A => Int): Unit		//Altera a função default de hash
    def apply(key: A): Option[B]        	//Busca por um valor no Hash Map baseado em uma chave
    def apply(pair: Tuple2[A,B]): Unit		//Insere um valor no Hash Map vinculando-o a uma chave, usar operador -> ou uma Tupla
    def apply(values: Tuple2[A,B]*): Unit	//Insere uma sequência de Tuplas no Hash Map
    def - (key: A): Unit					//Remove um valor do Hash Map
    def - (keys: A*): Unit					//Remove uma sequência de valores do Hash Map
*/
}
