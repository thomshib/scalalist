package org.shibut
package exercises


/*
   extend the list with
   1. Generic Trait MyPredicate[-T] with a test method Test[T] => Boolean
   2. Generic Trait MyTransformer[-A,B] with a method transform(A) => B
   3. MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(A transformer from A to B) => MyList[B]


    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) => [2,4,6]
    [1,2,3,4].filter( n % 2 ) => [2,4]
    [1,2,3[.flatMap(n => [n, n+1] ) => [1,2,2,3,3,4]


 */

trait MyPredicate[-T]{
  def test(elem: T) : Boolean
}

trait MyTransformer[-A,B]{
  def transform(elem: A): B
}

abstract class MyList[+A] {
  /*

    head - first element of the list
    tail - remainder of the list
    isEmpty
    add(Int) - return a new list with the element added
    toString - string representation of the list


   */




  def head : A
  def tail: MyList[A]
  def isEmpty: Boolean

  /*
    this def is not posbile
    def add(element: A): MyList[A]
    throws the error
      "Covariant type A occurs in contravariant position in type A of value element"

     A = Cat
     B = Dog which in turn is an animal

     HARD question: what happens if we add a dog to a list of cats
     Answer: we convert the list to a list of animal instead of cat
     technical B = Animal

     this means that add method has to be as below
      def add[B >: A] (element: B) : MyList[B]

      where B is a supertype of A
   */

  def add[B >: A] (element: B) : MyList[B]


  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A,B]) : MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A]

  def flatMap[B](transformer: MyTransformer[A,MyList[B]]): MyList[B]

  //concatenation
  def ++[B >: A](list: MyList[B]):MyList[B]
}


// define an empty list

// any type can be replaced by Nothing
// val listOfInts: MyList[Int] = Empty
// val lisOfStrings = MyList[String] = Empty

object Empty extends MyList[Nothing]{
  def head : Nothing =  throw new NoSuchElementException
  def tail: MyList[Nothing] =  throw new NoSuchElementException
  def isEmpty: Boolean =  true
  def add[B >: Nothing](element: B) : MyList[B] =  new Cons(element,Empty)
  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing,B]):MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing,MyList[B]]):MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]):MyList[Nothing] = Empty

  //concatenate with Nothing should return the list
  def ++[B >: Nothing](list: MyList[B]):MyList[B] = list





}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A]{

  def head : A =  h
  def tail: MyList[A] =  t
  def isEmpty: Boolean =  false
  def add[B >: A] (element: B) : MyList[B] = new Cons(element, this)
  def printElements: String = {
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
  }


  /*
    [1,2,3].filter(n % 2 == 0)
      = [2,3].filter( n % 2 == 0)
      = new Cons(2, [3].filter( n % 2 == 0))
      = new Cons(2, Empty.filter( n % 2 == 0))
      = new Cons(2,Empty)
   */

  def filter(predicate: MyPredicate[A]): MyList[A] ={
    if(predicate.test(h)) new Cons(h,t.filter(predicate))
    else t.filter(predicate)
  }


  /*
    [1,2,3.map( n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6,  Empty)))

   */
  def map[B](transformer: MyTransformer[A,B]): MyList[B] ={
    // transform head first
    // transform tail with recursive call
    new Cons[B](transformer.transform(h), t.map(transformer))
  }


  /*
    [1,2] ++ [3,4,5]
              = new Cons(1, [2] ++ [3,4,5])
              = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
              = new Cons(1, new Cons(2, [3,4,5]))
              = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5,Empty))))

   */
  def ++[B >: A](list: MyList[B]):MyList[B] = new Cons(h, t ++ list)

  /*
    [1,2].flatMap( n => [n, n+1])
        = [1,2] ++ [2].flatMap( n => [n, n+1])
        = [1,2] ++ [2,3] ++ Empty.flatMap( n => [n, n+1])
        = [1,2] ++ [2,3] ++ Empty
        = [1,2,2,3]


   */
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

}

object ListTests extends  App{
  val listOfIntegers:MyList[Int] = new Cons(1,new Cons(2, new Cons(3,Empty)))

  val anotherListOfIntegers:MyList[Int] = new Cons(4,new Cons(5, new Cons(6,Empty)))

  println(listOfIntegers.head)
  println(listOfIntegers.tail.head)
  println(listOfIntegers.add(4).head)
  println(listOfIntegers.toString)

  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))


  println(listOfStrings.toString)


  // map , transform and filter

  println( listOfIntegers.map(new MyTransformer[Int,Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println( listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)


  println( listOfIntegers ++ anotherListOfIntegers)

  println( listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem , new Cons[Int](elem + 1, Empty))
  }).toString)


}
