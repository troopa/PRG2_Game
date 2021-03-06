package Network;
import Enums.MessageTypeEnum;
import GameObjects.Line;
import Interfaces.ObserverInterface;
import Interfaces.OpponentInterface;
import Interfaces.SubjectInterface;
import java.io.IOException;
import Logger.Logger;

/**
 *
 * @author Daniel
 */
public class NetworkController implements OpponentInterface,SubjectInterface{
    NetworkConnection network = null;
    ObserverInterface observer = null;
    
    public NetworkController(int port, String hostname) throws IOException{
        network = new Client(port, hostname);
    }
    
    public NetworkController(int port) throws IOException{
        network = new Server(port);
    }
    
    @Override
    public void sendLine(Line selectedLine) {
        network.sendDataStream(selectedLine);
    }

    @Override
    public void registerObserver(ObserverInterface observer) {
        this.observer = observer;
        network.setObserver(observer);
    }

    @Override
    public void setOpponentTurn() {
        Logger.logToConsole(MessageTypeEnum.Information, "Not used method - set OpponentTurn()");
    }

}
