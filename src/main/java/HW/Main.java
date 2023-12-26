package HW;

import java.sql.Connection;

/**
 * * Задания необходимо выполнять на ЛЮБОЙ СУБД (postgres, mysql, sqllite, h2, ...)
 * *
 * * 1. С помощью JDBC выполнить:
 * * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
 * * 1.2 Добавить в таблицу 10 книг
 * * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
 * *
 * * 2. С помощью JPA(Hibernate) выполнить:
 * * 2.1 Описать сущность HW.Book из пункта 1.1
 * * 2.2 Создать Session и сохранить в таблицу 10 книг
 * * 2.3 Выгрузить список книг какого-то автора
 * *
 * * 3.* Создать сущность Автор (id biging, name varchar), и в сущности HW.Book сделать поле типа HW.Author (OneToOne)
 * * 3.1 * Выгрузить Список книг и убедиться, что поле author заполнено
 * * 3.2 ** В классе HW.Author создать поле List<HW.Book>, которое описывает список всех книг этого автора. (OneToMany)
 */


public class Main {
    public static void main(String[] args) {
        String searchAuthor = "Эрих Мария Ремарк";

        // JDBC
        System.out.println("JDBC");
        System.out.println("-----------------------------");
        HW.DatabaseJDBC databaseJDBC = new HW.DatabaseJDBC();
        Connection connectionDatabase = databaseJDBC.dbConnection();
        databaseJDBC.prepareTables(connectionDatabase);
        databaseJDBC.insertData(connectionDatabase);
        System.out.println("-----------------------------");
        databaseJDBC.getData(connectionDatabase, searchAuthor);
        databaseJDBC.dbClose(connectionDatabase);

        // HIBERNATE
        System.out.println("\nHIBERNATE");
        System.out.println("-----------------------------");
        DatabaseHibernate databaseHibernate = new DatabaseHibernate();
        databaseHibernate.createTableBook();
        databaseHibernate.getBooksByAuthor(searchAuthor);
        databaseHibernate.closedSession();
    }
}