package validator;

import java.util.HashMap;
import java.util.Map;

/* @author BertGoens */

public abstract class FormValidatorBase implements FormValidator {

    private Map<String, String> errors;

    public FormValidatorBase() {
        errors = new HashMap<>();
    }

    void checkItems(String key, Object value) {
    }

    void setErrors(Map errors) {
        this.errors = errors;
    }

    @Override
    public Map getErrors() {
        return errors;
    }
}
