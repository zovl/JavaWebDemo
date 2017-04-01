package zovlzhongguanhua.demo.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.example.echoserver.EchoProtocolHandler;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * MINA服务器
 */
public class MINAServer {

    public static void main(String args[]) {

        SocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.setReuseAddress( true );
        acceptor.setHandler(new EchoProtocolHandler() {

            @Override
            public void sessionCreated(IoSession session) {
                super.sessionCreated(session);
                System.out.println("sessionCreated: session=" + session);
            }

            @Override
            public void sessionClosed(IoSession session) throws Exception {
                super.sessionClosed(session);
                System.out.println("sessionClosed: session=" + session);
            }

            @Override
            public void sessionOpened(IoSession session) throws Exception {
                super.sessionOpened(session);
                System.out.println("sessionOpened: session=" + session);
            }

            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                super.messageReceived(session, message);
                System.out.println("messageReceived: session=" + session);
            }

            @Override
            public void messageSent(IoSession session, Object message) throws Exception {
                super.messageSent(session, message);
                System.out.println("messageSent: message=" + message);
            }
        });
        try {
            acceptor.bind(new InetSocketAddress(4722));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (;;) {
            System.out.println("R: " + acceptor.getStatistics().getReadBytesThroughput() + ", W: " + acceptor.getStatistics().getWrittenBytesThroughput());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
