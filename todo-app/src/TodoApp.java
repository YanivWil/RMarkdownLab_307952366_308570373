import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point. Shows a menu, reads the user's choice, and calls TaskStore.
 */
public class TodoApp {
    public static void main(String[] args) {
        TaskStore store = new TaskStore(Path.of("tasks.txt"));
        Scanner in = new Scanner(System.in);

        System.out.println("=== Simple To-Do App ===");
        while (true) {
            System.out.println();
            System.out.println("1) List tasks  2) Add task  3) Mark done  4) Delete task  0) Exit");
            System.out.print("> ");
            if (!in.hasNextLine()) break;
            String choice = in.nextLine().trim();

            switch (choice) {
                case "1" -> printTasks(store.getAll());
                case "2" -> {
                    System.out.print("Task title: ");
                    String title = in.nextLine().trim();
                    if (title.isEmpty()) System.out.println("Title can't be empty.");
                    else { store.add(title); System.out.println("Added."); }
                }
                case "3" -> {
                    int n = askNumber(in, "Task number to mark done: ");
                    System.out.println(store.markDone(n) ? "Done!" : "No such task.");
                }
                case "4" -> {
                    int n = askNumber(in, "Task number to delete: ");
                    System.out.println(store.remove(n) ? "Deleted." : "No such task.");
                }
                case "0" -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private static void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) { System.out.println("(no tasks yet)"); return; }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static int askNumber(Scanner in, String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(in.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // invalid -> store will say "No such task."
        }
    }
}
