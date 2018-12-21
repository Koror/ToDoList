Проект ToDoList предназначен для отслеживания своих дел.

*Используемые технологии* 
* Java (1.8.0_192)
* Maven (3.6.0)

*Структура прокета*
* [manager]
  * GroupManager - интерфейс CRUD функций
  * TaskManager - класс для создание и управления группами и задачами
* [entity]
  * Group - Группа
  * Task - Задача
* [gui]
  * CommandLineGUI - реализация графического интерфейса в командной строке
* [service]
  *GroupService
  *TaskService
  *ToDoListService
* App - Основной класс 

*Сборка проекта*
'''bash
 mvn clean install
'''
 
*Запуск*
'''bash
 java -jar ./target/my-app-1.0-SNAPSHOT.jar
'''