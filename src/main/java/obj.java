public class obj {
    private String memberID;
    private int timer = 0;
    private int xp = 1;

    public obj(){

    }

    public int getTimer() {
        return timer;
    }

    public int getXp() {
        return xp;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}

