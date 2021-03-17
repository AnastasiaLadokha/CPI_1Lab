package ua.stu.methods;

import javax.swing.*;

public class SimpleReplacementDecryption {

    private String dictionary;

    public int getIndexInDictionary(char symbol) {
        return dictionary.indexOf(symbol);
    }

    public String decryption(String alphabet, int a, int b, String message){
        dictionary = alphabet.toLowerCase();
        int length = alphabet.length();

        int greatestCommonDivisor = MathUtils.greatestCommonDivisor(a, length);
        if (greatestCommonDivisor != 1 || a >= length || b >= length || a <= 0 || b <= 0) {
            JOptionPane.showMessageDialog(null,
                    "А повинно бути взаємно простим до довжини алфавіту",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        char[] symbols = message.toLowerCase().toCharArray();
        StringBuilder decryptMessage = new StringBuilder();

        for (char symbol : symbols){
            decryptMessage.append(doCryptSymbol(symbol, a, b, length));
        }
        return decryptMessage.toString();
    }

    public char doCryptSymbol(char symbol, int a, int b, int length) {
        int x = getIndexInDictionary(symbol);
        if (x == -1) {
            return symbol;
        }

        int i;
        for(i = 0; i < length; i++){
            if((a * i) % length ==  1){
                break;
            }
        }

        int y = (i * (x + length - b)) % length;
        return dictionary.charAt(y);
    }
}
