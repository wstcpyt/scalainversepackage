package com.infomagnetic.spark

import com.infomagnetic.CEA._
import com.infomagnetic.example.inverse1D.shaw.ShawFitness
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

import scala.util.Random

object Functionfitness {
  def calculatefitness(individual: Individual) = {
    val properties = individual.individualProperites
    val x = properties(0)
    val y = properties(1)
    val f1 = 1 - x*x - y*y
    val f2 = 1 - x* x
    f1*f1+f2*f2
  }
}

object Solvefunction {
  private val threadNumber = 5
  private val populationLengthPerThread = 20
  private val populationLength = threadNumber * populationLengthPerThread
  private val bestList = Vector()
  private val discretizationSize = 40
  private val random = new Random()
  private val loop = 1000
  private val randMin = -1.2d
  private val randMax = 1.2d
  private val cglsIndex = 8
  private var loopcondition = true
  private var functionsolution = Set((0.0,0.0))

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Inverse Problem")
    val sc = new SparkContext(conf)
    val staticPopulation = CEAlgorithm.initPopulation(populationLength, 2, randMin, randMax)
    var subPopulationbList: List[SubPopulation] = SubPopulationList.generateList(staticPopulation, threadNumber, populationLength, populationLengthPerThread)
    while (loopcondition) {
      var subPopulationListRDD: RDD[SubPopulation] = sc.parallelize(subPopulationbList)
      //generate new sub population list
      val newSubPopulationArray: Array[SubPopulation] = subPopulationListRDD.map(s =>{
        val subPopulation = s.subPopulation
        val newsubPopulation = subPopulation.zipWithIndex.map {row =>
          row._1.zipWithIndex.map{col =>
            //evaluate individual node
            val fitnessValue = Functionfitness.calculatefitness(col._1)
            var individual = new Individual(col._1.individualProperites, fitnessValue)
            //produce offspring
            var offSprings = CEAlgorithm.produceOffSpring(row._2, col._2, subPopulation, randMin, randMax)
            //random choosen two offSprings
            offSprings = random.shuffle(offSprings).slice(0, 2)
            offSprings.foreach{offspring =>
              val offSpringFitness =Functionfitness.calculatefitness(offspring)
              if (offSpringFitness < fitnessValue){
                individual = new Individual(offspring.individualProperites, offSpringFitness)
              }
            }
            individual
          }
        }
        new SubPopulation(newsubPopulation, s.thread)
      }
      ).collect()
      var newStaticPopulation = Array.ofDim[Individual](populationLength, populationLength)
      newSubPopulationArray.foreach{subPopulation =>
        val thread = subPopulation.thread
        for (i <- 0 until populationLength){
          for (j <- thread * populationLengthPerThread until (thread + 1) * populationLengthPerThread){
            if(thread == 0){
              newStaticPopulation(i)(j) = subPopulation.subPopulation(i)(j)
            }else{
              newStaticPopulation(i)(j) = subPopulation.subPopulation(i)(j - thread*populationLengthPerThread + 1)
            }
          }
        }
      }
      val tempStaticPopulation = newStaticPopulation.map(row => row.toVector).toVector
      subPopulationbList = SubPopulationList.generateList(tempStaticPopulation, threadNumber, populationLength, populationLengthPerThread)
      val bestindividual = new BestFitness(tempStaticPopulation).bestFitnessIndividual
      val bestfitness = bestindividual.fitness
      if (bestfitness < 10e-10)
        functionsolution += (((math floor bestindividual.individualProperites(0) * 100) / 100, (math floor bestindividual.individualProperites(1) * 100) / 100))
        if (functionsolution.size==3)
          loopcondition = false
      print(functionsolution)
    }
  }

}
