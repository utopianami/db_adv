import java.io.IOException;


public class Parser {
	private String data;
	private String type;

	public Parser(String data) {
		this.data = data;
		typeParsing();
	}
	
	/*
	 * insert or select
	 */
	private void typeParsing() {
		this.type = data.split(" ")[0];
	}
	
	public void execute(){
		try {
			long startExecute = System.currentTimeMillis();
			long endExecute;
			if (this.type.equals("INSERT")){
				insert();
				
				endExecute = System.currentTimeMillis();
				double time = (endExecute - startExecute)/1000.0;
				System.out.println("Query OK, 1 rows affected (" + time + "sec)\n");
			}else if (this.type.equals("SELECT")) {
				select();
				
				endExecute = System.currentTimeMillis();
				double time = (endExecute - startExecute)/1000.0;
				System.out.println("Query OK, 1 rows affected (" + time + "sec)\n");
			}else{
				System.out.println("ERROR : You have an error in your SQL syntax; check the manual");
			}
		} catch (Exception e) {
			System.out.println("ERROR : You have an error in your SQL syntax; check the manual");
		}
	}
	private void select() {
		String value = data.split("\"")[1];
		System.out.println(value);
		try {
			String result = Main.searchFile(value);
			System.out.println("reasult : " + result);
		} catch (IOException e) {
			System.out.println("Empty set");
		}
	}

	private void insert() {
		String[] values = data.split(" ")[4].split("\"");
		String name = values[1];
		String ssn = values[3];
		int phoneNumber = Integer.parseInt(values[5]);
		ThreadHandler handler = new ThreadHandler(new User(Main.keyFileLineNumber, name, ssn, phoneNumber));
		handler.start();
	}

}
