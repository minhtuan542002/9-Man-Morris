package Player;

import Status.Status;

public class Player_2 extends Player{
    public Player_2(String name) {
        super(name);
        this.addStatus(Status.HUMAN);
        this.addStatus(Status.BLUE);
        this.addStatus(Status.PHASE_1);
    }

}
