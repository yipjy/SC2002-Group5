import java.util.List;


public interface Action { // Author: R.V.
    void execute(Combatant user, List<Combatant> targets);
    boolean canUse(Combatant user);
    String getName();
}

