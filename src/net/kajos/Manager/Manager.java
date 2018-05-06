package net.kajos.Manager;

import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

import java.awt.*;

public class Manager extends BaseWebSocketHandler {
    private Viewer viewer = null;
    private Input input;
    private WebSocketConnection connection = null;

    public Manager() throws AWTException {
        input = new Input();
    }

    public void sendImage(byte[] data) {
        if (connection == null) return;

        connection.send(data);
    }

    final static byte[] EMPTY_IMAGE = new byte[]{0};
    public void sendEmptyImage() {
        sendImage(EMPTY_IMAGE);
    }

    private void closeConnection() {
        if (connection != null) {
            connection.close();
            connection = null;
        }
        viewer = null;
    }

    public void onOpen(WebSocketConnection conn) {
        closeConnection();

        connection = conn;

        System.out.println("Connection opened");
    }

    public void onClose(WebSocketConnection conn) {
        connection = null;
        viewer = null;

        System.out.println("Connection closed");
    }

    public Viewer getViewer() {
        return viewer;
    }

    public Viewer ensureViewer() {
        if (viewer == null) viewer = new Viewer();
        return viewer;
    }

    @Override
    public void onMessage(WebSocketConnection connection, String message) {
        if (viewer == null) return;

        if (message.equals("1")) {
            viewer.frameUpdate();
        } else {
            input.parseInput(message);
        }
    }

    public float getSumBandwidth() {
        if (viewer == null) return 0f;

        return viewer.bandwidth.get();
    }
}