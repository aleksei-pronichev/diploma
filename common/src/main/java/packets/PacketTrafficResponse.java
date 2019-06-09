package packets;

/* Ответ на запрос трафика по монитору
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import Enums.PacketType;
import entities.Traffic;
import lombok.Getter;

@Getter
public class PacketTrafficResponse extends PacketResponse {
    {
        setPacketType(PacketType.TRAFFIC_RESPONSE);
    }
    private Traffic[] traffic;

    public PacketTrafficResponse(String respID, Traffic[] traffic) {
        super(respID);
        this.traffic = traffic;
    }

    public PacketTrafficResponse(Packet packet, Traffic[] traffic) {
        super(packet);
        this.traffic = traffic;
    }
}
