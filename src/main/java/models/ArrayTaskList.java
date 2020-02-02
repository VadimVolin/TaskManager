package models;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements Iterable<Task>, Cloneable {

    private Task[] taskArray;
    private int size = 0;
    private int count = 0;

    public ArrayTaskList() {
        this(10);
    }

    public ArrayTaskList(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size should be > 0");
        }
        this.size = size;
        taskArray = new Task[size];
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        ArrayTaskList object = (ArrayTaskList) obj;
        return this.taskArray.length == object.taskArray.length
                && Arrays.equals(this.taskArray, object.taskArray);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ArrayTaskList clonableTaskList = (ArrayTaskList) super.clone();
        clonableTaskList.taskArray = new Task[size()];
        for (int i = 0; i < this.size(); i++) {
            clonableTaskList.taskArray[i] = this.getTask(i).clone();
        }
        return clonableTaskList;
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        if (count == taskArray.length - 2) {
            taskArray = resize(size);
        }
        taskArray[count] = task;
        count++;
    }

    private Task[] resize(int size) {
        Task[] tasks = new Task[size * 2];
        System.arraycopy(taskArray, 0, tasks, 0, count);
        return tasks;
    }

    @Override
    public boolean remove(Task task) {
        if (task == null) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (taskArray[i].equals(task)) {
                for (int j = i; j < count - 1; j++) {
                    taskArray[j] = taskArray[j + 1];
                }
                taskArray[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Task getTask(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("Index not in array size");
        }
        if (size() == 0) {
            return null;
        }
        return taskArray[index];
    }

    @Override
    public Stream<Task> getStream() {
        List<Task> list = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            list.add(this.getTask(i));
        }
        return list.stream();
    }

}
