package entities;
/* Количество трафика
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
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "traffic")
public class Traffic {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "count")
    private long count;

    @Column(name = "date")
    private Date date;


    @Column(name = "monitor")
    private String monitor;

    public Traffic(long count, Date date, String monitor) {
        this.count = count;
        this.monitor = monitor;
        this.date = date;
        this.id = UUID.randomUUID().toString();
    }
}
