package visibility;

/*
1. Visibility. Общий ресурс вне критической секции [#1102]
В предыдущем уроке мы познакомились с понятием
объекта монитора и критической секции.

Если у нас есть общий ресурс, то с ним нужно
работать только в критической секции.

Если это не сделать, то мы получаем
состояние гонки (race condition).

Как бы две нити соревнуются кто первый
получит доступ к ресурсу.

Example - демонстрация с пояснением

Задание: ParseFileBad
Решение: ParseFile, SaveFile

*/