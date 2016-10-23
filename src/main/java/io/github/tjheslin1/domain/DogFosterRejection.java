package io.github.tjheslin1.domain;

import static java.lang.String.format;

public class DogFosterRejection extends ValueType {

    public final Dog dog;
    public final String reason;

    public DogFosterRejection(Dog dog, String reason) {
        this.dog = dog;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return format("Unable to foster '%s' due to '%s'", dog, reason);
    }
}
