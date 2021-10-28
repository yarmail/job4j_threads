/**
 * 1. Синхронизация общих ресурсов. [#1096 #221415]
 *
 * Синхронизация блокирует одновременный доступ
 * к методам. Чтобы методы стали
 * синхронизированными нужно использовать
 * ключево слово:
 * synchronized
 *
 * Задание
 * Это код содержит ошибку атомарности
 *
 * Было:
 * public static Cache instOf()
 *
 * Стало:
 * public synchronized static Cache instOf()
 *
 */
public final class SynchronizedCache {
    private static SynchronizedCache cache;

    public synchronized static SynchronizedCache instOf() {
        if (cache == null) {
            cache = new SynchronizedCache();
        }
        return cache;
    }
}