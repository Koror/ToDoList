Проект ToDoList предназначен для отслеживания своих дел.

*Используемые технологии* 
* Java (1.8.0_192)
* Maven (3.6.0)

*Структура прокета*
* [repository]
  * GroupRepository - класс для созднания и управления группами
  * TaskRepository - класс для создания и управления задачами
* [entity]
  * Group - группа
  * Task - задача
* [controller]
  * Bootstrap
  * InterfaceGUI - графический интерфейс
  * CommandLineGUI - реализация графического интерфейса в командной строке
* [service]
  * GroupService - сервис групп
  * TaskService - сервис задач
* App - основной класс 

*Сборка проекта*
```bash
 mvn clean install
```
 
*Запуск*
```bash
 java -jar ./target/ToDoList-1.0-SNAPSHOT.jar
```