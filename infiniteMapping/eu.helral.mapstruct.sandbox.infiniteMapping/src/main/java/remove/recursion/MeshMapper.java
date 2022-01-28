package remove.recursion;

import java.util.Map;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeshMapper extends CommonMapperForCycleAvoiding <MeshDto, Mesh> {
    default Integer from(String string, @Context Map<Character, Integer> charToIntMap) {
        return Integer.valueOf( string );
    }

    @Override
    // Enforce id being mapped first before everything else.
    @Mapping( target = "id", source = "e.id" )
    MeshDto toDto(Mesh e, @Context CycleAvoidingMappingContext context);
}
