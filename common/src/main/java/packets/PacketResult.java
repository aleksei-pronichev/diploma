package packets;

/* Запрос результатов анализа
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import Enums.PacketType;
import lombok.Getter;

@Getter
public class PacketResult extends Packet {
    {
        setPacketType(PacketType.RESULT);
    }
    private String monitor;

    public PacketResult(String monitor) {
        this.monitor = monitor;
    }
}
