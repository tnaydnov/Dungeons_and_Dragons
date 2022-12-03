import java.util.List;

public class Hunter extends Player{

    private int range;
    private int arrows_count;
    private int ticks_count;

    public Hunter(int x, int y, String name, int health_pool, int attack_points, int defense_points, int range) {
        super(x, y, name, health_pool, attack_points, defense_points);
        this.range = range;
        this.arrows_count = 10;
        this.ticks_count = 0;
    }
    @Override
    public void castAbility() {
        if (arrows_count > 0) {
            arrows_count = arrows_count - 1;
            List<Enemy> enemies_inRange = enemyList_byRange(range);
            if (enemies_inRange.size() > 0){
                Enemy e = enemies_inRange.get(0);
                double minRange = range(e);
                for (Enemy enemy: enemies_inRange) {
                    if(range(enemy) < minRange) {
                        e = enemy;
                        minRange = range(enemy);
                    }
                }
                manager.sendMessage(getName() + " shot " + e.getName());
                int defense = e.random_Defense();
                manager.sendMessage(getName() + " hit " + e.getName() + " for " + (getAttack_points() - defense) + " ability damage.");
                e.lose_health(getAttack_points() - defense);
            }
        }
        else {
            manager.sendMessage(getName() + " tried to shoot, but there were not enough arrows.");
        }

    }

    @Override
    public void on_GameTick() {
        if (ticks_count == 10) {
            arrows_count = arrows_count + getLevel();
            ticks_count = 0;
        }
        else {
            ticks_count = ticks_count + 1;
        }
    }

    @Override
    public void interact(Unit unit) {
        unit.interact(this);
    }

    public void levelUp() {
        super.levelUp();
        arrows_count = arrows_count + (10 * getLevel());
        setAttack_points(getAttack_points() + (2 * getLevel()));
        setDefense_points(getDefense_points() + getLevel());
    }

    public String description(){
        return super.description() + " Arrows: " + arrows_count;
    }
}
