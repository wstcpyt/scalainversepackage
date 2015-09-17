package example.inverse1D

import breeze.linalg.{DenseVector, DenseMatrix}

/**
 * Created by yutongpang on 9/15/15.
 */
abstract class Inverse1D(matrixSize:Int, bError: Vector[Double]){
  val a = initA()
  val x = initX()
  val b = calculateB()
  protected def initA(): DenseMatrix[Double]
  protected def initX(): DenseVector[Double]
  protected def calculateB():DenseVector[Double] = {
    val b = a * x
    bError.zipWithIndex.foreach{case(z, i) => b(i) += z}
    b
  }
}
