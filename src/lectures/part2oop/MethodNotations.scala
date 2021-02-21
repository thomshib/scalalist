package org.shibut
package lectures.part2oop
import scala.language.postfixOps

object MethodNotations extends  App{
  class Person(val name: String, favouriteMovie: String, age: Int = 0){
    def likes(movie:String):Boolean =  movie == favouriteMovie
    def hangOutWith(other: Person):String = s"${this.name} hangs out with ${other.name}"
    def +(other: Person):String = s"${this.name} hangs out with ${other.name}"


    def unary_! : String = s"${this.name} what the heck!"
    def isAlive : Boolean = true
    def apply() :String = s"Hi my name is $name and I like $favouriteMovie an my age is $age"

    def +(newName: String) :Person = {
      new Person(s"$name ($newName)", favouriteMovie)
    }

    def unary_+ : Person = new Person(name, favouriteMovie , age + 1)

    def learns(input: String) :String = s"$name learns $input"

    def learnsScala() =  this learns "Scala"

    def apply(input: Int) = s"$name watched $favouriteMovie $input times"


  }

  val mary = new Person("Mary","Inception")
  println(mary.likes("Inception"))

  // infix notation = operator notation
  // works for methods with one parameter
  println(mary likes "Inception")

  // "operators"  in Scala

  val tom = new Person("Tom", "Flight Club")

  println(mary hangOutWith tom)

  println(mary + tom)

  println(mary.+(tom))

  // ALL OPERATORS are METHODS

  println( 1 + 2)
  println( 1.+(2))


  // prefix notations
  // for unary operators
  val x = -1    // equivalent to 1.unary_-
  val y = 1.unary_-

  // unary_ prefix operator works with + - ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  // methods that do not take any parameters

  println(mary.isAlive)
  println(mary isAlive)


  // special method "apply"
  println(mary.apply())
  println(mary()) // equivalent; when an object get called as a method; compiler invokes the object's apply method


  //exercise
  val maryClone = mary.+("the rockstar")
  println(maryClone())

  val maryAged = +mary
  println(maryAged())

  println(mary learnsScala)
  println(mary(4))







}
