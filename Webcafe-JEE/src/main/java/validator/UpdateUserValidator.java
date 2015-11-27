package validator;

import dao.UserDao;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import model.User;
import org.apache.commons.fileupload.FileItem;

/* @author BertGoens */
public class UpdateUserValidator implements ObjectValidator<Boolean> {

    private User updateUser;
    private HashMap<String, String> errors;

    public UpdateUserValidator() {
        this.updateUser = new User();
        errors = new HashMap<>();
    }

    /**
     Validates a list of FileItems and checks if each one is correct.
     Stores the user in the database if that is the case

     @param items

     @return A persisted User() or null
     */
    @Override
    public Boolean validate(List items) {
        errors = new HashMap<>();

        Iterator iterator = items.iterator();
        while (iterator.hasNext()) {
            FileItem item = (FileItem) iterator.next();

            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                saveItem(fieldName, fieldValue);
            } else {
                saveImage(item);
            }
        }

        UserDao ud = new UserDao();
        if (updateUser.getId() > 0) {
            ud.merge(updateUser);
        }

        return errors.isEmpty();
    }

    private void saveItem(String key, String value) {
        if (value == null) {
            errors.put(key, "is null");
            return;
        }

        switch (key) {
            case "userId":
                int uid = Integer.valueOf(value);
                updateUser.setId(uid);
                break;

            case "isAdmin":
                boolean admin = Boolean.valueOf(value);
                updateUser.setIsAdmin(admin);
                break;

            case "email":
                if (!value.contains("@")) {
                    errors.put(key, "email must contain @ sign");
                    break;
                }
                if (value.length() > 50) {
                    errors.put(key, "email cannot exceed 50 chars");
                    break;
                }
                if (value.length() < 3) {
                    errors.put(key, "please fill in email");
                    break;
                }
                UserDao userDao = new UserDao();
                User emailOfUser = userDao.findByEmail(value);
                if (emailOfUser != null && emailOfUser.getId() != updateUser.getId()) {
                    errors.put(key, "email address must be unique");
                    break;
                }
                updateUser.setEmail(value);
                break;

            case "pwd":
                if (value.length() > 30) {
                    errors.put(key, "password cannot exceed 30 chars");
                    break;
                }
                if (value.length() < 3) {
                    errors.put(key, "password must be filled in");
                    break;
                }
                updateUser.setPassword(value);
                break;

            case "name":
                if (value.length() > 30) {
                    errors.put(key, "name cannot exceed 30 chars");
                    break;
                }
                if (value.length() < 3) {
                    errors.put(key, "name must be filled in");
                    break;
                }
                updateUser.setName(value);
                break;

            case "forename":
                if (value.length() > 30) {
                    errors.put(key, "forename cannot exceed 30 chars");
                    break;
                }
                if (value.length() < 3) {
                    errors.put(key, "forename must be filled in");
                    break;
                }
                updateUser.setForename(value);
                break;

            case "bdate":
                if (value.length() != 10) {
                    errors.put(key, "birthday must be filled in");
                    break;
                }
                Date bDate;
                try {
                    bDate = stringToSQLDate(value);
                } catch (Exception e) {
                    errors.put(key, "birthday date is in an incorrect format, use dd/MM/yyyy");
                    break;
                }
                updateUser.setBirthday(bDate);
                break;

            case "firm":
                if (value.length() > 30) {
                    errors.put(key, "firm cannot exceed 30 chars");
                    break;
                }
                if (value.length() < 3) {
                    errors.put(key, "firm must be filled in");
                    break;
                }
                updateUser.setFirm(value);
                break;

            case "function":
                if (value.length() > 30) {
                    errors.put(key, "function cannot exceed 30 chars");
                    break;
                }
                if (value.length() < 3) {
                    errors.put(key, "function must be filled in");
                    break;
                }
                updateUser.setFirmFunction(value);
                break;

            default:
                throw new AssertionError(key + " " + value + " is not handled (RegisterUserValidator)");
        }

    }

    private java.sql.Date stringToSQLDate(String Date) throws ParseException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        return new java.sql.Date(sdf.parse(Date).getTime());
    }

    private void saveImage(FileItem item) {
        if (!item.getContentType().startsWith("image")) {
            errors.put(item.getFieldName(), "Not an image type!");
            return;
        }

        //CONSTANTS
        String sRootPath = new File("").getAbsolutePath();
        sRootPath = "/Users/BertGoens/NetBeansProjects/WebCafe/Webcafe-JEE/src/main/webapp/";
        String serverFolder = "/images/data/profiles/";

        //Delete old image
        try {
            File delFile = new File(sRootPath + updateUser.getImagePath());
            delFile.delete();
        } catch (Exception e) {
        }

        //Generate random GUID for name
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String fileName = String.valueOf(randomUUIDString);

        //Add extension to name
        String extension = ".";
        int i = item.getName().lastIndexOf('.');
        int p = Math.max(item.getName().lastIndexOf('/'), item.getName().lastIndexOf('\\'));

        if (i > p) {
            extension += item.getName().substring(i + 1);
        }
        fileName += extension;

        //Save the new image in the correct directory
        File imageFile = new File(sRootPath + serverFolder, fileName);
        try {
            updateUser.setImagePath(serverFolder + fileName);
            item.write(imageFile);
        } catch (Exception ex) {
            errors.put(item.getFieldName(), "Error saving the file: " + ex.getMessage());
        }
    }

    public HashMap<String, String> getErrors() {
        return (HashMap<String, String>) errors.clone();
    }

    public User getUpdateUser() {
        return updateUser;
    }

}
