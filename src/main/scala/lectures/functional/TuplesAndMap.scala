package lectures.functional

object TuplesAndMap extends App {
  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello Scala") // Tuple2[Int, String] = (Int, String)
  val bTuple = Tuple2(2, "Hello Scala") // Tuple2[Int, String] = (Int, String)
  val cTuple = (2, "Hello Scala") // Tuple2[Int, String] = (Int, String)

  // can group upto 22 elements of different types
  // because they are used in conjunction with function types

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("Hello Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), ("Daniel", 789))
  val phonebookS = Map("Jim" -> 555, ("Daniel", 789)) // syntactic sugar
  val phonebookError = Map("Jim" -> 555, ("Daniel", 789)).withDefaultValue(-1) // syntactic sugar
  // a -> b is sugar for (a,b)
  println(phonebook)

  // Map operations
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  //println(phonebook("Mary")) // throw error since does not exits
  println(phonebookError("Mary")) // gives -1


  // Add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing // maps are immutable
  println(newPhonebook)

  // Functions on maps
  // map, flatmap, filter

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J")))

  // mapValues
  println(phonebook.mapValues(num => num * 10))
  println(phonebook.mapValues(num => "0245-" + num))

  // Conversions
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  // Exercise
  // 1.
  val a = Map("Jim" -> 555, "JIM" -> 678)
  println(a)
  println(a.map(pair => pair._1.toLowerCase -> pair._2)) // since they overlap -- resulting keys do not overlap

  // 2.
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  def friend(network: Map[String, Set[String]], person: String, friend: String): Map[String, Set[String]] = {
    val friendA = network(person)
    val friendB = network(friend)

    network + (person -> (friendA + friend)) + (friend -> (friendB + person))
  }

  def unfriend(network: Map[String, Set[String]], person: String, friend: String): Map[String, Set[String]] = {
    val friendA = network(person).filter(x => x != friend)
    val friendB = network(friend).filter(x => x != person)

    network + (person -> (friendA - friend)) + (friend -> (friendB - person))
    network + (person -> friendA) + (friend -> friendB)
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(network, "Bob", "Mary"))
  println(remove(network, "Bob"))


  // Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if(!network.contains(person)) 0
    else network(person).size
  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1
  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.filter(pair => pair._2.isEmpty).size
    network.count(pair => pair._2.isEmpty) // same
    network.count(_._2.isEmpty) // same
  }
  println(nPeopleWithNoFriends(testNet))

  def socialConnections(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if(person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }
  println(socialConnections(testNet, " Mary", "Jim"))
  println(socialConnections(network, "Mary", "Bob"))
}

/*
  Takeaways

  Tuples

      val tuple = (42, "Jim")
      tuple._1                    // 42  ---- retrieve elements using _n
      tuple.copy(_1 = 0)          // (0, Jim)  -----  create new tuples
      tuple.toString              // "(42, Jim)" ----  pretty print
      tuple.swap                  // (Jim, 42)



  Maps

      val phonebook = Map("Jim" -> 555, "Mary" -> 789)
      phonebook.contains("Jim")
      val anotherBook = phonebook + ("Daniel", 123)



  Functions:

      - filterKeys, mapValues
      - map, flatmap, filter (on pairings)


  To/from other collections

      - .toList, .toMap
      - .groupBy
*/


/*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 90 and ran line 40?
    2. Overly simplified social network based on maps
       Person = String
       - add a person to the network
       - remove
       - friend (mutual)
       - unfriend

       - number of friends of a given person
       - person with most friends
       - how many people have no friends
       - if there is a social connections between two people (direct or not)
*/
