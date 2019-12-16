package demo.com.easylist;

public class RvOneModel {

    int id;
    String taskType;
    String taskTime;

    public RvOneModel(String taskType, String taskTime) {
        this.taskType = taskType;
        this.taskTime = taskTime;
    }

    public RvOneModel(int id, String taskType, String taskTime) {
        this.id = id;
        this.taskType = taskType;
        this.taskTime = taskTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }
}
