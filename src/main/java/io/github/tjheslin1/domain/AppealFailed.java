package io.github.tjheslin1.domain;

public class AppealFailed extends ValueType {

    public final String appealFailureReason;
    public final DogFosterRejection fosterRejection;

    public AppealFailed(String appealFailureReason, DogFosterRejection fosterRejection) {
        this.appealFailureReason = appealFailureReason;
        this.fosterRejection = fosterRejection;
    }
}
