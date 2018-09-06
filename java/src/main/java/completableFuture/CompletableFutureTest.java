package completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void main(String[] args) {
        thenApplyExample();
    }

    static void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            if (Thread.currentThread().isDaemon()) {
                throw new IllegalArgumentException("Not deamon");
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }).thenApply(s -> {
            if (Thread.currentThread().isDaemon()) {
                throw new IllegalArgumentException("Not deamon");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toLowerCase().substring(0,3);
        });
        String msg = cf.getNow(null).toString();
        if(!"mes".equals(msg)) {
            throw new IllegalArgumentException("not message");
        }
    }
}
