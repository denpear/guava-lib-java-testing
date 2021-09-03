package denpear.javatrain.learn.patterns.builder;

import denpear.javatrain.learn.patterns.immutableobjects.immutable.Animal;
import java.util.List;

public class AnimalBuilder {
    private String species;
    private int age;
    private List<String> favoriteFoods;
    public AnimalBuilder setAge(int age) {
        this.age = age;
        return this;
    }
    public AnimalBuilder setSpecies(String species) {
        this.species = species;
        return this;
    }
    public AnimalBuilder setFavoriteFoods(List<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
        return this;
    }
    public Animal build() {
        return new Animal(species,age,favoriteFoods);
    }
}
