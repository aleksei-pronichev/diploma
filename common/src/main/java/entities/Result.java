package entities;
/* Сущность Настройки Монитора в БД
 *
 * @author Aleksei Pronichev
 * @version 09.06.2019
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "results")
public class Result {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "monitor")
    private String monitor;

    @Column(name = "attack")
    private boolean attack;

    @Column(name = "traffic")
    private Date date;

    public Result(String monitor, boolean attack, Date date) {
        this.monitor = monitor;
        this.attack = attack;
        this.date = date;
    }

    public Result(String monitor, boolean attack) {
        this.monitor = monitor;
        this.attack = attack;
        this.date = new Date();
    }
}
