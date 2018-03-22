package br.unb.cic.ed

/**
  * Uma implementacao do tipo pilha usando
  * alocacao sequencial (um array de elementos).
  *
  * @author: thaleslim
  */
class StackImpl(private val elements: br.unb.cic.ed.List) extends Stack {

  private var _size = 0

  def push(value: Int): Unit = {
    elements.insert(_size, value)
    _size += 1
  }

  def pop(): Option[Int] = {
    val res = elements.elementAt(_size-1)
    res match {
      case Some(v) => {
        elements.remove(_size-1)
        _size -= 1
      }
      case None => {}
    }
    return res
  }

  def top(): Option[Int] = elements.elementAt(_size-1)

  def size() : Int = _size
}
