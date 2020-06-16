package martintrollip.task1;

import martintrollip.task1.util.ChristmasUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

@RunWith(BlockJUnit4ClassRunner.class)
public class ChristmasUtilsTest {

    @Test
    public void test_christmasUtils_juneToChristmas() {
        ChristmasUtils christmasUtils = getUtilsOn(2020, 6, 16);
        String message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("192 days until Christmas!", message);
    }

    @Test
    public void test_christmasUtils_dayAfterChristmasToNextChristmas() {
        ChristmasUtils christmasUtils = getUtilsOn(2019, 12, 26);
        String message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("365 days until Christmas!", message);//+1 day because it's a leap year

        christmasUtils = getUtilsOn(2020, 12, 26);
        message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("364 days until Christmas!", message);

        christmasUtils = getUtilsOn(2021, 12, 26);
        message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("364 days until Christmas!", message);
    }

    @Test
    public void test_christmasUtils_dayBeforeChristmasToNextChristmas() {
        ChristmasUtils christmasUtils = getUtilsOn(2019, 12, 24);
        String message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("1 day until Christmas!", message);

        christmasUtils = getUtilsOn(2020, 12, 24);
        message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("1 day until Christmas!", message);

        christmasUtils = getUtilsOn(2021, 12, 24);
        message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("1 day until Christmas!", message);
    }

    @Test
    public void test_christmasUtils_lastDayOfYearToChristmas() {
        ChristmasUtils christmasUtils = getUtilsOn(2020, 12, 31);
        String message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("359 days until Christmas!", message);

        christmasUtils = getUtilsOn(2021, 12, 31);
        message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("359 days until Christmas!", message);
    }

    @Test
    public void test_christmasUtils_firstDayOfYearToChristmas() {
        ChristmasUtils christmasUtils = getUtilsOn(2020, 1, 1);
        String message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("359 days until Christmas!", message); //+1 day because it's a leap year

        christmasUtils = getUtilsOn(2021, 1, 1);
        message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("358 days until Christmas!", message);
    }

    @Test
    public void test_christmasUtils_onChristmas() {
        ChristmasUtils christmasUtils = getUtilsOn(2019, 12, 25);
        String message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("Merry Christmas! \uD83C\uDF84 \uD83C\uDF84 \uD83C\uDF84", message);

        christmasUtils = getUtilsOn(2020, 12, 25);
        message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("Merry Christmas! \uD83C\uDF84 \uD83C\uDF84 \uD83C\uDF84", message);

        christmasUtils = getUtilsOn(2021, 12, 25);
        message = christmasUtils.getDaysUntilChristmasMessage();
        Assert.assertEquals("Merry Christmas! \uD83C\uDF84 \uD83C\uDF84 \uD83C\uDF84", message);
    }

    private ChristmasUtils getUtilsOn(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        Clock fixedClock = Clock.fixed(date.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        return new ChristmasUtils(fixedClock);
    }
}
