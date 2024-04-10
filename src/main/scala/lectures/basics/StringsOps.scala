package lectures.basics

object StringsOps extends App {

  val str: String = "Hello, I am learning scala"
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  var aString = "45"
  val aNumber = aString.toInt
  println('a' +: aString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // scala-specific: string interpolation
  val name = "David"
  val age = 23
  val greeting = s"Hello, my nae is $name and I am ${age+1} years old"
  println(greeting)

  // F-interpolation
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burger per minute"
  println(myth)

  // raw-interpolator
  println(raw"This is a \n newLine")
  var escaped = "This is a \n newLine"
  println(raw"$escaped")
}
