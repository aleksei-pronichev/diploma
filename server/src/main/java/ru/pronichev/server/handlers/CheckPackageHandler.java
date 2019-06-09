package ru.pronichev.server.handlers;

/* Контрольная проверка на соответвие стандартам входящего пакета
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import packets.Packet;
import packets.PacketError;

public class CheckPackageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {

        if (o != null && o instanceof Packet) {
            ctx.fireChannelRead(o);
        } else {
            noValid(ctx, new PacketError("Сообщение не являеться валидным пакетом"));
        }
    }

    // если клиент будет нам отсылать невалидные сообщения мы ему сообщим об этом
    private void noValid(ChannelHandlerContext ctx, Packet packet) {
        ctx.writeAndFlush(packet);
    }

    // возникновение исключения в данном контексте приравнивается к невалидному сообщению
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        noValid(ctx, new PacketError("Critical Error with Validation"));
    }
}
