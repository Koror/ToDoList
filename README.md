Проект ToDoList предназначен для отслеживания своих дел.

*Используемые технологии* 
* Java (1.8.0_192)
* Maven (3.6.0)

*Структура прокета*
* [command]
	* классы команд
* [repository]
  * GroupRepository - класс для созднания и управления группами
  * TaskRepository - класс для создания и управления задачами
* [enumerated]
  * Priority - перечесление приоритетов
* [error]
  * WrongInputException - класс ошибки пользовательского ввода
  * MissingCommandException - класс ошибки отсутствия комманд
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