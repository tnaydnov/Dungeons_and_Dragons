import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeEach
    public void setUp() {
        Manager manager = new Manager();
        Board board = new Board(30, 30);
        manager.setBoard(board);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {

                manager.initializer('.', i, j);
            }
        }
        manager.create_player('1', 5,5);
    }



    @Test
    void MonsterMove() {
        Monster MonsterTestUp = new Monster('H', 9, 5, "MonsterTest", 100, 30, 5, 5, 50);
        Monster MonsterTestDown = new Monster('H', 1, 5, "MonsterTest", 100, 30, 5, 5, 50);
        Monster MonsterTestLeft = new Monster('H', 5, 9, "MonsterTest", 100, 30, 5, 5, 50);
        Monster MonsterTestRight = new Monster('H', 5, 1, "MonsterTest", 100, 30, 5, 5, 50);
        MonsterTestUp.move();
        MonsterTestDown.move();
        MonsterTestLeft.move();
        MonsterTestRight.move();
        assertEquals(8, MonsterTestUp.getX());
        assertEquals(5, MonsterTestUp.getY());
        assertEquals(2, MonsterTestDown.getX());
        assertEquals(5, MonsterTestDown.getY());
        assertEquals(5, MonsterTestLeft.getX());
        assertEquals(8, MonsterTestLeft.getY());
        assertEquals(5, MonsterTestRight.getX());
        assertEquals(2, MonsterTestRight.getY());
    }

    @Test
    void Wall() {
        Monster MonsterTestUp = new Monster('H', 9, 5, "MonsterTest", 100, 30, 5, 5, 50);
        Wall WallTest = new Wall(8,5);
        MonsterTestUp.move();
        assertEquals(9, MonsterTestUp.getX());
        assertEquals(5, MonsterTestUp.getY());
    }

    @Test
    void battle() {
        Monster MonsterTest = new Monster('H', 7, 7, "MonsterTest", 100, 30, 1, 5, 50);
        Rogue RogueTest = new Rogue(7, 6, "RogueTest", 100, 40, 1, 12);
        RogueTest.interact(MonsterTest);
        assertTrue(MonsterTest.getCurrent_health()<100);
        MonsterTest.interact(RogueTest);
        assertTrue(RogueTest.getCurrent_health()<100);
    }

    @Test
    void Trap() {
        Trap TrapTest = new Trap('T',2,2,"TrapTest",1000,20,1,20,2,2);
        assertTrue(TrapTest.getVisible());
        TrapTest.on_GameTick();
        assertTrue(TrapTest.getVisible());
        TrapTest.on_GameTick();
        assertTrue(TrapTest.getVisible());
        TrapTest.on_GameTick();
        assertFalse(TrapTest.getVisible());
        TrapTest.on_GameTick();
        assertFalse(TrapTest.getVisible());
        TrapTest.on_GameTick();
        TrapTest.on_GameTick();
        assertTrue(TrapTest.getVisible());
        TrapTest.on_GameTick();
        TrapTest.on_GameTick();
        TrapTest.on_GameTick();
        assertFalse(TrapTest.getVisible());
        Warrior warrior = new Warrior(3, 2, "warrior1", 100, 70, 20, 5);
        warrior.moveUp();
        assertTrue(TrapTest.getCurrent_health()<1000);



    }

    @Test
    void specialAbilities() {
        Warrior warrior = new Warrior(1, 29, "warrior1", 100, 10, 20, 5);
        Mage mage = new Mage(5, 20, "mage1", 100, 40, 15, 100, 20, 20, 4, 4);
        Hunter hunter = new Hunter(15, 13, "hunter", 40, 20, 30, 12);
        Rogue rogue = new Rogue(22,2,"rogue1", 40, 30, 50, 70);
        Monster monster1 = new Monster('H', 1, 28, "monster1", 100, 30, 5, 2, 50);
        Monster monster2 = new Monster('H', 5, 19, "monster2", 100, 30, 5, 2, 50);
        Monster monster3 = new Monster('H', 22, 3, "monster3", 100, 30, 5, 2, 50);
        Monster monster4 = new Monster('H', 15, 12, "monster4", 100, 30, 5, 2, 50);
        Monster monster5 = new Monster('H', 15, 11, "monster5", 100, 30, 5, 2, 50);

        warrior.castAbility();
        assertTrue(monster1.getCurrent_health() < 100);
        mage.castAbility();
        assertTrue(monster2.getCurrent_health() < 100);
        int health = monster2.getCurrent_health();
        mage.castAbility();
        assertEquals(monster2.getCurrent_health(), health);
        rogue.castAbility();
        assertTrue(monster3.getCurrent_health() < 100);
        int health2 = monster3.getCurrent_health();
        rogue.castAbility();
        assertEquals(monster3.getCurrent_health(), health2);
        hunter.castAbility();
        assertTrue(monster4.getCurrent_health() < 100);
        assertEquals(monster5.getCurrent_health(), 100);//The hunter should hit only monster4 because he is the closest
    }
}