package br.unb.cic.ed.mutable

/**
  * A Binary Tree is a hierarchy based data structure 
  * divided in nodes.
  * 
    This could:
   - Have 0 nodes (Empty Tree); or
   - Have 1 node, the Tree' root, that holds a value and the address' to 2 other Binary Trees.
  */

trait BinTree[T <: Comparable[T]] {
  /** Inserts a value in this */
  def insert(value : T): Unit
  /** Searchs for a value in this */
  def exist(value: T): Boolean
}
