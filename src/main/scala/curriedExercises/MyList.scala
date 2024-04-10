package curriedExercises

import lectures.oops.Generics.MyList

// linked list implementation
abstract class MyListLambda[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(Int) = new list with this element added
    toString = a string representation of the list
  */
  def head: A
  def tail: MyListLambda[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyListLambda[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "["+ printElements + "]"

  // higher order function ----- either receive functions as parameters or return other functions as a result
  def map[B](transformer: A => B): MyListLambda[B]
  def filter(predicate: A => Boolean): MyListLambda[A]
  def flatMap[B](transformer: A => MyListLambda[B]): MyListLambda[B]

  def ++[B >: A](list: MyListLambda[B]): MyListLambda[B]

  // HOFs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyListLambda[A]
  def zipWith[B, C](list: MyListLambda[B], f: (A, B) => C): MyListLambda[C]
  def fold[B](start: B)(f: (B, A) => B): B
}

object Empty extends MyListLambda[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListLambda[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListLambda[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyListLambda[B] = Empty

  def filter(predicate: Nothing => Boolean): MyListLambda[Nothing] = Empty

  def flatMap[B](transformer: Nothing => MyListLambda[B]): MyListLambda[B] = Empty

  def ++[B >: Nothing](list: MyListLambda[B]): MyListLambda[B] = list

  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyListLambda[Nothing] = Empty

  def zipWith[B, C](list: MyListLambda[B], f: (Nothing, B) => C): MyListLambda[C] = {
    if(!list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else Empty
  }

  def fold[B](start: B)(f: (B, Nothing) => B): B = start
}

class Cons[+A](h: A, t: MyListLambda[A]) extends MyListLambda[A] {
  def head: A = h

  def tail: MyListLambda[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListLambda[B] = new Cons(element, this)

  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements

  /*
      [1,2,3].filter(n % 2 == 0)
        = [2,3].filter(n % 2 == 0)
        = new Cons(2, [3].filter(n % 2 == 0))
        = new Cons(2, Empty.filter(n % 2 == 0))
        = new Cons(2, Empty)
  */
  def filter(predicate: A => Boolean): MyListLambda[A] =
    if(predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2)
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty)))
  */
  def map[B](transformer: A => B): MyListLambda[B] =
    new Cons(transformer(h), t.map(transformer))

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
  */
  def ++[B >: A](list: MyListLambda[B]): MyListLambda[B] = new Cons(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
  */
  def flatMap[B](transformer: A => MyListLambda[B]): MyListLambda[B] =
    transformer(h) ++ t.flatMap(transformer)


  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyListLambda[A] = {
    def insert(x: A, sortedList: MyListLambda[A]): MyListLambda[A] = {
      if(sortedList.isEmpty) new Cons(x, Empty)
      else if(compare(x, sortedList.head) < 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortTail = t.sort(compare)
    insert(h, sortTail)
  }

  def zipWith[B, C](list: MyListLambda[B], f: (A, B) => C): MyListLambda[C] = {
    if(list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else new Cons(f(h, list.head), t.zipWith(list.tail, f))
  }

  /*
     [1,2,3].fold(0)(+) =
     = [2,3].fold(1)(+)
     = [3].fold(3)(+)
     = [].fold(6)(+)
     = 6
  */
  def fold[B](start: B)(f: (B, A) => B): B = {
    t.fold(f(start, h))(f)
  }
}

object ListTest extends App {
  //  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  //  println(list.head)
  //  println(list.add(4).head)
  //  println(list.isEmpty)
  //
  //  println(list.toString)

  val listOfIntegers: MyListLambda[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyListLambda[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyListLambda[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map((elem: Int) => elem * 2).toString)
  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers.filter((elem: Int) => elem % 2 == 0).toString)
  println(listOfIntegers.filter(_ % 2 == 0).toString)

  println(listOfIntegers ++ anotherListOfIntegers)
  println(listOfIntegers.flatMap((elem: Int) => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  // HOFs
  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x, y) => y - x))
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)(_ + _))
}

// Scala offers class-based inheritance
// - access modifiers: private, protected, default(none=public)
// - need to pass in constructor arguments to parent class

// Derived classes can override members or methods
// Reuse parent fields/methods with super
// Prevent inheritance with final and sealed
// abstract classes
// traits
// Inheriting from a class and multiple traits



/*
  1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
  2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
  3. MyListLambda:
    - map(transformer) => MyListLambda
    - filter(transformer) => MyListLambda
    - flatMap(transformer from A to MyListLambda[B]) => MyListLambda[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) = [2,4,6]
    [1,2,3,4].filter(n % 2) = [2,4]
    [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
*/


//trait MyPredicate[-T] { // T => Boolean
//  def test(A: T): Boolean
//}
//
//trait MyTransformer[-A, B] { // A => B
//  def transform(elem: A): B
//}

