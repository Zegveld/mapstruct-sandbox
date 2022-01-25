package infinite.mapping;

import java.util.List;

import org.mapstruct.Context;

public interface CommonMapperForCycleAvoiding<D, E> {

    D toDto(E e, @Context CycleAvoidingMappingContext context);

    E toEntity(D d, @Context CycleAvoidingMappingContext context);

    List<D> toListDto(List<E> entityList, @Context CycleAvoidingMappingContext context);

    List<E> toListEntity(List<D> dtoList, @Context CycleAvoidingMappingContext context);
}
