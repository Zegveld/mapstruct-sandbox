package infinite.mapping;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mapstruct.factory.Mappers;

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
        List<Mesh> listMesh = mapper.toListEntity( listDto, new CycleAvoidingMappingContext() );

        // prints out '2' and '1'. which is correct because mesh1 has 2 columns, and mesh2 has 1 column.
        listMesh.stream().map( mesh -> mesh.getColumns().size() ).forEach( System.out::println );
        listMesh
                .stream()
                .flatMap( mesh -> mesh.getColumns().stream() )
                .map( MeshColumn::getMesh )
                .forEach( System.out::println );
    }

    @Test
    void infiniteRecursionTest() {
        Mesh mesh1 = new InfiniteMesh();
        mesh1.setId( 1l );

        MeshMapper mapper = Mappers.getMapper( MeshMapper.class );

        Assertions
                  .assertThatThrownBy( () -> mapper.toDto( mesh1, new CycleAvoidingMappingContext() ) )
                  .isInstanceOf( StackOverflowError.class );
    }

    @Test
    void fixedInfiniteRecursionTest() {
        Mesh mesh1 = new InfiniteMesh();
        mesh1.setId( 1l );

        MeshMapper mapper = Mappers.getMapper( MeshMapper.class );

        Assertions
                  .assertThatNoException()
                  .isThrownBy( () -> mapper.toDto( mesh1, new FixedCycleAvoidingMappingContext() ) );
    }

    private class InfiniteMesh extends Mesh {
        @Override
        public List<MeshColumn> getColumns() {
            return List.of( new InfiniteMeshColumn( this.getId() ) );
        }
    }

    private class InfiniteMeshColumn extends MeshColumn {
        private static final long COLUMN_INCREASE = 1_000_000;

        public InfiniteMeshColumn(Long requestedMeshId) {
            this.setMesh( new InfiniteMesh() );
            this.getMesh().setId( requestedMeshId );
            this.setId( requestedMeshId + COLUMN_INCREASE );
        }

    }
}
