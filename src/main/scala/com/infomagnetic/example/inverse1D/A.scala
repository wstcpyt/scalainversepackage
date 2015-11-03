package com.infomagnetic.example.inverse1D

import breeze.linalg.DenseMatrix

/**
 * Created by yutongpang on 9/15/15.
 */
trait A {
  protected def initAwithF(matrixLength: Int, sOrigin: Double, sLength: Double, tOrigin: Double, tLength: Double)
                (f: (Double, Double) => Double): DenseMatrix[Double] =
  {
    val a = DenseMatrix.zeros[Double](matrixLength, matrixLength).pairs.map
    {
      v => {
        val t = tOrigin + tLength / matrixLength * (v._1._1 + 0.5)
        val s = sOrigin + sLength / matrixLength * (v._1._2 + 0.5)
        1.0 / matrixLength * f(t, s)
      }
    }
    a
  }
}