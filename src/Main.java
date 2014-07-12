import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;


public class Main {

	private static final String DATABASE_HASH_TABLE = "/Users/yoon/Desktop/key.txt";
	private static final String DATABASE_NAME = "/Users/yoon/Desktop/db.txt";
	
	
	//private static final String DATABASE_HASH_TABLE = "/Users/youngnam/Desktop/key.txt";
	//private static final String DATABASE_NAME = "/Users/youngnam/Desktop/db.txt";
	/*
	 * Schema name, ssn, address, phone_num
	 */
	private static int MAXIMUN = 90;
	private static int MINIMUN = 65;
	static int DATA_LENGTH = 43;

	static RandomAccessFile fileManager;
	static RandomAccessFile key;
	static Map<String, Integer> ssnManager = new HashMap<String, Integer>();
	static int keyFileLineNumber = 0;
	public static long end;
	private static int number = 10000;
	
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
	
	private static void insertFile() throws FileNotFoundException{
		String name = null;
		String ssn = null;
		int phoneNumber = 0;
		
		for(int i = 0 ; i < number  ; ++i) {
			name = getName();
			ssn = getUUID();
			phoneNumber = 0;
			new ThreadHandler(new User(i, name, ssn, phoneNumber)).start();;
		}
	}
	
	private static void setSsnManager() throws IOException{
		String readLinesData = key.readLine();
		while(readLinesData != null) {
			ssnManager.put(readLinesData, keyFileLineNumber);
			keyFileLineNumber ++;
			readLinesData = key.readLine();
		}
	}
	
	static String searchFile(String ssn) throws IOException{
		int cursor;
		cursor = ssnManager.get(ssn);
		fileManager.seek(cursor * DATA_LENGTH);
		String data = fileManager.readLine();
		
		return data;
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		File dbFile = new File(DATABASE_NAME);
		File hashFile = new File(DATABASE_HASH_TABLE);
		
		if (dbFile.exists()) {
			dbFile.delete();
		}
		
		if (hashFile.exists()) {
			hashFile.delete();
		}
		
		fileManager = new RandomAccessFile(DATABASE_NAME, "rw");
		key = new RandomAccessFile(DATABASE_HASH_TABLE, "rw");
		setSsnManager();
		
		InsertThread.getInstance().start();
		long start = System.currentTimeMillis();
		insertFile();
		Main.end = System.currentTimeMillis();
		
		System.out.println("\n\n Welcome to the yySQL");
		System.out.println("_________________________");
		double time = (end-start)/1000.0;
		System.out.println("Query OK, " + keyFileLineNumber + " rows affected (" + time + "sec)\n");
		
		Scanner scan = new Scanner(System.in);
		boolean isOperation = true;
		while(isOperation){
			System.out.print("\nyySQL>  ");
			String data = scan.nextLine();
			isOperation = new Parser(data).execute();
		}
		
		scan.close();
		InsertThread.getInstance().destroy();
	}


}