Проект ToDoList предназначен для отслеживания своих дел.

*Используемые технологии* 
* Java (1.8.0_192)
* Maven (3.6.0)

*Структура прокета*
* [command]
	* AbstractCommand - абстрактный класс команды
	* GroupAddCommand - класс команды добавить группу
    * TaskAddCommand - класс команды добавить задачу
    * TaskClearCommand - класс команды очистить команды
    * TaskCompleteCommand - класс команды выполнить задачу
    * TaskToGroupCommand - класс команды присвоить задаче группу
    * GroupDeleteCommand - класс команды удалить группу
    * TaskDeleteCommand - класс команды удалить задачу
    * GroupReadCommand - класс команды вывести все группы
    * TaskReadCommand - класс команды вывести все задачи
    * GroupUpdateCommand - класс команды обновить группу
    * TaskUpdateCommand - класс команды обновить задачу
* [repository]
  * GroupRepository - класс для созднания и управления группами
  * TaskRepository - класс для создания и управления задачами
* [enumerated]
  * Priority - перечесление приоритетов
* [error]
  * WrongInputException
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
 java -jar ./to-do-list.jar
```