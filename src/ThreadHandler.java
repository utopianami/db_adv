import java.io.IOException;


public class ThreadHandler extends Thread{
	
	User user;
	
	public ThreadHandler(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		
		String data = 
				user.getName()
				+ ","
				+ user.getSsn()
				+ ","
				+ user.getPhoneNumber()
				+ "\n";
		String key = ""+user.getSsn();
		try {
			System.out.println(data.length());
			System.out.println("key : "+key.length()+"\n");
			Main.fileManager.writeBytes(data);
			
			Main.key.writeBytes(key+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
