import java.util.LinkedList;

public class Pokemon {
    //Instance Variables
    protected String name;
    protected int hp, originalHp;
    // ArrayList with the type 'Move'
    private LinkedList<Move> moveLinkedList = new LinkedList<>();

    //Constructor
    // used to initialize the values
    public Pokemon(String name, int hp, int originalHp){
        this.name = name;
        this.hp = hp;
        this.originalHp = hp;
    }
    // Methods
    //getters for the Name, HitPoint and ArrayList
    public String getName(){
        return name;
    }
    public int getHp(){
        return hp;
    }

    public LinkedList<Move> getMoveLinkedList(){
        return moveLinkedList;
    }

    public void addMove(Move move){
        moveLinkedList.add(move);
    }

    public Move getMove(String moveName){
        Move foundMove = null;
        //enhanced for loop
        for(Move move: moveLinkedList){
            if(move.getMoveName().equals(moveName)){
                foundMove = move;
                break;
            }
        }
        return foundMove;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void resetHealth() {
        hp = originalHp; // Reset health to original value
    }

    //one form of polymorphism
    @Override
    public String toString(){
        return String.format("Name: %s\nHP: %d\n",name,hp);
    }

}
