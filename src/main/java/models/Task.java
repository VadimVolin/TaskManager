package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable, Serializable {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeated;



    public Task(String title, LocalDateTime time) {
        this.title = title;
        if (time != null) {
            this.time = time;
        } else {
            throw new IllegalArgumentException("Time should be not 0");
        }
    }


    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        this.title = title;
        if (start == null|| end == null || interval < 0) {
            throw new IllegalArgumentException("Time should be > 0");
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        if (!repeated) {
            return time;
        } else {
            return start;
        }
    }


    public void setTime(LocalDateTime time) {
        if (repeated) {
            repeated = false;
        }
        if (time == null) {
            throw new IllegalArgumentException("Time should be > 0");
        }
        this.time = time;
    }

    public LocalDateTime getStartTime() {
        if (!repeated) {
            return time;
        } else {
            return start;
        }
    }

    public LocalDateTime getEndTime() {
        if (!repeated) {
            return time;
        } else {
            return end;
        }
    }

    public int getRepeatInterval() {
        if (!repeated) {
            return 0;
        } else {
            return interval;
        }
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (!repeated) {
            repeated = true;
        }
        if (start == null || end == null || interval < 0) {
            throw new IllegalArgumentException("Time should be > 0");
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return repeated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getTime() == task.getTime() &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                isActive() == task.isActive() &&
                isRepeated() == task.isRepeated() &&
                getTitle().equals(task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getTime(), start, end, interval, isActive(), isRepeated());
    }

    @Override
    public Task clone()  {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Task: ");
        if (isRepeated()) {
            stringBuilder.append(getTitle())
                    .append(" | start time: ")
                    .append(getStartTime().toString().replace('T', ' '))
                    .append(" | end time: ")
                    .append(getEndTime().toString().replace('T', ' '))
                    .append(" | interval time: ")
                    .append(getRepeatInterval())
                    .append(" |");
        } else {
            stringBuilder.append(getTitle())
                    .append(" | time:  ")
                    .append(getTime().toString().replace('T', ' '))
                    .append(" |");
        }
        return stringBuilder.toString();
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (!active) {
            return null;
        }
        if (!isRepeated()) {
            if (time.isAfter(current)) {
                return time;
            } else {
                return null;
            }
        } else {
            if (getEndTime().isBefore(current)) {
                return null;
            } else {
                for (LocalDateTime i = getStartTime(); ; i = i.plusSeconds(getRepeatInterval())) {
                    if ( i.isAfter(current) && (i.isBefore(getEndTime()) || i.isEqual(getEndTime()))) {
                        return i;
                    }
                    else if(i.isAfter(getEndTime())) {
                        return null;
                    }
                }
            }
        }
    }

}