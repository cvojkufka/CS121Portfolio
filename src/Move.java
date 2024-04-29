public class Move {

    //Instance variables
    private String moveName;
    private int movePower;
    private int moveSpeed;

    // Constructor
    // used to initialize the values
    public Move(String moveName, int movePower, int moveSpeed){

        this.moveName = moveName;
        this.movePower = movePower;
        this.moveSpeed = moveSpeed;
    }

    //Methods
    public String getMoveName()
    {
        return moveName;
    }
    public int getMovePower(){
        return movePower;
    }

    @Override
    public String toString(){
        return String.format("Move: %s (Power: %d Speed: %d)", moveName,movePower,moveSpeed);
    }
}
