package cs307.com.playdeck;

/**
 * Created by msi on 3/5/2015.
 */

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Client;


/**
 * Created by msi on 3/5/2015.
 */


public class GameServer extends Listener {
    private Server server; //Game server. Could be this client if host
    //Ports to listen on
    static int udpPort = 27960;
    static int tcpPort = 27960;

    public GameServer(boolean isHost, GameClient c) throws Exception {

            System.out.println("Creating Server...\n");
            this.server = new Server();
            this.server.getKryo().register(PacketMessage.class);
            System.out.printf("Just before port binding\n");
            this.server.bind(tcpPort,udpPort);
            System.out.printf("Just before server start");
            this.server.start();
            System.out.println("Server Operational!\n");
            this.server.addListener(c);

    }

    public void recieved(Connection c, Object p){

    }

    public void connected(Connection c){
        System.out.println("Recieved Connection from "+c.getRemoteAddressTCP().getHostString());
        PacketMessage p = new PacketMessage();
        p.message = "Hello World!";
        c.sendTCP(p);
    }
    public void disconnected(Connection c){
        System.out.println("A client has disconnected!");
    }
}


