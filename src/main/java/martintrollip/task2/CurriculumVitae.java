package martintrollip.task2;

import java.util.Collections;
import java.util.List;

/**
 * Immutable and final CV.  Use the builder to construct a new instance
 *
 * @author Martin Trollip
 * @since 2020/06/18 19:02
 */
public final class CurriculumVitae {

    private final PersonalDetails personalDetails;
    private final List<Qualification> qualifications;
    private final List<WorkHistory> workHistories;

    private CurriculumVitae(PersonalDetails personalDetails, List<Qualification> qualifications,
            List<WorkHistory> workHistories) {
        this.personalDetails = personalDetails;
        this.qualifications = Collections.unmodifiableList(qualifications);
        this.workHistories = Collections.unmodifiableList(workHistories);
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public List<Qualification> getQualifications() {
        return Collections.unmodifiableList(qualifications);
    }

    public List<WorkHistory> getWorkHistories() {
        return Collections.unmodifiableList(workHistories);
    }

    @Override
    public String toString() {
        return "CurriculumVitae{" + "personalDetails=" + personalDetails + ", qualifications=" + qualifications +
                ", workHistories=" + workHistories + '}';
    }

    public static class CurriculumVitaeBuilder {
        private PersonalDetails personalDetails;
        private List<Qualification> qualifications;
        private List<WorkHistory> workHistories;

        public CurriculumVitaeBuilder personalDetails(PersonalDetails personalDetails) {
            this.personalDetails = personalDetails;
            return this;
        }

        public CurriculumVitaeBuilder qualifications(List<Qualification> qualifications) {
            this.qualifications = qualifications;
            return this;
        }

        public CurriculumVitaeBuilder workHistories(List<WorkHistory> workHistories) {
            this.workHistories = workHistories;
            return this;
        }

        public CurriculumVitae build() {
            return new CurriculumVitae(personalDetails, qualifications, workHistories);
        }
    }
}
