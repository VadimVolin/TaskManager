package views;

public class NotificationView implements PrintListViewTemplate {
    @Override
    public void printTaskLsit(String abstractTaskList) {
        System.out.println("------------------------Notification-----------------------");
        System.out.println(abstractTaskList);
        System.out.println("-----------------------------------------------------------");
    }

}
