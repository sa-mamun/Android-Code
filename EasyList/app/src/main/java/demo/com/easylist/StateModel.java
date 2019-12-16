package demo.com.easylist;

public class StateModel {

    int stateId;
    int stateInfo;
    int taskId;

    public StateModel(int stateInfo, int taskId) {
        this.stateInfo = stateInfo;
        this.taskId = taskId;
    }

    public StateModel(int stateId, int stateInfo, int taskId) {
        this.stateId = stateId;
        this.stateInfo = stateInfo;
        this.taskId = taskId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(int stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
