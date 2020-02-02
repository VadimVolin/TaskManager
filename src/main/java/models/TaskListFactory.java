package models;

public class TaskListFactory {

    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ListTypes.types.ARRAY) {
            return new ArrayTaskList();
        } else if (type == ListTypes.types.LINKED) {
            return new LinkedTaskList();
        }
        throw new IllegalArgumentException("Different types of class list");

    }

}
