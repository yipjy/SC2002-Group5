import java.util.List;


public interface Action {
    void execute(Combatant user, List<Combatant> targets);
    boolean canUse(Combatant user);
    String getName();
}

