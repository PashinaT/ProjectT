import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Matrix {
    private int n;
    private int m;
    double [][] matrix;

    Matrix(int n,int m)
    {
        this.n=n;
        this.m=m;
        this.matrix=new double[n][m];
    }

    public int getN ()
    {
        return n;
    }
    public int getM ()
    {
        return m;
    }
    public double getElement(int i,int j)
    {
        return matrix[i][j];
    }
    public void changeElement(int i, int j, double newElement)
    {
        matrix[i][j]= newElement;
    }

    static Matrix rezult (Matrix A,Matrix B) {
        Matrix z = null;
        int n = A.getN();
        int m = B.getM();
        double s = 0;
        z = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int l = 0; l < m; l++) {
                s = 0;
                for (int j = 0; j < A.getM(); j++) {
                    s += A.getElement(i, j) * B.getElement(j, l);
                }
                z.changeElement(i, l, s);

            }
        }

            return z;
        }


    public static int[] countNM (String file) throws IOException {
        int NM[] = new int[2];
        int n =0;
        int m=0;
        int maxM=0;
        String line = "";
        String line2 = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                if (!line.equals(""))
                {
                    line2 = line;
                    m = line2.split(" +").length;
                    if (m>maxM)
                    {
                        maxM=m;
                    }
                    n++;
                }
            }
            m = line2.split(" +").length;
            reader.close();
            NM[0]=n;
            NM[1]=maxM;
            if (n==0)
                throw new InputMismatchException("");
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("Файл не найден при подсчёте размера матрицы");

        }
        catch (IOException e) {
            System.err.println("Ошибка");

        }
        catch (InputMismatchException e)
        {
            System.err.println("файл пуст");

        }

        return NM;
    }





    public static void putInFile(Matrix A, String file) throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "UTF8"));
        for(int i=0;i<A.getN();i++) {
            for (int j = 0; j < A.getM(); j++) {
                String s = Double.toString(A.getElement(i, j));
                out.append( s + " ");
            }
           out.write('\n') ;
        }
        out.flush();
        out.close();


    }


    public static Matrix createMatrix(String file) throws IOException {

        Matrix z = null;
        int[] NM = new int[2];

        try {
            NM = countNM(file);
         z = new Matrix(NM[0], NM[1]);
        Scanner reader = new Scanner(new File(file));

        for (int i = 0; i < NM[0]; i++) {
                for (int j = 0; j < NM[1]; j++) {
                    z.changeElement(i, j, reader.nextDouble());
                }
        }
    }
        catch (FileNotFoundException ex) {
            System.err.println("Файл не найден ");

        }
        catch (InputMismatchException ex) {
            System.err.println("Ошибка ввода");

        }
        catch (NoSuchElementException e) {
            System.err.println("Не найден элемет(" );
       }
           return z;
    }
}


