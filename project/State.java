/**
 * This enum contains the various possible states.
 */
public enum State {
    /**
     * Contains string "arrives".
     */
    ARRIVES("arrives"),
    /**
     * Contains string "waits".
     */
    WAITS("waits"),
    /**
     * Contains string "served".
     */
    SERVED("served"),
    /**
     * Contains string "leaves".
     */
    LEAVES("leaves"),
    /**
     * Contains string "done".
     */
    DONE("done");

    /**
     * Stores the state.
     */
    private String state;

    /**
     * Initialises the enum.
     * @param state is used to iniatlise the state field.
     */
    State(String state) {
        this.state = state;
    }
   
    /**
     * Returns the state.
     * @return the state.
     */
    @Override
    public String toString() {
        return this.state;
    }
}

