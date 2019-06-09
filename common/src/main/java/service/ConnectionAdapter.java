package service;

/* Интерфейс для работы c удаленными подключениями
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import packets.Packet;

public interface ConnectionAdapter {

    public boolean isConnected();

    public boolean connect();

    public Packet sendPacket(Packet packet);

    public void closeConnection();
}
