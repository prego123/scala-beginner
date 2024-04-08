package caseClassesExercise

// with case class and case object
// - implemented equals and hashcode out of the box so the list can be used in collections as well
// - my list is serializable which makes it extremely powerful to use in distributed n/ws

// linked list implementation
abstract class MyListCC[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(Int) = new list with this element added
    toString = a string representation of the list
  */
  def head: A
  def tail: MyListCC[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyListCC[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "["+ printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyListCC[B]
  def filter(predicate: MyPredicate[A]): MyListCC[A]
  def flatMap[B](transformer: MyTransformer[A, MyListCC[B]]): MyListCC[B]

  def ++[B >: A](list: MyListCC[B]): MyListCC[B]
}

case object Empty extends MyListCC[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListCC[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListCC[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyListCC[B] = Empty

  def filter(predicate: MyPredicate[Nothing]): MyListCC[Nothing] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, MyListCC[B]]): MyListCC[B] = Empty

  def ++[B >: Nothing](list: MyListCC[B]): MyListCC[B] = list
}

case class Cons[+A](h: A, t: MyListCC[A]) extends MyListCC[A] {
  def head: A = h

  def tail: MyListCC[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListCC[B] = new Cons(element, this)

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
  def filter(predicate: MyPredicate[A]): MyListCC[A] =
    if(predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2)
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty)))
  */
  def map[B](transformer: MyTransformer[A, B]): MyListCC[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
  */
  def ++[B >: A](list: MyListCC[B]): MyListCC[B] = new Cons(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
  */
  def flatMap[B](transformer: MyTransformer[A, MyListCC[B]]): MyListCC[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

object ListTest extends App {
  //  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  //  println(list.head)
  //  println(list.add(4).head)
  //  println(list.isEmpty)
  //
  //  println(list.toString)

  val listOfIntegers: MyListCC[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyListCC[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyListCC[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyListCC[String] = new Cons("Hello", new Cons("You", new Cons("Scala", Empty)))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println(listOfIntegers ++ anotherListOfIntegers)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyListCC[Int]] {
    override def transform(elem: Int): MyListCC[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).toString)


  println(listOfIntegers == cloneListOfIntegers) // true
  // makes it easier otherwise recursion has to be done for checking all the integers equality
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
  3. MyListCC:
    - map(transformer) => MyListCC
    - filter(transformer) => MyListCC
    - flatMap(transformer from A to MyListCC[B]) => MyListCC[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) = [2,4,6]
    [1,2,3,4].filter(n % 2) = [2,4]
    [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
*/


trait MyPredicate[-T] {
  def test(A: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}
