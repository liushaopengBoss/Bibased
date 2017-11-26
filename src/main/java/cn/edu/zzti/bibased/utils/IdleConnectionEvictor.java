package cn.edu.zzti.bibased.utils;

import org.apache.http.conn.HttpClientConnectionManager;

public class IdleConnectionEvictor extends Thread {

	private final HttpClientConnectionManager connMgr;

	private volatile boolean shutdown;
	
	private Integer waitTime;

	public IdleConnectionEvictor(HttpClientConnectionManager connMgr , Integer waitTime) {
		super();
		this.connMgr = connMgr;
		this.waitTime = waitTime;
		this.start();
	}

	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(waitTime);
					//关闭无效的连接
					connMgr.closeExpiredConnections();
				}
			}
		} catch (InterruptedException ex) {
		}
	}

	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}
}
