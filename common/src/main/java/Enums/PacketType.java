package Enums;

/* Типы классов пакетов
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

public enum PacketType {
    NONE,                   // пустой пакет
    NO_VALID,               // ошибка

    TASK,               // Выдача задания/настройка монитора
    TRAFFIC,            // Информация о трафике

    TASK_RESPONSE,             // Настройки монитора ответ
    TRAFFIC_RESPONSE,          // Ответ на запросы о трафике
}
