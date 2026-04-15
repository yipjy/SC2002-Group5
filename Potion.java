import java.util.List;


public class Potion extends Item { // Author: R.V.
    private static final int HEAL_AMOUNT = 100;


    public Potion() { super("Potion"); }


    // implementing use from Item
    public void use(Player user, List<Combatant> targets) {
        user.heal(HEAL_AMOUNT);
        System.out.println(user.getName() + " healed for " + HEAL_AMOUNT + " HP!");
    }
}



