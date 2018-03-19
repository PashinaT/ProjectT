
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("127.0.0.1", 1313);
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            System.out.println("Client connected to socket.");
            String file1="file.txt";
            String file2="file1.txt";
            String file3="file3.txt";

            System.out.println("Введите размерности матрицы");
            Scanner scanner = new Scanner(System.in);
            try {
                int n = scanner.nextInt();
                int m = scanner.nextInt();
                oos.writeInt(n);
                oos.writeInt(m);
                System.out.println("Введите матрицy");
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < m; j++)
                        oos.writeDouble(scanner.nextDouble());
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Ошибка");
            }
//            String file1 = scanner.nextLine();
//            System.out.println("Введите файл со второй матрицей");
//            String file2 = scanner.nextLine();
//            System.out.println("Введите файл для результирующей матрицы");
//            String file3 = scanner.nextLine();
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file3), "UTF8"));


         /*   Matrix matrix1 = Matrix.createMatrix(file1);
            oos.writeInt(matrix1.getN());
            oos.writeInt(matrix1.getM());
            System.out.println("Client sent matrix1 ");
            // тут должна быть передача данных
            for (int i = 0; i < matrix1.getN(); i++) {
                for (int j = 0; j < matrix1.getM(); j++)
                    oos.writeDouble(matrix1.getElement(i, j));
            }*/

            Matrix matrix2 = Matrix.createMatrix(file2);
            oos.writeInt(matrix2.getN());
            oos.writeInt(matrix2.getM());
            System.out.println("Client sent matrix2 ");
            // тут должна быть передача данных
            for (int i = 0; i < matrix2.getN(); i++) {
                for (int j = 0; j < matrix2.getM(); j++)
                    oos.writeDouble(matrix2.getElement(i, j));
            }


            // Получаем результат
            Boolean flag = ois.readBoolean();
            if (flag == true)
            {
                FileOutputStream fos=new FileOutputStream(file3);
                String str= "Матрицы не подходящего размера";
                byte[] buffer =str.getBytes();
                fos.write(buffer);
            }
            else
            {
                int N = ois.readInt();
                int M = ois.readInt();
                Matrix Z = new Matrix(N, M);
                for (int i = 0; i < Z.getN(); i++) {
                    for (int j = 0; j < Z.getM(); j++) {
                        Z.changeElement(i, j, ois.readDouble());
                    }
                }
                Matrix.putInFile(Z, file3);
            }
        }
        catch(ConnectException ex)
        {
            System.err.println("Проблема в связи");
        }
        catch(SocketException ex)
        {
            System.err.println("упс");
        }



    }
}
