package models;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task> {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract Stream<Task> getStream();

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        AbstractTaskList list = this.getClass().equals(ArrayTaskList.class) ? new ArrayTaskList() : new LinkedTaskList();
        this.getStream().filter(task -> task.nextTimeAfter(from) != null
                && task.nextTimeAfter(from).isBefore(to))
                .forEach(task -> list.add(task));
        return list;
    }

    @Override
    public Iterator<Task> iterator() {

        return new Iterator<Task>() {
            private int iteratorCount = -1;
            private int currentIndex;

            @Override
            public boolean hasNext() {
                try {
                    return AbstractTaskList.this.getTask(iteratorCount + 1) != null;
                } catch (NullPointerException e) {
                    return false;
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }

            @Override
            public Task next() {
                iteratorCount++;
                return AbstractTaskList.this.getTask(currentIndex++);
            }

            @Override
            public void remove() {
                if (iteratorCount == -1) {
                    throw new IllegalStateException();
                }
                AbstractTaskList.this.remove(AbstractTaskList.this.getTask(iteratorCount));
                iteratorCount--;
                currentIndex--;
            }
        };
    }
}