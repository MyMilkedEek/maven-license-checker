package net.mymilkedeek.maven;

import org.apache.maven.model.License;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyMilkedEek <Michael>
 */
public class LicenseResolver {

    public static License resolve(String licenseName) {

        if ( licenses.containsKey(licenseName) ) {
            return licenses.get(licenseName);
        }

        return PROPRIETARY;
    }

    public static final License MIT = instantiateLicense("MIT");
    public static final License PROPRIETARY = instantiateLicense("Proprietary");

    private static Map<String, License> licenses;

    static {
        licenses = new HashMap<String, License>();

        licenses.put("MIT", MIT);
    }

    private static License instantiateLicense(String licenseName) {
        License license = new License();
        license.setName(licenseName);
        return license;
    }
}