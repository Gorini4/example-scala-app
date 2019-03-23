// package определяет положение объектов в логической структуре кода
package org.example

// Импортируем форматы и методы для парсинга из библиотеки json4s
// https://github.com/json4s/json4s
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods.parse

// Импортируем классы, которые понадобятся нам для обработки ошибок
import scala.util.{Failure, Success, Try}

// Импортируем объект для чтения файлов
import scala.io.Source

// "extends App" указывает, что мы определяем класс, с которого начнется выполнение программы
// Таких классов может быть несколько (конкретный указывается при запуске или в build.sbt)
object JsonApp extends App {

  // Объявляем стандартный набор кодеков для парсинга JSON
  implicit val formats: DefaultFormats.type = org.json4s.DefaultFormats

  // Объявляем класс, который описывает наши данные
  case class Person(name: String, age: Int)

  // Задаем имя файла
  val filename = "data/test.json"

  // Можно получать имя файла из аргументов
  // val filenameFromArgs = args(0)

  // Читаем строки из файла
  for (line <- Source.fromFile(filename).getLines) {

    // Давайте попробуем использовать Try для перехвата возможных исключений
    // Варианты как обрабатывать исключения: https://bit.ly/2TRQK5H
    val result: Try[Person] = Try(parse(line).extract[Person])

    // Проверим, успешно ли удалось распарсить файл
    result match {
      case Success(value) => println(value)
      case Failure(exception) => exception.printStackTrace()
    }

  }

}
