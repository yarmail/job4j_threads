package asynch;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Задание:
 * 1. Дан каркас класса. Ваша задача подсчитать суммы
 * по строкам и столбцам квадратной матрицы.
 * - sums[i].rowSum - сумма элементов по i строке
 * - sums[i].colSum  - сумма элементов по i столбцу
 * 2. Реализовать последовательную версию программы
 * 3. Реализовать асинхронную версию программы.
 * i - я задача считает сумму по i столбцу и i строке
 * 4. Написать тесты
 *
 * (тесты есть)
 */
public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum
                    && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = new Sums(getRowSum(matrix, i), getColSum(matrix, i));
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int finalI = i;
            int row = CompletableFuture.supplyAsync(() -> getRowSum(matrix, finalI)).get();
            int col = CompletableFuture.supplyAsync(() -> getColSum(matrix, finalI)).get();
            sums[i] = new Sums(row, col);
        }
        return sums;
    }

    public static int getRowSum(int[][] data, int index) {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[index][i];
        }
        return sum;
    }

    public static int getColSum(int[][] data, int index) {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i][index];
        }
        return sum;
    }
}
