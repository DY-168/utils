package com.spacedo.demo.io.socket.bio;

import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *真实的Tomcat中,服务器还有超时时间,读超时,写超时,线程超时等.
 */
public class BioServer{
    private ExecutorService ioExecutor ;
    private ExecutorService workExecutor;
    //tomcat源码中就是并发10000
    private static final int maxThread = 10;
    private int threadNum ;
    private int port ;
    private static final int PORT =8888 ;

    public BioServer(int port,int threadNum) {
        this.port = port;
        this.workExecutor = Executors.newFixedThreadPool(threadNum);
    }

    public BioServer(){
        this.port = PORT;
        this.workExecutor = Executors.newFixedThreadPool(maxThread);
        this.ioExecutor = Executors.newFixedThreadPool(3);
    }

    public void start(){
        try {
            System.out.println("服务器启动");
            ServerSocket serverSocket = new ServerSocket(port);
            for (int i = 0; i < 3; i++) {
                ioExecutor.submit(new IoThread(i, serverSocket, workExecutor));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class IoThread implements Runnable{
        private int i;
        private ServerSocket serverSocket;
        private ExecutorService workExecutor;

        public IoThread(int i, ServerSocket serverSocket, ExecutorService workExecutor) {
            this.i = i;
            this.serverSocket = serverSocket;
            this.workExecutor = workExecutor;
        }

        @SneakyThrows
        public void run() {
            while (true) {
                System.out.println("第"+i+"个IO线程'accept start");
                Socket socket = serverSocket.accept();
                System.out.println("第"+i+"个IO线程 submit work");
                workExecutor.submit(new WorkThread(socket));
            }
        }

    }

    private class WorkThread implements Runnable{
        private Socket socket;

        public WorkThread(Socket socket) {
            this.socket = socket;
        }

        @SneakyThrows
        public void run() {
            for (int i = 0; i <2 ; i++) {


                //接收请求
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = br.readLine();
                System.out.println("收到请求:"+request);
                //servlet
                String response = servletWork(request);
                // 响应
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write(response);
                bw.flush();
                System.out.println("返回响应:"+response);
            }
            socket.close();
        }

        /**
         * 这是模拟的逻辑
         * @param request
         * @return
         */
        @SneakyThrows
        private String servletWork(String request) {
            Thread.sleep(new Random().nextInt(20));
            if("时间".equals(request)) {
                return "当前时间是:" + System.currentTimeMillis()+"\n";
            }
            return "请输入正确命令,输入时间\n";
        }
    }

    public static void main(String[] args) {
        BioServer server = new BioServer();
        server.start();
    }

    public void clientStart(){
        System.out.println("客户端启动");
        for (int i = 0; i < 20; i++) {
            workExecutor.submit(new Client(i, "127.0.0.1", 8888, "客户端测试报文"));
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

        @SneakyThrows
        @Override
        public void run() {
            try {
                Socket client=new Socket(ip, port);
                OutputStream out=client.getOutputStream();
                InputStream input=client.getInputStream();

                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
                BufferedReader reader=new BufferedReader(new InputStreamReader(input));
                writer.write("客户端"+num+"第一次发送："+sendMsg);
                writer.flush();
                String clientWord=reader.readLine();
                System.out.println("server response to "+num+"-"+clientWord);

                Thread.sleep(10);

                writer.write("客户端"+num+"第二次发送："+sendMsg);
                writer.flush();
                String clientWord2=reader.readLine();
                System.out.println("server response to "+num+"-"+clientWord2);

                writer.close();
                reader.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}