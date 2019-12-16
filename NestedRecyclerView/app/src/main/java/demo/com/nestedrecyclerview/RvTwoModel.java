package demo.com.nestedrecyclerview;

public class RvTwoModel {

    int id;
    String taskName;
    int status;
    int typeId;

    public RvTwoModel(String taskName, int status, int typeId) {
        this.taskName = taskName;
        this.status = status;
        this.typeId = typeId;
    }

    public RvTwoModel(int id, String taskName, int status, int typeId) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
        this.typeId = typeId;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
