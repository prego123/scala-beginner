package lectures.functional

import scala.util.Random

object Sequence extends App {
  // Seq

  //  trait Seq[+A] {
  //    def head: A
  //    def tail: Seq[A]
  //  }

  /*
    A (very) general interface for data structures that
      - have a well defined order
      - can be indexed


    Supports various operations
      - apply, iterator, length, reverse for indexing and iterating
      - concatenation, appending, prepending
      - a lot of others: grouping, sorting, zipping, searching, slicing
  */

  val aSequence = Seq(1,3,4,2) // output is a list
  println(aSequence)
  // companion object actually has an apply factory method that can
  // construct subclasses of sequence
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted) // sorted method works if the type is by default ordered

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println(("Hello")))


  /*
    List

    sealed abstract class List[+A]
    case object Nil extends List[Nothing]
    case class ::[A](val hd: A, val t1: List[A]) extends List[A]

    A LinearSeq immutable linked list
     - head, tail, isEmpty methods are fast: O(1)
     - most operations are O(n): length, reverse


    Sealed - has two subtypes:
     - object Nil (empty)
     - class ::
  */
  val aList = List(1,2,3)

  val prepended = 42 :: aList
  println(prepended)

  val prependedPlus = 42 +: aList :+ 89
  println(prependedPlus)
  // : is always on the side of the list

  val apples5 = List.fill(5)("apples") // curried function
  println(apples5)
  println(aList.mkString("-|-")) // concatenate between elements in the list

  /*
     Array

     final class Array[T]
        extends java.io.Serializable
        with java.lang.Cloneable


     The equivalent of simple Java arrays
     - can be manually constructed with predefined lengths
     - can be mutated (updated in place)
     - are interoperable with Java's T[] arrays
     - indexing is fast

     Where's the Seq?!*
  */

  val numbers = Array(1,2,3,4)

  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println) // for all number type values initialized with 0

  val threeElementsString = Array.ofDim[String](3)
  threeElementsString.foreach(println) // for the reference types initialized with null

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  // update is used for mutable corrections and special and it allows for this kind of syntactic sugar
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // Implicit conversion - advanced topic
  println(numbersSeq)


  /*
      Vector

      final class Vector[+A]

      the default implementation for immutable sequences
      - effectively constant indexed read and write: O(log32(n))
      - fast element addition: append/prepend
      - implemented as a fixed-branched trie (branch factor 32) - so it holds 32 elements at any one level
      - good performance for large sizes
  */

  val noElements = Vector.empty
  val nums = noElements :+ 1 :+ 2 :+ 3
  println(nums)

  val modified = nums updated(0, 7)
  println(modified)

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)


  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // advantage - List has the property that it saves the reference to the tail
  // 1) so if it tries to update the first element - the list is incredibly efficient

  // 2) disadvantage - if try to update an element in the middle of the list - not efficient
  println(getWriteTime(numbersList))

  //  Vector needs to traverse the whole 32 branch tree and then replace that entire chunk
  // advantage - depth of the tree is small
  // disadvantage - needs to replace entire 32 element chunk
  println(getWriteTime(numbersVector))
}
