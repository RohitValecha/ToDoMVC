package com.singtel.todomvc.web.utils;

public class ElementContainer {

    public static String getElementLocator(String elementName) throws NoSuchFieldException {

        String elementLocator;

        switch (elementName) {
            case "ToDoText":
                elementLocator = "//*[@class='new-todo']";
                break;
            case "SafariEdit":
                elementLocator = "//*[@class='edit']";
                break;
            case "ToDos":
                elementLocator = "//*[h1='todos']";
                break;
            case "NumberOfItems":
                elementLocator = "//*[@class='todo-count']/strong";
                break;
            case "ClearCompleted":
                elementLocator = "//*[@class='clear-completed']";
                break;
            case "ToDoList":
                elementLocator = "//*[@class='todo-list']";
                break;
            case "ToggleAll":
                elementLocator = "//*[@for='toggle-all']";
                break;
            case "Toggle":
                elementLocator = "//*[@class='toggle']";
                break;
            case "Destroy":
                elementLocator = "//*[@class='destroy']";
                break;
            case "AllLink":
                elementLocator = "//*[@href='#/all']";
                break;
            case "ActiveLink":
                elementLocator = "//*[@href='#/active']";
                break;
            case "CompletedLink":
                elementLocator = "//*[@href='#/completed']";
                break;
            default:
                throw new NoSuchFieldException("Unrecognized element field [" + elementName + "]!");
        }
        return elementLocator;
    }
}
