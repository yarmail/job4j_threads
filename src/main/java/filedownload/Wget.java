package filedownload;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public record Wget(String url, int speed, String target) implements Runnable {

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
                        if (rsl < speed) {
                            Thread.sleep(speed - rsl);
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
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed, "temp.xml"));
        wget.start();
        wget.join();
    }
}