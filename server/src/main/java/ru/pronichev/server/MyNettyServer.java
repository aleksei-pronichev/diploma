package ru.pronichev.server;

/* Сервер реализованный c помощью библиотеки Netty
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import service.HybernateSQLservice;
import service.SQLService;

public class MyNettyServer {
    private final int port;
    private final int maxObjSize;
    private final SQLService sqLservice = new HybernateSQLservice();


    public MyNettyServer(int port, int maxObjSize) {
        this.port = port;
        this.maxObjSize = maxObjSize;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer(maxObjSize, sqLservice))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
