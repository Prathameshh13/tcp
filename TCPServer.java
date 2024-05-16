    import java.io.*;
    import java.net.*;

    public class TCPServer {
        public static void main(String[] args) {
            try {
                ServerSocket serverSocket = new ServerSocket(5000); // Server socket listening on port 5000
                System.out.println("Server started. Waiting for client...");

                Socket clientSocket = serverSocket.accept(); // Accept client connection
                System.out.println("Client connected.");

                // Input stream to receive file from client
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

                // Output stream to write file to server's disk
                FileOutputStream fos = new FileOutputStream("received_file.txt");

                // Buffer to read file in chunks
                byte[] buffer = new byte[4096];
                int bytesRead;

                // Read file from client and write to server's disk
                while ((bytesRead = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                System.out.println("File received successfully.");

                // Close streams and socket
                fos.close();
                dis.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
