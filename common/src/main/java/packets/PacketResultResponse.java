package packets;
/* Ответ на запрос о результататх анализов
 *
 * @author Aleksei Pronichev
 * 09.06.2019
 */

import Enums.PacketType;
import entities.Result;
import lombok.Getter;

@Getter
public class PacketResultResponse extends PacketResponse {
    {
        setPacketType(PacketType.RESULT_RESPONSE);
    }
    private Result[] results;

    public PacketResultResponse(String respID, Result[] results) {
        super(respID);
        this.results = results;
    }

    public PacketResultResponse(Packet packet, Result[] results) {
        super(packet);
        this.results = results;
    }
}
