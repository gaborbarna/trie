package com.lesbroot.trie


case class Trie[T, U](value: Option[U], children: Map[T, Trie[T, U]])


object Trie {
  def find[T, U](trie: Trie[T, U], path: Seq[T]): Option[U] = path match {
    case x +: xs => for {
      child <- trie.children.get(x)
      value <- find(child, xs)
    } yield value
    case _ => trie.value
  }

  def insert[T, U](trie: Trie[T, U], path: Seq[T], value: U): Trie[T, U] = path match {
    case x +: xs => {
      val child = trie.children.getOrElse(x, Trie(None, Map[T, Trie[T, U]]()))
      Trie(trie.value, trie.children + (x -> insert(child, xs, value)))
    }
    case _ => Trie(Some(value), trie.children)
  }

  def insertAll[T, U](trie: Trie[T, U], elems: Seq[(Seq[T], U)]) = {
    elems.foldLeft(trie)({case (acc, (path, value)) => insert(acc, path, value)})
  }

  def getEmpty[T, U] = Trie[T, U](None, Map[T, Trie[T, U]]())
}
