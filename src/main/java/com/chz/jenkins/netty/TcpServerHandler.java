package com.chz.jenkins.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: chz
 * @Date: 2021/12/23/10:02
 * @Description:
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {
    public TcpServerHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object source) throws Exception {
        // 拿到传过来的msg数据，开始处理
        ByteBuf recvmg = (ByteBuf) source;// 转化为ByteBuf
        byte[] data = new byte[recvmg.readableBytes()];
        recvmg.readBytes(data);
        String request = new String(data, StandardCharsets.UTF_8);
        //写给客户端
        String response = "我是反馈的信息:收到消息再见";
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
        //ctx.writeAndFlush(recvmg);// 收到及发送，这里如果没有writeAndFlush，上面声明的ByteBuf需要ReferenceCountUtil.release主动释放

    }
}
