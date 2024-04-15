package lectures.functional

import scala.util.Random

object Options extends App {
  // invention of null pointer

  val string: String = null
  // println(string.length)
  // function calls on null objects result in NPEs and app crashes

  if(string != null) // working with null values leads to spaghetti code
    println(string.length)

  /*
    Meet Options
      An option is a wrapper for a value that might be present or not
  */

  sealed abstract class Option[+A]
  case class Some[+A](x: A) extends Option[A]
  case object None extends Option[Nothing]

  // Some warps a concrete value
  // None is a singleton for absent values

  // Options are present in many places:
  val map = Map("key" -> "value")
  map.get("key")        // Some(value)
  map.get("other")      // None
  // map uses options on its basic get operation; prefer it over apply

  val numbers = List(1,2,3)
  numbers.headOption       // Some(1)
  numbers.find(_ % 2 == 0) // Some(2)

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None
  println(myFirstOption)

  // WORK with unsafe APIs
  def unsafeMethod(): String = null
  // val result = Some(unsafeMethod) // WRONG - Some should have a valid value

  val result = Option(unsafeMethod()) // Some of None
  println(result)
  // ** should never do the NULL checks on ourselves - Option will check that **


  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  // val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // function on Option
  // println(myFirstOption.isEmpty)
  // println(myFirstOption.get) // UNSAFE - DO NOT USE THIS - Breaks option idea


  // map, flatmap, filter
  // println(myFirstOption.map(_ * 2))
  // println(myFirstOption.filter(x => x > 10))
  // println(myFirstOption.flatmap(x => Option(x * 10))


  // for-comprehensions

  /*
    Exercise
  */

  val config : Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  /*
    if (h != null)
      if(p != null)
        return Connection(h, p)

    return null
  */
  //  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))

  /*
      if (c != null)
        return c.connect
      return null
  */
  //  val connectionStatus = connection.map(c => c.connect)

  /*
     if (connectionStatus == null) println(None) else print (Some(connectionStatus.get))
  */
  //  println(connectionStatus)

  /*
     if (status != null)
        println(status)
  */
  //  connectionStatus.foreach(println)

  // chained calls
  //  config.get("host")
  //    .flatMap(host => config.get("port")
  //      .flatMap(port => Connection(host, port)
  //        .map(connection => connection.connect))
  //      .foreach(println))

  // for-comprehensions
  //  val forConnectionStatus = for {
  //    host <- config.get("host")
  //    port <- config.get("port")
  //    connection <- Connection(host, port)
  //  } yield connection.connect
  //
  //  forConnectionStatus.foreach(println)
}

/*
    Wrapping Up

    Use Options to stay away from the Boogeyman:
    - avoid runtime crashes due to NPEs
    - avoid an endless amount of null-related assertions

    A functional way of dealing with absence
    - map, filter, flatmap
    - orElse
    - others: fold, collect, toList

   if you design a method to return a (some type) but may return null,
   return an Option[that type] instead
*/
