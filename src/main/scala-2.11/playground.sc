import java.io.PrintWriter
import Math._
import breeze.linalg.{DenseVector, DenseMatrix}
import breeze.storage.Zero

import scala.reflect.ClassTag

val a = DenseVector.zeros[Double](5)
a(1 to 3)


val m = 256
val n = 256

val X = DenseMatrix.zeros[Double](m, n)
val I = m/5 to 3*m/5
val J = n/5 to 3*n/5
X(I, J) := 0.5
X.foreachPair{
  case ((i,j), v)=> if (pow(i -3 * m / 5, 2.0) + pow(j - 5 * n / 8 , 2.0) < pow(m/5, 2.0)) X(i, j) = 1
}
X
import com.infomagnetic.linalg._
var c = Vector.fill[Double](m)(0)
(5 to 1 by -1) zip (0 to 4) foreach {case(v, i) => c = c.updated(i, v/15.0)}
c
val Ac = Linalg.toeplitz(c)
var r = Vector.fill[Double](n)(0)
(5.0 to 0.5 by -0.5) zip (0 until 10) foreach {case(v, i) => r = r.updated(i, v/15.0)}
r
val Ar = Linalg.toeplitz(c, r)
val B = Ac * (X * Ar.t)
//new PrintWriter("/Users/yutongpang/IdeaProjects/scalainversepackage/src/main/scala/tempfolder/test.txt"){write(B.toString(5000, 5000)); close()}
