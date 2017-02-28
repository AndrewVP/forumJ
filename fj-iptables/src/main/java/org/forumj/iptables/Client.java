package org.forumj.iptables;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Andrew on 25/02/2017.
 */
public class Client {

    public static void main(String[] args){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Iptables iptables = (Iptables) registry.lookup("ip-tables");
            iptables.banIp("1.2.5.8");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
