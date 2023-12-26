package HW;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * 2. С помощью JPA(Hibernate) выполнить:
 * 2.1 Описать сущность HW.Book из пункта 1.1
 * 2.2 Создать Session и сохранить в таблицу 10 книг
 * 2.3 Выгрузить список книг какого-то автора
 */
public class DatabaseHibernate {
    final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
    Book book;

    /**
     * Метод создания и заполнения таблицы BOOKS
     */
    public void createTableBook() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Author author1 = new Author("Михаил Булгаков");
            session.persist(author1);

            Author author2 = new Author("Федор Михайлович Достоевский");
            session.persist(author2);

            Author author3 = new Author("Антуан де Сент-Экзюпери");
            session.persist(author3);

            Author author5 = new Author("Эрих Мария Ремарк");
            session.persist(author5);

            Author author6 = new Author("Джером Сэлинджер");
            session.persist(author6);

            Author author7 = new Author("Михаил Лермонтов");
            session.persist(author7);

            Author author8 = new Author("Оскар Уайльд");
            session.persist(author8);

            Author author9 = new Author("Джон Рональд Руэл Толкин");
            session.persist(author9);


            book = new Book("Мастер и Маргарита", author1);
            session.persist(book);

            book = new Book("Преступление и наказание", author2);
            session.persist(book);

            book = new Book("Маленький принц", author3);
            session.persist(book);

            book = new Book("Собачье сердце", author1);
            session.persist(book);

            book = new Book("Три товарища", author5);
            session.persist(book);

            book = new Book("Над пропастью во ржи", author6);
            session.persist(book);

            book = new Book("Герой нашего времени", author7);
            session.persist(book);

            book = new Book("Приключения Шерлока Холмса", author7);
            session.persist(book);

            book = new Book("Портрет Дориана Грея", author8);
            session.persist(book);

            book = new Book("Властелин колец", author9);
            session.persist(book);

            session.getTransaction().commit();
        }
    }

    /**
     * Метод поиска в базе данных по автору
     *
     * @param searchAuthor строка (имя автора)
     */
    public void getBooksByAuthor(String searchAuthor) {
        try (Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery(
                            "FROM HW.Book WHERE author = (FROM HW.Author WHERE nameAuthor = :name_author)", Book.class
                    ).setParameter("name_author", searchAuthor)
                    .getResultList();

            books.forEach(System.out::println);
        }
    }

    /**
     * Метод закрытия сессии
     */
    public void closedSession() {
        sessionFactory.close();
    }
}