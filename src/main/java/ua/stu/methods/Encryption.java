package ua.stu.methods;

public class Encryption {

    public static char[][] result(String word, String keyWordColumn, String keyWordRow) {
        int count = 5;
        StringBuilder StringBuilderKeyWordColumn = new StringBuilder(keyWordColumn);
        StringBuilder StringBuilderKeyWordRow = new StringBuilder(keyWordRow);

        StringBuilder StringBuilderWord = new StringBuilder(word);
        //Массив для ключевого слова.
        char[] keyColumn = new char[StringBuilderKeyWordColumn.length()];
        char[] keyRow = new char[StringBuilderKeyWordColumn.length()];

        char[][] table = new char[count][count];
        char[][] newTable = new char[count][count];
        char[][] newTableResult = new char[count][count];
        //записываем шифр в таблицу
        for (int i = 0, ii = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                table[i][j] = StringBuilderWord.charAt(ii);
                ii++;
            }
        }

        //записываем слово-шифр в массив
        for (int i = 0; i < StringBuilderKeyWordColumn.length(); i++) {
            keyColumn[i] = StringBuilderKeyWordColumn.charAt(i);
            keyRow[i] = StringBuilderKeyWordRow.charAt(i);
            System.out.print(keyRow[i]);
        }

        //меняем порядок букв в слове-шифре по столбцам в массиве
        for (int i = 0; i < keyColumn.length; i++) {
            int min = (int) keyColumn[i];
            int imin = i;
            //ищем букву, коротая встретиться в алвавите раньше всего
            for (int j = i + 1; j < keyColumn.length; j++) {
                if ((int) keyColumn[j] < min) {
                    min = (int) keyColumn[j];
                    imin = j;
                }
            }
            //если индекс этой буквы не равен букве, которую мы нашли , то меняем их местами
            if (i != imin) {
                int temp = (int) keyColumn[i];
                keyColumn[i] = (char) keyColumn[imin];
                keyColumn[imin] = (char) temp;
            }
        }

        //меняем порядок букв в слове-шифре по рядкам в массиве
        for (int i = 0; i < keyRow.length; i++) {
            int min = (int) keyRow[i];
            int imin = i;
            //ищем букву, коротая встретиться в алвавите раньше всего
            for (int j = i + 1; j < keyRow.length; j++) {
                if ((int) keyRow[j] < min) {
                    min = (int) keyRow[j];
                    imin = j;
                }
            }
            //если индекс этой буквы не равен букве, которую мы нашли , то меняем их местами
            if (i != imin) {
                int temp = (int) keyRow[i];
                keyRow[i] = (char) keyRow[imin];
                keyRow[imin] = (char) temp;
            }
        }

        boolean[] flag = new boolean[count];
        for (int i = 0; i < count; i++) {
            flag[i] = false;
        }
        //переставляем столбцы
        for (int j = 0; j < StringBuilderKeyWordColumn.length(); j++) {
            for (int y = 0; y < StringBuilderKeyWordColumn.length(); y++) {
                if ((int) keyColumn[j] == (int) StringBuilderKeyWordColumn.charAt(y) && !flag[y]) {
                    flag[y] = true;
                    for (int i = 0; i < count; i++) {
                        newTable[j][i] = table[i][y];
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < count; i++) {
            flag[i] = false;
        }
        //переставляем рядки
        for (int j = 0; j < StringBuilderKeyWordRow.length(); j++) {
            for (int y = 0; y < StringBuilderKeyWordRow.length(); y++) {
                if ((int) keyRow[j] == (int) StringBuilderKeyWordRow.charAt(y) && !flag[y]) {
                    flag[y] = true;
                    for (int i = 0; i < count; i++) {
                        newTableResult[i][j] = newTable[i][y];
                    }
                    break;
                }
            }
        }
        return newTableResult;
    }
}
