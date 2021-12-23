package com.chz.jenkins.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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
        ctx.writeAndFlush(recvmg);// 收到及发送，这里如果没有writeAndFlush，上面声明的ByteBuf需要ReferenceCountUtil.release主动释放

    }
}
