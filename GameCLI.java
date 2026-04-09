import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameCLI {
    private Scanner scanner;


    public GameCLI() { this.scanner = new Scanner(System.in); }


    public void displayStatus(Player player, List<Enemy> enemies) {
        System.out.println("\n--- Battle Status ---");
        System.out.println(player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp()
                + " | Cooldown: " + player.getCooldown());
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                System.out.println(enemy.getName() + " HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
            }
        }
        System.out.println("---------------------");
    }


    public Action chooseAction(Player player) {
        System.out.println("\nChoose an action:");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");


        List<Item> items = player.getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((3 + i) + ". Use " + items.get(i).getName());
        }


        int specialOption = 3 + items.size();
        if (player.getCooldown() == 0) {
            System.out.println(specialOption + ". " + player.getSpecialSkill().getName() + " (Special Skill)");
        } else {
            System.out.println(specialOption + ". " + player.getSpecialSkill().getName() + " (Cooldown: " + player.getCooldown() + ")");
        }


        int choice = readInt(1, specialOption);
        if (choice == 1) return new BasicAttack();
        if (choice == 2) return new Defend();
        if (choice < specialOption) return new UseItem(choice - 3);
        return new UseSpecialSkill();
    }


    public List<Combatant> chooseTargets(Player player, Action action, List<Enemy> activeEnemies) {
        List<Combatant> targets = new ArrayList<>();
        List<Enemy> aliveEnemies = new ArrayList<>();
        for (Enemy e : activeEnemies) {
            if (e.isAlive()) aliveEnemies.add(e);
        }


        // Arcane Blast hits all enemies
        if (action instanceof UseSpecialSkill && player instanceof Wizard) {
            targets.addAll(aliveEnemies);
            return targets;
        }


        // Defend targets self
        if (action instanceof Defend) {
            targets.add(player);
            return targets;
        }


        // Item targeting
        if (action instanceof UseItem) {
            Item item = player.getItem(((UseItem) action).getItemIndex());
            if (item instanceof Potion || item instanceof SmokeBomb) {
                targets.add(player);
                return targets;
            }
            if (item instanceof PowerStone && player instanceof Wizard) {
                targets.addAll(aliveEnemies);
                return targets;
            }
        }


        // Single enemy target
        System.out.println("\nChoose a target:");
        for (int i = 0; i < aliveEnemies.size(); i++) {
            Enemy e = aliveEnemies.get(i);
            System.out.println((i + 1) + ". " + e.getName() + " HP: " + e.getHp() + "/" + e.getMaxHp());
        }


        int choice = readInt(1, aliveEnemies.size());
        targets.add(aliveEnemies.get(choice - 1));
        return targets;
    }


    public void displayRoundStart(int round) {
        System.out.println("\n========= Round " + round + " =========");
    }


    public void displayVictory(Player player, int rounds) {
        System.out.println("\n*** Congratulations! You have defeated all your enemies! ***");
        System.out.println("Remaining HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("Total Rounds: " + rounds);
    }


    public void displayDefeat(List<Enemy> enemies, int rounds) {
        long remaining = 0;
        for (Enemy e : enemies) { if (e.isAlive()) remaining++; }
        System.out.println("\n*** Defeated. Don't give up, try again! ***");
        System.out.println("Enemies remaining: " + remaining);
        System.out.println("Total Rounds Survived: " + rounds);
    }


    private int readInt(int min, int max) {
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
