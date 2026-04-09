import java.util.List;


public class BasicAttack implements Action {


    // implementing execute from Action
    public void execute(Combatant user, List<Combatant> targets) {
        Combatant target = targets.get(0);
        if (target.isInvulnerable()) {
            System.out.println(target.getName() + " is invulnerable! No damage dealt.");
            return;
        }
        int damage = Math.max(0, user.getAttack() - target.getDefense());
        target.takeDamage(damage);
        System.out.println(user.getName() + " attacks " + target.getName() + " for " + damage + " damage!");
    }


    // implementing canUse from Action
    public boolean canUse(Combatant user) { return true; }


    // implementing getName from Action
    public String getName() { return "Basic Attack"; }
}

