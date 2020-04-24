package com.bitxiaoxia.test.retry;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author JasonZhang
 * @date 2020/4/23 10:44
 */
public abstract class RetryTemplate {
	private static final int DEFAULT_RETRY_TIME = 1;
	private static final long DEFAULT_SLEEP_TIME = TimeUnit.SECONDS.toMillis(60);
	private int retryTime = DEFAULT_RETRY_TIME;
	/**
	 * 多久重试一次，默认60s。防止雪崩
	 */
	private long sleepTime = DEFAULT_SLEEP_TIME;
	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	private String threadName = "rt";

	public String getThreadName() {
		return threadName;
	}

	public RetryTemplate setThreadName(String threadName) {
		this.threadName = threadName;
		return this;
	}

	/**
	 * 设置重试次数，允许设置0次。重试0次，意味着执行一次doBiz方法后立即执行finallyDo方法。
	 *
	 * @param retryTime 重试次数。
	 * @return
	 */
	public RetryTemplate setRetryTime(int retryTime) {
		if (retryTime < 0) {
			throw new IllegalArgumentException("RetryTemplate retryTime shouldn't be smaller than 0");
		}
		this.retryTime = retryTime;
		return this;
	}

	public int getRetryTime() {
		return retryTime;
	}

	/**
	 * 设置休眠时间，默认为60s。
	 *
	 * @param sleepTime
	 * @return
	 */
	public RetryTemplate setSleepMills(long sleepTime) {
		if (sleepTime <= 0) {
			throw new IllegalArgumentException("RetryTemplate sleepTime should be bigger than 0");
		}
		this.sleepTime = sleepTime;
		return this;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	/**
	 * 单次执行任务，返回值为任务是否执行成功
	 *
	 * @return
	 * @throws
	 */
	protected abstract boolean doBiz();

	/**
	 * 如果重试次数耗尽了，仍然没有成功，应该怎么做
	 * 或者进程被意外杀死，应该怎么做
	 */
	protected abstract void finallyDo();

	private void runOnce() {
		boolean result = this.doBiz();
		if (!result) {
			this.retryTime--;
			if (this.getRetryTime() < 0) {
				finallyDo();
				executor.shutdown();
			}
		} else {
			this.retryTime=-1;
			executor.shutdown();
		}
	}

	public void retry() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			//目前如果有重试任务，但是主进程被杀了，不会继续重试，且不会执行finallyDo方法。
			//finallyDo 不能放在这里。否则每次RetryTemplate被杀死都会执行一次finallyDo方法。
			//TODO 如果主进程被意外杀死了，需要执行finallyDo方法。
			executor.shutdown();
		}));
		executor.scheduleAtFixedRate(this::runOnce, 0, sleepTime, TimeUnit.MILLISECONDS);
	}

}
