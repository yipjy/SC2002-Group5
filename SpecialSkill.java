import java.util.List;


public interface SpecialSkill {
    void activate(Player user, List<Combatant> targets);
    String getName();
}

