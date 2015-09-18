import breeze.linalg.{DenseVector, DenseMatrix}

val a = Vector[Double](0, 2, 2)
val b = DenseVector[Double](0, 1, 2, 3, 4)
b :*= 2.0
b - b
val c = DenseMatrix.zeros[Double](5, 5)
a.zipWithIndex.foreach{case(x, i) => println(i)}
val d = c.copy
d(0, 0) = 1
d
c