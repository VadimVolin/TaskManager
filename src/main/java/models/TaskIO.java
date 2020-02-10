package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Iterator;

public class TaskIO {

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outputStream = null;
        try {
            outputStream = new DataOutputStream(out);
            Iterator<Task> iterator = tasks.iterator();
            outputStream.writeInt(tasks.size());
            for (Task task : tasks) {
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeBoolean(task.isActive());
                outputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    outputStream.writeInt(task.getStartTime().getNano());
                    outputStream.writeInt(task.getEndTime().getNano());
                } else {
                    outputStream.writeInt(task.getTime().getNano());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(in);
            int taskCount = dataInputStream.readInt();
            String title;
            boolean active;
            int start = 0;
            int end = 0;
            int interval;
            int time = 0;

            for (int i = 0; i < taskCount; i++) {
                int lengthTitle = dataInputStream.readInt();
                title = dataInputStream.readUTF();
                active = dataInputStream.readBoolean();
                interval = dataInputStream.readInt();
                if (interval != 0) {
                    start = dataInputStream.readInt();
                    end = dataInputStream.readInt();
                } else {
                    time = dataInputStream.readInt();
                }
                Task task;
                if (time != 0) {
                    task = new Task(title, LocalDateTime.now().withNano(time));
                    task.setActive(active);
                } else {
                    task = new Task(
                            title,
                            LocalDateTime.now().withNano(start),
                            LocalDateTime.now().withNano(end),
                            interval);
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        FileOutputStream fileOutputStream = null;
        fileOutputStream = new FileOutputStream(file);
        write(tasks, fileOutputStream);
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(file);
        read(tasks, fileInputStream);
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        Iterator<Task> iterator = tasks.iterator();
        Task[] arrTasks = new Task[tasks.size()];
        int i = 0;
        do {
            if (i < tasks.size()) {
                arrTasks[i++] = iterator.next();
            }
        } while (iterator.hasNext());
        gson.toJson(arrTasks, out);
        out.close();

    }

//    public static void writeJSON(AbstractTaskList tasks, Writer out) throws IOException {
        //        JSONObject jsonObject = null;
//        JSONArray jsonArray = new JSONArray();
//        for (Task task : tasks) {
//            jsonObject = new JSONObject();
//            jsonObject.put("Title", task.getTitle());
//            jsonObject.put("Active", task.isActive());
//            jsonObject.put("Repeated", task.isRepeated());
//            if (task.isRepeated()) {
//                jsonObject.put("Time start", task.getStartTime());
//                jsonObject.put("Time end", task.getEndTime());
//                jsonObject.put("Interval", task.getRepeatInterval());
//            } else {
//                jsonObject.put("Time", task.getTime());
//            }
//        jsonArray.add(tasks);
//        }
//        out.write(jsonArray.toJSONString());
//    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException, ParseException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Task[] arrayTasks = gson.fromJson(in, Task[].class);
        if (arrayTasks == null) {
            return;
        }
        for (Task task : arrayTasks) {
            tasks.add(task);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        write(tasks, fileWriter);
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException, ParseException {
        FileReader fileReader = new FileReader(file);
        read(tasks, fileReader);
    }
}
