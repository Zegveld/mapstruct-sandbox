package remove.recursion;

import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

public class CycleAvoidingMappingContext {

    private Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();

    @BeforeMapping
    public <T extends BaseDto<T>> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        T t = (T) knownInstances.get( source );
        if ( t != null ) {
            return t.createEndpointObject();
        }
        return t;
    }

    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put( source, target );
    }
}