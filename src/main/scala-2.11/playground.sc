def testFunction(people: Vector[Double]) = {
  val people1 = people.updated(0, 2.0)
  people1
}

val people = Vector(1.0, 2.0)
val people1 = testFunction(people)
people1