import project.ProjectClass;

public class Main {
    public static void main(String[] args) {
        ProjectClass projectClass = new ProjectClass();

        projectClass.createTask("Wash the dishes", 1);
        projectClass.createTask("Prepare for the exam", 5);
        projectClass.createTask("Practice Exam", 2);

        projectClass.createDependency("Practice exam", "Prepare for the exam");

        projectClass.generateSchedule();

        System.out.println(projectClass.displaySchedule());

    }
}