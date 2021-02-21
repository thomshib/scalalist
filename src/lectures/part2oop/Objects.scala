package org.shibut
package lectures.part2oop

object Objects  extends  App{

  // SCALA DOES NOT HAVE STATIC aka CLASS LEVEL FUNCTIONALITY


  // Here is scala version of static class


  object Person {

    // "STATIC" - "CLASS LEVEL" / Functionality
    val N_EYES = 2


    def canFly:Boolean = false


    //factory method
    def apply(father: Person, mother: Person): Person = new Person("Child Bobby")
  }

  class Person(val name:String){
    // Instance level functionality

  }

  //COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // scala object is a Singleton instance
  val person1 = Person
  val person2 = Person
  println( person1 == person2)

  // scala creating class instances

  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val bobby = Person(john,mary)

}
