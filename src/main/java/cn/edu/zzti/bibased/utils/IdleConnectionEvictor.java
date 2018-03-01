package cn.edu.zzti.bibased.utils;

import org.apache.http.conn.HttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

/**
 * 定时清理无效连接
 */
public class IdleConnectionEvictor extends Thread {

	private final HttpClientConnectionManager connMgr;

	private volatile boolean shutdown;
	
	private long waitTime;

	public IdleConnectionEvictor(HttpClientConnectionManager connMgr , long waitTime) {
		super();
		this.connMgr = connMgr;
		this.waitTime = waitTime;
		this.setDaemon(true);
		this.start();
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					this.wait(waitTime);
					//关闭无效的连接
					connMgr.closeExpiredConnections();
					// 选择关闭 空闲15秒的链接
					connMgr.closeIdleConnections(15, TimeUnit.SECONDS);
				}
				System.out.printf("clear Thread!!!!");
			}
		} catch (InterruptedException ex) {
		}
	}

	public void shutdown() {
		synchronized (this) {
			shutdown = true;
			notifyAll();
		}
	}
}
