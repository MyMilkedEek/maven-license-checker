package net.mymilkedeek.maven;

import org.apache.maven.model.License;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author MyMilkedEek <Michael>
 */
public class LicenseResolverTest {

    @Test
    public void licenseNotFoundTest() {
        resolutionHelper(LicenseResolver.PROPRIETARY, "totally-non-existant-license");
    }

    @Test
    public void mitLicense() {
        resolutionHelper(LicenseResolver.MIT, "MIT");
    }

    private void resolutionHelper(final License expected, final String licenseName) {
        License actual = LicenseResolver.resolve(licenseName);
        Assert.assertEquals(expected.getName(), actual.getName());
    }
}