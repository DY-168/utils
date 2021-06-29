package com.spacedo.demo.io.socket.nio;

import java.nio.ByteBuffer;

public class Test {

	public static void main(String[] args) {
		ByteBuffer writeBuffer=ByteBuffer.allocate(128);
		writeBuffer.putChar('h');
		writeBuffer.putChar('e');
		writeBuffer.putChar('u');
		writeBuffer.putChar('u');
		writeBuffer.flip();
		System.out.println("位置"+writeBuffer.position()+"-"+writeBuffer.limit());
		writeBuffer.flip();
		System.out.println("位置"+writeBuffer.position()+"-"+writeBuffer.limit());
		
		while(writeBuffer.hasRemaining()) {
			System.out.println(writeBuffer.getChar());
		}
		
	}

}
