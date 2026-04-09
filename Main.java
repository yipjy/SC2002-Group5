import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Welcome to the Turn-Based Combat Arena!");
        boolean playAgain = true;


        while (playAgain) {
            Player player = choosePlayer();
            chooseItems(player);
            int difficulty = chooseDifficulty();


            List<Enemy> initialWave = new ArrayList<>();
            List<Enemy> backupWave = new ArrayList<>();
            setupLevel(difficulty, initialWave, backupWave);


            GameCLI cli = new GameCLI();
            TurnOrderStrategy turnOrder = new SpeedBasedTurnOrder();
            BattleEngine engine = new BattleEngine(player, initialWave, backupWave, turnOrder, cli);


            engine.runBattle();


            if (player.isAlive()) {
                cli.displayVictory(player, engine.getRoundCount());
            } else {
                cli.displayDefeat(engine.getActiveEnemies(), engine.getRoundCount());
            }


            playAgain = askPlayAgain();
        }


        System.out.println("Thanks for playing!");
    }


    private static Player choosePlayer() {
        System.out.println("\nChoose your character:");
        System.out.println("1. Warrior  (HP: 260, ATK: 40, DEF: 20, SPD: 30) — Special: Shield Bash");
        System.out.println("2. Wizard   (HP: 200, ATK: 50, DEF: 10, SPD: 20) — Special: Arcane Blast");
        int choice = readInt(1, 2);
        return choice == 1 ? new Warrior() : new Wizard();
    }


    private static void chooseItems(Player player) {
        System.out.println("\nChoose 2 items (duplicates allowed):");
        for (int i = 1; i <= 2; i++) {
            System.out.println("Pick item " + i + ":");
            System.out.println("1. Potion      — Heal 100 HP");
            System.out.println("2. Power Stone — Free special skill use");
            System.out.println("3. Smoke Bomb  — Enemy attacks deal 0 damage for 2 turns");
            int choice = readInt(1, 3);
            if (choice == 1) player.addItem(new Potion());
            else if (choice == 2) player.addItem(new PowerStone());
            else player.addItem(new SmokeBomb());
        }
    }


    private static int chooseDifficulty() {
        System.out.println("\nChoose difficulty:");
        System.out.println("1. Easy   — 3 Goblins");
        System.out.println("2. Medium — 1 Goblin + 1 Wolf, then 2 Wolves");
        System.out.println("3. Hard   — 2 Goblins, then 1 Goblin + 2 Wolves");
        return readInt(1, 3);
    }


    private static void setupLevel(int difficulty, List<Enemy> initialWave, List<Enemy> backupWave) {
        if (difficulty == 1) {
            initialWave.add(new Goblin("Goblin A"));
            initialWave.add(new Goblin("Goblin B"));
            initialWave.add(new Goblin("Goblin C"));
        } else if (difficulty == 2) {
            initialWave.add(new Goblin("Goblin"));
            initialWave.add(new Wolf("Wolf"));
            backupWave.add(new Wolf("Wolf A"));
            backupWave.add(new Wolf("Wolf B"));
        } else {
            initialWave.add(new Goblin("Goblin A"));
            initialWave.add(new Goblin("Goblin B"));
            backupWave.add(new Goblin("Goblin C"));
            backupWave.add(new Wolf("Wolf A"));
            backupWave.add(new Wolf("Wolf B"));
        }
    }


    private static boolean askPlayAgain() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Play again");
        System.out.println("2. Exit");
        return readInt(1, 2) == 1;
    }


    private static int readInt(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) return input;
                System.out.println("Enter a number between " + min + " and " + max + ":");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again:");
            }
        }
    }
}
