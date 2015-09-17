import breeze.linalg.{DenseVector, DenseMatrix}

val a = Vector[Double](0, 2, 2)
val b = DenseVector[Double](0, 1, 2, 3, 4)

a.zipWithIndex.foreach{case(x, i) => println(i)}
