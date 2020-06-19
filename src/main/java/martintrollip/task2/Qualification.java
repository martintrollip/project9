package martintrollip.task2;

import java.time.LocalDate;

/**
 * @author Martin Trollip
 * @since 2020/06/18 19:06
 */
public final class Qualification {

    private final String qualification;
    private final LocalDate dateObtained;
    private final String institution;

    private Qualification(String qualification, LocalDate dateObtained, String institution) {
        this.qualification = qualification;
        this.dateObtained = dateObtained;
        this.institution = institution;
    }

    public String getQualification() {
        return qualification;
    }

    public LocalDate getDateObtained() {
        return dateObtained;
    }

    public String getInstitution() {
        return institution;
    }

    @Override
    public String toString() {
        return "Qualification{" + "qualification='" + qualification + '\'' + ", dateObtained=" + dateObtained +
                ", institution='" + institution + '\'' + '}';
    }

    public static class QualificationBuilder {
        private String qualification;
        private LocalDate dateObtained;
        private String institution;

        public QualificationBuilder() {
        }

        public QualificationBuilder qualification(String qualification) {
            this.qualification = qualification;
            return this;
        }

        public QualificationBuilder dateObtained(LocalDate dateObtained) {
            this.dateObtained = dateObtained;
            return this;
        }

        public QualificationBuilder institution(String institution) {
            this.institution = institution;
            return this;
        }

        public Qualification build() {
            return new Qualification(qualification, dateObtained, institution);
        }
    }
}
