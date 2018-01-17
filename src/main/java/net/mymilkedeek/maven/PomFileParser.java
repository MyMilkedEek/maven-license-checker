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
 * @author MyMilkedEek <Michael>
 */
public class PomFileParser {

    private final String licenseTagStart = "<license>";
    private final String licenseTagEnd   = "</license>";
    private final String nameTagStart    = "<name>";
    private final String nameTageEnd     = "</name>";

    public PomFileParser() {
        // empty constructor
    }

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