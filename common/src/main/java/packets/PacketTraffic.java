package packets;

/* Пакет, содержащий запрос на выдачу трафика
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import Enums.PacketType;
import lombok.Getter;

import java.util.Date;

@Getter
public class PacketTraffic extends Packet {
    {
        setPacketType(PacketType.TRAFFIC);
    }

    private Date dateStart;
    private Date dateFinish;
    private String monitor;

    public PacketTraffic(Date dateStart, Date dateFinish, String monitor) {
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.monitor = monitor;
    }

    public PacketTraffic(String monitor) {
        this.monitor = monitor;
        this.dateStart = new Date(0);
        this.dateFinish = new Date();
    }
}
