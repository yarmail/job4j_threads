package waitnotify;

/*
0. Управление нитью через wait. [#6858]
В блоке IO рассматривалась тема с Socket.
Класс ServerSocket имеет метод accept.
Метод accept переведет программу в режим ожидания.
Программа ждет, когда к порту присоединится клиента.

Очевидное решение такой задачи - это цикл с задержкой.
Но такое решение не верно, потому что клиент может прийти сразу,
а может и через час.
Программа будет проверять состояние в пустую. Тратить на это ресурсы.
В Java есть механизм позволяющий разбудить нить,
если состояние программы поменялось.

У объекта монитора есть методы wait, notifyAll.
Метод notifyAll будит все нити, которые ждали изменения состояния.
Метод wait переводит нить в состояние ожидания,
если программа не может дальше выполняться.

Рассмотрим пример. Класс Barrier.
Пример использования MultiUser
 */