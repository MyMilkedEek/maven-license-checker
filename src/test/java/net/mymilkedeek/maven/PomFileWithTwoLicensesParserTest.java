package net.mymilkedeek.maven;

import org.apache.maven.model.License;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author MyMilkedEek <Michael>
 */
@RunWith(Parameterized.class)
public class PomFileWithTwoLicensesParserTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {LicenseResolver.MIT, LicenseResolver.MPL, "mit-mpl"},
        });
    }

    private License firstExpected;
    private License secondExpected;
    private String pomName;

    public PomFileWithTwoLicensesParserTest(License firstExpected, License secondExpected, String pomName) {
        this.firstExpected = firstExpected;
        this.secondExpected = secondExpected;
        this.pomName = pomName;
    }

    @Test
    public void parsePomFileForLicense() throws IOException {
        File file = new File("src/test/resources/pom/" + pomName + ".pom");
        PomFileParser pomFileParser = new PomFileParser();
        PomFile actual = pomFileParser.parseFile(file);

        List<License> licenses = actual.getLicenses();

        Assert.assertTrue(licenses.size() == 2);
        Assert.assertEquals(firstExpected.getName(), licenses.get(0).getName());
        Assert.assertEquals(secondExpected.getName(), licenses.get(1).getName());
    }

}