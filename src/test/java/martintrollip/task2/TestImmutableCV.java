package martintrollip.task2;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author Martin Trollip
 * @since 2020/06/18 19:38
 */
public class TestImmutableCV {

    @Test
    public void test_CV_unmodifiable() {
        CurriculumVitae cv = new CurriculumVitae.CurriculumVitaeBuilder().personalDetails(getPersonalDetails())
                .qualifications(getQualifications()).workHistories(getWorkHistory()).build();

        Assert.assertEquals("Martin", cv.getPersonalDetails().getName());
        Assert.assertEquals("Trollip", cv.getPersonalDetails().getSurname());
        Assert.assertEquals("BEng Electrical and Electronic Engineering", cv.getQualifications().get(0).getQualification());
        Assert.assertEquals("University of Studying", cv.getQualifications().get(1).getInstitution());
        Assert.assertEquals(LocalDate.of(2018, 1, 1), cv.getQualifications().get(2).getDateObtained());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_CV_unmodifiableQualificationLists() {
        CurriculumVitae cv = new CurriculumVitae.CurriculumVitaeBuilder().personalDetails(getPersonalDetails())
                .qualifications(getQualifications()).workHistories(getWorkHistory()).build();

        cv.getQualifications().addAll(getQualifications());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_CV_unmodifiableWorkHistoryLists() {
        CurriculumVitae cv = new CurriculumVitae.CurriculumVitaeBuilder().personalDetails(getPersonalDetails())
                .qualifications(getQualifications()).workHistories(getWorkHistory()).build();

        cv.getWorkHistories().addAll(getWorkHistory());
    }

    private PersonalDetails getPersonalDetails() {
        return new PersonalDetails.PersonalDetailsBuilder().name("Martin").surname("Trollip").cellphone("012 123 1234")
                .address("314 Cul-de-sac Cres, Suburban, 4732").build();
    }

    private List<Qualification> getQualifications() {
        Qualification qualificationBEng =
                new Qualification.QualificationBuilder().qualification("BEng Electrical and Electronic Engineering")
                        .dateObtained(LocalDate.of(2014, 1, 1)).institution("University of Studying").build();

        Qualification qualificationBSc = new Qualification.QualificationBuilder().qualification("BSc Computer Science")
                .dateObtained(LocalDate.of(2014, 1, 1)).institution("University of Studying").build();

        Qualification qualificationMEng =
                new Qualification.QualificationBuilder().qualification("MEng Electrical and Electronic Engineering")
                        .dateObtained(LocalDate.of(2018, 1, 1)).institution("University of Studying").build();

        return Arrays.asList(qualificationBEng, qualificationBSc, qualificationMEng);
    }

    private List<WorkHistory> getWorkHistory() {
        WorkHistory workHistoryArchimedes = new WorkHistory.WorkHistoryBuilder().company("Archimedes").yearsWorked(1)
                .responsibility("Duties included technical support to employees and building prototype applications.").build();

        WorkHistory workHistoryLearning = new WorkHistory.WorkHistoryBuilder().company("Learning Pty Ltd").yearsWorked(6)
                .responsibility("Responsibilities includes the development of requirements for the Learning Pty Ltd\n" +
                        "consumer application and Spring webservices.").build();

        return Arrays.asList(workHistoryArchimedes, workHistoryLearning);
    }
}
