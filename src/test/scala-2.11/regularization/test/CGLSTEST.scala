package regularization.test

import breeze.linalg.{DenseVector, norm}
import example.inverse1D.shaw.Shaw
import org.scalatest.FunSuite
import regularization.CGLS

/**
 * Created by ypang on 9/17/15.
 */
class CGLSTEST extends FunSuite{
  test("CGLS") {
    val shaw = new Shaw(128, Vector(0, 0, 0))
    val x = CGLS.calculateX(shaw.a, shaw.b, 17)
    println(norm(shaw.x - x))
  }
}
