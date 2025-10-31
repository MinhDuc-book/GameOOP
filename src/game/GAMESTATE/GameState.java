    package game.GAMESTATE;

    public class GameState {

        public enum State {
            MENU, PLAY, PAUSE, END, DONE
        }

        private State currentState;

        public GameState() {
            currentState = State.MENU;
        }

        public void setCurrentState(State state) {
            this.currentState = state;
        }

        public State getCurrentState() {
            return currentState;
        }

    }
