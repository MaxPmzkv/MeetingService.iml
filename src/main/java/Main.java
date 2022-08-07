

import java.util.Random;

public class Main
{
    private static final int USERS_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {

        RedisStorage storage = new RedisStorage();
        storage.init();
        storage.registerUsers(USERS_COUNT);

        while (true)
        {


            for(int i = 0; i < USERS_COUNT; i++)
            {
                System.out.println("Show user " + storage.peekFirstUser());
                storage.addLastUser(storage.removeFirstUser());
                Thread.sleep(500);


                if(Math.random() < 0.1)
                {

                    int rnd = storage.chooseRandom(USERS_COUNT);
                    storage.deleteUser(rnd);
                    storage.pushUser(rnd);
                    System.out.println("User " + rnd + " PAYED");
//                    System.out.println("Show user " + rnd);


                    Thread.sleep(1000);

                }


            }



        }

    }

}
