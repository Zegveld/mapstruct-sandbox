package remove.recursion;

import java.util.List;
import org.junit.jupiter.api.Test;

import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class MeshMapperTest {

    @Test
    void normalRecursionTest() {
        MeshMapper mapper = Mappers.getMapper( MeshMapper.class );

        Mesh mesh1 = new Mesh();
        MeshColumn meshColumn1_1 = new MeshColumn();
        meshColumn1_1.setMesh( mesh1 );
        Mesh mesh2 = new Mesh();
        MeshColumn meshColumn1_2 = new MeshColumn();
        meshColumn1_2.setMesh( mesh2 );
        mesh1.setColumns( List.of( meshColumn1_1, meshColumn1_2 ) );
        MeshColumn meshColumn2 = new MeshColumn();
        meshColumn2.setMesh( mesh1 );
        mesh2.setColumns( List.of( meshColumn2 ) );
        mesh1.setId( 1l );
        mesh2.setId( 2l );

        List<MeshDto> listDto = mapper.toListDto( List.of( mesh1, mesh2 ), new CycleAvoidingMappingContext() );

        MeshDto meshDto = listDto.get( 0 );
        MeshDto otherMeshDto = meshDto.getColumns().get( 0 ).getMesh();

        assertThat( meshDto ).isNotSameAs( otherMeshDto );
        assertThat( meshDto.getId() ).isEqualTo( otherMeshDto.getId() );
        assertThat( meshDto.getColumns() ).isNotEmpty();
        assertThat( otherMeshDto.getColumns() ).isNullOrEmpty();
    }

}
