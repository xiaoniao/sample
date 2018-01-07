package com.liuzhuang.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {

		// 会阻塞住所以服务端客户端各开一个线程

		new Thread(new Runnable() {
			public void run() {
				Server server = new Server();
				server.start();
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				Client client = new Client();
				client.start();
			}
		}).start();
	}

	public void start() {
		Socket client = null;
		try {
			client = new Socket("127.0.0.1", 9009); // 建立连接 TCP/IP

			Writer writer = new OutputStreamWriter(client.getOutputStream());
			writer.write("你好\r\n\r\n");
			writer.flush();

			Reader reader = new InputStreamReader(client.getInputStream());
			StringBuffer stringBuffer = new StringBuffer();
			int chars;
			while ((chars = reader.read()) != -1) {
				stringBuffer.append((char) chars);
			}
			System.out.println(stringBuffer);
			
			reader.close();
			writer.close(); // 关闭流才会读出来-1,或者自己约定格式 对应Server 28行
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
