package socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread {

    private Socket s;

    public SendThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try (OutputStream os = s.getOutputStream();
             DataOutputStream dos = new DataOutputStream(os)) {
            while (true) {
                System.out.println("请输入要发送的信息：");
                Scanner s = new Scanner(System.in);
                String msg = s.next();
                dos.writeUTF(msg);
                if (msg.equals("exit")) {
                    System.out.println("退出互聊");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
