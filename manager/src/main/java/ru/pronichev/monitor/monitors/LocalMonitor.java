package ru.pronichev.monitor.monitors;

/* Будущий локильный монитор
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import entities.Result;

public class LocalMonitor implements Monitor {

    @Override
    public String getID() {
        return null;
    }

    @Override
    public Result[] getResults() {
        return new Result[0];
    }

    @Override
    public boolean isAttack() {
        return false;
    }

    @Override
    public Result[] attacks() {
        return new Result[0];
    }

    @Override
    public void setAnalise(boolean analise) {

    }

    @Override
    public void run() {

    }
}
