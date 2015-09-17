package example.inverse1D

import breeze.linalg.{DenseVector, DenseMatrix}

/**
 * Created by yutongpang on 9/15/15.
 */
abstract class Inverse1D(matrixSize:Int, bError: Vector[Double]){
  val a = initA(matrixSize)
  val x = initX(matrixSize)
  val b = calculateB()
  protected def initA(matrixSize: Int): DenseMatrix[Double]
  protected def initX(matrixSize: Int): DenseVector[Double]
  protected def calculateB():DenseVector[Double] = {
    a * x
  }
}
