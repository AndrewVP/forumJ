package org.forumj.forum.business;

import java.util.concurrent.*;

/**
 * Created by Andrew on 26/02/2017.
 */
public class Application {

    //Singleton start
    private static Application ourInstance = new Application();

    public static Application getInstance() {
        return ourInstance;
    }

    private Application() {
    }
    //Singleton end

    private ExecutorService asyncEventsProcessingPool;

    private BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();

    public void startAsyncEventsProcessingPool(){
        asyncEventsProcessingPool = new ThreadPoolExecutor(1, 3, 60, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());
    }

}
