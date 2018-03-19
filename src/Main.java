
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        Socket client;
        DataOutputStream out;
        DataInputStream in;
        ServerSocket server=null;

        try {
            server = new ServerSocket(1313);
        }
        catch(IOException ex)
        {
            ex.getMessage();
        }


try{
    System.out.print("Connection accepted.");
    while(true) {

         client = server.accept();
         out = new DataOutputStream(client.getOutputStream());
         in = new DataInputStream(client.getInputStream());

        int N = in.readInt();
        int M = in.readInt();
        System.out.println();

        Matrix A = new Matrix(N, M);
        // Запись матрицы A
        for (int i = 0; i < A.getN(); i++) {
            for (int j = 0; j < A.getM(); j++) {
                A.changeElement(i, j, in.readDouble());
                System.out.print(A.getElement(i,j)+" ");

            }
            System.out.println();

        }

        N = in.readInt();
        M = in.readInt();

        Matrix B = new Matrix(N, M);
        // Запись матрицы B
        for (int i = 0; i < B.getN(); i++) {
            for (int j = 0; j < B.getM(); j++) {
                B.changeElement(i, j, in.readDouble());

            }
        }


        if ((A.getM()) != (B.getN())) {
            out.writeBoolean(true);
        } else {
            out.writeBoolean(false);
            Matrix rezult = Matrix.rezult(A, B);
            // Отправляем результат
            out.writeInt(rezult.getN());
            out.writeInt(rezult.getM());


            for (int i = 0; i < rezult.getN(); i++) {
                for (int j = 0; j < rezult.getM(); j++) {

                    out.writeDouble(rezult.getElement(i, j));

                }

            }
        }
        in.close();
        out.close();
        client.close();

    }

}
catch(IOException ex)
{


}
catch(OutOfMemoryError ex){

}

    }

}
