package com.infomagnetic.CEA

import com.infomagnetic.common.CommonTypedef._

class BestFitness(population:Population) {
  val bestFitnessIndividual = getBestfitnessIndividual

  def getBestfitnessIndividual = {
    val flatPopulation = population.flatMap(row => row)
    val sortedFlatPopulation = flatPopulation.sortBy(individual => individual.fitness)
    sortedFlatPopulation.head
  }
  def printBestInfo = {
    var bestInfo: String = "Best: (" + bestFitnessIndividual.fitness + ")\n"
    bestFitnessIndividual.individualProperites.foreach{properties =>
      bestInfo = bestInfo + properties + " "
    }
    bestInfo = bestInfo + "\n"
    println(bestInfo)
  }
}
