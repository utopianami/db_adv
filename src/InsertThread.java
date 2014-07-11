import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class InsertThread extends Thread{
	private static InsertThread instance = new InsertThread();
	private InsertThread(){};
	private static Queue<User> todoBuffer = new LinkedList<User>();
	
	static InsertThread getInstance() {
		return instance;
	}
	
	public static boolean addQueue(User user) {
		return todoBuffer.add(user);
	}
	
	@Override
	public void run() {
		while(true) {
			writeDataToFile();
		}
	}

	private void writeDataToFile() {
		User targetUser = todoBuffer.peek();
		
		if (targetUser==null)
			return;
		System.out.println("test");

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
			System.out.println("keyLine  : "+ Main.keyFileLineNumber);
			++Main.keyFileLineNumber;
		} catch (IOException e) {
			//poll하지 않기
			//TODO Error Report
			e.printStackTrace();
			return;
		}
		
		todoBuffer.poll();
	}
}
