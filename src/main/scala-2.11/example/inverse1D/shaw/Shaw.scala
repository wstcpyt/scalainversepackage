package example.inverse1D.shaw

/**
 * Created by yutongpang on 9/16/15.
 */

import java.lang.Math._
import breeze.linalg.{DenseVector, DenseMatrix}
import example.inverse1D.{Inverse1D, A, X}

class Shaw(matrixLength: Int, bError:Vector[Double]) extends Inverse1D(matrixLength: Int, bError:Vector[Double]) with A with X {
  def initA(): DenseMatrix[Double] = {
    initAwithF(matrixLength, -PI / 2, PI, -PI / 2, PI) { (s, t) => {
      val firstTerm = pow(cos(s) + cos(t), 2.0)
      val secondTerm = if (sin(s) + sin(t) != 0) pow(sin(PI * (sin(s) + sin(t))) / (PI * (sin(s) + sin(t))), 2.0) else 1
      firstTerm * secondTerm
    }
    }
  }

  def initX(): DenseVector[Double] = {
    initXwithF(matrixLength, -PI / 2, PI) {t => 2 * exp(-6 * pow(t - 0.8, 2.0)) + exp(-2 * pow(t + 0.5, 2.0))}
  }
}