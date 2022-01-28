package remove.recursion;

public interface BaseDto<SELF extends BaseDto<SELF>> {

    SELF createEndpointObject();
}