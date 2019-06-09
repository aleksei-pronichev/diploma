package Enums;

/* Типы Трафика
 * Новые типы трафика добавляем сюда
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

public enum TrafficType {
    ALL ("Весь"),   // весь трафик

    UDP("UDP"),        // UDP траффик
    TCP("TCP");        // TCP траффик

    private final String name;

    TrafficType(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
