package packets;

/* Базовый класс пакета
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import Enums.PacketType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class Packet implements Serializable {

    private String id = UUID.randomUUID().toString();
    private PacketType packetType = PacketType.NONE;

}