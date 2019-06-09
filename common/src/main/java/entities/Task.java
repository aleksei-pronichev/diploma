package entities;
/* Сущность Настройки Монитора в БД
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import Enums.TaskType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import packets.PacketTask;
import Enums.TrafficType;

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

    @Column(name = "master")
    private String master;

    @Column(name = "traffic")
    @Enumerated(EnumType.ORDINAL)
    private TrafficType traffic;

    @Column(name = "task")
    @Enumerated(EnumType.ORDINAL)
    private TaskType task;

    public Task(String id, String address, String master, TrafficType traffic, TaskType task) {
        this.id = id;
        this.address = address;
        this.master = master;
        this.traffic = traffic;
        this.task = task;
    }

    public Task(String id, String master, TrafficType traffic, TaskType task) {
        this.id = id;
        this.master = master;
        this.traffic = traffic;
        this.task = task;
    }

    public Task(String id, String master, TaskType task) {
        this.id = id;
        this.master = master;
        this.task = task;
    }

    public Task(String id, String address, String master, TaskType task) {
        this.id = id;
        this.address = address;
        this.master = master;
        this.task = task;
    }

    public Task(PacketTask packet, TaskType task) {
        this.id = packet.getId();
        this.address = packet.getAddress();
        this.master = packet.getMaster();
        this.traffic = packet.getTrafficType();
        this.task = task;
    }
}
