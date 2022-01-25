package infinite.mapping;

import java.beans.Transient;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.data.domain.Persistable;

public class AbstractEntity<ID extends Serializable> implements Persistable<ID> {

    private ID id;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Override
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }


}