# AUCA School Management Console

This Java console application lets users register and manage departments, students, teachers, administrators, courses, classrooms, enrollments, assessments, fee payments, library books, and loans.

## Run from VS Code

Run `App.java` or `com.auca.main.Main` from the Java extension. The menu prompts for the fields required to create and manage records.

## Compile and run from PowerShell

From the project directory:

```powershell
$sources = Get-ChildItem .\src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d .\bin $sources
java -cp .\bin App
```

Records are held in memory and are cleared when the application exits.
