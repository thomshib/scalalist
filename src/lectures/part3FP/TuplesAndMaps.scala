package org.shibut
package lectures.part3FP

import scala.annotation.tailrec

object TuplesAndMaps extends  App{

  // tuples finite ordered lists
  val aTuple = (2," Hello Scala")
  println(aTuple._1)
  println(aTuple.swap)
  println(aTuple.copy(_2 =" goodby Java"))


  //maps  key -> value pairs
  val aMap: Map[String, Int] = Map()
  val phoneBook = Map( ("Jim", 333), "JIM" -> 999,"Mary" -> 555).withDefaultValue(-1)
  println(phoneBook)

  println(phoneBook.contains("jim"))
  println(phoneBook("Luke"))

  // add a pairing

  val newPairing = "Luke" -> 676
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // map, filter, and flatMap
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterkeys
  //println(newPhoneBook.view.filterKeys(x => x.startsWith("J")))


  /*
    Simplified social network
      Person = String
      - add a person to the network
      - remove
      - friend
      - unfriend

      - number of friends of a person
      - person with most friends
      - how manu ppl have NO friends
      - if there is a connection b/w two ppl or not (direct or not)



   */


  def add(network: Map[String,Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]]={
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b))  + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]]= {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b))  + (b -> (friendsB -  a))

  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]]= {

    // remove dangling friends links
    def removeAux(friends:Set[String],networkAccu: Map[String,Set[String]]): Map[String,Set[String]] ={
      if(friends.isEmpty) networkAccu
      else removeAux(friends.tail, unfriend(networkAccu,person, friends.head))
    }
    // unfriend the person recursively
    val undfriended = removeAux(network(person),network)
    //remove the person key
    undfriended - person

  }

  val empty: Map[String, Set[String]] = Map()
  val bob = add(empty,"Bob")
  val mary = add(empty,"Mary")
  val network =  add(add(empty,"Bob"), "Mary")
  println(network)
  println(friend(network,"Bob","Mary"))
  println(unfriend(friend(network,"Bob","Mary"),"Mary","Bob"))
  println(remove(friend(network,"Bob","Mary"),"Bob"))

  // jim bob mary

  val people = add(add(add(empty,"Jim"),"Bob"),"Mary")
  val jimbob = friend(people,"Bob","Jim")
  val testNet  =  friend(jimbob,"Bob","Mary")
  println(testNet)

  def nFriends(network: Map[String, Set[String]], person:String): Int ={
    if(!network.contains(person)) 0
    else network(person).size
  }


  println(nFriends(testNet,"Bob"))

  def mostFriends(network: Map[String, Set[String]] ): String = {
    network.maxBy(pair => pair._2.size)._1

  }

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String,Set[String]]): Int =
    network.view.filterKeys(k => network(k).size == 0).size

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]] , a: String, b: String): Boolean = {
  {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople:Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else{
        val person = discoveredPeople.head
        if(target == person) true
        else if(consideredPeople.contains(person)) bfs(target,consideredPeople, discoveredPeople.tail )
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))

      }
    }

    bfs(b,Set(), network(a) + a)
  }


  }

  println(socialConnection(testNet,"Mary","Jim"))

}
