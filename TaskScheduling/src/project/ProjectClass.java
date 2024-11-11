package project;

import task.TaskClass;

import java.time.LocalDate;
import java.util.*;

public class ProjectClass {

    private List<TaskClass> taskList;

    public ProjectClass() {
        this.taskList = new ArrayList<>();
    }

    public String displaySchedule() {
        StringBuilder scheduleOutput = new StringBuilder();
        scheduleOutput.append("Here is your schedule:");

        taskList.forEach(taskClass -> {
            scheduleOutput.append("\nTask Name: ").append(taskClass.getName()).append("\nDuration:").append(taskClass.getDuration())
                    .append("\nStart Date: ").append(taskClass.getStartDate()).append("\nEnd Date:").append(taskClass.getEndDate())
                    .append("\nDependencies:");
            taskClass.getDependencies().forEach(dependency -> {
                scheduleOutput.append(dependency.getName());
            });
            scheduleOutput.append("\n");
        });

        return scheduleOutput.toString();
    }

    public void createTask(String name, int duration) {
        TaskClass task = new TaskClass(name, duration);
        taskList.add(task);
        System.out.println("New task created!");
    }

    public void createDependency(String taskName, String dependencyName) {
        Optional<TaskClass> task = taskList.stream().filter(taskClass -> taskClass.getName().equalsIgnoreCase(taskName)).findFirst();
        Optional<TaskClass> dependency = taskList.stream().filter(taskClass -> taskClass.getName().equalsIgnoreCase(dependencyName)).findFirst();

        if (task.isPresent() && dependency.isPresent()) {
            task.get().getDependencies().add(dependency.get());
            System.out.println("New dependency created!");
        } else {
            System.out.println("Task or Dependency does not exist!");
        }

    }

    public void generateSchedule() {
        List<TaskClass> sortedTasks = sortTasks();
        LocalDate currentDate = LocalDate.now();

        for (TaskClass task : sortedTasks) {
            LocalDate startDate = currentDate;
            for (TaskClass dependency : task.getDependencies()) {
                if (dependency.getEndDate() != null) {
                    startDate = startDate.isBefore(dependency.getEndDate()) ? dependency.getEndDate() : startDate;
                }
            }
            task.setStartDate(startDate);
            task.setEndDate(startDate.plusDays(task.getDuration()));
        }
    }

    private List<TaskClass> sortTasks() {
        List<TaskClass> sortedTasks = new ArrayList<>();
        List<String> visitedTasks = new ArrayList<>();
        for (TaskClass task : taskList) {
            if (!isVisited(task.getName(), visitedTasks)) {
                sortTaskDependencies(sortedTasks, task, visitedTasks);
            }
        }

        return sortedTasks;
    }

    private void sortTaskDependencies(List<TaskClass> sortedList, TaskClass task, List<String> visitedTasks) {
        visitedTasks.add(task.getName());
        for (TaskClass dependency : task.getDependencies()) {
            if (!isVisited(dependency.getName(), visitedTasks)) {
                sortTaskDependencies(sortedList, dependency, visitedTasks);
            }
        }
        sortedList.add(task);
    }

    private boolean isVisited(String taskName, List<String> visitedTasks) {
        return visitedTasks.contains(taskName);
    }


}
