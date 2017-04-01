package zovlzhongguanhua.demo.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * MINA客户端
 */
public class MINAClient {

    public static void main(String args[]) {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        SocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ProtocolCodecFactory() {

            @Override
            public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
                System.out.println("getEncoder: ioSession=" + ioSession);
                return null;
            }

            @Override
            public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
                System.out.println("getDecoder: ioSession=" + ioSession);
                return null;
            }
        }));
        connector.setConnectTimeoutMillis(3000);
        connector.setHandler(new IoHandlerAdapter() {
            @Override
            public void sessionCreated(IoSession session) throws Exception {
                super.sessionCreated(session);
                System.out.println("sessionCreated: session=" + session);
            }

            @Override
            public void sessionOpened(final IoSession session) throws Exception {
                super.sessionOpened(session);
                System.out.println("sessionOpened: session=" + session);

                String sending = null;
                try {
                    sending = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    if (sending != null) {
                        System.out.println("Client：" + sending);
                        WriteFuture writeFuture = session.write(ByteBuffer.wrap(sending.getBytes()));
                        writeFuture.join();
                    }
                    try {
                        sending = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void sessionClosed(IoSession session) throws Exception {
                super.sessionClosed(session);
                System.out.println("sessionClosed: session=" + session);
            }

            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                super.messageReceived(session, message);
                System.out.println("messageReceived: session=" + session);
            }

            @Override
            public void messageSent(IoSession session, Object message) throws Exception {
                super.messageSent(session, message);
                System.out.println("messageSent: session=" + session);
            }
        });
        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 4722));
        future.addListener(new IoFutureListener() {

            @Override
            public void operationComplete(IoFuture s) {

            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IoSession ioSession = future.getSession();
        ioSession.write("Client：start");

        future.awaitUninterruptibly();
        future.getSession().getCloseFuture().awaitUninterruptibly();
        System.out.println("Client: stop");
    }
}
