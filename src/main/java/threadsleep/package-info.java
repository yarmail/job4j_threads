package threadsleep;

/*
1.2. Режим ожидания. [#231217]

Выполнение нити можно приостановить
в самой программе. Это может понадобиться
для ограничения скорости работы вашей программы.
Например: отрисовки положения игрока на поле
или ограничение на скорость скачивания файла по сети,
поставить воспроизведение видео или звука на паузу.
В классе Thread есть статический метод sleep.

Thread.sleep(millisecond);

Этот метод переводит нить в состояние TIMED_WAITING.
Так же этот метод может выкинуть
исключение -InterruptedException. Это связано с тем,
что нить могут попросить прервать свое
выполнение и программисту необходимо
предусмотреть дальнейшие действия, если такое случилось.

Давайте напишем программу, которая ждет
3 секунды и печатает на консоль слово loaded.

ThreadSleep
 */