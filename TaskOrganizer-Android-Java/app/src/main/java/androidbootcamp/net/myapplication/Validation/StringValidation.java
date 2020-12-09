package androidbootcamp.net.myapplication.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidbootcamp.net.myapplication.R;

public class StringValidation {

    String Message = " ";

    public StringValidation() {

    }

    //process
    public boolean isValidUsername(final String Username){
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(Username);
        boolean B = m.find();
        if (!B && Username.length()<20)
        {
            return true;
        }else
        {
            return false;
        }
    }

    public boolean isValidPassword( final String password) {

            Pattern p;
            Matcher m;
            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
            p = Pattern.compile(PASSWORD_PATTERN);
            m = p.matcher(password);
            boolean B = m.find();
            if(B&&password.length()>8&&password.length()<20)
            {
                return true;
            }else{
                return false;
            }
        }

        public String inValidUsernameMessage(){
           Message= "Username can't contain Special characters!\r\n"+
                    "Username can't be longer than 20 characters!\r\n"+
                    "Username can be upper or lower case characters or numbers!\r\n";
            return Message;
        }

        public String inValidPasswordMessage(){
            Message= "Password must contain number, letter (regular and Capital)" +
                    ", and special characters\r\n"+
                    "Password can't be longer than 20 and more than 8 characters!\r\n";

            return Message;
        }
    }

