package remove.recursion;

public class MeshColumnDto implements BaseDto<MeshColumnDto> {

    private Long id;

    private MeshDto mesh;

    public MeshDto getMesh() {
        return mesh;
    }

    public void setMesh(MeshDto mesh) {
        this.mesh = mesh;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public MeshColumnDto createEndpointObject() {
        MeshColumnDto meshColumnDto = new MeshColumnDto();
        meshColumnDto.setId( id );
        return meshColumnDto;
    }
}