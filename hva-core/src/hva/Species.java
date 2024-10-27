package hva;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;

public class Species implements Serializable, Comparable<Species> { // Implement Comparable
    private String _speciesId;
    private String _name;
    private Map<String, Animal> _animalsList = new TreeMap<>();
    
    public Species(String speciesId, String name) {
        _speciesId = speciesId;
        _name = name;
    }

    public String getSpeciesId() {
        return _speciesId;
    }

    public void setSpeciesId(String speciesId) {
        _speciesId = speciesId;
    }

    public String getSpeciesName() {
        return _name;
    }

    public void setSpeciesName(String name) {
        _name = name;
    }
    public void addAnimal(Animal animal) {
        _animalsList.put(animal.getAnimalId(), animal);
    }
    public int getPopulation() {
        return _animalsList.size();
    }
    

    // Implement the compareTo method
    @Override
    public int compareTo(Species other) {
        return this._speciesId.compareTo(other._speciesId); // Compare based on speciesId
    }
}
