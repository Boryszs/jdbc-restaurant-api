package dm.api.mapper;

import java.util.List;

public interface ConvertList<T, U> {
    List<T> toListDto(List<U> u);
    T toDto(U u);
}
