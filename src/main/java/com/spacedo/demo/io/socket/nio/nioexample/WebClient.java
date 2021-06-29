package com.spacedo.demo.io.socket.nio.nioexample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WebClient {

	public static void main(String[] args) {
		try {
			SocketChannel client=SocketChannel.open();
			client.configureBlocking(false);
			client.connect(new InetSocketAddress("127.0.0.1", 8888));
			ByteBuffer writeBuffer=ByteBuffer.allocate(128);
			writeBuffer.put("hello world!".getBytes());
			writeBuffer.flip();
			
			client.write(writeBuffer);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
