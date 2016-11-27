package io.github.tjheslin1;

import io.github.theangrydev.businessflows.BusinessFlow;
import io.github.theangrydev.businessflows.HappyPath;
import io.github.tjheslin1.domain.*;

import static io.github.theangrydev.businessflows.HappyPath.happyPath;
import static io.github.theangrydev.businessflows.HappyPath.sadPath;
import static java.lang.String.format;

public class DogShelter {

    public static final int MINIMUM_DONATION = 75;

    public BusinessFlow<DogFosterSuccess, AppealFailed> fosterRequest(Dog dog) {
        return dogFosterRequest(dog)
                .ifHappy().peek(this::payDonationFee)
                .then(this::checkSuitability)
                .ifSad().then(this::appealFailedRequest)
                .peek(this::reportUnsuccessfulFosterAttempt)
                .ifTechnicalFailure().peek(this::reportError)
                .ifHappy()
                .peek(this::reportSuccess);

    }

    private HappyPath<DogFosterRequest, DogFosterRejection> dogFosterRequest(Dog dog) {
        return happyPath(new DogFosterRequest(dog));
    }

    private void payDonationFee(DogFosterRequest dogFosterRequest) {
        if (dogFosterRequest.dog.donationOffer() < MINIMUM_DONATION) {
            throw new IllegalStateException(format("Unable to process payment for '%s'. The minimum donation fee is '%s'",
                    dogFosterRequest.dog, MINIMUM_DONATION));
        }
    }

    private HappyPath<DogFosterSuccess, DogFosterRejection> checkSuitability(DogFosterRequest dogFosterRequest) {
        if (dogFosterRequest.dog instanceof Westie) {
            return sadPath(new DogFosterRejection(dogFosterRequest.dog, "Currently all Westie's are too young for adoption."));
        } else if (dogFosterRequest.dog instanceof Patterdale && dogFosterRequest.dog.trainingRequired()) {
            return sadPath(new DogFosterRejection(dogFosterRequest.dog, "All Patterdale's must be trained before adoption."));
        }

        return happyPath(new DogFosterSuccess(dogFosterRequest.dog));
    }

    private void reportSuccess(DogFosterSuccess dogFosterSuccess) {
        System.out.println(format("Congratulations! '%s' has been fostered.", dogFosterSuccess.dog));
    }

    private void reportUnsuccessfulFosterAttempt(AppealFailed appealFailed) {
        DogFosterRejection dogFosterRejection = appealFailed.fosterRejection;
        System.out.println(format("Sorry! '%s' has not been fostered due to '%s'", dogFosterRejection.dog, dogFosterRejection.reason));
    }

    private HappyPath<DogFosterSuccess, AppealFailed> appealFailedRequest(DogFosterRejection dogFosterRejection) {
        if (dogFosterRejection.reason.contains("too young")) {
            return sadPath(new AppealFailed("Appeal failed. ", dogFosterRejection));
        }

        return happyPath(new DogFosterSuccess(dogFosterRejection.dog));
    }

    private void reportError(Exception e) {
        System.out.println(e.getMessage());
    }
}
