package demo.com.easylist;

public class RvTwoModel {

    int id;
    String taskName;
    int status;
    int typeId;
    String TypeName;

    public RvTwoModel(String taskName, int status, String TypeName, int typeId) {
        this.taskName = taskName;
        this.status = status;
        this.TypeName = TypeName;
        this.typeId = typeId;
    }

    public RvTwoModel(int id, String taskName, int status, String TypeName, int typeId) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
        this.TypeName = TypeName;
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

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }
}
