package service;

/* Интерфейс для работы c БД
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import entities.Task;
import entities.Traffic;

public interface SQLService {

    // добавление сущности в базу
    public void add(Object entity);

    // удаление сущности из базы
    public void remove(Object entity);

    // запрос задачи по ID
    public Task getTask(String id);

    // запрос всех существующих задач в БД
    public Task[] getTasks();

    // получить трафик по задаче
    public Traffic[] getTraffic(Task task);
}
