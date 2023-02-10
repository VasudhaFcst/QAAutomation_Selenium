package Example;

public class ThreadConcept {
Thread t;
static boolean status=true;
Object obj1 = new Object();
public String concept() throws InterruptedException {
	System.out.println("Started...");
	t=new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=1;i<1000;i++) {
				System.out.println("Main "+i);
				for(int j=1;j<200;j++) {
					
					System.out.println("Child "+j+" of "+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				}
			}
		}
		
	});
//	t.start();
	t.join();
	return null;
}

public void waiting() {
	synchronized (this) {
		System.out.println(status);
	
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}

public void resume() {
	synchronized (this) {
		System.out.println(status);
	
			this.notify();
	}
}

}
