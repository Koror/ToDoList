Проект ToDoList предназначен для отслеживания своих дел.

*Используемые технологии* 
* Java (1.8.0_192)
* Maven (3.6.0)

*Структура прокета*
* [dao]
  * Manager - интерфейс CRUD функций
  * TaskManager - класс для создание и управления группами и задачами
* [entity]
  * Group - Группа
  * Task - Задача
* [GUI]
  * GUICommandLine - реализация графического интерфейса в командной строке
* App - Основной класс 