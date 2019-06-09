package entities;
/* Сущность Настройки Монитора в БД
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import Enums.TaskType;
import Enums.TrafficType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import packets.PacketTask;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "address")
    private String address;

    @Column(name = "device")
    private String device;

    @Column(name = "traffic")
    @Enumerated(EnumType.ORDINAL)
    private TrafficType traffic;

    @Column(name = "task")
    @Enumerated(EnumType.ORDINAL)
    private TaskType task;

    public Task(String id, String address, String device, TrafficType traffic, TaskType task) {
        this.id = id;
        this.address = address;
        this.device = device;
        this.traffic = traffic;
        this.task = task;
    }

    public Task(String id, String device, TrafficType traffic, TaskType task) {
        this.id = id;
        this.device = device;
        this.traffic = traffic;
        this.task = task;
    }

    public Task(String id, String device, TaskType task) {
        this.id = id;
        this.device = device;
        this.task = task;
    }

    public Task(String id, String address, String device, TaskType task) {
        this.id = id;
        this.address = address;
        this.device = device;
        this.task = task;
    }

    public Task(PacketTask packet, TaskType task) {
        this.id = packet.getId();
        this.address = packet.getAddress();
        this.device = "localhost";
        this.traffic = packet.getTrafficType();
        this.task = task;
    }
}
