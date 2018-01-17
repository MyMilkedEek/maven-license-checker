package net.mymilkedeek.maven;

import org.apache.maven.model.License;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to parse a pom file.
 *
 * @author MyMilkedEek <Michael>
 */
public class PomFileParser {

    // tag that starts a license entry
    private final String licenseTagStart = "<license>";

    // tag that ends a license entry
    private final String licenseTagEnd   = "</license>";

    // tag that starts a license name entry
    private final String nameTagStart    = "<name>";

    // tag that ends a license name entry
    private final String nameTageEnd     = "</name>";

    /**
     * Parses a java.io.File instance to a PomFile instance.
     * Adds one or more license definitions to the PomFile instance.
     * If no license has been defined in the pom file, then PROPRIETARY will be set.
     *
     * @param fileToBeParsed the pom file
     * @return parsed PomFile instance
     * @throws IOException when the java.io.File can't be found
     */
    public PomFile parseFile(File fileToBeParsed) throws IOException {
        List<License> licenses = new ArrayList<>();

        byte[] encodedBytes = Files.readAllBytes(Paths.get(fileToBeParsed.getAbsolutePath()));
        String pomContents = new String(encodedBytes, StandardCharsets.UTF_8);

        int licenseIndex = pomContents.indexOf(licenseTagStart);

        while ( licenseIndex > 0 ) {
            int licenseEnd = pomContents.indexOf(licenseTagEnd);
            String licenseContents = pomContents.substring(licenseIndex, licenseEnd);
            int nameIndex = licenseContents.indexOf(nameTagStart);

            if ( nameIndex > 0 ) {
                int nameEnd = licenseContents.indexOf(nameTageEnd);
                String licenseName = licenseContents.substring(nameIndex + nameTagStart.length(), nameEnd);
                licenses.add(LicenseResolver.resolve(licenseName));
            }

            pomContents = pomContents.substring(licenseEnd + licenseTagEnd.length());
            licenseIndex = pomContents.indexOf(licenseTagStart);
        }

        if ( licenses.size() == 0 ) {
            licenses.add(LicenseResolver.PROPRIETARY);
        }

        return new PomFile(licenses);
    }

}