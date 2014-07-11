

public class ThreadHandler extends Thread{
	
	User user;
	
	public ThreadHandler(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		InsertThread.addQueue(user);
	}
}
