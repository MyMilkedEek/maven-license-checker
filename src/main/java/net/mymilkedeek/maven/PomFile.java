package net.mymilkedeek.maven;

import org.apache.maven.model.License;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a pom file of a maven project. Mainly intended as a container for properties the checker needs that aren't
 * easily accessible through the provided API.
 *
 * @author MyMilkedEek <Michael>
 */
public class PomFile {

    // list of licenses defined in the pom file
    private List<License> licenses;

    /**
     * Creates a PomFile instance with only one license key.
     *
     * @param license license of the project
     */
    public PomFile(License license) {
        this.licenses = new ArrayList<>();
        this.licenses.add(license);
    }

    /**
     * Creates a PomFile instance with only one license key.
     *
     * @param licenses licenses of the project
     */
    public PomFile(Collection<License> licenses) {
        this.licenses = new ArrayList<>();
        this.licenses.addAll(licenses);
    }

    /**
     * Returns the (first) license set to this project.
     *
     * @return defined license
     */
    public License getLicense() {
        return this.licenses.get(0);
    }

    /**
     * Returns all the licenses defined to this project.
     *
     * @return defined licenses
     */
    public List<License> getLicenses() {
        return this.licenses;
    }
}