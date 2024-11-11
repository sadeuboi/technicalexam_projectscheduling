package task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskClass {
    private String name;
    private int duration;

    private List<TaskClass> dependencies;
    private LocalDate startDate;
    private LocalDate endDate;

    public TaskClass(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.dependencies = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<TaskClass> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<TaskClass> dependencies) {
        this.dependencies = dependencies;
    }
}
