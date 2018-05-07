package br.unb.cic.ed.mutable

/**
  * A Stack is a linear data structure that follows 
  * a strict order to execute its operations (Last
  * in First Out).
  *
  * @author thaleslim
  */

trait Stack[T] {
  /** 
    * Pushes a value in this
    * @example {{{
    *      var stack: Stack[Int]
    *      stack.push(5)         //stack = [5]
    *      stack.push(4)         //stack = [4,5]
    *      stack.push(3)         //stack = [3,4,5]
    * }}}
    */
  def push(value: T): Unit
  /** 
    * Pops a value from this
    * @example {{{
    *      var stack: Stack[Int]
    *      stack(5,4,3,2,1)      //stack = [1,2,3,4,5]
    *      stack.pop             //== Some(1); stack = [2,3,4,5]
    * }}}
    */
  def pop(): Option[T]
  /** 
    * Returns the value at this' top
    * @example {{{
    *      var stack: Stack[Int]
    *      stack(5,4,3,2,1)      //stack = [1,2,3,4,5]
    *      stack.top             //== Some(1); stack = [1,2,3,4,5] 
    * }}}
    */
  def top(): Option[T] 
  /** 
    * Returns the amount of values stacked
    * @example {{{
    *      var stack: Stack[Int]
    *      stack(5,4,3,2,1)      //stack = [1,2,3,4,5]
    *      stack.size            //== 5 
    * }}}
    */
  def size(): Int
  /** 
    * Alternative to using this.push
    * @example {{{
    *      var stack: Stack[Int]
    *      stack(5)              //== stack.push(5)
    * }}}
    * @see [[Stack!.push(value:T):Unit* this.push(T)]]
    */
  def apply(value: T): Unit = this.push(value)
  /** 
    * Pushes a value sequence in this
    * @example {{{
    *      var stack: Stack[Int]
    *      stack(5,4,3,2,1)      //stack = [1,2,3,4,5]
    * }}}
    * @see [[Stack!.push(value:T):Unit* this.push(T)]]
    */
  def apply(that: T*): Unit = {
    if(!that.isEmpty){
        this.push(that.head)
        this.apply(that.tail: _*)
    }
  } 
}