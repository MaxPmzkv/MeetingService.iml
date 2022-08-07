

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.System.out;

public class RedisStorage {


    private RedissonClient redisson;


    private RKeys rKeys;


    private RDeque<Integer> registeredUsers;

    private final static String KEY = "REGISTERED_USERS";

    private double getTs() {
        return new Date().getTime() / 1000;
    }


    public void listKeys() {
        Iterable<String> keys = rKeys.getKeys();
        for(String key: keys) {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));

        }
    }

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        try {
            redisson = Redisson.create(config);

        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }

        rKeys = redisson.getKeys();

        registeredUsers = redisson.getDeque(KEY);

        rKeys.delete(KEY);
        registeredUsers.clear();

    }

    public void shutdown() {
        redisson.shutdown();
    }





    public void registerUsers(int amountOfUsers)
    {
        for(int i = 1; i <= amountOfUsers; i++ ){
            registeredUsers.add(i); }
    }
    public int peekFirstUser()
    {
        return  registeredUsers.peekFirst();
    }
    public void addLastUser(int id)
    {
        registeredUsers.addLast(id);
    }

    public int removeFirstUser()
    {
         return registeredUsers.removeFirst();


    }
    public void pushUser(int id)
    {
        registeredUsers.push(id);
    }

    public int chooseRandom(int bound)
    {

        Object[] temp = registeredUsers.toArray();
        int rnd = new Random().nextInt(bound);

        return (int) temp[rnd];

    }
    public void deleteUser(int id)
    {
        registeredUsers.remove(id);
    }

    }


