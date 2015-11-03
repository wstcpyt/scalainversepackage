package com.infomagnetic.example.inverse1D

import breeze.linalg.{DenseVector, DenseMatrix}

/**
 * One dimensional Inverse Problem Abstract Class
 * @constructor Create a one dimensional inverse problem
 * @param matrixLength discretization size
 * @param bError bError vector which define the error of the b vector
 */
abstract class Inverse1D(matrixLength:Int, bError: Vector[Double]){
  require(bError.size <= matrixLength, "bError Vector size must be equal or less than matrixSize")
  private val _a = initA()
  private val _x = initX()
  private val _b = calculateB()
  def a = _a.copy
  def x = _x.copy
  def b = _b.copy
  protected def initA(): DenseMatrix[Double]
  protected def initX(): DenseVector[Double]
  private def calculateB():DenseVector[Double] = {
    val btemp = _a * _x
    bError.zipWithIndex.foreach{case(z, i) => btemp(i) += z}
    btemp
  }
}
