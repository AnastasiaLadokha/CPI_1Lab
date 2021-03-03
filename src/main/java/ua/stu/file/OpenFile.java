package ua.stu.file;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OpenFile {
    public static String open() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFileChooser dialog = new JFileChooser();
            dialog.setDialogTitle("Виберіть файл для відкриття");
            dialog.setApproveButtonText("Відкрити");
            dialog.showOpenDialog(null);
            File file = dialog.getSelectedFile();
            String lineFromFile = readLineFromFile(file.getAbsolutePath());
            return lineFromFile;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Щось пішло не так!",
                    "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private static String readLineFromFile(String pathToFile) {
        try {
            return Files.readAllLines(Paths.get(pathToFile))
                    .get(0);
        } catch (IOException exception) {
            throw new RuntimeException("Cant read file", exception);
        }
    }

    public static char[][] getFilledMatrix(String listOfWords) {
        char[][] matrix = new char[5][5];
        char[] letters = listOfWords.toCharArray();
        int index = 0;
        System.out.println();
        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {
                matrix[i][j] = letters[index];
                System.out.print(matrix[i][j]);
                index++;
            }
            System.out.println();
        }
        return matrix;
    }
}
