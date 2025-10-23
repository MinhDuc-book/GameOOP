package game.GAMESTATE;

import java.awt.event.MouseEvent;
import game.MAIN.GamePanel;

public class GameState {

    public enum State {
        MENU, PLAY, PAUSE, END, DONE
    }

    private State currentState;
    private GamePanel gp;

    public GameState(GamePanel gp) {
        this.gp = gp;
        this.currentState = State.MENU;
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }

    public State getCurrentState() {
        return currentState;
    };


    // Hàm xử lý chuột cho từng trạng thái
    public void mousePressed(MouseEvent e) {
        switch (currentState) {
            case DONE:
                DoneState doneState = new DoneState(gp);
                doneState.mousePressed(e);
                break;
            case END:
                EndState endState = new EndState(gp);
                endState.mousePressed(e);
                break;
            case PAUSE:
                PauseState pauseState = new PauseState(gp);
                pauseState.mousePressed(e);
                break;
            default:
                break;
        }
    }
}
