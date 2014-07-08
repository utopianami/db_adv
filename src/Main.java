import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.UUID;


public class Main {

	/*
	 * Schema name, jumin, address, phone_num
	 */
	private static int MAXIMUN = 90;
	private static int MINIMUN = 65;
	private static int DATA_LENGTH = 43;
	private static int KEY_LENGTH = 36;
	
	static RandomAccessFile fileManager;
	static RandomAccessFile key;
	private static char getChar() {
		int randomNum = 0;
		Random rn = new Random();
		int n = MAXIMUN - MINIMUN + 1;
		int i = rn.nextInt() % n;
		randomNum =  MINIMUN+ i;
		
		return (char)randomNum;
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	private static String getName() {
		String returnName ="" ;
		returnName += getChar();
		returnName += getChar();
		returnName += getChar();
		return returnName;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		//65 ~ 90
		fileManager = new RandomAccessFile("/Users/youngnam/Desktop/db.txt", "rw");
		key = new RandomAccessFile("/Users/youngnam/Desktop/key.txt", "rw");
		
		int number = 10000;
		String name = null;
		String ssn = null;
		int phoneNumber = 0;
		
		for(int i = 0 ; i< number ; ++i) {
			name = getName();
			ssn = getUUID();
			phoneNumber = 0;getPhoneNumber();
			ThreadHandler handler = new ThreadHandler(new User(i, name, ssn, phoneNumber));
			handler.start();
		}
	}

	private static int getPhoneNumber() {
		return (int) (Math.random() * 11);
	}
	
	private static User selectUserFromUUID(String ssn) throws IOException {
		String data = Main.key.readUTF();
		System.out.println();
		while(data.contains("ssn")) {
			data.code
		}
		return null;
	}
}
