import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;

public class Menu {

    Scanner scnr = new Scanner(System.in);
    Pokedex pokedex = new Pokedex();
    Random rand = new Random();

    public void displayMenu() {
        boolean keepGoing = true;
        while(keepGoing) {
            System.out.println("************ MENU ************");
            System.out.println("Please make a selection:");
            System.out.print("1) Add a Pokemon\n2) Remove a Pokemon\n3) Display Pokemon info\n4) Display all Pokemon info\n5) Battle\n6) Exit\n>>");

            String choice = scnr.nextLine();

            if (choice.equals("1")) {
                createPokemon();
            } else if (choice.equals("2")) {
                deletePokemon();
            } else if (choice.equals("3")) {
                displayPokemon();
            } else if (choice.equals("4")) {
                displayAllPokemon();
            } else if (choice.equals("5")) {
                battle();
            } else if (choice.equals("6")) {
                keepGoing = false;
            } else {
                System.out.print("Not a valid option. Please try again.\n");
            }
        }
    }

    private void createPokemon() {
        boolean keepGoing = true;

        System.out.println("Please enter the name and hp and or type of your pokemon.");
        System.out.print("Name: ");
        String name = scnr.nextLine();
        System.out.print("HP: ");
        int health = scnr.nextInt();
        scnr.nextLine();
        System.out.print("Does your pokemon have a type: ");
        String option = scnr.nextLine();

        if(option.equals("y") || option.equals("yes")) {
            System.out.print("Type: ");
            String type = scnr.nextLine();
            TypePokemon pokemon = new TypePokemon(name, health, health, type);
            pokedex.addPokemon(pokemon);

            while (keepGoing) {
                System.out.print("Please enter the move of the pokemon or 'q' to quit: ");
                String choice = scnr.nextLine();
                if (choice.equals("q")) {
                    keepGoing = false;
                } else {
                    System.out.print("Please enter the power of the move: ");
                    int power = scnr.nextInt();
                    System.out.print("Please enter the speed of the move: ");
                    int speed = scnr.nextInt();
                    scnr.nextLine();
                    Move move = new Move(choice, power, speed);
                    pokemon.addMove(move);
                }
            }
            System.out.println(name + " has been added to the pokedex.");

        } else if (option.equals("no") || option.equals("n")){
            Pokemon pokemon = new Pokemon(name, health, health);
            pokedex.addPokemon(pokemon);

            while (keepGoing) {
                System.out.print("Please enter the move of the pokemon or 'q' to quit: ");
                String choice = scnr.nextLine();
                if (choice.equals("q")) {
                    keepGoing = false;
                } else {
                    System.out.print("Please enter the power of the move: ");
                    int power = scnr.nextInt();
                    System.out.print("Please enter the speed of the move: ");
                    int speed = scnr.nextInt();
                    scnr.nextLine();
                    Move move = new Move(choice, power, speed);
                    pokemon.addMove(move);
                }
            }
            System.out.println(name + " has been added to the pokedex.");
        } else {
            System.out.print("Invalid option.\n");
        }

    }

    private void deletePokemon() {
        System.out.println("What is the name of the pokemon you want to delete?");
        String name = scnr.nextLine();
        Pokemon pokemon = pokedex.getPokemon(name);
        if(pokemon == null) {
            System.out.println("Pokemon not found.");
        } else {
            pokedex.removePokemon(pokemon);
            System.out.println("That pokemon has been removed from the pokedex.");
        }

    }

    private void displayPokemon() {
        System.out.print("Enter the name of the pokemon you want to display: ");
        String pokemonName = scnr.nextLine();
        Pokemon pokemon = pokedex.getPokemon(pokemonName);
        if(pokemon == null) {
            System.out.println("Pokemon not found.");
        } else {
            System.out.print(pokemon);
            System.out.println("Moves: ");
            for(Move move : pokemon.getMoveLinkedList()) {
                System.out.println("- " + move);
            }
        }
    }

    private void displayAllPokemon() {
        LinkedList<Pokemon> allPokemon = pokedex.getPokemonLinkedList();
        System.out.println("All pokemon in pokedex: ");
        for(Pokemon pokemon : allPokemon) {
            System.out.print(pokemon);
            System.out.println("Moves:");
            for(Move move : pokemon.getMoveLinkedList()) {
                System.out.println("- " + move);
            }
            System.out.println("");
        }
    }

    private void battle() {
        System.out.print("Please enter the name of the first Pokemon: ");
        String pokemonName1 = scnr.nextLine();
        Pokemon pokemon1 = pokedex.getPokemon(pokemonName1);

        if(pokemon1 == null || !(pokemon1 instanceof TypePokemon)) {
            System.out.print("The first Pokemon is not recorded in your Pokedex or is not a type Pokemon.\n");
            return;
        }

        System.out.print("Please enter the name of the second Pokemon: ");
        String pokemonName2 = scnr.nextLine();
        Pokemon pokemon2 = pokedex.getPokemon(pokemonName2);

        if(pokemon2 == null || !(pokemon2 instanceof TypePokemon)) {
            System.out.print("The Pokemon is not recorded in your Pokedex or is not a type Pokémon.\n");
            return;
        }

        TypePokemon typePokemon1 = (TypePokemon) pokemon1;
        TypePokemon typePokemon2 = (TypePokemon) pokemon2;

        ArrayList<Pokemon> pokemonBattle = new ArrayList<>();
        pokemonBattle.add(typePokemon1);
        pokemonBattle.add(typePokemon2);

        System.out.println("Pokémon in Battle:\n" + pokemonBattle.get(0) + "\n" + pokemonBattle.get(1) + "\n");

        int round = 1;
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("*****Round " + round + "*****");

            int attacker = rand.nextInt(2);
            int defender = (attacker + 1) % 2;

            Move attackMove = pokemonBattle.get(attacker).getMoveLinkedList().get(rand.nextInt(pokemonBattle.get(attacker).getMoveLinkedList().size()));
            System.out.println(pokemonBattle.get(attacker).getName() + " attacks " + pokemonBattle.get(defender).getName() + " with " + attackMove.getMoveName());

            int damage = attackMove.getMovePower();
            pokemonBattle.get(defender).takeDamage(damage);

            if (pokemonBattle.get(defender).getHp() <= 0) {
                System.out.println(pokemonBattle.get(defender).getName() + " fainted!");
                System.out.println(pokemonBattle.get(attacker).getName() + " wins the battle!\n");
                break;
            }

            attacker = (attacker + 1) % 2;
            defender = (attacker + 1) % 2;
            attackMove = pokemonBattle.get(attacker).getMoveLinkedList().get(rand.nextInt(pokemonBattle.get(attacker).getMoveLinkedList().size()));
            System.out.println(pokemonBattle.get(attacker).getName() + " attacks " + pokemonBattle.get(defender).getName() + " with " + attackMove.getMoveName());

            damage = attackMove.getMovePower();
            pokemonBattle.get(defender).takeDamage(damage);

            if (pokemonBattle.get(defender).getHp() <= 0) {
                System.out.println(pokemonBattle.get(defender).getName() + " fainted!");
                System.out.println(pokemonBattle.get(attacker).getName() + " wins the battle!\n");
                break;
            }
            System.out.print("---------------\nHit enter to simulate another round: ");
            String user = scnr.nextLine();
            System.out.println();
            round++;
        }

        for (Pokemon pokemon : pokemonBattle) {
            pokemon.resetHealth();
        }
    }

}




