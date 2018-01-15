package net.mymilkedeek;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

/**
 * @goal check
 * @phase compile
 * @requiresProject true
 */
public class MavenLicenseChecker extends AbstractMojo {

    public void execute() throws MojoExecutionException {
        final Log log = getLog();

        log.info("Starting License Checker v 0.0.1");


    }
}
