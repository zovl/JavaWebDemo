package zovlzhongguanhua.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 */
public class SocketClient {

    public static void main(String args[]) {
        try {
            Socket socket = new Socket("127.0.0.1", 4722);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            final PrintWriter os;
            final BufferedReader is;
            if (socket != null) {
                os = new PrintWriter(socket.getOutputStream());
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String sending;
                String received;
                sending = reader.readLine();
                while (sending != "stop") {
                    if (sending != null) {
                        System.out.println("Client：" + sending);
                        os.println(sending);
                        os.flush();
                    }

                    received = is.readLine();
                    if (received != null) {
                        System.out.println("Server：" + received);
                    }

                    sending = reader.readLine();
                }
            } else {
                is = null;
                os = null;
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (is !=  null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader !=  null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Error：" + e);
        }
    }
}
