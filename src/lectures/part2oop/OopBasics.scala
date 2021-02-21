package org.shibut
package lectures.part2oop

import org.w3c.dom.css.Counter

object OopBasics extends  App{

  val person = new Person("John",46)
  println(person.age)
  println(person.greet("Daniel"))

  //------------------------

  val writer: Writer = new Writer("John","Doe", 1990)

  println(writer.fullName())

  val novel: Novel = new Novel("Art of War",2000,writer)

  val newNovel = novel.copy(2001)

  println(s" the novel ${novel.name} is written by ${novel.isWrittenBy(writer)}")

  val counter = new Counter
  counter.inc.print
  counter.inc(4).print




}


//constructor
class Person(name: String, val age: Int) {
  //class parameters are not FIELDS

  //to covert a class parameter as a field, add val to it


//use this to differentiate method params and class fields
  def greet(name: String) = print(s"${this.name} says Hi $name")


  //overloading
  def greet() : Unit = println(s"Hi Iam ${name}")


  //multiple constructors
def this(name: String) = this(name,0)

}

class Writer(firstName: String, lastName: String, val ageYear: Int){

  def fullName():String ={
    firstName + " " + lastName
  }


}


class Novel(val name: String, yearOfRelease: Int, author: Writer){

  def authorAge():Int ={
    yearOfRelease - author.ageYear
  }

  def isWrittenBy(author:Writer):Boolean ={

    this.author == author
  }

  def copy(releaseYear: Int):Novel ={
    new Novel(this.name, releaseYear, this.author)
  }


}

class Counter(val count: Int = 0){
  //def currentCount():Int = count

  def inc():Counter = {
    println("incrementing")
    new Counter(count + 1)
  }

  def inc(n: Int):Counter = {

    if(n <= 0) this
    else inc.inc(n-1)

  }

  def dec():Counter = {
    println("decrementing")
    new Counter(count - 1)
  }

  def dec(n: Int):Counter = {
    if( n <= 0) this
    else dec.dec(n-1)
  }

  def print = println(count)


}



