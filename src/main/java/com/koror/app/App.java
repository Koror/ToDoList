package com.koror.app;

import com.koror.app.service.ToDoListService;

public class App {

    public static void main(String[] args) {
        ToDoListService service = new ToDoListService();
        service.start();
    }

}
