package ru.pronichev.monitor.monitors;

/* Удаленный монитор, не хватает анализатора
 *
 * @author Aleksei Pronichev
 * 02.04.2019
 */

import entities.Result;
import entities.Traffic;
import packets.*;
import service.Analizator;
import service.ConnectionAdapter;
import service.NettyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConnectMonitor implements Monitor {

    private Analizator analizator;
    private String id;
    private ConnectionAdapter adapter;
    private boolean analise;
    private Traffic[] traffic;
    private Result[] results;

    public ConnectMonitor(String id, String address, int port, boolean analise) {
        this.id = id;
        this.adapter = new NettyAdapter(address, port);
        this.analise = analise;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override //тут в первой части нужен анализатор, и реализация запуска
    public Result[] getResults() {
        if (analise) {
            if (adapter.connect()) {
                Packet packet = adapter.sendPacket(new PacketTraffic(id));
                switch (packet.getPacketType()) {
                    case TRAFFIC_RESPONSE:
                        PacketTrafficResponse packetResponse = (PacketTrafficResponse) packet;
                        traffic = packetResponse.getTraffic();
                        break;
                    default:
                        traffic = null;
                }
            }
            return null; //вот прямо тут, что бы ноль не возвращал
        } else {
            if (adapter.connect()) {
                Packet packet = adapter.sendPacket(new PacketResult(id));
                switch (packet.getPacketType()) {
                    case RESULT_RESPONSE:
                        PacketResultResponse packetResponse = (PacketResultResponse) packet;
                        results = packetResponse.getResults();
                        break;
                    default:
                        results = null;
                }
            } else {
                results = null;
            }
        }
        return results;
    }


    @Override
    public boolean isAttack() {
        if (results == null) {
            if (getResults() == null)
                return false;
        }
        for (Result r : results) {
            if (r.isAttack())
                return true;
        }
        return false;
    }

    @Override
    public Result[] attacks() {
        List<Result> attacks = new ArrayList<>();
        for (Result r : results) {
            if (r.isAttack())
                attacks.add(r);
        }
        return attacks.toArray(new Result[attacks.size()]);
    }

    @Override
    public void setAnalise(boolean analise) {
        this.analise = analise;
    }

    @Override
    public void run() {

    }
}
