package br.unb.cic.ed.mutable

/**
  * Uma implementação do tipo Hash Map usando
  * alocacao sequencial (um array de elementos).
  *
  * @author: thaleslim
  */

case class HashMapElement[A, B](var key: A, var value: B)

class ArrayHashMap[A <: Comparable[A], B: Manifest](private val max: Int = 10) extends HashMap[A,B]{

    private var elements = Array.ofDim[HashMapElement[A,B]](max)
	private var default_hash: A => Int = ( _.hashCode() % max )

	private def get_position(key: A): Int = {
		
	}

	private def insert(key: A, value: B): Unit = {
		var index = this.default_hash(key)
		if(index > -1 && index < max ){
			if(this.elements(index) == null){
				this.elements(index) = HashMapElement[A,B](key,value)
			}
			else throw br.unb.cic.ed.mutable.InvalidArgument("Position occupied");
		}
		else throw br.unb.cic.ed.mutable.InvalidArgument("Out of range");
    }
	
	private def search(key: A): Option[B] = {
        if( max == 0 ) None
		var index = this.default_hash(key)
		if( index > -1 && index < max && this.elements(index) != null ){
			if( key.compareTo(this.elements(index).key) == 0 ){
				return Some(this.elements(index).value)
			}
		}
		return None
    }

	def apply(newHash: A => Int): Unit = { this.default_hash = newHash }

	def apply(key: A): Option[B] = this.search(key)

	def apply(pair: Tuple2[A,B]): Unit = this.insert(pair._1, pair._2)

	def apply(values: Tuple2[A,B]*): Unit = {
		if(!values.isEmpty){
			this.apply(values.head)
			this.apply(values.tail: _*)
		}
	}

	def - (key: A): Unit = {
		var index = this.default_hash(key) 
		if(max > 0 && index > -1 && index < max && this.elements(index).key == key)
			this.elements(index) = null
	}

}