package infinite.mapping;

import javax.persistence.JoinColumn;

public class MeshLineDto extends AbstractEntity<Long> {

    private int number;

    private MeshDto mesh;

    @JoinColumn( name = "mesh_id" )
    public MeshDto getMesh() {
        return mesh;
    }

    public void setMesh(MeshDto mesh) {
        this.mesh = mesh;
    }
}