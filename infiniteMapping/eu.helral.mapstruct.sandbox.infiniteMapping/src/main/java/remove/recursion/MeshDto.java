package remove.recursion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class MeshDto implements BaseDto<MeshDto> {

    private Long id;

    private String name;

    private int comSize;

    @JsonIgnore
    private List<MeshColumnDto> columns;

    public List<MeshColumnDto> getColumns() {
        return columns;
    }

    public void setColumns(List<MeshColumnDto> columns) {
        this.columns = columns;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return (int) Math.random();
    }

    @Override
    public MeshDto createEndpointObject() {
        MeshDto meshDto = new MeshDto();
        meshDto.setId( id );
        return meshDto;
    }
}