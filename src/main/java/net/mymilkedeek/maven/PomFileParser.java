package net.mymilkedeek.maven;

import org.apache.maven.model.License;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author MyMilkedEek <Michael>
 */
public class PomFileParser {

    public PomFileParser() {

    }

    public PomFile parseFile(File fileToBeParsed) throws IOException {
        License license = LicenseResolver.PROPRIETARY;

        byte[] encodedBytes = Files.readAllBytes(Paths.get(fileToBeParsed.getAbsolutePath()));
        String pomContents = new String(encodedBytes, StandardCharsets.UTF_8);

        int licenseIndex = pomContents.indexOf("<license>");

        if ( licenseIndex > 0 ) {
            int licenseEnd = pomContents.indexOf("</license>");

            String licenseContents = pomContents.substring(licenseIndex, licenseEnd);

            int nameIndex = licenseContents.indexOf("<name>");

            if ( nameIndex > 0 ) {
                int nameEnd = licenseContents.indexOf("</name>");

                String licenseName = licenseContents.substring(nameIndex + "<name>".length(), nameEnd);
                license = LicenseResolver.resolve(licenseName);
            }
        }

        return new PomFile(license);
    }

}