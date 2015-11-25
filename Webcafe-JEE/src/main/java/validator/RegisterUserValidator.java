package validator;

import dao.UserDao;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import model.User;
import org.apache.commons.fileupload.FileItem;

/*
 @author BertGoens
 */
public class RegisterUserValidator implements ObjectValidator<User> {

    private User returnUser;
    private HashMap<String, String> errors;

    private final String REGEX_BIRTHDATE = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    private final String REGEX_MAXLENGTH30MINLENGTH3 = "{3,30}$";

    public RegisterUserValidator() {
        errors = new HashMap<>();
    }

    @Override
    public User validate(List items) {
        errors = new HashMap<>();
        User resultUser = new User();
        FileItem imagePath = null;
        Iterator iterator = items.iterator();
        while (iterator.hasNext()) {
            FileItem item = (FileItem) iterator.next();

            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                saveItem(fieldName, fieldValue);
            } else {
                imagePath = item;
            }
        }

        if (errors.isEmpty()) {
            //Create user & do sync to db
            Iterator iteratorNoError = items.iterator();
            while (iteratorNoError.hasNext()) {
                FileItem item = (FileItem) iteratorNoError.next();

                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    createUser(fieldName, fieldValue, resultUser);
                } else {
                    imagePath = item;
                }
            }
            UserDao ud = new UserDao();
            ud.persist(resultUser);
            try {
                processUploadedFile(imagePath);
            } catch (Exception ex) {
                errors.put("image", "problem uploading image");
            }
            return resultUser;
        }

        throw new IllegalArgumentException("Illegal argument @ RegisterUserValidator");
    }

    private void saveItem(String name, String value) {

        switch (name) {
            case "email":
                if (value != null) {
                    if (!value.contains("@")) {
                        errors.put("email", "email must contain @ sign");
                        break;
                    }
                    if (value.length() > 50) {
                        errors.put("email", "email cannot exceed 50 chars");
                        break;
                    }
                }
                errors.put("email", "please fill in email");

                break;
            case "pwd":
                if (value != null) {
                    if (value.matches(REGEX_MAXLENGTH30MINLENGTH3)) {
                        errors.put("password", "password cannot exceed 30 chars");
                        break;
                    }
                }
                errors.put("password", "password must be filled in");
                break;
            case "name":
                if (value != null) {
                    if (value.matches(REGEX_MAXLENGTH30MINLENGTH3)) {
                        errors.put("name", "name cannot exceed 30 chars");
                        break;
                    }
                }
                errors.put("name", "name must be filled in");
                break;
            case "forename":
                if (value != null) {
                    if (value.matches(REGEX_MAXLENGTH30MINLENGTH3)) {
                        errors.put("forename", "forename cannot exceed 30 chars");
                        break;
                    }
                }
                errors.put("forename", "forename must be filled in");
                break;

            case "bdate":
                if (value != null) {
                    if (value.matches(REGEX_BIRTHDATE)) {
                        errors.put("birthday", "birthday date is in an incorrect format");
                        break;
                    }
                }
                errors.put("birthday", "birthday must be filled in");
                break;
            case "firm":
                if (value != null) {
                    if (value.matches(REGEX_MAXLENGTH30MINLENGTH3)) {
                        errors.put("firm", "firm cannot exceed 30 chars");
                        break;
                    }
                }
                errors.put("firm", "firm must be filled in");
                break;
            case "function":
                if (value != null) {
                    if (value.matches(REGEX_MAXLENGTH30MINLENGTH3)) {
                        errors.put("function", "function cannot exceed 30 chars");
                        break;
                    }
                }
                errors.put("funtion", "function must be filled in");
                break;
            case "id":
                //not needed
                break;
            default:
                throw new AssertionError(name + " " + value + " is not handled (RegisterUserValidator)");
        }

    }

    private void createUser(String name, String value, User resultUser) {
        switch (name) {
            case "email":
                resultUser.setEmail(value);
                break;
            case "pwd":
                resultUser.setPassword(value);
                break;
            case "name":
                resultUser.setName(value);
                break;
            case "forename":
                resultUser.setForename(value);
                break;
            case "bdate":
                resultUser.setBirthday(stringToSQLDate(value));
                break;
            case "firm":
                resultUser.setFirm(value);
                break;
            case "function":
                resultUser.setFirmFunction(value);
                break;
            case "id":
                //not needed
                break;
            default:
                throw new AssertionError(name + " " + value + " is not handled (RegisterUserValidator)");
        }

    }

    private java.sql.Date stringToSQLDate(String fecString) {

        java.sql.Date fecFormatoDate = null;
        try {
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return new java.sql.Date(sdf.parse(fecString).getTime());
        } catch (ParseException ex) {
        }
        return null;
    }

    private void processUploadedFile(FileItem item) throws Exception {
        String fileName = String.valueOf(returnUser.getId());
        String extension = "";

        int i = item.getName().lastIndexOf('.');
        int p = Math.max(item.getName().lastIndexOf('/'), item.getName().lastIndexOf('\\'));

        if (i > p) {
            extension = item.getName().substring(i + 1);
        }
        fileName += extension;
        String contentType = item.getContentType();
        if (contentType.startsWith("image")) {
            File imageFile = new File("images/data/profiles", fileName);
            item.write(imageFile);
        } else {
            throw new IllegalArgumentException("Not an image type!");
        }
    }

    public HashMap<String, String> getErrors() {
        return (HashMap<String, String>) errors.clone();
    }
}
