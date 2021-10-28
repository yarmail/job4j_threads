package filedownload;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Примечание
 * Такой класс можно переделать в Record
 */
public final class Wget implements Runnable {
    private final String url;
    /**
     * Предполанаем, что наша скорость задана в байтах/сек
     */
    private final int speed;
    private final String target;

    public Wget(String url, int speed, String target) {
        this.url = url;
        this.speed = speed;
        this.target = target;
    }

    /**
     * Разработать метод run()
     * Программа должна скачивать файл из сети
     * с ограничением по скорости скачки.
     * Чтобы ограничить скорость скачивания,
     * нужно засечь время скачивания 1024 байт.
     * Если время меньше указанного (int speed),
     * то нужно выставить
     * паузу за счет Thread.sleep(speed - rsl);
     * Пауза должна вычисляться, а не быть константой.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(target)) {
                    byte[] dataBuffer = new byte[1024];
                    int bytesRead;
                    long time = System.currentTimeMillis();
                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                        fileOutputStream.write(dataBuffer, 0, bytesRead);
                        long rsl = System.currentTimeMillis() - time;
                        /*
                        вычисляем скорость в байтах в секунду
                        */
                        int speedTest = (int) (1024 / (rsl / 1000));
                        if (speedTest < speed) {
                            Thread.sleep((speed - speedTest) * 1000);
                        }
                        time = System.currentTimeMillis();
                    }
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed, "temp.xml"));
        wget.start();
        wget.join();
    }
}