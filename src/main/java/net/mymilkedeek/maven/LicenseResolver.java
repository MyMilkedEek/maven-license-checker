package net.mymilkedeek.maven;

import org.apache.maven.model.License;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that resolves a license name to a License object.
 *
 * @author MyMilkedEek <Michael>
 */
public class LicenseResolver {

    /**
     * Resolves a license name to a License object.
     * If the license can't be resolved, PROPRIETARY is returned.
     *
     * @param licenseName name of the license
     * @return found License object, PROPRIETARY if not found
     */
    public static License resolve(String licenseName) {

        if ( licenses.containsKey(licenseName) ) {
            return licenses.get(licenseName);
        }

        return PROPRIETARY;
    }

    /**
     * MIT License
     */
    public static final License MIT = instantiateLicense("MIT");

    /**
     * MPL License
     */
    public static final License MPL = instantiateLicense("MPL");

    /**
     * Proprietary license
     */
    public static final License PROPRIETARY = instantiateLicense("Proprietary");

    // internal mapping of names onto licenses
    private static Map<String, License> licenses;

    /**
     * Instantiates a license instance and adds it to the internal mapping
     */
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