package lectures.patternMatching

import caseClassesExercise.{Empty, MyListCC, Cons}

object AllThePatterns extends App {
  /*
    // 1 - constants
    val x: Any = "Scala"
    val constants = x match {
      case 1 => "a number"
      case "Scala" => "THE SCALA"
      case true => "The Truth"
      case AllThePatterns => "A singleton object"
    }

    // 2 - match anything
    // 2.1 wildcard

    val matchAnything = x match {
      case _ =>
    }

    // 2.2 variable
    val matchVariable = x match {
      case something => s"I've found $something"
    }

    // 3 - tuples
    val aTuple = (1,2)
    val matchATuple = aTuple match {
      case (1, 1) =>
      case (something, 2) => s"I've found $something"
    }

    val nestedTuple = (1, (2, 3))
    val matchANestedTuple = nestedTuple match {
      case (_ , (2, v)) =>
    }
    // PMs can be NESTED!

    // 4 - case classes - constructor pattern
    // PMs can be NESTED with CCs as well
    val aList: MyListCC[Int] = Cons(1, Cons(2, Empty))
    val matchAList = aList match {
      case Empty =>
      case Cons(head, Cons(subHead, subTail)) =>
    }

    // 5 - list patterns
    val aStandardList = List(1,2,3,42)
    val standardListMatching = aStandardList match {
      case List(1, _, _, _) => // extractor for a list - advanced
      case List(1, _*) => // list of arbitrary length
      case 1 :: List(_) => // infix pattern
      case List(1,2,3) :: 42 => //infix pattern
    }

    // 6 - types specifiers
    val unknown : Any = 2
    val unknownMatch = unknown match {
      case list: List[Int] => // explicit type specifier
      case _ =>
    }

    // 7 - name binding
    val nameBindingMatch = aList match {
      case nonEmptyList @ Cons(_, _) => // name bonding => use the name later(here)
      case Cons(1, rest @ Cons(2, _)) => // name binding inside patterns
    }

    // 8 - multi-patterns
    val multipattern = aList match {
      case Empty | Cons(0, _) => // compound pattern (multi-pattern)
    }

    // 9 - if guards
    val secondElementSpecial = aList match {
      case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
    }
  */

  // All.


  /*
      Question
  */
  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => " a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch) // ?! TYPE ERASURE
  // JVM trick question ----- JVM was designed in this way Scala has no fault of its
  // as JVM is oblivious of the generics as generics was introduced in JAVA 5
  // so in here numbers is a List and in case matching it matches the list and not the type
}
