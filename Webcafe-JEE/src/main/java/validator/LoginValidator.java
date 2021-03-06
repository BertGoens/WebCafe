package validator;

/* @author BertGoens */
public final class LoginValidator extends FormValidatorBase {

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

            case "id":
                //not needed
                break;

            default:
                throw new AssertionError("unknown " + key + " " + value + ((String[]) value)[0]);
        }
    }

}
