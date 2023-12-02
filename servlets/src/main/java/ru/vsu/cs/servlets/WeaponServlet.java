package ru.vsu.cs.servlets;

import ru.vsu.cs.CRUD;
import ru.vsu.cs.DbConnection;
import ru.vsu.cs.items.Weapon;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

public class WeaponServlet extends HttpServlet {

    private static final Scanner scanner = new Scanner(System.in);

    private static CRUD crud;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();

        String path = req.getPathInfo();
        if(path == null){
            writer.write("Path not specified");
            return;
        }

        String[] pathParts = path.split("/");
        if(pathParts.length <= 1){
            writer.write("Invalid path");
            return;
        }

        if(pathParts[1].equals("getByName")){
            try{
                crud = new CRUD(DbConnection.connector());
                String name = req.getParameter("name");
                Weapon weapon = crud.readWeapon(name);
                writer.write(weapon.toString());
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();

        String path = req.getPathInfo();
        if(path == null){
            writer.write("Path not specified");
            return;
        }

        String[] pathParts = path.split("/");
        if(pathParts.length <= 1){
            writer.write("Invalid path");
            return;
        }

        if(pathParts[1].equals("add")){
            try{
                crud = new CRUD(DbConnection.connector());
                String name = req.getParameter("name");
                int damage = Integer.parseInt(req.getParameter("damage"));
                Weapon weapon = new Weapon(0, name, damage);
                crud.createWeapon(weapon);
                writer.write(weapon.toString());
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();

        String path = req.getPathInfo();
        if(path == null){
            writer.write("Path not specified");
            return;
        }

        String[] pathParts = path.split("/");
        if(pathParts.length <= 1){
            writer.write("Invalid path");
            return;
        }

        if(pathParts[1].equals("update")){
            try{
                crud = new CRUD(DbConnection.connector());
                int id = Integer.parseInt(req.getParameter("id"));
                int damage = Integer.parseInt(req.getParameter("damage"));
                crud.updateWeapon(id, damage);
                writer.write("Update successful");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();

        String path = req.getPathInfo();
        if(path == null){
            writer.write("Path not specified");
            return;
        }

        String[] pathParts = path.split("/");
        if(pathParts.length <= 1){
            writer.write("Invalid path");
            return;
        }

        if(pathParts[1].equals("delete")){
            try{
                crud = new CRUD(DbConnection.connector());
                int id = Integer.parseInt(req.getParameter("id"));
                System.out.println(id);
                crud.deleteWeapon(id);
                writer.write("Delete successful");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
