package org.forumj.iptables;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Andrew on 25/02/2017.
 */
public interface Iptables extends Remote{
    void banIp(String ip) throws RemoteException, Exception;
}
