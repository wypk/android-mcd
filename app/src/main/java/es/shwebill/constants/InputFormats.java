package es.shwebill.constants;

import java.util.regex.Pattern;

public class InputFormats {

    // Username accepts a-z, A-Z, 0-9, underscore and dot characters. It must
    // have minimum length 5 and maximum 16.
    public static final String USERNAME_FORMAT = "^([a-zA-Z0-9]+[_\\\\.]{0,1}[a-zA-Z0-9]+)+$";
    public static final Pattern USERNAME_PATTERN = Pattern.compile(InputFormats.USERNAME_FORMAT);

    // Email accepts a-z, A-Z, 0-9, underscore and hyphen
    public static final String EMAIL_FORMAT =
            "^([a-zA-Z0-9]+([\\.\\-_]{0,1}[a-zA-Z0-9]+)+)+@([a-zA-Z0-9]+([\\.\\-_]{0,1}[a-zA-Z0-9]+)+)+(\\.[a-zA-Z0-9]+)+$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(InputFormats.EMAIL_FORMAT);
}