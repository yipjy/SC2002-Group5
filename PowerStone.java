import java.util.List;


public class PowerStone extends Item { // Author: R.V.
    public PowerStone() { super("Power Stone"); }


    // implementing use from Item -- triggers special skill without affecting cooldown
    public void use(Player user, List<Combatant> targets) {
        user.getSpecialSkill().activate(user, targets);
        System.out.println("Power Stone triggers " + user.getName() + "'s special skill for free!");
    }
}

