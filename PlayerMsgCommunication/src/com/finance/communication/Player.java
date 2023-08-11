package com.finance.communication;

import java.util.concurrent.atomic.AtomicInteger;

class Player {
    private static AtomicInteger globalCounter = new AtomicInteger(0);
    private final String name;
    private AtomicInteger sendCount;
    private AtomicInteger receiveCount;

    public Player(String name) {
        this.name = name;
        this.sendCount = new AtomicInteger(0);
        this.receiveCount = new AtomicInteger(0);
    }

    public void sendMessage(Player receiver, String message) {
        int currentSendCount = sendCount.getAndIncrement();
        String replyMessage = message;
        if (currentSendCount > 0) {
            replyMessage += " - ";
            for (int i = 0; i < currentSendCount; i++) {
                replyMessage += i + " - ";
            }
            replyMessage += (currentSendCount - 1);
        }
        receiver.receiveMessage(this, replyMessage);
    }

    public void receiveMessage(Player sender, String message) {
        int currentReceiveCount = receiveCount.getAndIncrement();
        System.out.println(name + " received message from " + sender.name + ": " + message + " (Received Count: " + currentReceiveCount + ")");
        if (sendCount.get() < 10) {
            sendMessage(sender, message);
        }
    }

    public AtomicInteger getSendCount() {
        return sendCount;
    }

    public AtomicInteger getReceiveCount() {
        return receiveCount;
    }

    public static AtomicInteger getGlobalCounter() {
        return globalCounter;
    }

    public static void setGlobalCounter(AtomicInteger counter) {
        Player.globalCounter = counter;
    }
}
