package org.example

import scala.io.Source

object TsvApp extends App {

  case class Person(name: String, age: Int)

  val filename = "data/test.tsv"

  for (line <- Source.fromFile(filename).getLines) {

    val fields = line.split("\t")

    fields match {

      case fs if fs.length == 2 =>
        val person = Person(fs(0), fs(1).toInt)
        println(person)

      case s => println(s"Wrong format: $s")

    }

  }

}
