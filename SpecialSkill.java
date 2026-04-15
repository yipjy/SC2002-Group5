import java.util.List;


public interface SpecialSkill { // Author: R.V.
    void activate(Player user, List<Combatant> targets);
    String getName();
}

