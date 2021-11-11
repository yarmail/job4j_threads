package asynch;

/*
Ранее мы тесно работали с многопоточностью.
Сегодня мы поговорим об асинхронности.
В чем разница вы можете почитать по первой ссылке,
которая указана в доп. материалах.

Для начала поговорим, что такое синхронность.
Синхронность – это когда действия выполняются
последовательно. К примеру, вы сталкивались
со словом synchronized, что значит, в данном месте
потоки выполняют последовательно, а не одновременно.

Асинхронность – это наоборот, когда действие
выполняется отдельно от другого действия.

Рассмотрим схематично как работает асинхронность.
Как видим, асинхронная задача это та, что выполняется
вне основного потока. К тому же можно заметить,
что асинхронность хорошо подходит, когда основной
поток сильно загружен, а нам нужно выполнить
что-то отдельно. В этом случае удобно использовать
асинхронность, при этом не требуется управление
потоками напрямую.

Для написания асинхронного кода в Java существует
замечательный класс CompletableFuture. Данный класс
имеет много методов.



 */