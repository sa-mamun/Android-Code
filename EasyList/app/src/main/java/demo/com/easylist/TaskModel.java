package demo.com.easylist;

public class TaskModel {

    int taskId;
    String taskName;
    String taskType;
    String taskTime;
    boolean selected;

    public TaskModel(String taskName, String taskType, String taskTime) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskTime = taskTime;
    }

    public TaskModel(int taskId, String taskName, String tastType, String taskTime) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskType = tastType;
        this.taskTime = taskTime;
    }

    public TaskModel(int taskId, String taskName, String taskType, String taskTime, boolean selected) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskTime = taskTime;
        this.selected = selected;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
