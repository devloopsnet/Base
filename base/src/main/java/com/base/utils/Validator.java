package com.base.utils;

import android.text.TextUtils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class Validator extends StringUtils {
    /*
             UnderStanding regex
             ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$
             How it works?
            ^                 # start-of-string
            (?=.*[0-9])       # a digit must occur at least once
            (?=.*[a-z])       # a lower case letter must occur at least once
            (?=.*[A-Z])       # an upper case letter must occur at least once
            (?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
            (?=\\S+$)         # no whitespace allowed in the entire string
            .{4,}             # anything, at least six places though
            $                 # end-of-string
    */

    // alphanumeric parttern for password
    private static final String PASSWORDPARTTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*])(?=\\\\S+$).{6,}$";
    private static final String USERNAMEPARTTEN = "(?=\\S+$).{3,}";
    private static final String PHONE_PATTERS = "^011(999|998|997|996|995|994|993|992|991| 990|979|978|977|976|975|974|973|972|971|970| 969|968|967|966|965|964|963|962|961|960|899| 898|897|896|895|894|893|892|891|890|889|888| 887|886|885|884|883|882|881|880|879|878|877| 876|875|874|873|872|871|870|859|858|857|856| 855|854|853|852|851|850|839|838|837|836|835| 834|833|832|831|830|809|808|807|806|805|804| 803|802|801|800|699|698|697|696|695|694|693| 692|691|690|689|688|687|686|685|684|683|682| 681|680|679|678|677|676|675|674|673|672|671| 670|599|598|597|596|595|594|593|592|591|590| 509|508|507|506|505|504|503|502|501|500|429| 428|427|426|425|424|423|422|421|420|389|388| 387|386|385|384|383|382|381|380|379|378|377| 376|375|374|373|372|371|370|359|358|357|356| 355|354|353|352|351|350|299|298|297|296|295| 294|293|292|291|290|289|288|287|286|285|284| 283|282|281|280|269|268|267|266|265|264|263| 262|261|260|259|258|257|256|255|254|253|252| 251|250|249|248|247|246|245|244|243|242|241| 240|239|238|237|236|235|234|233|232|231|230| 229|228|227|226|225|224|223|222|221|220|219| 218|217|216|215|214|213|212|211|210|98|95|94| 93|92|91|90|86|84|82|81|66|65|64|63|62|61|60| 58|57|56|55|54|53|52|51|49|48|47|46|45|44|43| 41|40|39|36|34|33|32|31|30|27|20|7|1)[0-9]{0,14}$";

    /**
     * Is email valid boolean.
     *
     * @param email email address that need to check
     * @return true if email address is matched with defined pattern. Here pattern is used from api level 22 android
     */
    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static boolean isPhoneValid(String number) {
        return number.matches(PHONE_PATTERS);
    }

    /**
     * Is password valid boolean.
     *
     * @param password that need to check
     * @return true if password is matched with defined pattern.
     * @link http ://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
     */
    public static boolean isPasswordValid(String password) {
        if (password.contains(" "))
            return false;
        return password.length() >= 6;
    }


    /**
     * Is password matches boolean.
     *
     * @param password        the password
     * @param confirmPassword that need to match
     * @return true if email address is matched with defined pattern. Here pattern is used from api level 22 android
     */
    public static boolean isPasswordMatches(String password, String confirmPassword) {
        return isMatches(password, confirmPassword);
    }

    /**
     * Is username valid boolean.
     *
     * @param username that need to check
     * @return true if email address is matched with defined pattern. Here pattern is used from api level 22 android
     */
    public static boolean isUsernameValid(String username) {
        return username.matches(USERNAMEPARTTEN);
    }

    /**
     * This method is used to check if the entered string is null, blank, or
     * "null"
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmptyOrNull(String str) {
        return !(!TextUtils.isEmpty(str) && !str.equals("null"));
    }

    /**
     * Is matches boolean.
     *
     * @param str1 the str 1
     * @param str2 the str 2
     * @return the boolean
     */
    private static boolean isMatches(String str1, String str2) {
        return TextUtils.equals(str1, str2);
    }

    /**
     * Is string contains blank space
     *
     * @param str1 the str 1
     * @return the boolean true if contains blank space
     */
    public static boolean isContainSpace(String str1) {
        return str1.contains(" ");
    }
}