package lesson6;

import java.io.*;
import java.net.Socket;

public class HTTPSocket {

    private static final String ENDPOINT = "/market/api/v1/products/";
    private static final String HOST = "localhost";
    private static final int PORT = 8180;
    private static final String USERNAME = "user";
    private static final String PASSWORD = "100";
    private String token;
    private StringBuilder sb;

    public HTTPSocket() {
        try {
            this.sb = new StringBuilder();
            this.token = getJwt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendGet() throws IOException {
        sb.setLength(0);
        sb.append("GET " + ENDPOINT + "1 HTTP/1.1\n");
        sb.append("Accept: */*\r\n");
        sb.append("Authorization: Bearer ");
        sb.append(token);
        sb.append("\r\n");
        sb.append("Host: " + HOST + ":" + PORT + "\r\n");
        sb.append("Connection: Close\r\n");

        return sendRequestAndGetResponse();
    }

    public String sendPostRequest() throws IOException {
        sb.setLength(0);
        String body = "{\"title\": \"Test\", \"price\": \"10\", \"category\": {\"id\": \"1\", \"title\": \"Фрукты\"}}";
        sb.append("POST " + ENDPOINT).append(" HTTP/1.1\r\n");
        sb.append("Accept: */*\r\n");
        sb.append("Authorization: Bearer ").append(token).append("\r\n");
        sb.append("Host: " + HOST + ":" + PORT + "\r\n");
        sb.append("Content-Type: application/json\r\n");
        sb.append("Content-Length: ");
        sb.append(body.getBytes().length);
        sb.append("\r\n");
        sb.append("Connection: Close\r\n");
        sb.append("\r\n");
        sb.append(body);
        return sendRequestAndGetResponse();
    }

    public String sendDeleteRequest() throws IOException {
        sb.setLength(0);
        sb.append("DELETE " + ENDPOINT + "21").append(" HTTP/1.1\r\n");
        sb.append("Accept: */*\r\n");
        sb.append("Authorization: Bearer ").append(token).append("\r\n");
        sb.append("Host: " + HOST + ":" + PORT + "\r\n");
        sb.append("Connection: Close\r\n");
        sb.append("\r\n");
        return sendRequestAndGetResponse();
    }

    @SuppressWarnings("DuplicatedCode")
    private String getJwt() throws IOException {
        sb.setLength(0);
        String body = "{\"username\": \"" + USERNAME + "\", \"password\": \"" + PASSWORD + "\"}";
        sb.append("POST /market/auth").append(" HTTP/1.1\r\n");
        sb.append("Accept: */*\r\n");
        sb.append("Host: " + HOST + ":" + PORT + "\r\n");
        sb.append("Content-Type: application/json\r\n");
        sb.append("Content-Length: ").append(body.getBytes().length).append("\r\n");
        sb.append("Connection: Close\r\n");
        sb.append("\r\n");
        sb.append(body);
        String token = "";
        String[] responseSplit = sendRequestAndGetResponse().split("\n");
        for (String s : responseSplit) {
            if (s.matches(".*token.*")) {
                token = s.split(":")[1].replace("}", "").replace("\"", "");
            }
        }
        return token;
    }

    private String sendRequestAndGetResponse() throws IOException {
        Socket socket = new Socket(HOST, PORT);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        byte[] message = sb.toString().getBytes();
        out.write(message);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        sb.setLength(0);
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str).append("\n");
        }
        reader.close();
        socket.close();
        return sb.toString();
    }

}
