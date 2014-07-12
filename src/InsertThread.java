import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class InsertThread extends Thread{
	private static InsertThread instance = new InsertThread();
	private InsertThread(){};
	private Queue<User> todoBuffer = new ConcurrentLinkedQueue<User>();//new LinkedList<User>();
	private boolean isOperation = true;
	
	static InsertThread getInstance() {
		return instance;
	}
	
	public static boolean addQueue(User user) {
		return instance.todoBuffer.add(user);
	}
	
	@Override
	public void run() {
		while(isOperation) {
			writeDataToFile();
		}
	}

	private void writeDataToFile() {
		User targetUser = instance.todoBuffer.peek();

		while (targetUser!=null) {
			String data = 
					targetUser.getName()
					+ ","
					+ targetUser.getSsn()
					+ ","
					+ targetUser.getPhoneNumber()
					+ "\n";
			String key = ""+targetUser.getSsn();
			try {
				Main.fileManager.writeBytes(data);
				Main.key.writeBytes(key+"\n");
				Main.ssnManager.put(key, Main.keyFileLineNumber);
				++Main.keyFileLineNumber;
				//System.out.println("keyLine  : "+ Main.keyFileLineNumber);
			} catch (IOException e) {
				//poll하지 않기
				//TODO Error Report
				e.printStackTrace();
				return;
			}
			todoBuffer.poll();
			targetUser = todoBuffer.peek();
		} //while end
	}
	
	public void destroy() {
		this.isOperation = false;
	}
}
