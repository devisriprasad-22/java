import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    private static final String FILE_NAME = "todolist.txt";

    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();

        loadTasksFromFile(tasks);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("To-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Remove Task");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter a task: ");
                    String task = scanner.nextLine();
                    addTask(tasks, task);
                    break;
                case 2:
                    listTasks(tasks);
                    break;
                case 3:
                    System.out.print("Enter the task number to remove: ");
                    int taskNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    removeTask(tasks, taskNumber);
                    break;
                case 4:
                    saveTasksToFile(tasks);
                    scanner.close();
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadTasksFromFile(List<String> tasks) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // Ignore if the file doesn't exist yet
        }
    }

    private static void saveTasksToFile(List<String> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTask(List<String> tasks, String task) {
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    private static void listTasks(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your to-do list is empty.");
        } else {
            System.out.println("Your to-do list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void removeTask(List<String> tasks, int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            String removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Removed task: " + removedTask);
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }
}
