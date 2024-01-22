public class Player {
    private int health;
    private String name;
    private Sword sword;
    private int upgrade = 0;

    public Player (String name) {
        this.name = name;
        health = 100;
        sword = new Sword();
    }

    public void setName (String newName) {
        name = newName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth (int newHealth) {
        health = newHealth;
    }

    public void Upgrade (){
        upgrade++;
    }
    public int getDamage() {
        return sword.getDamage() + upgrade * 10;
    }
}
