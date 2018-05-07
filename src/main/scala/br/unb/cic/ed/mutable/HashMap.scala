package br.unb.cic.ed.mutable

/**
  * A Hash Map is a data structure that associates access keys to specific values.
  *
    This' goal is, based on access keys, make the fastest search possible for a value, in terms of algorithm
  * complexity.
  * 
  * @author thaleslim
  */

trait HashMap[A <: Comparable[A],B]{
    /** Sets a new default hashCode */
    def apply(newHash: A => Int): Unit
    /** Searches for a value inside this based on its key */
    def apply(key: A): Option[B]
    /** Inserts a value in this, creating a bond to a key */
    def apply(pair: Tuple2[A,B]): Unit
    /** Inserts a Tuple2[A,B] sequence in this */
    def apply(values: Tuple2[A,B]*): Unit
    /** Removes a value from this */
    def - (key: A): Unit
    /** Removes a value sequence from this */
    def - (keys: A*): Unit
}
