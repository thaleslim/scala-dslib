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
        if( max == 0 ) None
		var index = default_hash(key) % max
		if( index > -1 && index < max && elements(index) != default_value)
			Some(elements(index))
		else
			None
    }

	private def insert (key: A, value: B): Unit = {
		var index = default_hash(key) % max
		if(index > -1 && index < max ){
			if(elements(index) == default_value)
				elements(index) = value
			else throw br.unb.cic.ed.mutable.InvalidArgument("Position occupied");
		}
		else throw br.unb.cic.ed.mutable.InvalidArgument("Out of range");
    }

	def apply(newHash: A => Int): Unit = this.setHashing(newHash)

	def apply(key: A): Option[B] = this.search(key)

	def apply(pair: Tuple2[A,B]): Unit = this.insert(pair._1,pair._2)

	def apply(values: Tuple2[A,B]*): Unit = {
		if(!values.isEmpty){
			this.apply(values.head)
			this.apply(values.tail: _*)
		}
	}

    def remove(index: A): Unit = {

    }
}