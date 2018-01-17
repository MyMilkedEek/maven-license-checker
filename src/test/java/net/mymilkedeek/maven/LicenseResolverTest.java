package net.mymilkedeek.maven;

import org.apache.maven.model.License;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyMilkedEek <Michael>
 */
@RunWith(Parameterized.class)
public class LicenseResolverTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {LicenseResolver.MIT, "MIT"},
                {LicenseResolver.PROPRIETARY, "totally-non-existant-license"}
        });
    }

    private final License expected;
    private final String licenseName;

    public LicenseResolverTest(License expected, String licenseName) {
        this.expected = expected;
        this.licenseName = licenseName;
    }

    @Test
    public void resolveLicense() {
        License actual = LicenseResolver.resolve(this.licenseName);
        Assert.assertEquals(this.expected.getName(), actual.getName());
    }
}