package validator;

import java.util.List;

public interface ObjectValidator<T extends Object> {

    public T validate(List items);
}
