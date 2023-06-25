package sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class Test {
	public static void main(String [] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		ReadWriteLock RW = new ReadWriteLock();
		
		
		executorService.execute(new Writer(RW));
		executorService.execute(new Writer(RW));
		executorService.execute(new Writer(RW));
		executorService.execute(new Writer(RW));
		
		executorService.execute(new Reader(RW));
		executorService.execute(new Reader(RW));
		executorService.execute(new Reader(RW));
		executorService.execute(new Reader(RW));
		
		
	}
}


class ReadWriteLock{
    private Semaphore reading_thread = new Semaphore(1);
    private Semaphore writing_thread = new Semaphore(1);
	public int num_rt = 0;
	
	public void readLock() {
		try{
            reading_thread.acquire();
        }
        catch (InterruptedException handle) {}
		num_rt++;

		if(num_rt==1){
			try{
				writing_thread.acquire();
			}
			catch (InterruptedException handle) {}
		}

        reading_thread.release();
        System.out.println(Thread.currentThread().getName() + " thread started reading now.");
		System.out.println(num_rt + " thread is reading now.");


	}
	public void writeLock() {
		try{
			writing_thread.acquire();
		}
		catch (InterruptedException handle) {}
		
        System.out.println(Thread.currentThread().getName() + " thread started writing now.");

	}
	public void readUnLock() {
		try{
			num_rt--;
            reading_thread.acquire();
        }
        catch (InterruptedException handle) {}
		
		if(num_rt==0){
			writing_thread.release();
		}
		
        System.out.println(Thread.currentThread().getName() + " thread finished reading now.");
		System.out.println(num_rt + " thread is reading Now.");
        reading_thread.release();
	}
	public void writeUnLock() {
		
        System.out.println(Thread.currentThread().getName() + " thread finished writing now.");
        writing_thread.release();
		
	}

}




class Writer implements Runnable
{
   private ReadWriteLock RW_lock;
   

    public Writer(ReadWriteLock rw) {
    	RW_lock = rw;
   }

    public void run() {
      while (true){
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}	
    	  RW_lock.writeLock();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}	
    	  RW_lock.writeUnLock();
       
      }
   }


}



class Reader implements Runnable
{
   private ReadWriteLock RW_lock;
   

   public Reader(ReadWriteLock rw) {
    	RW_lock = rw;
   }
    public void run() {
      while (true){ 
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}	  
    	  RW_lock.readLock();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
    	  RW_lock.readUnLock();
       
      }
   }

}