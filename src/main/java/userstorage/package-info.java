package userstorage;

/*
 Класс хранилища пользователей UserStorage [#1104]

В этом задании нужно сделать блокирующий кеш UserStorage
для модели User.
Это задача - это классическая задача по переводу денег
с одного счета на другой. Чтобы операции были атомарны,
нам нужно один объект монитора.

В этом задании объект монитора будет объект класса UserStore.

1. Создать класс - структуру данных для хранение
пользователей UserStorage.

2. В заголовке класса обозначить аннотацию
@ThreadSafe из библиотеки jcip

3. Хранилище должно иметь методы
boolean add (User user),
boolean update(User user),
boolean delete(User user).

4. И особый метод transfer(int fromId, int toId, int amount);

5. Структура данных должна быть потокобезопасная;

6. В структуре User Должны быть поля int id, int amount.

amount - это сумма денег на счете пользователя.

Пример использования:

UserStore stoge = new UserStore();

stoge.add(new User(1, 100));
stoge.add(new User(2, 200));

stoge.transfer(1, 2, 50);

 */