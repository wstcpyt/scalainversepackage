package example.inverse1D

import breeze.linalg.{DenseVector, DenseMatrix}

/**
 * Created by yutongpang on 9/15/15.
 */
abstract class Inverse1D(matrixSize:Int){
  val a = initA(matrixSize)
  val x = initX(matrixSize)
  protected def initA(matrixSize: Int): DenseMatrix[Double]
  protected def initX(matrixSize: Int): DenseVector[Double]
}
