import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Commutator implements Closeable {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Commutator(String ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = createBufferedWriter(socket);
        reader = createBufferedReader(socket);
    }

    public Commutator(ServerSocket server) throws IOException {

        try {
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = createBufferedWriter(socket);
        reader = createBufferedReader(socket);
    }

    private BufferedWriter createBufferedWriter(Socket socket) {
        try {
            return new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
           throw new RuntimeException("Writer fall");
        }
    }

    private BufferedReader createBufferedReader(Socket socket) {
        try {
            return new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Reader fall");
        }
    }

    public boolean sendRequest(String request) {
        System.out.println("Request: " + request);
        try {

            writer.write(request);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getResponse() {
        String response;
        try {
            response =  reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Response null");
        }
        System.out.println("Response: " + response);
        return response;
    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
