package infinite.mapping;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.OneToMany;

public class Mesh extends AbstractEntity<Long> {

    private String name;

    private int comSize;

    private List<MeshColumn> columns;
    private List<MeshLine> lines;

    @Column( name = "com_size" )
    public int getComSize() {
        return comSize;
    }

    @OneToMany( mappedBy = "mesh" )
    public List<MeshColumn> getColumns() {
        return columns;
    }

    @OneToMany( mappedBy = "mesh" )
    public List<MeshLine> getLines() {
        return lines;
    }

    public void setColumns(List<MeshColumn> columns) {
        this.columns = columns;
    }

    public void setLines(List<MeshLine> lines) {
        this.lines = lines;
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
    public String toString() {
        return getId().toString();
    }
}