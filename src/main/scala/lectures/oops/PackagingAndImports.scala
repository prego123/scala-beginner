package lectures.oops

import playground.{Cindrella => Princess, PrinceCharming}
// import playground._//{Cindrella, PrinceCharming}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports {

  // package is basically just a bunch of definitions grouped under the same name
  // in 99% of the time  --- directory structure

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2024)

  // import the package
  // val princess = new Cindrella
  val princess = new Princess
  // val princess = new playground.Cindrella = fully qualified name

  // packages are ordered hierarchy
  // matching folder structure

  // package object
  sayHello
  println(speed_of_light)

  // imports
  val prince = new PrinceCharming

  // 1. use fully qualified name
  val date = new Date
  val sqlDate = new java.sql.Date(2024, 5, 4)
  val sqlDateCheck = new SqlDate(2024, 5, 4)
  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.PreDef - println, ???
}


/*
  Takeaways

    package = a group of definitions under the same name

    To use a definition
    - be in the same package
    - or import the package

    Best practice - mirror the file structure

    // Person.scala
    package org.rtjvm.oop
    class Person
    object Person

    Fully qualified name

    package objects hold standalone methods/constants
    - one per package

    Name aliasing at imports
    import java.sql.{ Date => SqlDate }
    import java.util.{ Date => JavaDate }
    import java.{ util => ju }
*/
