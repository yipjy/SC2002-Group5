import java.util.List;


public class ArcaneBlast implements SpecialSkill {
    public void activate(Player user, List<Combatant> targets) {
        for (Combatant target : targets) {
            if (target.isAlive()) {
                int damage = Math.max(0, user.getAttack() - target.getDefense());
                target.takeDamage(damage);
                System.out.println(user.getName() + " hits " + target.getName() + " with Arcane Blast for " + damage + " damage!");
                if (!target.isAlive()) {
                    user.addStatusEffect(new ArcaneBlastBuffEffect());
                    System.out.println(target.getName() + " defeated! Wizard gains +10 ATK!");
                }
            }
        }
    }


    public String getName() { return "Arcane Blast"; }
}

