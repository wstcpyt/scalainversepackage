package example.inverse1D.test

import breeze.linalg.{DenseMatrix, DenseVector}
import example.inverse1D.Inverse1D
import org.scalatest.FunSuite

/**
 * Created by yutongpang on 9/17/15.
 */
class Inverse1DTEST extends FunSuite {
  test("Inverse1DClass") {
    class Inverse1Dtest(matrixLength: Int, bError:Vector[Double]) extends Inverse1D(matrixLength: Int, bError:Vector[Double]){
      protected def initA(): DenseMatrix[Double] = DenseMatrix.zeros[Double](matrixLength, matrixLength)
      protected def initX(): DenseVector[Double] = DenseVector.zeros[Double](matrixLength)
    }
    val inverse1Dtest = new Inverse1Dtest(5, Vector(1, 2, 2))
    assert(inverse1Dtest.a.size == 25)
    assert(inverse1Dtest.x.size == 5)
    assert(inverse1Dtest.b.size == 5)
    inverse1Dtest.b(0) = 10
    List(1.0, 2.0, 2.0, 0.0, 0.0).zipWithIndex.foreach{case(z, i) => assert(inverse1Dtest.b(i) == z)}
  }
}
