import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        //Change in Arguments
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager <command>");
            return;
        }

        String command = args[0];
        //Function Creation for Random
        switch (command.charAt(0)) {
            case 'l':
                employeeList(); //For Full List of Employee
                break;
            case 's':
                randomEmployee();//For Showing Random EMployee
                break;
            case '+':
                addingEmployee(command.substring(1));//For adding Employee
                break;
            case '?':
                SearchingEmployee(command.substring(1));//For Search an Employee
                break;
            case 'c':
                countEmployees();//For Count the Number of words of  Employee
                break;
            case 'u':
                updateEmployee(command.substring(1));//for reporting of updated employee
                break;
            case 'd':
                deleteEmployee(command.substring(1));//for Delete an employee
                break;
            default:
                System.out.println(Constants.INVALID_ARGUMENT);
        }
    }

    //  Creating Methode of ReadFile for avoiding duplication
    private  static String readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_PATH))) {
            return reader.readLine(); // Read entire line
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    //  Creating Methode of  writeFile for avoiding duplication

    private static void writeFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH))) {
            writer.write(data);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    //Listing all Employee
    public static void employeeList() {
        System.out.println(Constants.DATA_LOADING);
        String data = readFile();
        if (data != null) {
            System.out.println(data); // Output raw content
        } else {
            System.out.println("No data available.");
        }
        System.out.println(Constants.DATA_LOADED);
    }

    //Showing Randomly Employee
    public static void randomEmployee() {
        System.out.println(Constants.DATA_LOADING);
        String data = readFile();
        if (data != null) {
            String[] employees = data.split(", ");
            Random random = new Random();
            System.out.println(employees[random.nextInt(employees.length)]);
        } else {
            System.out.println("No data available.");
        }
        System.out.println(Constants.DATA_LOADED);
    }

    //For Adding New Employee
    public static void addingEmployee(String newEmployee) {
        System.out.println(Constants.DATA_LOADING);
        String data = readFile();
        if (data != null) {
            data = data.isEmpty() ? newEmployee : data + ", " + newEmployee;
            writeFile(data);
        }
        System.out.println(Constants.DATA_LOADED);
    }

    //Improved search logic and response
    public static void SearchingEmployee(String employeeName) {
        System.out.println(Constants.DATA_LOADING);
        String data = readFile();
        if (data != null) {
            if (Arrays.asList(data.split(", ")).contains(employeeName)) {
                System.out.println(Constants.EMPLOYEE_FOUND);
            } else {
                System.out.println(Constants.EMPLOYEE_NOT_FOUND);
            }
        }
        System.out.println(Constants.DATA_LOADED);
    }

    //Simplifying logic for Counting words
    public static void countEmployees() {
        System.out.println(Constants.DATA_LOADING);
        String data = readFile();
        if (data != null) {
            String[] employees = data.split(", ");
            System.out.println(employees.length + " employee(s) found.");
            System.out.println(data.length() + " character(s) found.");
        } else {
            System.out.println("No data available.");
        }
        System.out.println(Constants.DATA_LOADED);
    }

    // Updating  an employee's name
    public static void updateEmployee(String oldEmployeeName) {
        System.out.println(Constants.DATA_LOADING);
        String data = readFile();
        if (data != null) {
            String[] employees = data.split(", ");
            boolean updated = false;

            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(oldEmployeeName)) {
                    employees[i] = "Updated";
                    updated = true;
                    break;
                }
            }

            if (updated) {
                writeFile(String.join(", ", employees));
                System.out.println("Employee updated.");
            } else {
                System.out.println(Constants.EMPLOYEE_NOT_FOUND);
            }
        }
        System.out.println(Constants.DATA_LOADED);
    }

    //For Deleting an Employee from the list
    public static void deleteEmployee(String employeeName) {
        System.out.println(Constants.DATA_LOADING);
        String data = readFile();
        if (data != null) {
            List<String> employees = new ArrayList<>(Arrays.asList(data.split(", ")));
            if (employees.remove(employeeName)) {
                writeFile(String.join(", ", employees));
                System.out.println("Employee deleted.");
            } else {
                System.out.println(Constants.EMPLOYEE_NOT_FOUND);
            }
        }
        System.out.println(Constants.DATA_LOADED);
    }
}
