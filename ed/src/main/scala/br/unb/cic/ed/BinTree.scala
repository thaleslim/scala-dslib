package br.unb.cic.ed

trait BinTree[T <: Comparable[T]] {
  def insert(value : T): Unit
  def exist(value: T): Boolean
}
