package ua.stu.view;

import ua.stu.file.OpenFile;
import ua.stu.file.SaveFile;
import ua.stu.methods.Decryption;
import ua.stu.methods.Encryption;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui {
    public JFrame frame;
    private JPanel MainPanel;
    private JRadioButton RadioButton1;
    private JRadioButton RadioButton2;
    private JTextField textFieldWord;
    private JTextField textFieldKeyWordColumn;
    private JTextField textFieldRowKeyWordRow;
    private JTable table1;
    private JButton button1;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuFile = new JMenu("Файл");
    private final JMenuItem menuItem_save = new JMenuItem("Зберегти файл");
    private final JMenuItem menuItem_open = new JMenuItem("Відкрити файл");

    public MainGui() {
        initialize();

    }

    /**
     * Initilize the contens of the frame
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 600, 450);
        frame.setMinimumSize(new Dimension(600, 450));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Laboratory work №1");
        frame.setContentPane(MainPanel);
        menuBar.add(menuFile);
        menuFile.add(menuItem_open);
        menuFile.add(menuItem_save);
        ButtonGroup bgrp = new ButtonGroup();
        bgrp.add(RadioButton1);
        bgrp.add(RadioButton2);
        frame.setJMenuBar(menuBar);
        RadioButton1.setSelected(true);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (RadioButton1.isSelected()) {
                    //Написать ошибку, если слово больше 25 символов
                    String word = textFieldWord.getText();
                    String keyWordColumn = textFieldKeyWordColumn.getText();
                    String keyWordRow = textFieldRowKeyWordRow.getText();
                    if (word.equals("") || keyWordColumn.equals("") || keyWordRow.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Не всі поля заповнені", "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (word.length() != 25 || keyWordColumn.length() != 5 || keyWordRow.length() != 5) {
                        JOptionPane.showMessageDialog(frame, "Неправильно введені дані", "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    char[][] table = new char[5][5];
                    table = Encryption.result(word, keyWordColumn, keyWordRow);

                   /* for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            System.out.print(table[j][i]);
                        }
                        System.out.println();
                    }
                    System.out.println();*/
                    TableModel model;
                    String[] columnNames = {"", "", "", "", ""};
                    Object[][] res = new Object[5][5];
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            res[i][j] = table[j][i];
                            //System.out.print(table[j][i]);
                        }
                    }
                    model = new DefaultTableModel(res, columnNames);
                    table1.setModel(model);

                } else {
                    //Написать ошибку, если слово больше 25 символов
                    String word = textFieldWord.getText();
                    String keyWordColumn = textFieldKeyWordColumn.getText();
                    String keyWordRow = textFieldRowKeyWordRow.getText();
                    if (word.equals("") || keyWordColumn.equals("") || keyWordRow.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Не всі поля заповнені", "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (word.length() != 25 || keyWordColumn.length() != 5 || keyWordRow.length() != 5) {
                        JOptionPane.showMessageDialog(frame, "Неправильно введені дані", "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    char[][] table = OpenFile.getFilledMatrix(word);
                    try {
                        Decryption.decrypt(table, keyWordColumn, keyWordRow);
                    } catch (Exception err) {
                        System.out.println(err.getMessage());
                    }

                    String result = "";
                    //System.out.println();
                    TableModel model;
                    String[] columnNames = {"", "", "", "", ""};
                    Object[][] res = new Object[5][5];
                    for (int i = 0; i < table.length; i++) {
                        for (int j = 0; j < table.length; j++) {
                            res[i][j] = table[i][j];
                            result += table[i][j];
                            //System.out.print(table[i][j]);
                        }
                    }
                    model = new DefaultTableModel(res, columnNames);
                    table1.setModel(model);
                    JOptionPane.showMessageDialog(frame, result, "Результат розшифровки", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //НАДВСЕЙИСПАНИЕЙБЕЗОБЛАЧНО
        menuItem_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = OpenFile.open();
                textFieldWord.setText(word);

            }
        });
        menuItem_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[][] table = new char[5][5];
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        table[i][j] = (char) table1.getValueAt(i, j);
                    }
                }
                SaveFile.save(table);
            }
        });
    }
}
