package service;

import model.CtripApp;
import model.OfficialWebsite;
import model.SaleChannel;
import model.TrainStation;

public class TicketSaleThread implements Runnable {
    private int leftTickets = 500;
    private int sleepSecond;
    private Object lock = new Object();

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        int soldTickets = 0;
        while (true) {
            setSleepTime(name);
            synchronized (lock) {
                if(leftTickets<=0) {
                    System.out.println("票已售罄！" + name + " 共售出" + soldTickets + "张票");
                    break;
                }
                System.out.println(name + " 售出了" + ++soldTickets + "张火车票，剩余票数为：" + --leftTickets);
                work();
            }
        }
    }

    public void work() {
        try {
            Thread.sleep(this.sleepSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setSleepTime(String currentThreadName) {
        SaleChannel trainStation = new TrainStation();
        SaleChannel officialWebsite = new OfficialWebsite();
        SaleChannel ctripApp = new CtripApp();

        switch (currentThreadName) {
            case "火车站售票窗口":
                this.sleepSecond = trainStation.getTimeCostPerTicket();
                break;
            case "12306官网":
                this.sleepSecond = officialWebsite.getTimeCostPerTicket();
                break;
            case "携程App":
                this.sleepSecond = ctripApp.getTimeCostPerTicket();
                break;
        }
    }
}
