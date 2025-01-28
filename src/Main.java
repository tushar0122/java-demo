import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(8000);
        var list = new ArrayList<Integer>();
        for(int i=0;i<1000000;i++){
            list.add(i);
        }
        ForkJoinPool customthreadPool = new ForkJoinPool(1000);
        customthreadPool.submit(()->{
            list.parallelStream().forEach(x->{
                for(int i=1;i<=2;i++){
                    try {
                        Thread.sleep(100); // Calling Bigquery
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName()+ " Hello: "+i + " Line : " + x*i);
                }
            });
        }).join();
        customthreadPool.shutdown();
    }
}