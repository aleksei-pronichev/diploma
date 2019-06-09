package packets;
/* Содержащий настройки для сканирования
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import Enums.PacketType;
import Enums.TrafficType;
import lombok.Getter;

@Getter
public class PacketTask extends Packet {
    {
        setPacketType(PacketType.TASK);
    }
    private String address;
    private String master;
    private TrafficType trafficType;
    private boolean start;

    public PacketTask(String address, String master, TrafficType trafficType, boolean start) {
        this.address = address;
        this.master = master;
        this.trafficType = trafficType;
        this.start = start;
    }

    public PacketTask(String master, boolean start) {
        this.master = master;
        this.trafficType = TrafficType.ALL;
        this.start = start;
    }

    public PacketTask(String address, String master, boolean start) {
        this.address = address;
        this.master = master;
        this.trafficType = TrafficType.ALL;
        this.start = start;
    }

    public PacketTask(String master, TrafficType trafficType, boolean start) {
        this.master = master;
        this.trafficType = trafficType;
        this.start = start;
    }
}
