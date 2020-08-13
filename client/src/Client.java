import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try (Commutator commutator = new Commutator("127.0.0.1", 7777)) {

            String request = "T1000";
            commutator.sendRequest(request);
            commutator.getResponse();
        } catch (IOException e) {
            throw new RuntimeException("Client fall");
        }
    }
}
