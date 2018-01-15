package net.mymilkedeek;


import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.License;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @goal check
 * @phase compile
 * @requiresProject true
 */
public class MavenLicenseChecker extends AbstractMojo {



    public void execute() throws MojoExecutionException {
        getLog().info("Starting License Checker v 0.0.1");

        Object projectValue = getPluginContext().get("project");

        if ( projectValue == null || ! (projectValue instanceof MavenProject )) {
            throw new MojoExecutionException("Project not found! A project is needed to run this plugin.");
        }

        MavenProject project = (MavenProject) projectValue;

        List<License> licenses = resolveProjectLicenses(project.getLicenses());

        Set<Artifact> artifacts = project.getDependencyArtifacts();

        try {
            resolveArtifactLicenses(artifacts);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    private void resolveArtifactLicenses(Set<Artifact> artifacts) throws IOException {
        getLog().info(artifacts.size() + " artifact(s) found.");

        for ( Artifact artifact : artifacts ) {
            getLog().info(" - " + artifact.getGroupId() + ":" + artifact.getArtifactId() + ":" + artifact.getVersion() + " found.");
            File artifactJar = artifact.getFile();
            File pomFile = new File(artifactJar.getAbsolutePath().replace(".jar", ".pom"));
            String licenseInPom = getLicenseFromPomFile(pomFile);
            getLog().info("   - " + licenseInPom + " license found for " + artifact.getArtifactId());
        }
    }

    private String getLicenseFromPomFile(File pomFile) throws IOException {
        byte[] encodedBytes = Files.readAllBytes(Paths.get(pomFile.getAbsolutePath()));
        String pomContents = new String(encodedBytes, StandardCharsets.UTF_8);

        int licenseIndex = pomContents.indexOf("<license>");

        if ( licenseIndex > 0 ) {
            int licenseEnd = pomContents.indexOf("</license>");

            return pomContents.substring(licenseIndex, licenseEnd);
        }

        return "Proprietary";
    }

    private List<License> resolveProjectLicenses(List<License> licenses) {
        List<License> output = new ArrayList<License>();

        if ( licenses.size() == 0 ) {
            getLog().warn("No license found!");
            getLog().warn("This means that this project is assumed to have a proprietary license and that you probably will get a lot of false flags.");
            getLog().warn("Please correct your pom.xml to contain a valid <licenses> entry.");
            output.add(resolveLicense("proprietary"));
        } else {
            getLog().info(licenses.size() + " license(s) found." );
        }

        for ( License license : licenses ) {
            getLog().info(" - Found " + license.getName());
            output.add(resolveLicense(license.getName()));
        }

        return output;
    }

    private License resolveLicense(String licenseNameAsInPom) {
        License output = new License();
        output.setName(licenseNameAsInPom);
        return output;
    }


}
