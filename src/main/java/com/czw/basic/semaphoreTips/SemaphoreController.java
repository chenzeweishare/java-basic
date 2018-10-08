package com.czw.basic.semaphoreTips;

import java.util.concurrent.*;


/***
 * ReloadableConfig 为分布式配置中心获取值 这可以写死
 */

public class SemaphoreController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SemaphoreController.class.getName());

	private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "RebateSemaphoreController");
		}
	});

    private static final ConcurrentHashMap<String, SemaphoreWarpper> blockingQueues = new ConcurrentHashMap<String, SemaphoreWarpper>();


    static {
		syncBlockingQueueCount();
	}


    /**
     * 同步block数值用的, 主要是定时重置信号量数据, 第一次是10秒钟后重置, 剩下15秒钟后重置
     */
    private static void syncBlockingQueueCount() {
        // 定时去跑监控diamond的值是否有改变
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!blockingQueues.isEmpty()) {
                    for (String key : blockingQueues.keySet()) {
                        // ReloadableConfig.getInt(getBlockQueueCountByName(key), 0);
                        int newCount =0;
                        //有值时
                        if (0 != newCount) {
                            int oldCount = blockingQueues.get(key).getQueueCount();
                            if (newCount != oldCount) {
                                log.info("blockingQueueCount modify,methodName:" + key + ",old:" + oldCount + ",new:" + newCount);
                                //重置信号数量, 并不是释放, 只是再次创建
                                blockingQueues.get(key).resetSemaphore(newCount);
                            }
                        }
                    }
                }
            }
        //毫秒  10秒, 15秒钟
        }, 10000, 15000, TimeUnit.MILLISECONDS);
    }


	public static <T> T execute(String methodName, Callable<T> r) throws Exception {

		// 默认如果说配置了全局的开启阻塞数，就走全局的阻塞值
        //ReloadableConfig.getInt(getGlobalBlockQueueCount(), 0);
		int globalBlockingCount = 0;
		if (0 != globalBlockingCount) {
            //ReloadableConfig.getInt(getGlobalBlockQueueTimeOut(), 3000);
			int timeout = 3000;
			return getSemaphoreWarpper(methodName, globalBlockingCount).execute(r, methodName, timeout);
		}

		// 如果没有配置全局的，就走单独方法配置的值，如果没有配置直接回调
        //ReloadableConfig.getInt(getBlockQueueCountByName(methodName), 0);
		int methodBlockingCount = 0;
		if (0 == methodBlockingCount) {
			return r.call();
		}
        // ReloadableConfig.getInt(getBlockQueueTimeOutByName(methodName), 3000);
		int timeout =3000;
		return getSemaphoreWarpper(methodName, methodBlockingCount).execute(r, methodName, timeout);
	}



	public static SemaphoreWarpper getSemaphoreWarpper(String method, int blockingCount) {
		SemaphoreWarpper bqw = blockingQueues.get(method);
		if (null == bqw) {
			synchronized (blockingQueues) {
				bqw = blockingQueues.get(method);
				if (null == bqw) {
					bqw = new SemaphoreWarpper(blockingCount);
					blockingQueues.put(method, bqw);
				}
			}
		}
		return bqw;
	}


	public static SemaphoreWarpper getSemaphoreWarpper(String method) {
		return blockingQueues.get(method);
	}

	public static String getBlockQueueCountByName(String methodName) {
		return methodName + ".blockingQueue.count";
	}

	public static String getGlobalBlockQueueCount() {
		return "global.blockingQueue.count";
	}

	public static String getGlobalBlockQueueTimeOut() {
		return "global.blockingQueue.timeout";
	}

	public static String getBlockQueueTimeOutByName(String methodName) {
		return methodName + ".blockingQueue.timeout";
	}

	public static String printfMapMessage() {
		StringBuilder sb = new StringBuilder();
		if (!blockingQueues.isEmpty()) {
			for (String key : blockingQueues.keySet()) {
				sb.append("METHODNAME:" + key);
				sb.append("\n");
				sb.append("BLOCKING：" + blockingQueues.get(key).getBlockingCount());
				sb.append("\n");
				sb.append("---------------------------");
				sb.append("\n");
			}
		}
		return sb.toString();
	}



    public static void main(String[] args) {
        ConcurrentHashMap<String, String> blockingQueues = new ConcurrentHashMap<String, String>();
        System.out.println(blockingQueues.putIfAbsent("abc", "123"));
        System.out.println(blockingQueues.putIfAbsent("abc", "124"));
        System.out.println(blockingQueues.putIfAbsent("abc", "125"));
        System.out.println(blockingQueues.put("abc", "126"));
        System.out.println(blockingQueues.put("abc", "127"));

    }

}
