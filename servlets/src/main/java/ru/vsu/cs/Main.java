package ru.vsu.cs;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import ru.vsu.cs.servlets.ArmorServlet;
import ru.vsu.cs.servlets.PlayerServlet;
import ru.vsu.cs.servlets.WeaponServlet;


import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);

        Wrapper weaponServlet = Tomcat.addServlet(ctx, "weaponServlet", new WeaponServlet());
        weaponServlet.setLoadOnStartup(1);
        weaponServlet.addMapping("/weapon/*");

        Wrapper armorServlet = Tomcat.addServlet(ctx, "armorServlet", new ArmorServlet());
        armorServlet.setLoadOnStartup(1);
        armorServlet.addMapping("/armor/*");

        Wrapper playerServlet = Tomcat.addServlet(ctx, "playerServlet", new PlayerServlet());
        playerServlet.setLoadOnStartup(1);
        playerServlet.addMapping("/player/*");

        tomcat.start();
    }
}