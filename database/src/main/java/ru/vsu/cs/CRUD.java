package ru.vsu.cs;

import ru.vsu.cs.dao.*;
import ru.vsu.cs.dao.items.ArmorDAO;
import ru.vsu.cs.dao.items.ItemDAO;
import ru.vsu.cs.dao.items.WeaponDAO;
import ru.vsu.cs.items.Armor;
import ru.vsu.cs.items.Weapon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CRUD {
    private BalanceDAO balanceDAO;
    private EquipmentDAO equipmentDAO;
    private InventoryDAO inventoryDAO;
    private PlayerDAO playerDAO;
    private SkillDAO skillDAO;
    private SkillTreeDAO skillTreeDAO;
    private ItemDAO itemDAO;
    private ArmorDAO armorDAO;
    private WeaponDAO weaponDAO;

    public CRUD (Connection connection){
        this.armorDAO = new ArmorDAO(connection);
        this.balanceDAO = new BalanceDAO(connection);
        this.equipmentDAO = new EquipmentDAO(connection);
        this.itemDAO = new ItemDAO(connection);
        this.playerDAO = new PlayerDAO(connection);
        this.inventoryDAO = new InventoryDAO(connection);
        this.skillDAO = new SkillDAO(connection);
        this.skillTreeDAO = new SkillTreeDAO(connection);
        this.weaponDAO = new WeaponDAO(connection);
    }

    public void createPlayer(String name){
        playerDAO.createPlayer(name);
    }
    public void createPlayer(Player player){
        playerDAO.createPlayer(player);
    }

    public void createWeapon(Weapon weapon){
        weaponDAO.createWeapon(weapon);
    }

    public void createArmor(Armor armor){armorDAO.createArmor(armor);}

    public void createSkill(Skill skill){skillDAO.createSkill(skill);}
    //------------------------------//

    public Player readPlayer(String name){
        return playerDAO.readPlayerByName(name);
    }

    public Armor readArmor(String name){
        return armorDAO.readArmorByName(name);
    }

    public Weapon readWeapon(String name){
        return weaponDAO.readWeaponByName(name);
    }

    public Skill readSkill(String name){
        return skillDAO.readSkillByName(name);
    }
    //------------------------------//

    public void updatePlayer(int id, String name){
        playerDAO.updatePlayer(id, name);
    }

    public void updateArmor(int id, int defense){
        armorDAO.updateArmor(id, defense);
    }

    public void updateWeapon(int id, int damage){
        weaponDAO.updateWeapon(id, damage);
    }

    public void updateSkill(int id, String name){
        skillDAO.updateSkill(id, name);
    }

    //------------------------------//

    public void deletePlayer(int id){
        playerDAO.deletePlayer(id);
    }

    public void deleteArmor(int id){
        armorDAO.deleteArmor(id);
    }

    public void deleteWeapon(int id){
        weaponDAO.deleteWeapon(id);
    }

    public void deleteSkill(int id){
        skillDAO.deleteSkill(id);
    }

    //------------------------------//

    public List<Player> getAllPlayer() throws SQLException {
        return playerDAO.getAll();
    }

    public List<Weapon> getAllWeapon() throws SQLException {
        return weaponDAO.getAll();
    }

    public List<Armor> getAllArmor() throws SQLException {
        return armorDAO.getAll();
    }

    public List<Skill> getAllSkills() throws SQLException {
        return skillDAO.getAll();
    }

}
