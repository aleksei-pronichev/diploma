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
    private TrafficType trafficType;
    private boolean start;
    private String key;

    public PacketTask(String address, TrafficType trafficType, boolean start, String key) {
        this.address = address;
        this.trafficType = trafficType;
        this.start = start;
        this.key = key;
    }

    public PacketTask(boolean start, String key) {
        this.trafficType = TrafficType.ALL;
        this.start = start;
        this.key = key;
    }

    public PacketTask(String address, boolean start, String key) {
        this.address = address;
        this.trafficType = TrafficType.ALL;
        this.start = start;
        this.key = key;
    }

    public PacketTask(TrafficType trafficType, boolean start, String key) {
        this.trafficType = trafficType;
        this.start = start;
        this.key = key;
    }
}
