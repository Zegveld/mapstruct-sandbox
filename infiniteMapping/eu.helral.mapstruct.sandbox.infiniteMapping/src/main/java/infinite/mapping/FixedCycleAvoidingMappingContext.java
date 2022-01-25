package infinite.mapping;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

public class FixedCycleAvoidingMappingContext extends CycleAvoidingMappingContext {

    private Map<Key, Object> knownInstances = new HashMap<Key, Object>();

    @Override
    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {

        T t = (T) knownInstances.get( toKey( source ) );
        return t;
    }

    @Override
    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put( toKey( source ), target );
    }

    Key toKey(Object source) {
        if ( source instanceof AbstractEntity ) {
            Serializable id = ( (AbstractEntity<?>) source ).getId();
            return new Key( source, id );
        }
        return new Key( source, null );
    }

    private class Key {
        private Object source;
        private Serializable id;

        public Key(Object source, Serializable id) {
            this.source = source;
            this.id = id;
        }

        @Override
        public int hashCode() {
            return Objects.hash( source, id );
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            if (id != null) {
                return Objects.equals( id, ( (Key) obj ).id )
                    && Objects.equals( source.getClass(), ( (Key) obj ).source.getClass() );
            }else {
                if ( ( (Key) obj ).id != null ) {
                    return false;
                }
                else {
                    return Objects.equals( source.getClass(), ( (Key) obj ).source.getClass() );
                }
            }
        }
    }
}