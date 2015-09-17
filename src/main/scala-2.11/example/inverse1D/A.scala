package example.inverse1D

import breeze.linalg.DenseMatrix

/**
 * Created by yutongpang on 9/15/15.
 */
trait A {
  protected def initAwithF(matrixSize: Int, sOrigin: Double, sLength: Double, tOrigin: Double, tLength: Double)
                (f: (Double, Double) => Double): DenseMatrix[Double] =
  {
    val a = DenseMatrix.zeros[Double](matrixSize, matrixSize).pairs.map
    {
      v => {
        val t = tOrigin + tLength / matrixSize * (v._1._1 + 0.5)
        val s = sOrigin + sLength / matrixSize * (v._1._2 + 0.5)
        1.0 / matrixSize * f(t, s)
      }
    }
    a
  }
}