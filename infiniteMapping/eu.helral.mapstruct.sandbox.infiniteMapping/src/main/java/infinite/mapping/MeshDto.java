package infinite.mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class MeshDto {

    private Long id;

    private String name;

    private int comSize;

    @JsonIgnore
    private List<MeshColumnDto> columns;

    @JsonIgnore
    private List<MeshLineDto> lines;

    public List<MeshColumnDto> getColumns() {
        return columns;
    }

    public List<MeshLineDto> getLines() {
        return lines;
    }

    public void setColumns(List<MeshColumnDto> columns) {
        this.columns = columns;
    }

    public void setLines(List<MeshLineDto> lines) {
        this.lines = lines;
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
}