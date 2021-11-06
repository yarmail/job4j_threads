package cas;

/*
0. CAS - операции [#6859 #224892]
Операции write и read по отдельности атомарны.
volatile обеспечивает правильную публикацию изменений.
Но во многих случаях нам нужны действия check-then-act.
Чтобы  этого добиться, нужно делать синхронизацию.
Синхронизация блокирует выполнение нитей,
то есть программа из многопоточной превращается в однопоточную.

CAS
Процессоры на уровне ядра поддерживают операцию compare-and-swap.
Эта операция атомарная.
В Java есть структуры данных, которые реализуют этот механизм.
Рассмотрим не потокобезопасный Stack.
Метод push и poll используют шаблон check-then-act.
Напишем тесты.

Чтобы сделать Stack потокобезопасным мы будем использовать класс
java.util.concurrent.atomic.AtomicReference
Это класс поддерживает CAS операции. (см CASStack)

Задание: CASCount


 */