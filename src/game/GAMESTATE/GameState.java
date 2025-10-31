    package game.GAMESTATE;

    import game.MAIN.GamePanel;

    import java.awt.*;

    public class GameState {
        GamePanel gp;

        public enum State {
            MENU, PLAY, PAUSE, END, DONE
        }

        private State currentState;

        public GameState(GamePanel gp) {
            this.gp = gp;
            this.currentState = State.MENU;
        }

//        public GameState() {
//            currentState = State.MENU;
//        }

        public void setCurrentState(State state) {
            this.currentState = state;
        }

        public State getCurrentState() {
            return currentState;
        }

        public void draw(Graphics2D g2) {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString("Score: " + gp.score, 20, 30);
        }
    }
