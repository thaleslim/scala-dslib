package br.unb.cic.ed

/**
  * Uma implementacao do tipo pilha usando
  * alocacao sequencial (um array de elementos).
  *
  * @author: thaleslim
  */
class ArrayStack(private val max: Int = 10) extends Stack {

    private var _size = 0
    private var elements = new br.unb.cic.ed.ArrayList(max)

    def push(value: Int): Unit = {
        if(_size < max){
            elements.insert(_size,value)
            _size += 1
        }
        else throw InvalidArgument("the stack is full")

    }

    def pop(): Option[Int] = {
        if(_size > 0){
            var result = elements.elementAt(_size-1)
            elements.remove(_size-1)
            _size -= 1
            return result
        }
        else
            return None
    }

    def size() : Int = _size
}
