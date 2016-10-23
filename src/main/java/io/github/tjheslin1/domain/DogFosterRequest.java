package io.github.tjheslin1.domain;

public class DogFosterRequest extends ValueType {

    public final Dog dog;

    public DogFosterRequest(Dog dog) {
        this.dog = dog;
    }
}
