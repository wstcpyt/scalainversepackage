package example.inverse1D

import breeze.linalg.{DenseMatrix, DenseVector}
import org.scalatest.FunSuite

/**
 * Created by yutongpang on 9/17/15.
 */
class Inverse1DTEST extends FunSuite {
  test("Inverse1DClass") {
    class Inverse1Dtest(matrixSize: Int, bError:Vector[Double]) extends Inverse1D(matrixSize: Int, bError:Vector[Double]){
      protected def initA(matrixSize: Int): DenseMatrix[Double] = DenseMatrix.zeros[Double](matrixSize, matrixSize)
      protected def initX(matrixSize: Int): DenseVector[Double] = DenseVector.zeros[Double](matrixSize)
    }
    val inverse1Dtest = new Inverse1Dtest(5, Vector(0, 0, 0))
    assert(inverse1Dtest.a.size == 25)
    assert(inverse1Dtest.x.size == 5)
    assert(inverse1Dtest.b.size == 5)
  }
}
