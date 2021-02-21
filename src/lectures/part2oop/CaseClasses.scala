package org.shibut
package lectures.part2oop

object CaseClasses extends  App {


  case class Person(name: String, age: Int)

  // 1.  class parameters are fields

  val jim = new Person("jim", 34)
  println(jim.name)

  // 2. sensible toString

  println(jim.toString)

  // 3. println(instance) delegates to println(instance.toString)

  println(jim)

  // 4. hashcode and equals are implemented OOTB

  val jim2 = new Person("jim", 34)

  println(jim == jim2)

  // 5. has handy copy methods

  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 6. have companion objects

  val thePerson = Person


  // 7. factory method of Companion Objects
  // calls the apply method on companion object, whcih calls the constructor on the Person class
  // note we omit the new keyword

  val mary = Person("mary",45)

  // 8. case classes are serializable
  // helpful in serializing case classes in distributed systems like Spark / Akka / across JVMs


  // 9. case classes have extractor pattern; can be used in PATTERN matching


  // 10. u can create case objects too; only diff is case objects do not have companion objects

  case object UnitedKingdom{
    def name: String = " This is UK"
  }






}
