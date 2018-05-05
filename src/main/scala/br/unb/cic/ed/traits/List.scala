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
//TODO: comentar & usar comando doc ( generated in </home/thaleslim/Área de Trabalho/scala-dslib/target/scala-2.12/api> )
trait List[T] extends Traversable[T]{
    /**
      * Insere um valor em uma posição
      * @example 
      * {{{ var list: List[Int]
      *      list.insert(0,5)    //list = [5]
      * }}}
      */
    def insert        (idx: Int, value: T) : Unit
    /**
      * Remove o valor presente em uma posição
      * @example 
      * {{{ var list: List[Int]
      *      list(1,2,3,4,5)     //list = [1,2,3,4,5]
      *      list.remove(2)      //list = [1,2,4,5]
      * }}}
      */       
    def remove        (idx: Int)           : Unit
    /**
      * Recebe um índice e retorna o valor da posição correspondente
      * @return Some(valor) ou None, caso não encontre valor
      * @example 
      * {{{ var list: List[Int]
      *      list(1,2,3,4,5)     //list = [1,2,3,4,5]
      *      list.elementAt(2)   //== Some(3)
      *      list.elementAt(7)   //== None
      * }}}
      */
    def elementAt     (idx: Int)           : Option[T]
    /**
      * Busca pela posição correspondente a um certo valor 
      * @return Some(posição) ou None, caso não encontre valor
      * @example 
      * {{{ var list: List[Int]
      *      list(1,2,3,4,5)     //list = [1,2,3,4,5]
      *      list.find(2)        //== Some(1)
      *      list.find(88)       //== None
      * }}}
      */
    def find          (value: T)           : Option[Int]
    /**
      * Concatena this e that
      * @example 
      * {{{ var list: List[Int]; var that: List[Int]
      *      list(1,2);that(3,4) //list = [1,2]     && that = [3,4]
      *      list.addAll(that)   //list = [1,2,3,4] && that = [3,4]
      * }}}
      */
    def addAll[B <: T](that: List[B])      : Unit
    /** Remove todos os elementos */
    def clear(): Unit

    /** Substitue os elementos de this pelos de that */
    def subst (that: List[T]) : Unit = { 
        this.clear
        this.addAll(that)
    }
    
    /**
      * Insere o valor tuple._2 na posição tuple._1
      * @example 
      * {{{ var list: List[Int]
      *      list(0 -> 2)        //== list((0,2)) = [2]
      * }}}
      */
    def apply (tuple: Tuple2[Int,T]) = this.insert(tuple._1,tuple._2)
    /** Mesmo comportamento de this([[List!.apply(tuple:(Int,T)):Unit* Tuple2[Int,T]]]) */
    def +     (tuple: Tuple2[Int,T]) = this(tuple)
    /**Insere a sequência de tuplas na lista de acordo com ._1 de cada elemento*/
    def +     (that: Tuple2[Int,T]*): Unit =
        if( !that.isEmpty ) {
            this(that.head)
            this + (that.tail: _*)
        }
    /**Insere um valor no final da lista*/
    def +    (value: T) = this((this.size,value))
    /**Insere uma sequência de valores na Lista, argumentos na forma de value:T*/
    def apply(that: T*): Unit = {
        if( !that.isEmpty ) { this + (that.head); this(that.tail: _*) }
    }

    /**Busca e remove um valor da Lista, argumentos na forma de target:T*/
    def - (target: T): Unit	= this.find(target) match {
            case Some(index) => this.remove(index)
            case None => {}
        }
/* PROTÓTIPO QUE RECEBE ÍNDICES
    def - (index: Int): Unit = this.remove(index)
*/
    /**Busca e remove uma sequência de valores da Lista*/
    def - (that: T*): Unit = {
        if( !that.isEmpty ){ this - (that.head); this - (that.tail: _*) }
    }
}
