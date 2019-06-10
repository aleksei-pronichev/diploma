package ru.pronichev.monitor.monitors;

/* Обобщенный интерфейс для работы с мониторами
 *
 * @author Aleksei Pronichev
 * 02.04.2019
 */

import entities.Result;

public interface Monitor extends Runnable {

    // запрос ID монитора
    public String getID();

    // запрос результатов Мониторинга
    public Result[] getResults();

    // мы атакованы?
    public boolean isAttack();

    public Result[] attacks();

    // анализировать или нет?
    public void setAnalise(boolean analise);
}
