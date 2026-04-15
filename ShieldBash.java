import java.util.List;


public class ShieldBash implements SpecialSkill { // Author: R.V.
    public void activate(Player user, List<Combatant> targets) {
        Combatant target = targets.get(0);
        int damage = Math.max(0, user.getAttack() - target.getDefense());
        target.takeDamage(damage);
        target.addStatusEffect(new StunEffect());
        System.out.println(user.getName() + " uses Shield Bash on " + target.getName() + " for " + damage + " damage! " + target.getName() + " is stunned!");
    }


    public String getName() { return "Shield Bash"; }
}
