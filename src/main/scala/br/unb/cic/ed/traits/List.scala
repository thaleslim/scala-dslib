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
//TODO: comentar
trait List[T] extends Traversable[T]{
    def insert        (idx: Int, value: T) : Unit           //Insere um valor: T na posição idx+1 da lista
    def remove        (pos: Int)           : Unit           //Remove o valor presente no índice pos-1 da lista
    def elementAt     (idx: Int)           : Option[T]      //Recebe um índice e retorna o valor listado correspondente ou None, caso não tenha sido encontrado
    def find          (value: T)           : Option[Int]    //Busca por value: T e retorna seu índice correpondente na lista ou None, caso não tenha sido encontrado
    def addAll[B <: T](that: List[B])      : Unit           //Concatena ao final da lista todos os elementos presentes em that
    def clear(): Unit

    //Substitue a lista atual por uma nova
    def subst (that: List[T]) : Unit = { this.clear; this.addAll(that); }
    //Insere o valor tuple._2 na posição tuple._1, usar operador -> ou uma Tupla
    def apply (tuple: Tuple2[Int,T]) = this.insert(tuple._1,tuple._2)
    def +     (tuple: Tuple2[Int,T]) = this(tuple)
    //Insere a sequência de tuplas na lista de acordo com ._1 de cada elemento
    def +     (that: Tuple2[Int,T]*): Unit =
        if( !that.isEmpty ) {
            this(that.head)
            this + (that.tail: _*)
        }
    //Insere um valor no final da lista
    def +    (value: T) = this((this.size,value))
    //Insere uma sequência de valores na Lista, argumentos na forma de value:T
/* PROTÓTIPO QUE RECEBE TUPLAS
    def apply(tuples: Tuple2[Int,T]*): Unit = {
        if( !tuples.isEmpty ) { this(tuples.head); this(tuples.tail: _*) }
    }
*/
    def apply(that: T*): Unit = {
        if( !that.isEmpty ) { this + (that.head); this(that.tail: _*) }
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
    def - (that: T*): Unit = {
        if( !that.isEmpty ){ this - (that.head); this - (that.tail: _*) }
    }
}
