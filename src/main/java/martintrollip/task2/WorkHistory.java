package martintrollip.task2;

/**
 * WorkHistory. Use the builder to construct a new instance.
 *
 * @author Martin Trollip
 * @since 2020/06/18 19:07
 */
public final class WorkHistory {

    private final String comapny;
    private final int yearsWorked;
    private final String responsibility;

    private WorkHistory(String comapny, int yearsWorked, String responsibility) {
        this.comapny = comapny;
        this.yearsWorked = yearsWorked;
        this.responsibility = responsibility;
    }

    public String getComapny() {
        return comapny;
    }

    public int getYearsWorked() {
        return yearsWorked;
    }

    public String getResponsibility() {
        return responsibility;
    }

    @Override
    public String toString() {
        return "WorkHistory{" + "comapny='" + comapny + '\'' + ", yearsWorked=" + yearsWorked + ", responsibility='" +
                responsibility + '\'' + '}';
    }

    public static class WorkHistoryBuilder {
        private String comapny;
        private int yearsWorked;
        private String responsibility;

        public WorkHistoryBuilder company(String comapny) {
            this.comapny = comapny;
            return this;
        }

        public WorkHistoryBuilder yearsWorked(int yearsWorked) {
            this.yearsWorked = yearsWorked;
            return this;
        }

        public WorkHistoryBuilder responsibility(String responsibility) {
            this.responsibility = responsibility;
            return this;
        }

        public WorkHistory build() {
            return new WorkHistory(comapny, yearsWorked, responsibility);
        }
    }
}
