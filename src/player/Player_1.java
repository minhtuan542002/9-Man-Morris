package player;

import status.Status;

public class Player_1 extends Player{
    public Player_1(String name) {
        super(name);
        this.addStatus(Status.HUMAN);
        this.addStatus(Status.RED);
        this.addStatus(Status.PHASE_1);
    }
}
