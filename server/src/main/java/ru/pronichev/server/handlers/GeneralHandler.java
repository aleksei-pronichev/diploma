package ru.pronichev.server.handlers;

/* Последняя проверка, отправка пакетов на обработку
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import Enums.TaskType;
import entities.Task;
import entities.Traffic;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import packets.*;
import service.SQLService;

import java.util.*;


public class GeneralHandler extends ChannelInboundHandlerAdapter {
    private SQLService sqlService;

    public GeneralHandler(SQLService sqlService) {
        this.sqlService = sqlService;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = (Packet) msg;
        switch (packet.getPacketType()) {
            case TASK:
                setTask(ctx, (PacketTask) packet);
                break;
            case TRAFFIC: {
                getTraffic(ctx,(PacketTraffic) packet);
                break;
            }
            default:
                packet.toString();
                ctx.writeAndFlush(
                        new PacketError("Server: Вы послали команду, которая не поддерживается данным устройством"
                                + packet.getPacketType()));
        }
    }

    // ответ на запрос постановки задачи
    private void setTask(ChannelHandlerContext channel, PacketTask packet) {
        if (checkServer(packet.getKey())) {
            sqlService.add(new Task(packet, TaskType.LOCAL));
            channel.writeAndFlush(
                    new PacketTaskResponse(packet, true));
        } else {
            channel.writeAndFlush(
                    new PacketTaskResponse(
                            packet, false, "Сервер не являеться мастером с необходимыми правами"));
        }
    }

    // формирование ответа на запрос
    private void getTraffic(ChannelHandlerContext channel, PacketTraffic packet) {
        Task task = sqlService.getTask(packet.getMonitor());
        if (task == null) {
            channel.writeAndFlush(new PacketError("Задача не была найдена в БД" + packet.getMonitor()));
            return;
        }

        Date start = packet.getDateStart();
        Date finish = packet.getDateFinish();
        Traffic[] traffic = sqlService.getTraffic(task);

        if (start == null && finish == null) {
            channel.writeAndFlush(new PacketTrafficResponse(packet, traffic));
            return;
        }

        List<Traffic> list = new ArrayList<>();

        if (start == null) {
            for (int i = 0; i < traffic.length; i++) {
                if (start != null && traffic[i].getDate().compareTo(start)>=0 ||
                        finish != null && traffic[i].getDate().compareTo(finish)<=0) {
                    list.add(traffic[i]);
                }
            }
        }
        channel.writeAndFlush(new PacketTrafficResponse(packet, list.toArray(new Traffic[list.size()])));
    }

    // специальный метод, используется для проверки подленности мастера заданий, не реализован
    private boolean checkServer(String key) {
        return true;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.writeAndFlush(new PacketError("Critical general problem"));
    }
}
