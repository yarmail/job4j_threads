package threadstart;

/*
Запуск нити. Thread#start() [#1016]

В этом уроке мы научимся создавать нить исполнения.
Напомню, что нить исполнения говорит виртуальной машине,
что операторы в этой нити, можно выполнить
в многозадачном режиме или даже параллельном режиме.

Почему так неоднозначно? Потому что управление нитью
передается на усмотрение виртуальной машине. А она в свою
очередь может посчитать, что каждую нить можно выполнить,
либо в разных процессах (параллельно) или в одном процессе (многозадачно).

В любой программе по умолчанию есть главная нить.
В этой нити выполняются операторы из методы main.

Чтобы создать еще одну нить,
необходимо воспользоваться класс java.lang.Thread.
Давайте создадим класс ConcurrentOutput.

----

Давайте теперь поговорим о конструкторе класса java.lang.Thread.
Конструктор этого класса принимает функциональный интерфейс
java.lang.Runnable.
Это интерфейс имеет один метод public void run().
Методы определенные в этом методе будет выполняться
в многозадачной среде.

Чтобы не создавать анонимный класс,
в примере выше использовалось лямбда-выражение.
---
java
Thread another = new Thread(
        () -> System.out.println(Thread.currentThread().getName())
);
---

Давайте перепишем этот код через анонимный класс.

---
Thread another = new Thread(
        new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName())
            }
        }
);
---
Выглядит громоздко. Поэтому старайтесь использовать
лямбды для экспериментов с Thread.
Обратите внимание, что метод run имеет
модификатор public. А что будет если мы его вызовем?
(another.run();)

---
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.run();
        System.out.println(Thread.currentThread().getName());
    }
---
Вывод: main, main
В этом случае имя нити и в первом и во втором случае одинаковое.
Это происходит, потому что метод run не дает указания
выполнить свои операторы в отдельной нити,
как это делает метод Thread#start.
Метод run напрямую вызывает операторы в той же нити,
в которой запущен этот метод.
 */