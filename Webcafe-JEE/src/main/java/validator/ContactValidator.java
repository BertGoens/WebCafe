package validator;

/* @author BertGoens */
public class ContactValidator extends FormValidatorBase {

    @Override
    void checkItems(String key, Object value) {
        if (value == null) {
            getErrors().put(key, key + " must be filled in!");
            return;
        }

        switch (key) {
            case "name":
                String name = ((String[]) value)[0];
                if (name.length() < 3 || name.length() > 30) {
                    getErrors().put(key, "password must be filled in! (3-30 chars)");
                }
                break;

            case "email":
                String email = ((String[]) value)[0];
                if (!email.contains("@")) {
                    getErrors().put(key, "please enter full email adress (ex: john@webcafe.com");
                }
                if (email.length() < 2 || email.length() > 30) {
                    getErrors().put(key, "email must be filled in! (3-30 chars)");
                }
                break;

            case "message":
                String message = ((String[]) value)[0];
                if (message.length() < 20) {
                    getErrors().put(key, "message must be filled in! (atleast 20 chars)");
                }
                break;

            case "type":
                //not important to validate
                break;
        }

    }

}
