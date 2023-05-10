package utils;

public class Timer {
    private int seconds;
    private int secondleft;
    private long lastCheck;

    public Timer(int seconds) {
        this.seconds = seconds;
        this.secondleft = seconds;
        this.lastCheck = System.currentTimeMillis();
    }


    public void updatesSecondLeft() {
        if(lastCheck + 1000 < System.currentTimeMillis()) {
            this.secondleft--;
            this.lastCheck = System.currentTimeMillis();
        }
    }

    public int getSecondLeft() {
        return this.secondleft;
    }

    public boolean isFinished() {
        return this.secondleft <= 0;
    }

    public boolean updateTimer(){
        updatesSecondLeft();
        return isFinished();
    }
}
