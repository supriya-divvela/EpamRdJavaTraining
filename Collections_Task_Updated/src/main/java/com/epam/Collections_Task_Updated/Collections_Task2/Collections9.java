package com.epam.Collections_Task_Updated.Collections_Task2;
import java.util.*;
import java.lang.*;
class Employee
{
   int id; 
   String name;
   int age;
   String gender;
   String department;
   int yearOfJoining;
   double salary;
   public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) 
   {
       this.id = id;
       this.name = name;
       this.age = age;
       this.gender = gender;
       this.department = department;
       this.yearOfJoining = yearOfJoining;
       this.salary = salary;
   }
   public int getId() 
   {
       return id;
   }
   public String getName() 
   {
       return name;
   }
   public int getAge() 
   {
       return age;
   }
   public String getGender() 
   {
       return gender;
   }
   public String getDepartment() 
   {
       return department;
   }
   public int getYearOfJoining() 
   {
       return yearOfJoining;
   }
   public double getSalary() 
   {
       return salary;
   }
   @Override
   public String toString() 
   {
       return "Id : "+id+", Name : "+name+", age : "+age+", Gender : "+gender+", Department : "+department+", Year Of Joining : "+yearOfJoining+", Salary : "+salary;
   }
}
class EmployeeData
{
	public static List<Employee> dataSource()
	{
		List<Employee> employeeData=new ArrayList<Employee>();
		employeeData.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeData.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeData.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeData.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeData.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeData.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeData.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeData.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeData.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeData.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeData.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeData.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeData.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeData.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeData.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeData.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeData.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
		return employeeData;
	}
}
class EmployeeVariables{
	static List<Employee> employeeList = new ArrayList<Employee>(EmployeeData.dataSource());
	static ArrayList<String> dept=new ArrayList<String>();
	static int male=0;
	static int female=0;
	static int sum_age_male=0;
	static int sum_age_female=0;
	static double avg_sal_male=0;
	static double avg_sal_female=0;
	static double max_salary=Double.NEGATIVE_INFINITY;
	static int min_age=Integer.MAX_VALUE;
	static int doj_exp=Integer.MAX_VALUE;
	static int sales_male=0;
	static int sales_female=0;
	static double total_sal=0;
	static int max_age=Integer.MIN_VALUE;
	static HashMap<String,Integer> hm=new HashMap<String,Integer>();
}
class EmployeeService extends EmployeeVariables
{
	public static void maleAndFemaleEmployeeCount()
	{
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getGender().toLowerCase().equals("male"))
			{
				sum_age_male+=employeeList.get(i).getAge();
				avg_sal_male+=employeeList.get(i).getSalary();	
				male++;
			}
			else
			{
				sum_age_female+=employeeList.get(i).getAge();
				avg_sal_female+=employeeList.get(i).getSalary();
				female++;
			}
		}
		System.out.println("No of Male Employees are : "+male);
		System.out.println("No of Female Employees are : "+female);
	}
	public static void getAllDepartments()
	{
		for(int i=0;i<employeeList.size();i++)
		{
			if(dept.indexOf(employeeList.get(i).getDepartment())<0)
			{
				dept.add(employeeList.get(i).getDepartment());
			}
		}
		System.out.println("The Departments are : "+dept);
	}
	public static void getAverageAgeOfMaleAndFemaleEmployees()
	{
		System.out.println("Average age of male employees are : "+(double)sum_age_male/male);
		System.out.println("Average age of female employees are : "+(double)sum_age_female/female);
	}
	public static void getMaxSalaryEmployeeService()
	{
		Employee e1=null;;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getSalary()>max_salary)
			{
				max_salary=employeeList.get(i).getSalary();
				e1=employeeList.get(i);
			}
		}
		System.out.println("Highest paid Employee Details:\n "+e1);
	}
	public static void getEmployeesJoinedAfter2015()
	{
		ArrayList<String> join_after_2015=new ArrayList<String>();
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getYearOfJoining()>2015)
			{
				join_after_2015.add(employeeList.get(i).getName());
			}
		}
		System.out.println("Employees joined after 2015 are : "+join_after_2015.toString());
	}
	public static void countNumberOfEmployeesInEachDepartment()
	{
		for(int i=0;i<employeeList.size();i++)
		{
			if(hm.containsKey(employeeList.get(i).getDepartment()))
			{
				hm.put(employeeList.get(i).getDepartment(),hm.get(employeeList.get(i).getDepartment())+1);
			}
			else
			{
				hm.put(employeeList.get(i).getDepartment(),1);
			}
		}
		System.out.println("Number Of Employees in each Department are : "+hm);
	}
	public static void getEachDepartmentAverageSalary() 
	{
		HashMap<String,Double> avg_sal=new HashMap<String,Double>();
		for(int i=0;i<employeeList.size();i++)
		{
			if(avg_sal.containsKey(employeeList.get(i).getDepartment()))
			{
				avg_sal.put(employeeList.get(i).getDepartment(),avg_sal.get(employeeList.get(i).getDepartment())+employeeList.get(i).getSalary());
			}
			else{
				avg_sal.put(employeeList.get(i).getDepartment(),employeeList.get(i).getSalary());
			}
		}
		for (Map.Entry<String,Integer> entry : hm.entrySet())
		{
			avg_sal.put(entry.getKey(),avg_sal.get(entry.getKey())/entry.getValue());
		}
		System.out.println("Average Salary in each Department is : "+avg_sal);
	}
	public static void getYoungestMaleEmployeeInProductionDepartment()
	{
		Employee e2=null;
		for(int i=0;i<employeeList.size();i++)
		{
			if(min_age>employeeList.get(i).getAge() && employeeList.get(i).getDepartment().equals("Product Development"))
			{
				min_age=employeeList.get(i).getAge();
				e2=employeeList.get(i);
			}
		}
		System.out.println("Young Employee in Product Development is :\n"+e2);
	}
	public static void getMostExperienceEmployeeService()
	{
		Employee e3=null;
		for(int i=0;i<employeeList.size();i++)
		{
			if(doj_exp>employeeList.get(i).getYearOfJoining())
			{
				doj_exp=employeeList.get(i).getYearOfJoining();
				e3=employeeList.get(i);
			}
		}
		System.out.println("Most Experienced Employee is :\n"+e3);
	}
	public static void getMaleAndFemaleCountInSales()
	{
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getDepartment().equals("Sales And Marketing"))
			{
				if(employeeList.get(i).getGender().equals("Male"))
				sales_male+=1;
				else
				sales_female+=1;
			}	
		}
		System.out.println("Sales Department male employees count : "+sales_male);
		System.out.println("Sales Department female employees count : "+sales_female);
	}
	public static void getAverageSalaryOfMaleAndFemaleEmployees()
	{
		System.out.println("Average salary of male employees is : "+(double)avg_sal_male/male);
		System.out.println("Average salary of female employees is : "+(double)avg_sal_female/female);
	}
	public static void getNamesOfAllEmployeeInEachDepaartment()
	{
		HashMap<String,ArrayList<String>> emp_details = new HashMap<String, ArrayList<String>>();
		for(int i=0;i<employeeList.size();i++)
		{
			if(emp_details.containsKey(employeeList.get(i).getDepartment()))
			{
				emp_details.get(employeeList.get(i).getDepartment()).add(employeeList.get(i).getName());
				emp_details.put(employeeList.get(i).getDepartment(),emp_details.get(employeeList.get(i).getDepartment()));
			}
			else
			{
				ArrayList<String> a1=new ArrayList<String>();
				emp_details.put(employeeList.get(i).getDepartment(),a1);
			}
		}
		System.out.println("Employee names in specific departments are\n"+emp_details);
	}
	public static void getAverageAndTotalSalaryOfOrganisation()
	{
		for(int i=0;i<employeeList.size();i++)
		{
			total_sal+=employeeList.get(i).getSalary();
		}
		System.out.println("Total salary and average salary of whole organisation is : "+total_sal+","+(double)total_sal/(male+female));
		
	}
	public static void getEmployeesLessThanEqualTo25()
	{
		ArrayList<Employee> emp_lte_25=new ArrayList<Employee>();
		ArrayList<Employee> emp_gt_25=new ArrayList<Employee>();
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getAge()<=25)
			{
				emp_lte_25.add(employeeList.get(i));
			}
			else
			{
			emp_gt_25.add(employeeList.get(i));
			}
		}
		System.out.println("Employees Under 25 years old :");
		for(Employee e:emp_lte_25)
		{
			System.out.println(e);
		}
		System.out.println("Employees Greater than 25 years old :");
		for(Employee e:emp_gt_25)
		{
			System.out.println(e);
		}
	}
	public void getOldestEmployeeService()
	{
		Employee e4=null;
		for(int i=0;i<employeeList.size();i++)
		{
			if(employeeList.get(i).getAge()>max_age)
			{
				max_age=employeeList.get(i).getAge();
				e4=employeeList.get(i);
			}
		}
		System.out.println("Oldest employee in organisation is "+e4.getName()+" and his age is "+e4.getAge()+" and he belongs to "+e4.getDepartment()+".");
	}
}
class Collections9
{
	public static void main(String[] args)
	{
		EmployeeService e=new EmployeeService();
		System.out.println(e.employeeList);
		EmployeeService.maleAndFemaleEmployeeCount();
		EmployeeService.getAllDepartments();
		EmployeeService.getAverageAgeOfMaleAndFemaleEmployees();
		EmployeeService.getMaxSalaryEmployeeService();
		EmployeeService.getEmployeesJoinedAfter2015();
		EmployeeService.countNumberOfEmployeesInEachDepartment();
		EmployeeService.getEachDepartmentAverageSalary();
		EmployeeService.getYoungestMaleEmployeeInProductionDepartment();
		EmployeeService.getMostExperienceEmployeeService();
		EmployeeService.getMaleAndFemaleCountInSales();
		EmployeeService.getAverageSalaryOfMaleAndFemaleEmployees();
		EmployeeService.getNamesOfAllEmployeeInEachDepaartment();
		EmployeeService.getAverageAndTotalSalaryOfOrganisation();
		EmployeeService.getEmployeesLessThanEqualTo25();
		e.getOldestEmployeeService();
	}
}