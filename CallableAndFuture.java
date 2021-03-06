package def.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = 
		threadPool.submit(
				new Callable<String>(){
					public String call() throws Exception {
						Thread.sleep(2000);
						return "hello";
					};
				}
		);
		System.out.println("Wait for result");
		try {
			System.out.println("Got result "+future.get());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExecutorService threadPool12 = Executors.newFixedThreadPool(10);
		
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool12);
		for(int i=1;i<=10;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>(){
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}				
			});			
		}
		for(int i=0;i<10;i++){
			try {
				System.out.println(completionService.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
