package br.unb.cic.ed.mutable

/**Arvore binaria é uma estrutura de dados caracterizada por: Ou não tem elemento algum (árvore vazia). Ou tem um      *elemento distinto, denominado raiz, com dois apontamentos para duas estruturas diferentes, denominadas sub-árvore *esquerda e sub-árvore direita.
**/

trait BinTree[T <: Comparable[T]] {
  /**Insere um elemento na arvore Binaria**/
  def insert(value : T): Unit
  /**Checa se um elemento esta inserido na arvore binaria**/
  def exist(value: T): Boolean
}
