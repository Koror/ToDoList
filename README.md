Проект ToDoList предназначен для отслеживания своих дел.

*Используемые технологии* 
* Java (1.8.0_192)
* Maven (3.6.0)

*Структура прокета*
* [command]
	* AbstractCommand - абстрактный класс команды
	* CommandGroupAdd - класс команды добавить группу
    * CommandTaskAdd - класс команды добавить задачу
    * CommandTaskClear - класс команды очистить команды
    * CommandTaskComplete - класс команды выполнить задачу
    * CommandTaskToGroup - класс команды присвоить задаче группу
    * CommandGroupDelete - класс команды удалить группу
    * CommandTaskDelete - класс команды удалить задачу
    * CommandGroupRead - класс команды вывести все группы
    * CommandTaskRead - класс команды вывести все задачи
    * CommandGroupUpdate - класс команды обновить группу
    * CommandTaskUpdate - класс команды обновить задачу
* [repository]
  * GroupRepository - класс для созднания и управления группами
  * TaskRepository - класс для создания и управления задачами
* [enumerated]
  * Priority - перечесление приоритетов
* [entity]
  * Group - группа
  * Task - задача
* [controller]
  * Bootstrap - класс объединяющий репозитории и команды 
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