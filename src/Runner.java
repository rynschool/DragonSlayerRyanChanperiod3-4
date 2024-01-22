import java.util.Scanner;
public class Runner {
    Scanner myScanner = new Scanner(System.in);

    boolean gameRunning = true;
    Player player = new Player("");


    public void runGame() {
        int epic = 0;
        Rooms room = new Rooms(2);
        System.out.println("What's your name, young traveler?");
        player.setName(myScanner.nextLine());

        for (int i = 0; i <= 3; i++) {
            System.out.println("Loading..."); //Loading just for a more immersive experience
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        System.out.println();
        Dragon dragon = new Dragon(1);
        dragon.standard();
        int gold = 0;
        int maxHP = 100;
        int move = 0;
        int select;
        boolean searching = false;
        boolean dodge = false;
        boolean menu = true;
        int healthPotion = 0;
        while (menu) {
            System.out.println("--Menu--");
            System.out.println("1.New game\n2. Quit game");
            int option = myScanner.nextInt();
            if (option == 1) {
                System.out.println("Starting game...");
                try{
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            } else {
                System.out.println("Shutting down...");
                try{
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
                epic++;
            }
            menu = false;
        }
        if (epic == 0) {
            System.out.println("You are in the first room... good luck"); //It was hard to code
            System.out.println("1. ATTACK, 2.ATTEMPT A DODGE"); //no potion option as they shouldn't have a potion
            select = myScanner.nextInt();
            myScanner.nextLine();
            if (select == 1) {
                dragon.setHealth(dragon.getHealth() - player.getDamage());
                System.out.println("You did " + player.getDamage() + " damage. The dragon now has " + dragon.getHealth() + " health.");
                select = 0;
            } else {
                dodge = true;
                select = 0;
            }
            move++;
        }

        boolean dragonDead = false;
        while (gameRunning && epic == 0) {
            if (dragon.getHealth() <= 0) {
                dragonDead = true;
                int random = (int)(Math.random() * 4) + 1; //dragon drops
                if (random == 1) {
                    System.out.println("The dragon drops 10 gold!");
                    gold += 10;
                } else if (random == 2) {
                    System.out.println("The dragon drops one free sword upgrade!");
                    player.Upgrade();
                } else if (random == 3) {
                    System.out.println("Your bones heal! You can now tank more damage!");
                    maxHP += 500;
                } else {
                    System.out.println("You got nothing :(");
                }
            }
            if (player.getHealth() <= 0) {
                System.out.println("Your health is below Zero!");
                System.out.println("You failed!");
                gameRunning = false;
            } else if (dragonDead) {
                player.setHealth(maxHP);
                if (room.getRoom() == 6) {
                    gameRunning = false;
                } else {
                    System.out.println("You defeated the dragon and you have moved to the next room! You are now in room " + room.getRoom() + "!");
                    System.out.println("--Upgrades--");
                    System.out.println("1.Upgrade Damage\n2.Upgrade Health\n3.Search the next Room");
                    int decision = myScanner.nextInt();
                    if (decision == 1) {
                        if(gold >= 25) {
                            player.Upgrade();
                            gold -= 25;
                        } else {
                            System.out.println("You cannot afford this upgrade. Sorry!");
                        }
                        player.setHealth(maxHP);
                    } else if (decision == 2) {
                        if(gold >= 25) {
                            maxHP += 500;
                            gold -= 25;
                        } else {
                            System.out.println("You cannot afford this upgrade. Sorry!");
                        }
                        player.setHealth(maxHP);
                    } else {
                        int search = (int)(Math.random() * 2) + 1;
                        if (search == 1) {
                            searching = true;
                        } else {
                            searching = false;
                        }
                    }
                    dragon.setLevel(dragon.getLevel() + 1);
                    dragon.standard();
                    System.out.println("Moving on...");
                    try {
                        Thread.sleep(800);
                    } catch (Exception e) {
                        System.out.println("ERROR");
                    }
                    System.out.println();
                    if (room.getRoom() <= 4) {
                        room.addRoom();
                    } else if (room.getRoom() == 5) {
                        room.addRoom();
                        System.out.println("This is the final room... good luck.");
                    } else {
                        gameRunning = false;
                    }
                    dragonDead = false;
                }
            }else {
                if (searching == true) { //Searching the room
                    int search = (int)(Math.random() * 2) + 1;
                    if (search == 1) {
                        healthPotion++;
                    } else {
                        System.out.println("You found nothing.");
                    }
                }
                if (move % 2 == 0) {
                    System.out.println("1. ATTACK, 2.ATTEMPT A DODGE, 3. USE A POTION");
                    select = myScanner.nextInt();
                    if (select == 1) {
                        dragon.setHealth(dragon.getHealth() - player.getDamage());
                        System.out.println("You did " + player.getDamage() + " damage. The dragon now has " + dragon.getHealth() + " health.");
                        select = 0;
                    } else if (select == 2){
                        System.out.println("You will dodge on the dragon's turn");
                        dodge = true;
                        select = 0;
                        try{
                            Thread.sleep(500);
                        } catch (Exception e) {
                            System.out.println("Error");
                        }
                    } else {
                        if (healthPotion == 0) {
                            System.out.println("You don't have any health potions!");
                        } else {
                            healthPotion--;
                            player.setHealth(player.getHealth() + 100);
                        }
                    }
                    move++;
                } else {
                    dragon.newDamage();
                    System.out.println("The dragon attacks!");
                    if (dodge) {
                        int temp = (int)(Math.random() * 4) + 1;
                        if (temp >= 4) {
                            System.out.println("You successfully dodged!");
                            System.out.println();
                            dodge = false;
                        } else {
                            player.setHealth(player.getHealth()- dragon.getDamage());
                            System.out.println("The dodge failed and the dragon did " + dragon.getDamage() + " damage");
                            dodge = false;
                        }
                    } else {
                        player.setHealth(player.getHealth() - dragon.getDamage());
                        System.out.println("The dragon does " + dragon.getDamage() + " damage! You now have " + player.getHealth() + " health");
                    }
                    move = 0;
                }

            }
        }
        if (player.getHealth() <= 0) {
            System.out.println("You lose!");
            menu = true;
        } else if (epic == 1) {
            System.out.print("You quit the game :("); //differentiates between losing, quitting and winning
        } else {
            System.out.print("You win!");
            menu = true;
        }
    }
}