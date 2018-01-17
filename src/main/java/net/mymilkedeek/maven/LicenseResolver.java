package net.mymilkedeek.maven;

import org.apache.maven.model.License;

/**
 * @author MyMilkedEek <Michael>
 */
public class LicenseResolver {

    public static final License PROPRIETARY;

    static {
        PROPRIETARY = new License();
        PROPRIETARY.setName("Proprietary");
    }

    public static License resolve(String licenseName) {
        return PROPRIETARY;
    }
}