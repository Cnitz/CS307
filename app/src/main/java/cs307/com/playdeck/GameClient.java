package cs307.com.playdeck;

/**
 * Created by msi on 3/5/2015.
 */
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;

public class GameClient extends Listener {
    boolean isHost;
    static Client c;
    static int udpPort = 27960;
    static int tcpPort = 27960;

public GameClient(boolean isHost) {
    this.c = new Client();
    c.getKryo().register(PacketMessage.class);
    this.isHost = isHost;
}
public Object startListening(){
    c.start();
    c.addListener(this);
    System.out.println("Now Listening...");
    return null;
    }

    public void received(Connection c, Object p){
        //Is the received packet the same class as PacketMessage.class?
        if(p instanceof PacketMessage){
            //Cast it, so we can access the message within.
            PacketMessage packet = (PacketMessage) p;
            System.out.println("received a message from the host: "+packet.message);

        }
    }
    public Client getClient(){
        return c;
    }
}


