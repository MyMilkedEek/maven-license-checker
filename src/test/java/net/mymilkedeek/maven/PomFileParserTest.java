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

/**
 * @author MyMilkedEek <Michael>
 */
@RunWith(Parameterized.class)
public class PomFileParserTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {LicenseResolver.MIT, "mit"},

                {LicenseResolver.PROPRIETARY, "empty"},
                {LicenseResolver.PROPRIETARY, "proprietary"}
        });
    }

    private License expected;
    private String pomName;

    public PomFileParserTest(License expected, String pomName) {
        this.expected = expected;
        this.pomName = pomName;
    }

    @Test
    public void parsePomFileForLicense() throws IOException {
        File file = new File("src/test/resources/pom/" + pomName + ".pom");
        PomFileParser pomFileParser = new PomFileParser();
        PomFile actual = pomFileParser.parseFile(file);

        Assert.assertEquals(expected.getName(), actual.getLicense().getName());
    }

}