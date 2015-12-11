package Network;

import Enums.MessageTypeEnum;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Logger.Logger;


/**
 * Network server. communicates with the client on both sides.
 *
 * @author Daniel
 */
public class Server extends NetworkConnection implements Runnable{
    ServerSocket server = null;
    
    public Server(int port) throws IOException{
        server = new ServerSocket(port);
        
        Socket socket = server.accept();
        setSocket(socket);
        start();
        //Thread t = new Thread(this);
        //t.start();
    }
    
    /**
     * starts the server accept method and threads the received client.
     * starting a new Thread 
     *
    @Override
    public void run() {
        try{
            Logger.logToConsole(MessageTypeEnum.Debug, "server start");
            Socket socket = server.accept();
            setSocket(socket);
            start();
        } catch (IOException ioe){
            //@TODO make a logger
            System.err.println("ERROR: " + ioe.getMessage());
        }
    }*/
}
