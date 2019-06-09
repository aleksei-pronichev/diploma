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
    RESULT,             // Запрос результата мониторов

    TASK_RESPONSE,             // Настройки монитора ответ
    TRAFFIC_RESPONSE,          // Ответ на запросы о трафике
    RESULT_RESPONSE            // Результат мониторов
}
