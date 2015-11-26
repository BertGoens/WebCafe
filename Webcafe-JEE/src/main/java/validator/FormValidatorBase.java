package validator;

import java.util.*;

/* @author BertGoens */
public abstract class FormValidatorBase implements FormValidator {

    private Map<String, String> errors;

    public FormValidatorBase() {
        errors = new HashMap<>();
    }

    @Override
    public final boolean validate(Map parameterMap) {
        setErrors(new HashMap<>());
        Set s = parameterMap.entrySet();
        Iterator entries = s.iterator();

        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            String key = String.valueOf(thisEntry.getKey());
            Object value = thisEntry.getValue();
            checkItems(key, value);
        }

        return getErrors().isEmpty();
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
