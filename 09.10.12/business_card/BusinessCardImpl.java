import ru.ncedu.java.tasks.BusinessCard;
import java.util.*;
import java.text.*;

public class BusinessCardImpl implements BusinessCard {
	private String name, lastName, department, birthDate, gender;
	private int salary;
	private String phoneNumber;

	BusinessCardImpl() {
		super();
		this.name = "";
		this.lastName = "";
		this.department = "";
		this.birthDate = "";
		this.gender = "";
		this.salary = 0;
		this.phoneNumber = "";
	}

	BusinessCardImpl(String name, String lastName, String department, String birthDate,
						 String gender, int salary, String phoneNumber) {
		this.name = name;
		this.lastName = lastName;
		this.department = department;
		this.birthDate = birthDate;
		this.gender = gender;
		this.salary = salary;
		this.phoneNumber = phoneNumber;
	}

	public BusinessCard getBusinessCard (Scanner scanner) throws InputMismatchException, NoSuchElementException {
		scanner.useDelimiter(";");
		this.name = scanner.next();
		this.lastName = scanner.next();
		this.department = scanner.next();
		this.birthDate = scanner.next("\\d{2}-\\d{2}-\\d{4}");
		this.gender = scanner.next("[FM]");
		int sal = scanner.nextInt();
		if (sal <= 100000 && sal >= 100) {
			this.salary = sal;
		} else {
			throw new InputMismatchException();
		}
		this.phoneNumber = scanner.next("\\d{10}");
		return new BusinessCardImpl(name, lastName, department, birthDate, gender, salary, phoneNumber);
	}

	public String getEmployee() {
		return this.name + " " + this.lastName;
	}
	
	public String getDepartment() {
		return this.department;
	}

	public int getSalary() {
		return this.salary;
	}

	public int getAge() {
		DateFormat formatter = new SimpleDateFormat("DD-MM-YYYY");
		GregorianCalendar c = new GregorianCalendar();
		GregorianCalendar now = new GregorianCalendar();
		try {
			c.setTime((Date) formatter.parse(this.birthDate)); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int age = now.get(GregorianCalendar.YEAR) - c.get(GregorianCalendar.YEAR);
		now.set(GregorianCalendar.YEAR, c.get(GregorianCalendar.YEAR));
		if (now.before(c)) {
			age--;
		}
		return age;
	}

	public String getGender() {
		return this.gender;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public static void main(String[] args) {
		BusinessCardImpl q = new BusinessCardImpl();
	}
}