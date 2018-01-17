package net.mymilkedeek.maven;

import org.apache.maven.model.License;

/**
 * @author MyMilkedEek <Michael>
 */
public class PomFile {

    private License license;

    public PomFile(License license) {
        this.license = license;
    }

    public License getLicense() {
        return license;
    }
}