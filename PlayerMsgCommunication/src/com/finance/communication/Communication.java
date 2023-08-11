package com.finance.communication;

public class Communication {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        player1.sendMessage(player2, "Hello");

        // Wait for the players to finish sending messages
        while (player1.getSendCount().get() < 10 && player2.getSendCount().get() < 10) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Program completed gracefully.");
    }
}
