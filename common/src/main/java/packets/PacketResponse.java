package packets;
/* Пакет ответа на запрос
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import lombok.Getter;

@Getter
public class PacketResponse extends Packet {

    private String respID;

    public PacketResponse(String respID) {
        this.respID = respID;
    }

    public PacketResponse(Packet packet) {
        this.respID = packet.getId();
    }
}
