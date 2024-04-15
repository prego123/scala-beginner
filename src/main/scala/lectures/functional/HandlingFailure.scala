package lectures.functional

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  /*
      Let's Try[T]

      Exceptions are handled inside try-catch blocks:

      try {
        val config: Map[String, String] = loadConfig(path)
      } catch {
        case _: IOException => // handle IOException
        case _: Exception => // handle Exception
      }


      - multiple/nested try's make the code hard to follow
      - we can't chain multiple operations prone to follow


      A Try is a wrapper for a computation that might fail or not

      sealed abstract class Try[+T]
      case class Failure[+T](t: Throwable) extends Try[T]
      case class Success[+T](value: T) extends Try[T]

     - wrap failed computations
     - wrap succeeded computations
  */

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via apply mehtod
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try (
    // code that might throw
  )

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // If you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod().orElse(betterBackupMethod())

  // map, filter, flatmap
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))


  /*
      Exercise
  */

  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())

      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e., call renderHTML
  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/hime"))
  possibleHTML.foreach(renderHTML)

  // shorthand version
  HttpService.getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for-comprehension version
  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  } yield renderHTML(html)

  /*
    try {
      connection = HttpService.getConnection(host, port)
      try {
        page = connection.get("/home")
        renderHTML(page)
      } catch (some other exception) {
      }
    } catch (exception) {
    }
  */
}

/*
    Wrapping Up

    Use Try to handle exceptions gracefully
    - avoid runtime crashes due to uncaught exceptions
    - avoid an endless amount of try-catches


    A functional way of dealing with failure
    - map, flatmap, filter
    - orElse
    - others: fold, collect, toList, conversion to Options

    if you design a method to return a (some type) but may throw
    an exception, return a Try[that type] instead.
*/
