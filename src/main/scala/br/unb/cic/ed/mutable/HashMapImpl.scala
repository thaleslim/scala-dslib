package br.unb.cic.ed.mutable

/**
  * Uma implementação do tipo Hash Map usando
  * alocacao sequencial (um array de elementos).
  *
  * @author: thaleslim
  */

case class HashMapElement[A <: Comparable[A], B](var key: A, var value: B){
	def equalTo (key: A): Boolean = if( key.compareTo(this.key) == 0 ) true else false
}

class ArrayHashMap[A <: Comparable[A], B: Manifest](private val max: Int = 10) extends HashMap[A,B]{

    private var elements = Array.ofDim[HashMapElement[A,B]](max)
	private var default_hash: A => Int = ( _.hashCode() % max )

	private def sondagem_quadratica(start: Int): Int = {
		var index = start
		var contador = 1
		
		while( index < max && this.elements(index) != null )
			index = index + 2 * contador + 5 * contador * contador
		
		if( index < max && this.elements(index) == null ) index else -1
	}

	private def sondagem_linear(start: Int): Int = {
		var index = start
		var found: Boolean = false

		var break: Boolean = false
		while (!break){
			index += 1

			if( !(index < max) && start != 0 )
				index = 0
			else if( this.elements(index) == null ){
				break = true; found = true
			}else
				break = true

		}
		
		if( found ) return index else return -1
	}

	private def get_position(key: A): Int = {
		var index: Int = this.default_hash(key) % max
		
		if( index > -1 && index < max && this.elements(index) == null )
			return index
		
		var position = sondagem_quadratica(index)

		if( position > -1 )
			return position
		
		position = sondagem_linear(index)

		if( position > -1 )
			return position
		
		return -1
	}

	private def insert(key: A, value: B): Unit = {
		var index = get_position(key)
		if( index >= 0 )
			this.elements(index) = HashMapElement[A,B](key,value)
		else throw br.unb.cic.ed.mutable.InvalidArgument("Maximum Capacity Reached")
    }

	private def locate(start: Int, key: A): Int = {
		var index = start
		var found: Boolean = false

		var break: Boolean = false
		while( !break ){
			index += 1
			
			if( !(index < max) )
				index = 0
			else if( index == start )
				break = true
			else if( this.elements(index) != null && this.elements(index).equalTo(key) ){
				break = true; found = true
			}

		}

		if( found ) return index else return -1
	}
	
	private def search(key: A): Option[B] = {
        if( max == 0 ) None
		var index = this.default_hash(key) % max
		if( index > -1 && index < max && this.elements(index) != null ){
			if( this.elements(index).equalTo(key) )
				return Some(this.elements(index).value)
			else{
				index = locate(index,key)
				if( index > -1 )
					return Some(this.elements(index).value)
				else
					return None
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
		var index = this.default_hash(key) % max
		if( max > 0 && index > -1 && index < max && this.elements(index) != null ){
			if( this.elements(index).equalTo(key) )
				this.elements(index) = null
			else{
				index = locate(index, key)
				if( index > -1 )
					this.elements(index) = null
			}
		}	
	}

}