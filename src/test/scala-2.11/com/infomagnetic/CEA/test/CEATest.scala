package com.infomagnetic.CEA.test

import com.infomagnetic.CEA.CEAlgorithm
import org.scalatest.FunSuite

class CEATest extends FunSuite {
   test("init population") {
     val population = CEAlgorithm.initPopulation(10, 40, 0, 0.2)
     assert(population.length == 10)
     assert(population.head.length == 10)
     assert(population.head.head.individualProperites.length == 40)
     assert(population.head.head.fitness == 0)
     val sumProperties = population.head.head.individualProperites.indices.
       map(i => population.head.head.individualProperites(i)).sum
     assert(sumProperties / population.head.head.individualProperites.length > 0
       && sumProperties / population.head.head.individualProperites.length < 0.2)
   }

   test("produce offspring") {
     val population = CEAlgorithm.initPopulation(10, 40, 0, 0.2)
     val offSpring00 = CEAlgorithm.produceOffSpring(0, 0, population, 0, 0.2)
     assert(offSpring00.length == 2)
     val offSpring01 = CEAlgorithm.produceOffSpring(0, 1, population, 0, 0.2)
     assert(offSpring01.length == 3)
     val offSpring11 = CEAlgorithm.produceOffSpring(1, 1, population, 0, 0.2)
     assert(offSpring11.length == 4)
   }
 }
