package org.shibut
package lectures.part2oop

object AnonymousClasses extends  App{

  abstract  class Animal{
    def eat: Unit
  }

  // Anonymous Class
  val funnyAnimal = new Animal{
    def eat: Unit = println("asdfasfdasdf")
  }

  /*
    class AnonymousClasses$$anon$1 extends Animal{
      def eat: Unit = println("asdfasfdasdf")

      }

      val funnyAnimal = new AnonymousClasses$$anon$1


   */

  println(funnyAnimal.getClass)


  class PersonP(val name: String){
    def sayHi: Unit = println(s" Hi , my name is $name")
  }

  val jim = new PersonP("jim"){
    override def sayHi : Unit = println(s"Hi my name is $name, How can I help you?")
  }

}
