package br.unb.cic.ed.mutable

/**
  * Uma especificação do tipo lista usando
  * a construção trait da linguagem Scala. 
  * 
  * @author rbonifacio / thaleslim
  */

trait List[T] {
    def find(value: T): Option[Int]
    def elementAt(idx: Int): Option[T]
    def insert(idx: Int, value: T) : Unit
    def remove(idx: Int) : Unit
    def size() : Int
    def addAll(values: List[T]): Unit

    //Insere o valor pair._2 na posição pair._1, usar operador -> ou uma Tupla
    def apply(pair: Tuple2[Int,T]) = this.insert(pair._1,pair._2)
    def +    (pair: Tuple2[Int,T]) = this(pair)
    //Insere uma sequência de Tuplas na Lista
    def apply(values: Tuple2[Int,T]*): Unit = {
        if(!values.isEmpty){
            this.apply(values.head)
            this.apply(values.tail: _*)
        }
    }
    //Busca e remove um valor da Lista
    def - (value: T): Unit	= this.find(value) match {
            case Some(index) => this.remove(index)
            case None => {}
        }

}
