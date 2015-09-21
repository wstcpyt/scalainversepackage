package com.infomagnetic.example.inverse1D

import breeze.linalg.DenseVector

/**
 * Created by yutongpang on 9/16/15.
 */
trait X {
  protected def initXwithF(matrixLength: Int ,tOrigin: Double, tLength: Double)(f: Double => Double): DenseVector[Double] =
  {
    val x = DenseVector.zeros[Double](matrixLength).pairs.map{
      v => {
        val t = tOrigin + tLength / matrixLength * (v._1 + 0.5)
        f(t)
      }
    }
    x
  }
}
