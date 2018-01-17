package net.mymilkedeek.maven;

import org.apache.maven.model.License;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author MyMilkedEek <Michael>
 */
public class PomFile {

    private List<License> licenses;

    public PomFile(License license) {
        this.licenses = new ArrayList<>();
        this.licenses.add(license);
    }

    public PomFile(Collection<License> licenses) {
        this.licenses = new ArrayList<>();
        this.licenses.addAll(licenses);
    }

    public License getLicense() {
        return this.licenses.get(0);
    }

    public List<License> getLicenses() {
        return this.licenses;
    }
}