package validator;

import java.util.Map;

/* @author BertGoens */
public interface FormValidator {

    public boolean validate(Map parameterMap);

    public Map getErrors();
}
