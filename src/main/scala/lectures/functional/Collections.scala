package lectures.functional

object Collections {
  /*
    Overview

      Scala offers both mutable and immutable collections:
      1) mutable collections can be updated in place
      2) immutable collections never change

      we are using the immutable collections by default
      package object Scala {
        type List[+A] = immutable.List[A]
      }

      object Predef {
        type Map[A, +B] = immutable.Map[A, B]
        type Set[A] = immutable.Set[A]
      }

      Immutable Collections
        Immutable collections are found in scala.collections.immutable package

                            Traversable
                                 ^
      HashSet                    |                         HashMap
                    Set ----- Iterable (trait) ----- Map
      SortedSet                  ^                         SortedMap
                                 |
                                Seq
                  IndexedSeq           LinearSeq (ordered)
                    Vector                List
                    String                Stream
                    Range                 Stack
                                          Queue


     Mutable Collections
      Mutable collections are found in scala.collections.mutable.package

                              Traversable
                                   ^
        HashSet                    |                         HashMap
                      Set ----- Iterable (trait) ----- Map
        LinkedHashSet              ^                         MultiMap
                                   |
                                  Seq
           IndexedSeq             Buffer             LinearSeq (ordered)
             StringBuilder          ArrayBuffer          LinkedList
             ArrayBuffer            ListBuffer           MutableList


    Traversable
      Base trait for all collections. Offers a great variety of methods:
      1) maps:                map, flatmap, collect
      2) conversions:         toArray, toList, toSeq
      3) size info:           isEmpty, size, nonEmpty
      4) tests:               exists, forall
      5) folds:               foldLeft, foldRight, reduceLeft, reduceRight
      6) retrieval:           head, find, tail
      7) string ops:          mkString
  */
}
