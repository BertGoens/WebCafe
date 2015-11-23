package validator;

import java.util.Map.Entry;
import java.util.*;

/* @author BertGoens */
public final class LoginValidator extends FormValidatorBase {

    @Override
    public boolean validate(Map parameterMap) {
        setErrors(new HashMap<>());
        Set s = parameterMap.entrySet();
        Iterator entries = s.iterator();

        while (entries.hasNext()) {
            Entry thisEntry = (Entry) entries.next();
            String key = String.valueOf(thisEntry.getKey());
            Object value = thisEntry.getValue();
            checkItems(key, value);
        }

        return getErrors().isEmpty();
    }

    @Override
    void checkItems(String key, Object value) {
        if (value == null) {
            getErrors().put(key, key + " must be filled in!");
            return;
        }

        switch (key) {
            case "pwd":
                String password = ((String[]) value)[0];
                if (password.length() < 3 || password.length() > 30) {
                        getErrors().put("pwd", "password must be filled in! (3-30 chars)");
                }
                break;

            case "email":
                String email = ((String[]) value)[0];
                if (!email.contains("@")) {
                        getErrors().put("email", "please enter full email adress (ex: john@webcafe.com");
                    }
                if (email.length() < 2 || email.length() > 30) {
                        getErrors().put("email", "email must be filled in! (3-30 chars)");
                }
                break;

            default:
                throw new AssertionError();
        }
    }

}
