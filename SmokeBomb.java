import java.util.List;


public class SmokeBomb extends Item { // Author: R.V.
    public SmokeBomb() { super("Smoke Bomb"); }


    // implementing use from Item
    public void use(Player user, List<Combatant> targets) {
        user.addStatusEffect(new SmokeBombEffect());
        System.out.println(user.getName() + " threw a Smoke Bomb! Enemy attacks deal 0 damage this turn and next!");
    }
}
