package com.infomagnetic.CEA

import scala.util.Random

object CEAlgorithm {
  private val random = new Random()

  private def assignPopulation(populationLength: Int, individualPropertiesSize: Int) = {
    var populationRow = Vector[Individual]()
    val individual = new Individual(Vector.fill[Double](individualPropertiesSize)(0), 0)
    0 until populationLength foreach { _ => populationRow = populationRow :+ individual }
    var population = Vector[Vector[Individual]]()
    0 until populationLength foreach (_ => population = population :+ populationRow)
    population
  }

  def initPopulation(populationLength: Int, individualPropertiesSize: Int, randMin: Double, randMax: Double) = {
    val population = assignPopulation(populationLength, individualPropertiesSize)
    population.map { populationRow =>
      populationRow.map { individual =>
        val newProperties = individual.individualProperites.map { _ =>
          val randomTemp = random.nextDouble()
          val randomValue = randMin + (randMax - randMin) * randomTemp
          randomValue
        }
        new Individual(newProperties, individual.fitness)
      }
    }
  }

}