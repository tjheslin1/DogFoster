package io.github.tjheslin1.domain;

public class DogFosterRejection extends ValueType {

    public final Dog dog;
    public final String reason;

    public DogFosterRejection(Dog dog, String reason) {
        this.dog = dog;
        this.reason = reason;
    }
}
