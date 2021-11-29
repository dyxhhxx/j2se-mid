package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{

    //服务器开启8888端口，并监听着，时刻等待客户端的连接请求
    //客户端知道服务端的ip地址和监听端口号，发出请求到服务端，服务端的端口地址是系统分配的，通常都会大于1024
    //一旦建立了连接，服务端会得到一个新的Socket对象，该对象负责与客户端进行通信

    //注意：在开发调试的时候，如果修改了服务器的Server代码，要关闭启动的Server，否则新的Server不能启动，因为8888端口被占用了

    public static void main(String[] args) {

        try{
            //服务器打开端口8888
            ServerSocket ss=new ServerSocket(8888);
            //在8888端口上监听，看是否有连接请求过来
            System.out.println("监听在端口号：8888");
            Socket s=ss.accept();
            //监听在此处，不会执行下面的代码，直到有客户端连上
            System.out.println("有连接过来"+s);
            //一旦建立了连接，客户端和服务端就可以通过Socket进行通信了
//
//            //1.收发数字
//            //客户端打开输出流，并发送数字10
//            //服务端打开输入流，接受数字10，并打印
//            //打开输出流
//            InputStream is=s.getInputStream();
//            //读取客户端发送的数据
//            int msg=is.read();
//            //打印出来
//            System.out.println(msg);


            //2.收发字符串
            //直接用字节流收发字符串比较麻烦，使用数据流对字节流进行封装，这样收发字符串比较容易
            //1、把输出流封装在DataOutputStream中，使用writeUTF发送字符串
            //2、把输入流封装在DataInputStream中，使用readUTF读取字符串
//            InputStream is=s.getInputStream();
//            DataInputStream dis=new DataInputStream(is);
//            String msg1=dis.readUTF();
//            System.out.println(msg1);
//            String msg2= dis.readUTF();
//            System.out.println(msg2);

//            Scanner scanner=new Scanner(System.in);
//            OutputStream os=s.getOutputStream();
//            DataOutputStream dos=new DataOutputStream(os);

//            while(true){
//                String msg4=dis.readUTF();
//                System.out.println("Server接收："+msg4);
//                if(msg4.equals("exit")){
//                    System.out.println("退出互聊");
//                    break;
//                }
//                System.out.println("Server输入：");
//                String msg3=scanner.next();
//                dos.writeUTF(msg3);
//                if(msg3.equals("exit")){
//                    System.out.println("退出互聊");
//                    break;
//                }
//            }

            new SendThread(s).start();
            new ReceiveThread(s).start();



        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
