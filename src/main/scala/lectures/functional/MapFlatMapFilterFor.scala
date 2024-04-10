package lectures.functional

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatmap
  val toPair = (x: Int) => List(x, x+1)
  println(toPair(4))
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black","white")
  // List("a1","a2","b1",....."d4")

  val combination = (x: Int) => chars.map( _ + x.toString)
  println(numbers.flatMap(combination))

  val combination2 = numbers.flatMap((x: Int) => chars.map(c => "" + c + x))
  println(combination2)

  // "iterations"
  val combination3 = numbers.flatMap((x: Int) => chars.flatMap(c => colors.map(color => "" + c + "-" + x + "-" + color)))
  println(combination3)

  // forEach
  list.foreach(println)
  // for-comprehensions
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield "" + c + "-" + n + "-" + color

  println(forCombinations)
  // filter
  val forCombinationsFilter = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + "-" + n + "-" + color

  println(forCombinationsFilter)
  // this is what it looks like
  // val combination3 = numbers.filter(_ % 2 == 0).flatMap((x: Int) => chars.flatMap(c => colors.map(color => "" + c + "-" + x + "-" + color)))

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
     1. MyList supports for comprehensions
       map(f: A => B) => MyList[B]
       filter(f: A => Boolean) => MyList[A]
       flatmap(f: A => MyList[B]) => MyList[B]

     2. A small collection of at most one element - Maybe[+T]
        - map, flatMap, filter
  */
}


