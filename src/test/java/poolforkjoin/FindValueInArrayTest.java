package poolforkjoin;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindValueInArrayTest {

    @Test
    public void whenLengthLessTen() {
        int[] arr = {1, 2, 3};
        int val = 3;
        assertThat(FindValueInArray.find(arr, val), is(2));
    }

    @Test
    public void whenLengthMoreTen() {
        int[] arr = new int[30];
        for (int i = 0; i < 30; i++) {
            arr[i] = i;
        }
        int val = 3;
        assertThat(FindValueInArray.find(arr, val), is(3));
    }

    @Test
    public void whenOne() {
        int[] arr = new int[1];
        for (int i = 0; i < 1; i++) {
            arr[i] = i;
        }
        int val = 0;
        assertThat(FindValueInArray.find(arr, val), is(0));
    }

    /**
     * Добавьте тест, когда метод ничего не найдет и вернет -1
     * Значение 0 индекс не находится
     */
    @Test
    public void whenNotFindValue() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int val = 0;
        assertThat(FindValueInArray.find(arr, val), is(-1));
    }
}