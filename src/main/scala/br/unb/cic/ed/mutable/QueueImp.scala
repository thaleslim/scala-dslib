package br.unb.cic.ed.mutable

/**
  * Uma especificação do tipo fila
  * usando a construção trait da linguagem
  * Scala.
  * 
  * @author mekamdan
  */

class QueueImp[T <% Comparable[T]] (private val elements: List[T]) extends Queue[T]{
    private var _size = 0;
    private var _front = 0;
    
    def enqueue(value: T): Unit = {
        elements.insert (_size, value)
        _size += 1
    }

    def dequeue(): Option[T] = {
        val first = elements.elementAt(_front)
        first match{
            case Some(v) =>{
                elements.remove(_front)
                _size -= 1
            }
            case None => {
                _front = 0;
                _size = 0;
            }
        }
        return first
    }
    
    def First() : Option[T] = elements.elementAt(_front)

    def size() : Int = _size

}