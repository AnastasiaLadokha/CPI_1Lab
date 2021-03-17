package ua.stu.view;

import ua.stu.file.OpenFile;
import ua.stu.file.SaveFile;
import ua.stu.methods.DoubleSwapDecryption;
import ua.stu.methods.DoubleSwapEncryption;
import ua.stu.methods.SimpleReplacementDecryption;
import ua.stu.methods.SimpleReplacementEncryption;

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
    private JTabbedPane tabbedPane1;
    private JTextField textFieldAlphabet;
    private JRadioButton radioButtonEncryption;
    private JRadioButton radioButtonDecryption;
    private JTextArea textAreaMessage;
    private JTextArea textAreaResult;
    private JButton ButtonResultLab2;
    private JTextField textFieldA;
    private JTextField textFieldB;
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
        frame.setBounds(200, 200, 615, 465);
        frame.setMinimumSize(new Dimension(600, 450));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Laboratory work");
        frame.setContentPane(MainPanel);
        menuBar.add(menuFile);
        menuFile.add(menuItem_open);
        menuFile.add(menuItem_save);
        ButtonGroup bgrp = new ButtonGroup();
        bgrp.add(RadioButton1);
        bgrp.add(RadioButton2);
        bgrp.add(radioButtonEncryption);
        bgrp.add(radioButtonDecryption);
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
                    table = DoubleSwapEncryption.result(word, keyWordColumn, keyWordRow);
                    TableModel model;
                    String[] columnNames = {"", "", "", "", ""};
                    Object[][] res = new Object[5][5];
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            res[i][j] = table[j][i];
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
                        DoubleSwapDecryption.decrypt(table, keyWordColumn, keyWordRow);
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
        ButtonResultLab2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonEncryption.isSelected()) {
                    String s1 = textFieldA.getText();
                    String s2 = textFieldB.getText();
                    String dictionary = textFieldAlphabet.getText();
                    String message = textAreaMessage.getText();
                    if (s1.equals("") || s2.equals("") || dictionary.equals("") || message.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Не всі поля заповнені", "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int a, b;
                    try {
                        a = Integer.parseInt(s1);
                        b = Integer.parseInt(s2);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(frame, "Значення А та В повиині бути числовими", "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    SimpleReplacementEncryption enc = new SimpleReplacementEncryption();
                    String result = enc.encrypt(dictionary, a, b, message);
                    textAreaResult.setText(result);

                } else if (radioButtonDecryption.isSelected()){
                    int a = Integer.parseInt(textFieldA.getText());
                    int b = Integer.parseInt(textFieldB.getText());
                    String dictionary = textFieldAlphabet.getText();
                    String message = textAreaMessage.getText();
                    if (a == 0 || b == 0 || dictionary.equals("") || message.equals("")) {
                        JOptionPane.showMessageDialog(frame, "Не всі поля заповнені",
                                "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(a >= b){
                        JOptionPane.showMessageDialog(frame, "А повинно бути менше за В",
                                "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    SimpleReplacementDecryption decryption = new SimpleReplacementDecryption();
                    String result = decryption.decryption(dictionary, a, b, message);
                    textAreaResult.setText(result);
                }
            }
        });
        //НАДВСЕЙИСПАНИЕЙБЕЗОБЛАЧНО
        menuItem_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = OpenFile.open();
                if(tabbedPane1.getSelectedIndex() == 0) {
                    textFieldWord.setText(word);
                } else if(tabbedPane1.getSelectedIndex() == 1) {
                    textAreaMessage.setText(word);
                }
            }
        });
        menuItem_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tabbedPane1.getSelectedIndex() == 0) {
                    char[][] table = new char[5][5];
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            table[i][j] = (char) table1.getValueAt(i, j);
                        }
                    }
                    SaveFile.save(table);
                } else if(tabbedPane1.getSelectedIndex() == 1) {
                     String text = textAreaResult.getText();
                     if(text.equals("")) {
                         JOptionPane.showMessageDialog(frame, "Потрібно зашифрувати текст", "Помилка", JOptionPane.ERROR_MESSAGE);
                         return;
                     }
                     SaveFile.save_message(text);
                }
            }
        });
    }
}
