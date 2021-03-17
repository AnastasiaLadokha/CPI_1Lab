package ua.stu.methods;

import javax.swing.*;

public class SimpleReplacementEncryption {
    private String m_dictionary;
    private int m_a;
    private int m_b;
    private int m_length;

    public int getIndexInDictionary(char symbol) {
        return m_dictionary.indexOf(symbol);
    }

    public char doCryptSymbol(char symbol) {
        int x = getIndexInDictionary(symbol);
        if (x == -1) {
            return symbol;
        }
        int ex = (m_a * x + m_b) % m_length;

        return m_dictionary.charAt(ex);
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public String encrypt(String dictionary, int a, int b, String message){
        m_dictionary = dictionary.toLowerCase();
        m_a = a;
        m_b = b;
        m_length = dictionary.length();

        int greatestCommonDivisor = MathUtils.greatestCommonDivisor(a, m_length);
        if (greatestCommonDivisor != 1 || a >= m_length || b >= m_length || a <= 0 || b <= 0) {
            JOptionPane.showMessageDialog(null,
                    "А повинно бути взаємно простим до довжини алфавіту",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        StringBuilder cryptMessage = new StringBuilder();

        char[] symbols = message.toLowerCase().toCharArray();
        for(char symbol : symbols) {
            cryptMessage.append(doCryptSymbol(symbol));
        }

        return cryptMessage.toString();
    }
}
