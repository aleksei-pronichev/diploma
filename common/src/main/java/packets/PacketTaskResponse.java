package packets;
/* Пакет, ответ на зпрос установки монитора
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import Enums.PacketType;
import lombok.Getter;

@Getter
public class PacketTaskResponse extends PacketResponse {
    {
        setPacketType(PacketType.TASK_RESPONSE);
    }
    private boolean decision;
    private String why;

    public PacketTaskResponse(String id, boolean decision, String why) {
        super(id);
        this.decision = decision;
        this.why = why;
    }

    public PacketTaskResponse(Packet packet, boolean decision, String why) {
        super(packet);
        this.decision = decision;
        this.why = why;
    }

    public PacketTaskResponse(String id, boolean decision) {
        super(id);
        this.decision = decision;
    }

    public PacketTaskResponse(Packet packet, boolean decision) {
        super(packet);
        this.decision = decision;
    }
}
