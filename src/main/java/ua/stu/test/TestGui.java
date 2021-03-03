package ua.stu.test;


import ua.stu.view.MainGui;

import javax.swing.*;
import java.awt.*;

public class TestGui {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    MainGui window = new MainGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
