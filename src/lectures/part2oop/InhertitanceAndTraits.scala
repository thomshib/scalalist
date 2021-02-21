package org.shibut
package lectures.part2oop

object InhertitanceAndTraits  extends  App{

  // single class inheritance

   sealed class Animal {

    val creatureType = "wild"
     def eat = println("eats...")
  }



  // constructors
  // inheritance forces base class constructors to be called first
  // ok with both primary and auxiallry constructor


  class Person(name: String, age: Int){

    //auxillary constructor
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends  Person(name)


  // overriding
  // can override methods and fields

  class Dog extends Animal{

    override val creatureType: String = "domestic"
    override def eat: Unit = println("crunch crunch")
  }


  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  // override fields directly on the constructor

  class Cat(override val creatureType: String) extends  Animal{
    override def eat: Unit = {

        super.eat
        println("crunchy crunchy")
      }
  }

  val cat = new Cat("wild cats")
  println(cat.creatureType)


  // type substitution aka polymorphism
  // will invoke the most derived method

  val unknownAnimal: Animal = new Cat("wild cats")
  unknownAnimal.eat


  // super

  // preventing overrides
  // 1 - final on the member
  // 2 - final on the class
  // 3 - sealed on the class - allows extends in THIS FILE; prevents extends i OTHER FILES






}
