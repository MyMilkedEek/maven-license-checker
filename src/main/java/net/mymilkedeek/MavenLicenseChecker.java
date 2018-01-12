package net.mymilkedeek;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Echos an object string to the output screen.
 * @goal echo
 * @requiresProject false
 */
public class MavenLicenseChecker extends AbstractMojo {

    public void execute() throws MojoExecutionException {
        getLog().info("Starting Custom Maven goal");
    }
}
