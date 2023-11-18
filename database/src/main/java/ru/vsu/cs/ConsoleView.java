package ru.vsu.cs;

import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Weapon;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleView {

    private static final Scanner scanner = new Scanner(System.in);

    private static CRUD crud;

    public static void console() throws SQLException {

        while (true){
            System.out.println("Выберите действие: \n1. Добавить \n2. Найти \n3. Обновить \n4. Удалить \n5. Exit");
            int code = scanner.nextInt();
            switch (code){
                case 1 -> create();
                case 2 -> read();
                case 3 -> update();
                case 4 -> delete();
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void create(){
        System.out.println("Выберите что добавить: \n1. Броню \n2. Оружие \n3. Игрока \n4. Exit");
        int code = scanner.nextInt();
        switch (code){
            case 1 -> createArmor();
            case 2 -> createWeapon();
            case 3 -> createPlayer();
            case 4 ->  {
            }
        }
    }

    private static void createArmor() {
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите название: ");
            String name = scanner.next();
            System.out.println("Введите значение брони: ");
            int defense = scanner.nextInt();
            Armor armor = new Armor(0, name, defense);
            crud.createArmor(armor);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void createWeapon(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите название: ");
            String name = scanner.next();
            System.out.println("Введите значение урона: ");
            int damage = scanner.nextInt();
            Weapon weapon = new Weapon(0, name, damage);
            crud.createWeapon(weapon);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void createPlayer(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите ник: ");
            String name = scanner.next();
            crud.createPlayer(name);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void read(){
        System.out.println("Выберите что найти: \n1. Броню \n2. Оружие \n3. Игрока \n4. Exit");
        int code = scanner.nextInt();
        switch (code){
            case 1 -> readArmor();
            case 2 -> readWeapon();
            case 3 -> readPlayer();
            case 4 ->  {
            }
        }
    }

    private static void readPlayer(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите ник: ");
            String name = scanner.next();
            Player player = crud.readPlayer(name);
            System.out.println(player);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void readArmor(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите название: ");
            String name = scanner.next();
            Armor armor = crud.readArmor(name);
            System.out.println(armor);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void readWeapon(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите название: ");
            String name = scanner.next();
            Weapon weapon = crud.readWeapon(name);
            System.out.println(weapon);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void update(){
        System.out.println("Выберите что обновить: \n1. Броню \n2. Оружие \n3. Игрока \n4. Exit");
        int code = scanner.nextInt();
        switch (code){
            case 1 -> updateArmor();
            case 2 -> updateWeapon();
            case 3 -> updatePlayer();
            case 4 ->  {
            }
        }
    }

    private static void updateArmor(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите id: ");
            int id = scanner.nextInt();
            System.out.println("Введите новое значение защиты: ");
            int defense = scanner.nextInt();
            crud.updateArmor(id, defense);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void updateWeapon(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите id: ");
            int id = scanner.nextInt();
            System.out.println("Введите новое значение урона: ");
            int damage = scanner.nextInt();
            crud.updateWeapon(id, damage);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void updatePlayer(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите id: ");
            int id = scanner.nextInt();
            System.out.println("Введите новый ник: ");
            String name = scanner.nextLine();
            crud.updatePlayer(id, name);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    private static void delete(){
        System.out.println("Выберите что удалить: \n1. Броню \n2. Оружие \n3. Игрока \n4. Exit");
        int code = scanner.nextInt();
        switch (code){
            case 1 -> deleteArmor();
            case 2 -> deleteWeapon();
            case 3 -> deletePlayer();
            case 4 ->  {
            }
        }
    }

    private static void deleteArmor(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите id: ");
            int id = scanner.nextInt();
            crud.deleteArmor(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void deleteWeapon(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите id: ");
            int id = scanner.nextInt();
            crud.deleteWeapon(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void deletePlayer(){
        try{
            crud = new CRUD(DbConnection.connector());
            System.out.println("Введите id: ");
            int id = scanner.nextInt();
            crud.deletePlayer(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
