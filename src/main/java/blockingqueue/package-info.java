package blockingqueue;

/*
1. Реализовать шаблон Producer Consumer. [#1098]

Реализуйте шаблон Producer Consumer.
Для этого вам необходимо реализовать собственную
версию bounded blocking queue. Это блокирующая очередь,
ограниченная по размеру. В данном шаблоне Producer помещает
данные в очередь, а Consumer извлекает данные из очереди.

Если очередь заполнена полностью, то при попытке добавления
поток Producer блокируется, до тех пор пока Consumer не извлечет
очередные данные, т.е. в очереди появится свободное место.
И наоборот если очередь пуста поток Consumer блокируется,
до тех пор пока Producer не поместит в очередь данные.

В задании нельзя использовать потокобезопасные коллекции
реализованные в JDK. Ваша задача используя, wait/notify реализовать блокирующую очередь.

Давайте сделаем каркас нашего приложения.
Producer Consumer - по сути это обычные нити.
Для того чтобы нить перевести в ждущее состояние необходимо
в ее процессе вызвать метод wait() для объекта монитора.

Для того чтобы разбудить нить, нужно, чтобы другая
нить вызвала у объекта монитора метод notify().

Теперь давайте перейдем к созданию нашей блокирующей очереди.

Создадим класс SimpleBlockingQueue

 */