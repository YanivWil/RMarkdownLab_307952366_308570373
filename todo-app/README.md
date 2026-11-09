# Simple To-Do App (Java)

A minimal command-line to-do list. No frameworks, no build tool: just the JDK.

## Structure
| File | Role |
|------|------|
| `src/Task.java` | Model: one task (title + done flag) |
| `src/TaskStore.java` | Holds the list and saves/loads it to `tasks.txt` |
| `src/TodoApp.java` | `main()`: menu loop that reads input and calls `TaskStore` |

## Run
Requires JDK 17+ (`java -version`).

```bash
cd todo-app
javac -d out src/*.java     # compile into ./out
java -cp out TodoApp        # run
```

Tasks are saved to `tasks.txt` in the folder you run from, so they are still there the next time you start the app.

## Ideas for next steps
1. Add a "edit task title" option.
2. Add a due date to `Task` (`java.time.LocalDate`).
3. Replace `TaskStore`'s text file with a SQLite database via JDBC
   (`CREATE TABLE tasks(id INTEGER PRIMARY KEY, title TEXT, done INTEGER)`).
