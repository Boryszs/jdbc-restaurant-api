package dm.api.mapper;

public interface Convert<T,U, V> {
    T convert (U u);
    T update (T t,U u);
    V toDto(T t);
}
