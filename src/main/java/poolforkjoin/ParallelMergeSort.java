package poolforkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {

    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    /**
     * Примечания
     * compute() - метод, который переопределяется у RecursiveTask
     *
     * создаем задачи для сортировки частей
     * ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid);
     * ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to);
     * ----
     * производим деление.
     * оно будет происходить, пока в частях не останется по одному элементу
     * leftSort.fork();
     * rightSort.fork();
     * - метод fork() служит для деления.
     * Это аналогично тому, что мы запустили бы рекурсивный метод еще раз
     *
     * Например в случае факториала,
     * factorialTask.fork()
     * Аналогично запуску fact()
     * n * fact(n - 1)
     *
     * -----
     * объединяем полученные результаты
     * int[] left = leftSort.join();
     * int[] right = rightSort.join();
     *  - метод join(). Этот метод как раз дает пулу знать,
     *  что нужно запустить задачу в отдельном потоке
     *  метод invoke(). Этот метод служит для запуска выполнения
     *
     * MergeSort.merge - из соседнего класса
     *
     */
    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[] {array[from]};
        }
        int mid = (from + to) / 2;
        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to);
        leftSort.fork();
        rightSort.fork();
        int[] left = leftSort.join();
        int[] right = rightSort.join();
        return MergeSort.merge(left, right);
    }

    public static int[] sort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }
}