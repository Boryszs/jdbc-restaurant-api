package dm.api.mapper;

public interface Convert<T,U> {
    T convert (U u);
}
