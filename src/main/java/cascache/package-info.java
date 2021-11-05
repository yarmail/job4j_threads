package cascache;

/*
В этом задании мы познакомимся с CAS методами
потокобезопасной коллекции ConcurrentHashMap.

Коллекции Map используют для создания кешей.
Кеш - это элемент программы позволяющий увеличить
скорость работы за счет хранения данных в памяти.

Например, если данные о пользователя храниться в базе
данных и мы часто их используем, то что бы увеличить
скорость работы можно загрузить информацию о
пользователях в память и читать сразу из памяти.
Это даст значительный прирост скорости.

Схема кеша.
Service <> Cache <> DataBase

Service  - это все классы, которые работают с базой данных.
Cache - часть данных храниться в памяти.
DataBase - постоянное хранилище.

Базовая модель данных Base
описывается двумя полями: id, version.

В кеше Cache
должна быть возможность проверять актуальность данных.
Для этого в модели данных используется поле int version.

То есть в кеше сохраняться данные только последнего пользователя.
Продемонстрируем это на примере HashMap. Main




 */