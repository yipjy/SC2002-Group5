import java.util.List;


public class Defend implements Action { // Author: R.V.


    // implementing execute from Action
    public void execute(Combatant user, List<Combatant> targets) {
        user.addStatusEffect(new DefendEffect());
        System.out.println(user.getName() + " takes a defensive stance!");
    }


    // implementing canUse from Action
    public boolean canUse(Combatant user) { return true; }


    // implementing getName from Action
    public String getName() { return "Defend"; }
}
