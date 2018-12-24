Проект ToDoList предназначен для отслеживания своих дел.

*Используемые технологии* 
* Java (1.8.0_192)
* Maven (3.6.0)

*Структура прокета*
* [manager]
  * GroupManager - класс для созднания и управления группами
  * TaskManager - класс для создания и управления задачами
* [entity]
  * Group - группа
  * Task - задача
* [gui]
  * InterfaceGUI - графический интерфейс
  * CommandLineGUI - реализация графического интерфейса в командной строке
* [service]
  *GroupService - сервис групп
  *TaskService - сервис задач
  *ToDoListService - объединяющий сервис приложения
* App - основной класс 

*Сборка проекта*
```bash
 mvn clean install
```
 
*Запуск*
```bash
 java -jar ./target/ToDoList-1.0-SNAPSHOT.jar
```