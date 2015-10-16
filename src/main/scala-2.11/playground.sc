case class People(age:Int)

val a = Array.ofDim[People](2, 2)
a(0)(0) = new People(1)

a(0)(0)

var c = "Hello"
c = c + "World"
c