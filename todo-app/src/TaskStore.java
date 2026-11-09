import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Keeps the list of tasks and saves/loads them to a text file.
 * File format: one task per line -> "1|Buy milk" (1 = done, 0 = not done).
 */
public class TaskStore {
    private final Path file;
    private final List<Task> tasks = new ArrayList<>();

    public TaskStore(Path file) {
        this.file = file;
        load();
    }

    public List<Task> getAll() { return tasks; }

    public void add(String title) {
        tasks.add(new Task(title, false));
        save();
    }

    /** @param index 1-based index as shown to the user */
    public boolean markDone(int index) {
        if (index < 1 || index > tasks.size()) return false;
        tasks.get(index - 1).markDone();
        save();
        return true;
    }

    public boolean remove(int index) {
        if (index < 1 || index > tasks.size()) return false;
        tasks.remove(index - 1);
        save();
        return true;
    }

    private void load() {
        if (!Files.exists(file)) return;
        try {
            for (String line : Files.readAllLines(file)) {
                String[] parts = line.split("\\|", 2);
                if (parts.length == 2) {
                    tasks.add(new Task(parts[1], parts[0].equals("1")));
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read " + file + ": " + e.getMessage());
        }
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add((t.isDone() ? "1" : "0") + "|" + t.getTitle());
        }
        try {
            Files.write(file, lines);
        } catch (IOException e) {
            System.out.println("Could not save " + file + ": " + e.getMessage());
        }
    }
}
