package socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    //服务器开启8888端口，并监听着，时刻等待客户端的连接请求
    //客户端知道服务端的ip地址和监听端口号，发出请求到服务端，服务端的端口地址是系统分配的，通常都会大于1024
    //一旦建立了连接，服务端会得到一个新的Socket对象，该对象负责与客户端进行通信

    //注意：在开发调试的时候，如果修改了服务器的Server代码，要关闭启动的Server，否则新的Server不能启动，因为8888端口被占用了

    public static void main(String[] args) {

        //连接到本机的8888端口
        try{
            Socket s=new Socket("127.0.0.1",8888);
            System.out.println(s);
            //1.收发数字
            //打开输出流
//            OutputStream os=s.getOutputStream();
//            //发送数字10到服务端
//            os.write(10);
//            os.close();

            //2.收发字符串
//            OutputStream os=s.getOutputStream();
//            DataOutputStream dos=new DataOutputStream(os);
//            dos.writeUTF("Legendary!");
//            //使用Scanner
//            Scanner scanner=new Scanner(System.in);
////            String msg2=scanner.next();
////            dos.writeUTF(msg2);
////            InputStream is=s.getInputStream();
////            DataInputStream dis=new DataInputStream(is);


            //C-S互聊
//            while(true){
//                System.out.println("Client输入：");
//                String msg3=scanner.next();
//                dos.writeUTF(msg3);
//                if(msg3.equals("exit")){
//                    System.out.println("退出互聊");
//                    break;
//                }
//                String msg4=dis.readUTF();
//                System.out.println("Client接收："+msg4);
//                if(msg4.equals("exit")){
//                    System.out.println("退出互聊");
//                    break;
//                }
//            }

            //同时收发消息
            //上面的互聊并非同步的，当客户端或服务端在等待输入时，就无法输出收到的消息，因此借助多线程实现同步互聊
            new SendThread(s).start();
            new ReceiveThread(s).start();


//            dos.close();
//            dis.close();

        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
