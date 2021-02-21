package org.shibut
package lectures.part2oop

object AbstractDataTypes extends  App{

  // abstract

  abstract  class Animal{
    val creatureType:String
    def eat: Unit
  }

  class Dog  extends  Animal{
    override val creatureType: String = "Canine"

    override def eat: Unit = println("crunch crunch")
  }

  // traits

  trait Carnivore{
    def eat(animal: Animal): Unit
  }

  class Crocodile extends  Animal with Carnivore{
    override val creatureType: String = "Croc"

    override def eat: Unit = println("nomnomnom")

    override def eat(animal: Animal): Unit = println(s"I'm a croc and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // trait vs abstract classes
  // 1 - both can have abstract and non-abstract field and methods
  // 2 - traits do not constructor
  // 3 - multiple traits inheritance is allowed
  // 4 - traits == behaviour; abstract classes == things

}
