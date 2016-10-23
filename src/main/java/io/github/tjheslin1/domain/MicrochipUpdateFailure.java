package io.github.tjheslin1.domain;

public class MicrochipUpdateFailure extends ValueType {

    public final String failureMessage;

    public MicrochipUpdateFailure(String failureMessage) {
        this.failureMessage = failureMessage;
    }
}
