package br.unb.cic.ed.mutable

/**
  * The ArrayHashMap' abstract element, a representation
  * that allows it's bijective behaviour.
  *
    This' behaviour could be described as: Every key,
  * if previously inserted, will have one, and only one,
  * value associated.
  *
    @see [[ArrayHashMap]]
  * @author rbonifacio / thaleslim 
  */

case class HashMapElement[A <: Comparable[A], B](var key: A, var value: B){
    /** Compares if a key is associated with this.value */
    def equalTo (key: A): Boolean = if( key.compareTo(this.key) == 0 ) true else false
}

/**
  * A trait HashMap' implementation using 
  * sequential allocation (Array).
  *
  * @author thaleslim
  */

class ArrayHashMap[A <: Comparable[A], B: Manifest](private val max: Int = 10) extends HashMap[A,B]{

    private var elements = Array.ofDim[HashMapElement[A,B]](max)
    private var default_hash: A => Int = ( _.hashCode() )

    /** 
      * Uses a quadratic function to generate a index and checks
      * if inside this elements array at index position has a 
      * empty space.
      * @return free space' index or, if doesn't find any, -1 
      */
    private def sondagem_quadratica(start: Int): Int = {
        var index = start
        var contador = 1
        
        while( index < max && this.elements(index) != null )
            index = index + 2 * contador + 5 * contador * contador
        
        if( index < max && this.elements(index) == null ) index else -1
    }
    /** 
      * Runs through every index of this elements array, 
      * searching for a empty space.
      * @return free space' index or, if doesn't find any, -1
      */
    private def sondagem_linear(start: Int): Int = {
        var index = start
        var found: Boolean = false

        var break: Boolean = false
        while (!break && !found){
            index += 1

            if( !(index < this.max) && start != 0 )
                index = 0
            else if( this.elements(index) == null )
                found = true
            else if( index == start )
                break = true
        }
        
        if( found ) return index else return -1
    }
    /**
      * Selects a valid position for inserting a value:
      *
        - First Step: attemps using the hash method to find the index,
      * if that fails in pointing to a empty space proceeds;
      *
        - Second Step: attemps to use the index generated in the last
      * step with a quadratic function, that generates some empty
      * gaps between the values, but if that fails as a last resort
      * we proceed to the last step;
      *
        - Third Step: Runs through the entire array searching for
      * a empty space.
      *
      * @return free space' index or, if doesn't find any, -1 
      */
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
    /**
      * Inserts a new value.
      * @throws InvalidArgument if this has reached it's storage limit.
      */
    private def insert(key: A, value: B): Unit = {
        var index = get_position(key)
        if( index >= 0 )
            this.elements(index) = HashMapElement[A,B](key,value)
        else throw br.unb.cic.ed.mutable.InvalidArgument("Maximum Capacity Reached")
    }
    /** 
      * Helps this.search to locate a value in this. Usefull in cases where's a collision.
      *
      * @param start Usually the result from the hashing method, it's used as the
      * starting position for the search.
      * @param key The acess key to the value being searched.
      * @return value' index or -1 if doesn't find the corresponding value.
      */
    private def locate(start: Int, key: A): Int = {
        var index = start
        var found: Boolean = false

        var break: Boolean = false
        while( !break && !found ){
            index += 1
            
            if( !(index < max) && start != 0 )
                index = 0
            else if( index == start )
                break = true
            else if( this.elements(index) != null && this.elements(index).equalTo(key) )
                found = true

        }

        if( found ) return index else return -1
    }
    /** 
      * Searches for the values based on their respective keys.
      * @return None if doesn't find the corresponding value.
      */
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

    def - (keys: A*): Unit = {
        if(!keys.isEmpty){
            this.-(keys.head)
            this.-(keys.tail: _*)
        }
    }

}
