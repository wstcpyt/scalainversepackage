package example.inverse1D.test

/**
 * Created by yutongpang on 9/16/15.
 */

import example.inverse1D.X
import org.scalatest.FunSuite
class XTEST extends FunSuite with X{
  test("initXwithF"){
    val x = initXwithF(1, 1, 1){
      t => t * 2
    }
    x.foreachValue(m => assert(m == 3))
  }
}
