
public class User {
	private int number;
	private String name;
	private String ssn;
	private int phoneNumber;
	
	public User(int number, String name, String ssn, int phoneNumber) {
		this.number = number;
		this.name = name;
		this.ssn = ssn;
		this.phoneNumber = phoneNumber;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "User [number=" + number + ", name=" + name + ", ssn=" + ssn
				+ ", phoneNumber=" + phoneNumber + "]";
	}
}
