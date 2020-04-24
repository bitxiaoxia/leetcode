package com.bitxiaoxia.test.retry;

import java.util.Random;

/**
 * @author JasonZhang
 * @date 2020/4/23 10:53
 */
public class RetryTest {
	public static void main(String[] args) {
		RetryTemplate r1 = new RetryTemplate() {
			@Override
			protected boolean doBiz() {
				print("r1", this.getRetryTime());
				return false;
			}

			@Override
			protected void finallyDo() {
				print("r1", this.getRetryTime());
			}
		}.setRetryTime(10).setSleepMills(1).setThreadName("r1");
		r1.retry();
	}

	/**
	 * 测试性能。同时调度10000个任务
	 */
	private static void testTwo() {
		Random random = new Random();
		Random boolRandom = new Random();
		for (int i = 0; i < 10000; i++) {
			final String name = "r" + i;
			RetryTemplate retry = new RetryTemplate() {
				@Override
				protected boolean doBiz() {
					print(name, this.getRetryTime());
					return boolRandom.nextBoolean();
				}

				@Override
				protected void finallyDo() {
					print(name, this.getRetryTime());
				}
			}.setRetryTime(random.nextInt(10)).setSleepMills(1000L).setThreadName(name);
			retry.retry();
		}
	}


	/**
	 * 测试多个任务同时调度
	 */
	private static void testOne() {
		RetryTemplate r1 = new RetryTemplate() {
			@Override
			protected boolean doBiz() {
				print("r1", this.getRetryTime());
				return false;
			}

			@Override
			protected void finallyDo() {
				print("r1", this.getRetryTime());
			}
		}.setRetryTime(3).setSleepMills(1000).setThreadName("r1");
		r1.retry();

		RetryTemplate r2 = new RetryTemplate() {
			@Override
			protected boolean doBiz() {
				print("r2", this.getRetryTime());
				return false;
			}

			@Override
			protected void finallyDo() {
				print("r2", this.getRetryTime());
			}
		}.setRetryTime(1).setSleepMills(2000).setThreadName("r2");
		r2.retry();

		RetryTemplate r3 = new RetryTemplate() {
			@Override
			protected boolean doBiz() {
				print("r3", this.getRetryTime());
				return true;
			}

			@Override
			protected void finallyDo() {
				print("r3", this.getRetryTime());
			}
		}.setRetryTime(100).setSleepMills(2000).setThreadName("r3");
		r3.retry();
	}

	private static void print(String name, int retryTime) {
		if (retryTime < 0) {
			System.out.println(String.format("%s %s retryTimes used!", System.currentTimeMillis(), name));
		} else {
			System.out.println(String.format("%s %s retry: %s", System.currentTimeMillis(), name, retryTime));
		}
	}
}
