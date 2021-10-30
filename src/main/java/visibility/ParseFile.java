package visibility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.function.Predicate;

/**
 *  + Избавиться от get set за счет передачи File в конструктор.
 *  + Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 *  + Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 *  Нарушен принцип единой ответственности. Тут нужно сделать два класса.
 *  + Методы getContent написаны в стиле копипаста. Нужно применить шаблон стратегия.
 *  content(Predicate<Character> filter)
 */
public class ParseFile {
    private final File file;

    /**
     * Избавиться от get set за счет передачи File в конструктор
     */
    public ParseFile(File file) {
        this.file = file;
    }

    /**
     * + Ошибки в IO. Не закрытые ресурсы.
     * + Чтение и запись файла без буфера.
     * + Методы getContent написаны в стиле копипаста. Нужно применить шаблон стратегия.
     * content(Predicate<Character> filter)
     * Выносим общую часть методов сюда
     *
     */
    private String content(Predicate<Character> predicate) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int i;
            while ((i = reader.read()) != -1) {
                if (predicate.test((char) i)) {
                    output.append((char) i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getcontent() {
        return content(ch -> true);
    }

    public String getContentWithoutUnicode() {
        return content(ch -> ch > 0x80);
    }
}