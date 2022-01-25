package infinite.mapping;

import javax.persistence.JoinColumn;

public class MeshLine extends AbstractEntity<Long> {

    private int number;

    private Mesh mesh;

    @JoinColumn( name = "mesh_id" )
    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}