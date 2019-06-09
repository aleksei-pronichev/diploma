package ru.pronichev.server;

/* Инициализация
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import ru.pronichev.server.handlers.*;
import service.SQLService;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    private final int maxObjSize;                       // максимальный размер файла для сериализации
    private final SQLService sqlService;

    public ServerInitializer(int maxObjSize, SQLService sqlService) {
        this.maxObjSize = maxObjSize;
        this.sqlService = sqlService;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //выходные хендлеры
        pipeline.addLast("encoder", new ObjectEncoder());

        //входные хендлеры
        pipeline.addLast("decoder", new ObjectDecoder(maxObjSize, ClassResolvers.cacheDisabled(null)));
        pipeline.addLast("checkPacket", new CheckPackageHandler());
        pipeline.addLast("general", new GeneralHandler(sqlService));
    }
}
