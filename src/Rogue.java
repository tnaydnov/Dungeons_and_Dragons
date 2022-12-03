import java.util.ArrayList;
import java.util.List;

public class Rogue extends Player{
    private Integer cost;
    private Integer current_energy;

    public Rogue(int x, int y, String name, Integer health_pool, Integer attack_points, Integer defense_points, Integer cost) {
        super(x, y, name, health_pool, attack_points, defense_points);
        this.cost = cost;
        current_energy = 100;
    }

    public void castAbility() {
        if (current_energy > cost) {
            manager.sendMessage(getName() + " used Fan of Knives");
            current_energy = current_energy - cost;
            List<Enemy> enemies_inRange = enemyList_byRange(2);
            for (Enemy e : enemies_inRange){
                int defense = e.random_Defense();
                manager.sendMessage(getName() + " attacked " + e.getName() + " for " + (getAttack_points() - defense) + " ability damage.");
                e.lose_health(getAttack_points() - defense);
            }
        }
        else {
            manager.sendMessage(getName() + " tried to cast Fan of Knives, but there was not enough energy: " + current_energy + '/' + cost);
        }
    }

    @Override
    public void on_GameTick() {
        current_energy = Math.min(current_energy + 10, 100);
    }

    public void levelUp() {
        super.levelUp();
        current_energy = 100;
        setAttack_points(getAttack_points() + (3 * getLevel()));
    }

    public void interact(Unit unit) {
        unit.interact(this);
    }

    public String description() {
        return super.description() + ", Energy: " + current_energy + "/100";
    }
}
