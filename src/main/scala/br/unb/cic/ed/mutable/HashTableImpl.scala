package br.unb.cic.ed.mutable

/**
  * Uma implementacao do tipo hash table usando
  * alocacao sequencial (um array de elementos).
  *
  * @author: thaleslim
  */

class ArrayHashTable[A <: Int, B: Manifest](private val max: Int = 10) extends HashTable[A,B] {
    
    private var _size = 0;
    private var elements = Array.ofDim[B](max)
    
    def hashing(key: A): Int{
        
    }

    def insert(key: A, value: B){

    }

    //def search(key: Keys): Option[B] = {
    //    if(_size == 0) None
    //}

    def remove(pos: Int){

    }

    def size() = _size
}