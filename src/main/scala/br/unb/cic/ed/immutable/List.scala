package br.unb.cic.ed.immutable

/*
 * Todas as implementacoes do trait List precisam ser 
 * definidas neste arquivo fonte, uma vez que o trait 
 * foi definido com o modificador "sealed"
 */ 
sealed trait List[+A]

// uma unica instancia de Nil (um singleton) 
case object Nil extends List[Nothing]

// podemos ter varias instancias de Cons
case class Cons[+A](head: A, tail: List[A]) extends List[A] 


/**
  * Companion Object do trait List: mais um idioma da linguagem Scala  
  * 
    Um companion object precisa ser declarado no mesmo arquivo fonte 
  * que a classe / trait base (no caso, o trait List) e permite definir 
  * metodos uteis para manipular / instanciar objetos da classe / trait base. 
  * 
  * Sempre que em Java / C++ precisamos de um metodo estatico, em Scala definimos 
  * operacoes em um "companion object", que lembra bastante uma classe singleton. 
  */
object List {
  def append[A](first: List[A], second: List[A]) : List[A] = first match {
    case Nil         => second
    case Cons(x, xs) => Cons(x, append(xs, second))
  }

  def reverse[A](list: List[A]) : List[A] = list match {
    case Nil => Nil
    case Cons(x, xs) => append(reverse(xs), Cons(x, Nil))
  }

  def size[A](list: List[A]): Int = list match {
    case Nil => 0
    case Cons(x, xs) => 1 + size(xs)  
  }

  def map[A, B](list: List[A])(f: A => B) : List[B] = list match {
    case Nil => Nil
    case Cons(x, xs) => Cons(f(x), map(xs)(f)) 
  }

  def apply[A](values: A*): List[A] =
    if(values.isEmpty) Nil 
    else Cons(values.head, apply(values.tail: _*))  // idioma da linguagem Scala (nao temos como fugir disso).   
}
