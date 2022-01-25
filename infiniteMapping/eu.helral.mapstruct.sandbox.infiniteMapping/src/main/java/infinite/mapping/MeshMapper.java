package infinite.mapping;

import java.util.Map;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeshMapper extends CommonMapperForCycleAvoiding <MeshDto, Mesh> {
    default Integer from(String string, @Context Map<Character, Integer> charToIntMap) {
        return Integer.valueOf( string );
    }

    Dummy from(DummyOther string, @Context Map<Character, Integer> charToIntMap);
}

class Dummy {
    Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

class DummyOther {
    String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}