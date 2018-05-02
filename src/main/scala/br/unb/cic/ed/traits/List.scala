package br.unb.cic.ed.traits

/**
  * Uma especificação do tipo lista usando
  * a construção trait da linguagem Scala. 
  * 
  * @author rbonifacio / thaleslim
  */

/*
 Inserção:
    list.insert(0,5)
    list(5)
        ou uma sequência de valores list(5,6,7)
    list(0 -> 5) ou list((0,5))
    list + 5
    list + (0,5)
        ou uma sequência list + ((0,5),(1,6),(2,7))

 Remoção:
    list.remove(0)
    list - (5)
        ou uma sequência list - (5,6,7)
*/

trait List[T] {
    def insert        (idx: Int, value: T) : Unit
    def remove        (pos: Int)           : Unit
    def elementAt     (idx: Int)           : Option[T]
    def find          (value: T)           : Option[Int]
    def addAll[B <: T](values: List[B])    : Unit
    def clear(): Unit
    def size() : Int

    //Substitue a lista atual por uma nova
    def subst (newlist: List[T]) : Unit = { this.clear; this.addAll(newlist) }
    //Insere o valor tuple._2 na posição tuple._1, usar operador -> ou uma Tupla
    def apply (tuple: Tuple2[Int,T]) = this.insert(tuple._1,tuple._2)
    def +     (tuple: Tuple2[Int,T]) = this(tuple)
    //Insere a sequência de tuplas na lista de acordo com ._1 de cada elemento
    def +     (tuples: Tuple2[Int,T]*): Unit = {
        if( !tuples.isEmpty ) { this(tuples.head); this + (tuples.tail: _*) }
    }
    //Insere um valor no final da lista
    def +    (value: T) = this((this.size,value))
    //Insere uma sequência de valores na Lista, argumentos na forma de value:T
/* PROTÓTIPO QUE RECEBE TUPLAS
    def apply(tuples: Tuple2[Int,T]*): Unit = {
        if( !tuples.isEmpty ) { this(tuples.head); this(tuples.tail: _*) }
    }
*/
    def apply(values: T*): Unit = {
        if( !values.isEmpty ) { this + (values.head); this(values.tail: _*) }
    }

    //Busca e remove um valor da Lista, argumentos na forma de target:T
    def - (target: T): Unit	= this.find(target) match {
            case Some(index) => this.remove(index)
            case None => {}
        }
/* PROTÓTIPO QUE RECEBE ÍNDICES
    def - (index: Int): Unit = this.remove(index)
*/
    //Busca e remove uma sequência de valores da Lista
    def - (targets: T*): Unit = {
        if( !targets.isEmpty ){ this - (targets.head); this - (targets.tail: _*) }
    }
}
