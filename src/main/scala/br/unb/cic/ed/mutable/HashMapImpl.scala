package br.unb.cic.ed.mutable

/**
  * Uma implementacao do tipo hash table usando
  * alocacao sequencial (um array de elementos).
  *
  * @author: thaleslim
  */

class ArrayHashMap[A, B: Manifest](private val max: Int = 10) extends HashMap[A,B]{

    private var elements = Array.ofDim[B](max)
	private var default_hash: A => Int = ( _.hashCode() )
    private val default_value = elements(0)

	private def setHashing(newHash: A => Int): Unit = {default_hash = newHash}
	
	private def search(key: A): Option[B] = {
        if( !max ) None // If max == 0 return None
		var index = default_hash(key)
		if( index > -1 && index < max && _size < max )
			Some(elements(index))
		else
			None
    }

	def insert (key: A, value: B): Unit = {
		var index = default_hash(key)
		if(index > -1 && index < max ){
			if(elements(index) == default_value){
				elements(index) = value
				_size += 1
			}
			else throw br.unb.cic.ed.mutable.InvalidArgument("Position occupied");
		}
		else throw br.unb.cic.ed.mutable.InvalidArgument("Out of range");
    }

	def apply(newHash: A => Int): Unit = {this.setHashing(newHash)}

	def apply(key: A): Option[B] = {this.search(key)}

    def remove(index: A): Unit = {

    }
}