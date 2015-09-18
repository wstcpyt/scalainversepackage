package example.inverse1D.test

/**
 * Created by yutongpang on 9/16/15.
 */

import example.inverse1D.A
import org.scalatest.FunSuite
class ATEST extends FunSuite with A{
  test("initAwithF") {
    val a = initAwithF(5, 1, 0, 1, 0)((s, t) => s + t)
    a.foreachValue(m => assert(m == 2.0 / 5.0))
    val a1 = initAwithF(1, 1, 1, 1, 1)((s, t) => s + t)
    a1.foreachValue(m => assert(m == 3.0))
  }
}
