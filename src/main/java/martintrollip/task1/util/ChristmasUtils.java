package martintrollip.task1.util;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Utility to retrieve a user-friendly message indicating the number of days until the next Christmas.
 *
 * @author Martin Trollip
 * @since 2020/06/16 11:58
 */
public class ChristmasUtils {
    private static final int DECEMBER = 12;
    private static final int CHRISTMAS = 25;
    private static final int NEXT_YEAR = 1;

    private final Clock clock;

    /**
     * Create instance of ChristmasUtils
     *
     * @param clock the clock to use. Use <code>Clock.systemDefaultZone()</code> if you wish to use the System clock
     */
    public ChristmasUtils(Clock clock) {
        this.clock = clock;
    }

    /**
     * Returns the number of days until the next Christmas (month 12, day 25).
     *
     * @return A user-friendly message indicating the number of days until Christmas.
     *
     * For example if the current clock is 2020/06/16 the returned message is "192 days until Christmas!",
     *             if the current clock is 2020/12/34 the returned message is "1 day until Christmas!",
     *             if the current clock is 2020/12/25 the returned message is "Merry Christmas! 🎄 🎄 🎄",
     *             if the current clock is 2020/12/26 the returned message is "364 days until Christmas!"
     */
    public String getDaysUntilChristmasMessage() {
        long daysUntilChristmas = ChronoUnit.DAYS.between(LocalDate.now(clock).atStartOfDay(), nextChristmas());

        if (daysUntilChristmas == 0) {
            return "Merry Christmas! \uD83C\uDF84 \uD83C\uDF84 \uD83C\uDF84"; // \uD83C\uDF84 is a Christmas tree emoji
        } else if (daysUntilChristmas == 1) {
            return String.format("%s day until Christmas!", daysUntilChristmas);
        } else {
            return String.format("%s days until Christmas!", daysUntilChristmas);
        }
    }

    private LocalDateTime nextChristmas() {
        LocalDate today = LocalDate.now(clock);
        LocalDate christmas = LocalDate.of(today.getYear(), DECEMBER, CHRISTMAS);

        if (today.isBefore(christmas) || today.isEqual(christmas)) {
            return christmas.atStartOfDay();
        } else {
            return christmas.plusYears(NEXT_YEAR).atStartOfDay();
        }
    }
}
