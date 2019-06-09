package packets;

/* Ошибка при запросах
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import Enums.PacketType;
import lombok.Getter;

@Getter
public class PacketError extends Packet {
    {
        setPacketType(PacketType.NO_VALID);
    }
    private String error;

    public PacketError(String error) {
        this.error = error;
    }
}
