package com.tangwan.consumer;

import com.tangwan.dto.SellorderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 批量收MQ消息
 * @FileName:SaveMessage.java
 * @Description: TODO()  
 * @Author: tangwan
 * @Date: 2016年5月26日 下午5:12:59
 * @since:  JDK 1.8
 */
public class SaveMessage implements Runnable {
	private Queue<SellorderDto> queue = new ConcurrentLinkedQueue<SellorderDto>();
	private final ExecutorService threadPool = Executors.newFixedThreadPool(200);// 线程池限制的线程数量
	private AtomicInteger batchCount;
	private AtomicLong accessTime;
	private Lock lock = new ReentrantLock();
	private Thread longRunThread;
	private volatile boolean batchHandlePerRound = false;
	private int batchSize = 10;
	private int batchMillis = 5000;

	public SaveMessage() {
		longRunThread = new Thread(this);
		longRunThread.start();
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public int getBatchMillis() {
		return batchMillis;
	}

	public void setBatchMillis(int batchMillis) {
		this.batchMillis = batchMillis;
	}

	public void add(SellorderDto sellorderDto) {
		queue.add(sellorderDto);
	}

	@Override
	public void run() {
		try {
			batchCount = new AtomicInteger(0);
			accessTime = new AtomicLong(System.currentTimeMillis());
			List<SellorderDto> batchData = new ArrayList<SellorderDto>();
			while (true) {
				SellorderDto item = queue.poll();// 同步队列中取出要消费的对象

				if (item != null) {
					batchData.add(item);// 将同步队列中取出的对象加入到处理的集合中
					if (batchCount.incrementAndGet() >= batchSize) {// 处理的集合中的数量超过伐值即进行批量处理
						try {
							batchHandlePerRound = false;
							lock.lock();
							handle(batchData);// 处理
							batchData = new ArrayList<SellorderDto>();
							batchCount.set(0);
							batchHandlePerRound = true;// 置处理状态，为超时处理使用
						} catch (Exception e) {
							
						} finally {
							lock.unlock();
						}
					}
				} else {
					Thread.sleep(1000);
				}
				long currentTime = System.currentTimeMillis();
				if (batchHandlePerRound) {// 已进行批量
					accessTime.set(currentTime);
					batchHandlePerRound = false;
				} else {// 超过时间伐值进行处理
					if ((currentTime - accessTime.get()) > batchMillis) {
						accessTime.set(currentTime);
						try {
							lock.lock();
							handle(batchData);
							batchData = new ArrayList<SellorderDto>();
							batchCount.set(0);
						} catch (Exception e) {
							
						} finally {
							lock.unlock();
						}
					}
				}
			}
		} catch (Exception ex) {
			
		}
	}

	long start;
	long end;
	long cost;

	private void handle(final List<SellorderDto> batchData) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				if (batchData.size() > 0) {
					int result = 0;
					start = System.currentTimeMillis();
					try {
						//批量保存销售订单
						//result = sellorderErpProcessor.saveAllSellorderList(batchData);
					} catch (Exception e) {
						//sellorderBackOdcProvider.send(batchData);//发送至odc-erp 单个保存
						batchData.clear();
						return ;
					}
					if(result > 0){
						cost = System.currentTimeMillis();
						//仓库信息发送至odc-erp端
					}
					end = System.currentTimeMillis();
					//正式环境时 建议注释此发送日志，因为会影响处理时间
					batchData.clear();
				}
			}
		});
	}
}