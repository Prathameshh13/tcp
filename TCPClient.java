import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000); // Connect to server on localhost and port 5000

            // Output stream to send file to server
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Input stream to read file from client's disk
            FileInputStream fis = new FileInputStream("file_to_send.txt");

            // Buffer to read file in chunks
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read file from disk and send to server
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");

            // Close streams and socket
            fis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
