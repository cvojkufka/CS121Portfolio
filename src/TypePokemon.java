public class TypePokemon extends Pokemon{

    private String type;

    public TypePokemon(String name, int hp, int originalHp, String type) {
        super(name, hp, originalHp);
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nHP: %d\nType: %s\n", name, hp, type);
    }
}
