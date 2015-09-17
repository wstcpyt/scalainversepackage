package example.inverse1D

import breeze.linalg.{DenseMatrix, DenseVector}
import org.scalatest.FunSuite

/**
 * Created by yutongpang on 9/17/15.
 */
class Inverse1DTEST extends FunSuite {
  test("Inverse1DClass") {
    class Inverse1Dtest(matrixSize: Int) extends Inverse1D(matrixSize: Int){
      protected def initA(matrixSize: Int): DenseMatrix[Double] = DenseMatrix.zeros[Double](matrixSize, matrixSize)

      protected def initX(matrixSize: Int): DenseVector[Double] = DenseVector.zeros[Double](matrixSize)
    }
    assert(new Inverse1Dtest(5).a.size == 25)
    assert(new Inverse1Dtest(5).x.size == 5)
  }
}
