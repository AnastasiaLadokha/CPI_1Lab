package ua.stu.file;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SaveFile {
    public static void save(char[][] arr){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Виберіть файл для збереження");
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setApproveButtonText("Зберегти");
            jFileChooser.showOpenDialog(null);
            File file = jFileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if(path != null){
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                for(int i = 0; i < arr.length; i++){
                    for (int j = 0; j < arr.length; j++){
                        writer.write(String.valueOf(arr[i][j]));
                    }
                }
                writer.flush();
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Щось пішло не так!",
                    "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void save_message(String message){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Виберіть файл для збереження");
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.setApproveButtonText("Зберегти");
            jFileChooser.showOpenDialog(null);
            File file = jFileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if(path != null){
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                writer.write(message);
                writer.flush();
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Щось пішло не так!",
                    "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
