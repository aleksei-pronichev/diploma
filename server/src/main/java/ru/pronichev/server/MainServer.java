package ru.pronichev.server;

/* Класс запуса сервера для обработки входящих соединений
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */
public class MainServer {
    public static void main(String[] args) {
        try {
            final int port = 8189;  // порт
            final int maxObjSize = 1024 * 1024 * 100; // 100 mb максимальный размер файла
            new MyNettyServer(port, maxObjSize).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
