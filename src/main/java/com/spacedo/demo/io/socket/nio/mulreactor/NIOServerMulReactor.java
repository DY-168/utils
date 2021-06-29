package com.spacedo.demo.io.socket.nio.mulreactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerMulReactor {

	public static void main(String[] args) {
		try {
			//����һ��server
			ServerSocketChannel server = ServerSocketChannel.open();
			server.bind(new InetSocketAddress("127.0.0.1", 8888));
			//����server��acceptΪ������ģʽ
			server.configureBlocking(false);
			
			//����Selector
			Selector selector=Selector.open();
			//Ϊserverע��selector����Ȥ���¼�
			server.register(selector, SelectionKey.OP_ACCEPT);
			
			//��ȡ��Reactorģʽ�µ���Reactor������
			int coreNum=Runtime.getRuntime().availableProcessors();
			System.out.println("�õ�"+coreNum+"��������Ԫ");
			Processor[] processors=new Processor[coreNum];
			int index=0;
			for(int i=0;i<coreNum;i++) {
				processors[i]=new Processor();
			}
			
			while(true) {
				//����ֱ����ͨ����ѡ��
				selector.select();
				Set<SelectionKey> keys=selector.selectedKeys();
				Iterator<SelectionKey>iterator=keys.iterator();
				while(iterator.hasNext()) {
					SelectionKey  key=iterator.next();
					//����ǰkey��selector����ɾ��,��������һ�����key�Ͳ������
					iterator.remove();
					if(key.isAcceptable()) {
						SocketChannel client=server.accept();
						System.out.println("accept connection from "+client.getRemoteAddress());
						//����client socket�Զ�������
						client.configureBlocking(false);
						Processor processor=processors[index%coreNum];
						index=(index+1)%coreNum;
						processor.wakeup();
						processor.addChannel(client);
						
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
