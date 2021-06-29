package com.spacedo.demo.io.socket.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *真实的Tomcat中,服务器还有超时时间,读超时,写超时,线程超时等.
 */
public class BioServerTest {

    public static void main(String[] args) {
        BioServerTest test = new BioServerTest();
        test.clientStart();
    }

    public void clientStart(){
        System.out.println("客户端启动");
        for (int i = 0; i < 20; i++) {
            testExecutor.execute(new Client(i, "127.0.0.1", 8888, "客户端测试报文"));
        }
    }

    private ExecutorService testExecutor = Executors.newFixedThreadPool(20);;

    private class Client implements Runnable{
        private int num;
        private String ip;
        private int port;
        private String sendMsg;

        public Client(int num, String ip, int port, String sendMsg) {
            this.num = num;
            this.ip = ip;
            this.port = port;
            this.sendMsg = sendMsg;
        }

        @Override
        public void run() {
            try {
                Socket client=new Socket(ip, port);
                OutputStream out=client.getOutputStream();
                InputStream input=client.getInputStream();

                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
                BufferedReader reader=new BufferedReader(new InputStreamReader(input));
                writer.write("客户端"+num);
                writer.write("第一次发送：");
                Thread.sleep(100);
                writer.flush();
                writer.write(sendMsg+"\n");
                writer.flush();

                String clientWord=reader.readLine();
                System.out.println("server response to "+num+"-"+clientWord);

                Thread.sleep(new Random().nextInt(10));
                System.out.println("sleep end");
                writer.write("客户端"+num+"第二次发送："+sendMsg+"\n");
                System.out.println("write end");
                writer.flush();
                System.out.println("flash end");
                String clientWord2=reader.readLine();
                System.out.println("server response to "+num+"-"+clientWord2);

                writer.close();
                reader.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}