package net.kajos.Manager;

import java.util.concurrent.Semaphore;

public class Viewer {
    public Quality quality = new Quality();

    public int rgb[][][][] = null;

    public int[] lastKeyFrameSize = {-1, -1};
    public int[] lastInterFrameSize = {0, 0};
    public float[] lastDifference = {0, 0};
    public int frameCount = 0;
    public Semaphore frameSem = new Semaphore(1);
    public float sumDifference = 0f;
    public boolean keyFrameToggle = true;

    public int clientWidth = 1000;
    public int clientHeight = 1000;

    public int receivedFrameStamp = 0;
    public int allowedLatency = 0;
    public int latencyPolls = 0;

    public void frameUpdate(int frameStamp) {
        receivedFrameStamp = frameStamp;
    }

    public void reset() {
        receivedFrameStamp = 0;
        rgb = null;
    }
}
