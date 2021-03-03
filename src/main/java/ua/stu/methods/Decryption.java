package ua.stu.methods;

public class Decryption {
    public static void swapColumns(char m[][], int a, int b)
    {
        for (int c = 0; c < m.length; c ++) {
            char tmp = m[c][a];
            m[c][a] = m[c][b];
            m[c][b] = tmp;
        }
    }


    public static void swapRows(char m[][], int a, int b) {
        for (int c = 0; c < m.length; c ++) {
            char tmp = m[a][c];
            m[a][c] = m[b][c];
            m[b][c] = tmp;
        }
    }

    public static int findSymbol(char[] arr, char symbol, int lastIndex) {
        for (int i = lastIndex; i < arr.length; i ++) {
            if (arr[i] == symbol) {
                return i;
            }
        }
        return -1;
    }

    //метод расшифровка
    public static void decrypt(char[][] data, String keyCol, String keyRow) throws Exception {
        {
            char []arrSorted = keyCol.toCharArray();
            for (int i = 0; i < arrSorted.length; i ++) {
                for (int k = i; k < arrSorted.length; k ++) {
                    if (arrSorted[i] > arrSorted[k]) {
                        char tmp = arrSorted[i];
                        arrSorted[i] = arrSorted[k];
                        arrSorted[k] = tmp;
                    }
                }
            }

            char[] arr = keyCol.toCharArray();

            for (int i = 0; i < arr.length; i ++) {
                int index = findSymbol(arrSorted, arr[i], i);
                if (index != i && index != -1) {
                    swapColumns(data, index, i);

                    char tmp = arrSorted[i];
                    arrSorted[i] = arrSorted[index];
                    arrSorted[index] = tmp;
                }
            }

            if (!String.valueOf(arrSorted).equals(keyCol)) {
                throw new Exception("Cannot decrypt row key");
            }
        }
        {
            char []arrSorted = keyRow.toCharArray();
            for (int i = 0; i < arrSorted.length; i ++) {
                for (int k = i; k < arrSorted.length; k ++) {
                    if (arrSorted[i] > arrSorted[k]) {
                        char tmp = arrSorted[i];
                        arrSorted[i] = arrSorted[k];
                        arrSorted[k] = tmp;
                    }
                }
            }

            char[] arr = keyRow.toCharArray();

            for (int i = 0; i < arr.length; i ++) {
                int index = findSymbol(arrSorted, arr[i], i);
                if (index != i && index != -1) {
                    swapRows(data, index, i);

                    char tmp = arrSorted[i];
                    arrSorted[i] = arrSorted[index];
                    arrSorted[index] = tmp;
                }
            }

            if (!String.valueOf(arrSorted).equals(keyRow)) {
                throw new Exception("Cannot decrypt column key");
            }
        }
    }
}
