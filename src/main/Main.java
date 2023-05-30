package main;

import javax.swing.*;

import frame.LoginRegisterFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginRegisterFrame loginRegisterFrame = new LoginRegisterFrame();
                loginRegisterFrame.setVisible(true);
            }
        });
    }
}