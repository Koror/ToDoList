Проект ToDoList предназначен для распределения задач между пользователями.

*Используемые технологии* 
* Java (1.8.0_192)
* Maven (3.6.0)

*Структура проекта*
* [command]
	* классы команд
* [api]
  * controller.IBootstrap - интерфейс контроллера
  * repository - интерфейсы репозиториев
* [enumerated]
  * Priority - перечесление приоритетов
* [controller]
  * Bootstrap - класс объединяющий репозитории и команды
* [error]
  * WrongInputException - класс ошибки пользовательского ввода
  * MissingCommandException - класс ошибки отсутствия комманд
* [entity]
  * Group - группа
  * Task - задача
  * User - пользователь
  * AssigneeGroup - связь группы и пользователя
  * AssigneeTask - связь задачи и пользователя
* [repository]
  * GroupRepository - класс для созднания и управления группами
  * TaskRepository - класс для создания и управления задачами  
  * UserRepository - класс для создания и управления пользователями
  * AssigneeGroupRepository -  класс для создания связей между группами и пользователями
  * AssigneeTaskRepository -  класс для создания связей между задачами и пользователями
* [service]
  * GroupService - сервис групп
  * TaskService - сервис задач
  * UserService - сервис пользователей
  * AssigneeGroupService - сервис связи группы и пользователя
  * AssigneeTaskService - сервис связи задачи и пользователя
* [security]
  * Authorization - класс авторизации пользователя
* App - основной класс 

*Сборка проекта*
```bash
 mvn clean install
```
 
*Запуск*
```bash
 java -jar ./to-do-list.jar
```

*Учетные записи*

[логин] [пароль]

 admin  admin
 
 test   test 