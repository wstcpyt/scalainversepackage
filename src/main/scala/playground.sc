import breeze.linalg.DenseMatrix

val a = DenseMatrix.zeros[Double](5, 5)
val b = a.map{
  a => a + 1.0
}

val d = a.pairs.map(
  v => {
    val t = v._1._1 + 0.5
    val s = v._1._2 + 0.5
    v._2 + t + s
  }
)

d