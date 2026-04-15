// Updated by Yang
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class SpeedBasedTurnOrder implements TurnOrderStrategy {
    public List<Combatant> determineOrder(List<Combatant> combatants) {
        List<Combatant> order = new ArrayList<>();
        for (Combatant c : combatants) {
            if (c != null && c.isAlive()) order.add(c);
        }
        order.sort(Comparator.comparingInt(Combatant::getSpeed).reversed());
        return order;
    }
}

