package io.github.tjheslin1;

import io.github.tjheslin1.domain.Dog;
import io.github.tjheslin1.domain.Patterdale;
import io.github.tjheslin1.domain.Westie;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class DogShelterTest implements WithAssertions {

    private final DogShelter dogShelter = new DogShelter();

    private final Dog woody = new Patterdale("Woody", 5, false, 2, 80);
    private final Dog buzz = new Patterdale("Buzz", 8, false, 3, 90);
    private final Dog buster = new Patterdale("Buster", 8, true, 3, 110);

    private final Dog joey = new Westie("Joey", 2, false, true, 75);
    private final Dog archie = new Westie("Archie", 4, false, false, 50);

    @Test
    public void trainedPatterdalesAreFostered() throws Exception {
        assertThat(dogShelter.fosterRequest(woody).ifHappy().get().dog).isEqualTo(woody);
        assertThat(dogShelter.fosterRequest(buzz).ifHappy().get().dog).isEqualTo(buzz);
    }

    @Test
    public void untrainedPatterdalesAreFosteredAfterAppeal() throws Exception {
        assertThat(dogShelter.fosterRequest(buster).ifHappy().get().dog).isEqualTo(buster);
    }

    @Test
    public void donationTooLowResultInATechnicalFailure() throws Exception {
        Exception actual = null;
        try {
            actual = dogShelter.fosterRequest(archie).ifTechnicalFailure().get();
        } catch (Exception e) {
            fail("A donation too low should have thrown an exception");
        }

        assertThat(actual)
                .hasMessageContaining("Unable to process payment for 'Archie'. The minimum donation fee is '75'");
    }

    @Test
    public void westiesWhichAreTooYoungResultInTechnicalFailure() throws Exception {
        assertThat(dogShelter.fosterRequest(joey).ifSad().get().toString())
                .isEqualTo("Appeal failed. Unable to foster 'Joey' due to 'Currently all Westie's are too young for adoption.'");
    }
}