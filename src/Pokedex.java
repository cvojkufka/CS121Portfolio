import java.util.LinkedList;

public class Pokedex {
    // Instance Variable
    private LinkedList<Pokemon> pokemonLinkedList = new LinkedList<>();
    public void addPokemon(Pokemon pokemon){
        pokemonLinkedList.add(pokemon);
    }
    // Using the Built-in .remove() method from arrayList to
    // remove the pokemon out of the arrayList
    public void removePokemon(Pokemon pokemon){
        pokemonLinkedList.remove(pokemon);
    }

    // Returning the ArrayList of pokemons
    public LinkedList<Pokemon> getPokemonLinkedList(){
        return pokemonLinkedList;
    }
    // Searches the pokemonArrayList for the Pokemon object
    // which matches the name and returns it.
    // if no match pokemon is found it returns null.
    // This method is for getting the specific pokemon details from the Pokedex.
    public Pokemon getPokemon(String pokemonName){
        Pokemon foundPokemon = null;
        for(Pokemon pokemon : pokemonLinkedList){
            if(pokemon.getName().equals(pokemonName)){
                foundPokemon = pokemon;
                break;
            }
        }
        return foundPokemon;
    }
}
