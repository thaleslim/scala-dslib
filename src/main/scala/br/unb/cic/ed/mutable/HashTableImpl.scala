package br.unb.cic.ed.mutable

/**
  * Uma implementacao do tipo hash table usando
  * alocacao sequencial (um array de elementos).
  *
  * @author: thaleslim
  */

class ArrayHashTable[A, B: Manifest](private val max: Int = 10) extends HashTable[A,B]{
    
    private var _size = 0
    private var elements = Array.ofDim[B](max)
    private val default_value = elements(0)
	private var default_hash: A => Int = _

	def setHashin(hash: A => Int): Unit = {default_hash = hash}

	def insert (key: A, value: B){
		var index = default_hash(key)
		if(index > -1 && index < max && _size < max ){
			if(elements(index) == default_value){
				elements(index) = value
				_size += 1
			}
			else throw br.unb.cic.ed.mutable.InvalidArgument("Position occupied");
		}
		else throw br.unb.cic.ed.mutable.InvalidArgument("Out of range");
    }

    def search(key: A): Option[B] = {
        if( _size == 0 || max == 0 ) None
		var index = default_hash(key)
		if( index > -1 && index < max && _size < max )
			Some(elements(index))
		else
			None
    }

    def remove(index: A){

    }

    def size() = _size
}