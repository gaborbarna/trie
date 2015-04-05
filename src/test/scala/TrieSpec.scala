package com.lesbroot.trie

import org.scalatest._


class TrieSpec extends FlatSpec with Matchers {

  it should "get an empty trie" in {
    Trie.getEmpty[Char, String] should be (Trie(None, Map[Char, Trie[Char, String]]()))
  }

  it should "add one element to the trie" in {
    val trie = Trie.insert(Trie.getEmpty[Char, String], "asd".toSeq, "qwe")
    Trie.find(trie, "asd".toSeq) should be (Some("qwe"))
  }

  it should "find no element on a given path" in {
    val trie = Trie.getEmpty[Char, String]
    Trie.find(trie, "asd".toSeq) should be (None)
  }

  it should "add two elements to the trie w/ an overlapping path" in {
    val elems = Seq("asd".toSeq -> "qwe", "as".toSeq -> "foo")
    val trie = Trie.insertAll(Trie.getEmpty[Char, String], elems)
    Trie.find(trie, "asd".toSeq) should be (Some("qwe"))
    Trie.find(trie, "as".toSeq) should be (Some("foo"))
  }

  it should "add two elements to the trie w/ an overlapping path (reversed order)" in {
    val elems = Seq("as".toSeq -> "foo", "asd".toSeq -> "qwe")
    val trie = Trie.insertAll(Trie.getEmpty[Char, String], elems)
    Trie.find(trie, "asd".toSeq) should be (Some("qwe"))
    Trie.find(trie, "as".toSeq) should be (Some("foo"))
  }

  it should "add two elements to the trie w/ a non-overlapping path" in {
    val elems = Seq("asd".toSeq -> "qwe", "aqwe".toSeq -> "foo")
    val trie = Trie.insertAll(Trie.getEmpty[Char, String], elems)
    Trie.find(trie, "asd".toSeq) should be (Some("qwe"))
    Trie.find(trie, "aqwe".toSeq) should be (Some("foo"))
  }
}
