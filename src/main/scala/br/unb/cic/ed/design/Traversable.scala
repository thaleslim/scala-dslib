package br.unb.cic.ed.design

import br.unb.cic.ed.mutable.List

/**
  * Interface para detectar se uma classe é percorrível utilizando o foreach
  * Interface básica abstrata que não pode ser implementa sozinha 
  * Ao invés, deve ser implementada as interfaces IteratorAggregate ou Iterator 
  * 
  * @author thaleslim
  */

trait Traversable[T]{
    /** Executes a function for every element in a container, discarding its return value */
    def foreach[U](fun: T => U): Unit
    /** Calculates the number of elements of a container */
    def size(): Int = {
        var size = 0
        this.foreach[Int]{ x: T => size += 1; size }
        return size
    }
    /** Returns a new container that, in every position, holds the result from applying a function to the this' element from the same position */
    def map(fun: T => T): List[T]
    /** Applies a function foreach value of the container to reduce it to a single value */
    def reduce[A](fun: (A,T) => A)(start: A): A
    /** Returns a new container that holds every this' element to witch a boolean function returned true when applied */
    def filter(fun: T => Boolean): List[T]

}