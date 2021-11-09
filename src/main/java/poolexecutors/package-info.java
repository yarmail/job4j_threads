package poolexecutors;

/*
Задание.

1. Реализовать сервис для рассылки почты.
Создайте класс EmailNotification.
2. В классе будет метод emailTo(User user)
- он должен через ExecutorService отправлять почту.
Так же добавьте метод close() - он должен закрыть pool.
То есть в классе EmailNotification должно быть поле pool,
которые используется в emailTo и close().
3. Модель User описывают поля username, email.
4. Метод emailTo должен брать данные пользователя
и подставлять в шаблон
subject = Notification {username} to email {email}.
body = Add a new event to {username}
5. Создайте метод
public void send(String subject, String body, String email)
- он должен быть пустой.
6. Через ExecutorService создайте задачу,
которая будет создавать данные для пользователя и
передавать их в метод send.
 */