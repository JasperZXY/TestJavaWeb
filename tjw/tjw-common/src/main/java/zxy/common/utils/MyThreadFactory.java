package zxy.common.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class MyThreadFactory implements ThreadFactory {
	private String namePrefix;
	private AtomicLong count;

	public MyThreadFactory(String namePrefix) {
		this.namePrefix = namePrefix;
		this.count = new AtomicLong();
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, namePrefix + "-" + count.incrementAndGet());
	}

}