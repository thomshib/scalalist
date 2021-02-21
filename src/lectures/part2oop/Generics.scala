package org.shibut
package lectures.part2oop

object Generics  extends  App{

  class MyList[+A] {

    // use the type A
    // this is not possible
    // def add(element: A): MyList[A] = ???

    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Dog ; Dog is an animal; hence B will animal
      hence the list will turn into a list of animal, if we add a animal


     */
  }

  object MyList{
    def empty[A] : MyList[A] = ???
  }

  val listOfInts = new MyList[Int]

  val listOfStrings = MyList.empty[String]


  // variance problem
  class Animal
  class Dog extends  Animal
  class Cat extends  Animal

  // Question: if dog and cat extends Animal, can a List of Dogs or a List of Cats extend List of Animal

  // 1. Yes ;  List[Cat] extends List[Animal] == COVARIANCE ; substitute one type for another
  class CovariantList[+A]   // + stands for covariance
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // what happens if we do this; animalList.Add(new Dog) // HARD Question; it may pollute the list of cats with a dog
  // HARD Question => we return a  super type; i.e the list will be a list of Animals

  // 2. No ; INVARIANCE ; no substitution every type lives its own world
  class InvariantList[A]  // invarianct list
  // you cannot substitute list of animals with list of dogs; for eg. below is not possible
  // val invariantList:InvariantList[Animal] = new InvariantList[Dog]


  // 3. No; Contravariance; opposite of covariance ; substitute one type for another

  class ContravarianceList[-A] // - stands for contravariance
  val listContravarianceList: ContravarianceList[Cat] = new ContravarianceList[Animal]

  // the above eg makes no sense

  // this example makes sense

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]



  // bounded types - restricts types to subclasses or super class
  // allow subtypes <:
  // allows subtypes of animal
  class Cage[A <: Animal](animal: Animal)

  val cage = new Cage(new Dog)





}
