package org.personal.mason;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by mason on 8/6/15.
 */
@Component("receiverHandler")
public class ReceiverHandler extends SimpleChannelHandler {
    private static final Logger logger = LoggerFactory.getLogger(ReceiverHandler.class.getName());

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        byte[] recByte = buffer.copy().toByteBuffer().array();
        String recMsg = new String(recByte);
        logger.info("server received:" + recMsg.trim());
        Random random = new Random();
        int backWord = random.nextInt(10000);
        ChannelBuffer responseBuffer = new DynamicChannelBuffer(4);
        responseBuffer.readBytes(backWord);
        e.getChannel().write(responseBuffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        logger.error("{}", e.getCause());
        if (e.getChannel() != null) {
            e.getChannel().close().addListener(ChannelFutureListener.CLOSE);
        }
    }

}
