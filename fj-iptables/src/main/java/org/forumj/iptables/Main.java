package org.forumj.iptables;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrew on 25/02/2017.
 */
public class Main implements Iptables{

    private static Object monitor = new Object();

    public void banIp(String ip)throws RemoteException, Exception {
        Runtime r = Runtime.getRuntime();
        List<String> command = new ArrayList<>();
        command.add("/sbin/iptables");
        command.add("-I");
        command.add("INPUT");
        command.add("-s");
        command.add(ip);
        command.add("-j");
        command.add("DROP");
        Process p = r.exec(command.toArray(new String[0]), null);
        p.waitFor();
        try(BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));){
            String line = "";
            while ((line = b.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args){
        try {
            Main main = new Main();
            Iptables iptables = (Iptables)UnicastRemoteObject.exportObject(main);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("ip-tables", iptables);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
