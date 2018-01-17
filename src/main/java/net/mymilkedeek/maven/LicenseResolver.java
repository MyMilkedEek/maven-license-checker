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
    public static final License MPL = instantiateLicense("MPL");
    public static final License PROPRIETARY = instantiateLicense("Proprietary");

    private static Map<String, License> licenses;

    private static License instantiateLicense(final String licenseName) {
        final License license = new License();
        license.setName(licenseName);

        if ( licenses == null ) {
            licenses = new HashMap<>();
        }

        licenses.put(licenseName, license);

        return license;
    }
}