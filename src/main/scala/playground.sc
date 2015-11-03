case class People(age:Int)

val p1 = new People(1)
val p2 = new People(2)
val p3 = new People(3)

val a = Vector(p1, p3, p2)
a.sortBy(a => a.age)
var b = Set((1,2))
b
b += ((2, 2))
b
b.size

(math floor 1.23456789 * 100) / 100