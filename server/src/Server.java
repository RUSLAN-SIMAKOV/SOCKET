import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(7777);
        System.out.println("Server started ");

        while (true != false) {
        try (Commutator commutator = new Commutator(server)) {

            new Thread(() -> {
                commutator.getResponse();
                try {
                    Thread.sleep(7777);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                commutator.sendRequest( "T1000 ready");
            }).start();

        } catch (IOException e) {
            throw new RuntimeException("Server fall");
        }}
    }
}
