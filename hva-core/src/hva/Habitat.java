package hva;

import java.util.TreeMap;
import java.util.TreeSet;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Habitat implements Serializable {
    private String _habitatId;
    private String _Name;
    private int _area;
    private Map<String, Tree> _treesInHabitat = new TreeMap<String, Tree>();
    private Map<String, Animal> _animalsInHabitat = new TreeMap<String, Animal>();
    private Map<String, Species> _influencedSpecies = new TreeMap<String, Species>();
    private Map<String, String> _speciesInfluence = new TreeMap<String, String>();

    public Habitat(String habitatId, String name, int area) {
        _habitatId = habitatId;
        _Name = name;
        _area = area;
    }

    public void addTrees(String id, Tree tree) {
        _treesInHabitat.put(id, tree);
    }

    public void addAnimal(String AnimalId, Animal animal) {
        _animalsInHabitat.put(AnimalId, animal);
    }

    public void addSpecies(String SpeciesId, Species specie) {
        if (!_influencedSpecies.containsKey(SpeciesId))
            _influencedSpecies.put(SpeciesId, specie);
    }

    public List<Animal> getAnimalsInHabitat() {
        return new ArrayList<>(_animalsInHabitat.values());
    }

    public Collection<Tree> getTrees() {
        TreeSet<Tree> sortedTrees = new TreeSet<>(Comparator.comparing(Tree::getTreeId));
        sortedTrees.addAll(_treesInHabitat.values());
        return sortedTrees;
    }

    public String getHabitatId() {
        return _habitatId;
    }

    public void setHabitatId(String habitatId) {
        _habitatId = habitatId;
    }

    public String getHabitatName() {
        return _Name;
    }

    public void setHabitatName(String name) {
        _Name = name;
    }

    public int getHabitatArea() {
        return _area;
    }

    public void setHabitatArea(int area) {
        _area = area;
    }

    public int getNumberOfTrees() {
        return _treesInHabitat.size();
    }

    public void setHabitatInfluence(String SpeciesId, String influence) {
        _speciesInfluence.put(SpeciesId, influence);
    }

    public int getHabitatPopulation() {
        return _animalsInHabitat.size();
    }

    public void removeAnimal(String animalId) {
        _animalsInHabitat.remove(animalId);
    }

    public int getAdequation(Species specie) {
        String influence = _speciesInfluence.get(specie.getSpeciesId());

        // Check for null influence
        if (influence == null) {
            // Handle the case where there is no influence for the species
            return 0; // Or whatever default value makes sense in your context
        }

        // Continue with the existing logic if influence is not null
        if (influence.equals("POS")) {
            return 20;
        } else if (influence.equals("NEG")) {
            return -20;
        } else {
            return 0; // Assuming other cases are treated as neutral
        }
    }

    public int iguais(Animal animal) {
        int count = 0;
        String speciesId = animal.getSpeciesId();

        for (Animal otherAnimal : _animalsInHabitat.values()) {

            if (otherAnimal.getSpeciesId().equals(speciesId) && !otherAnimal.equals(animal)) {
                count++;
            }
        }
        return count;
    }

    public int diferentes(Animal animal) {
        int count = 0;
        String speciesId = animal.getSpeciesId();
        for (Animal otherAnimal : _animalsInHabitat.values()) {

            if (!otherAnimal.getSpeciesId().equals(speciesId) && !otherAnimal.equals(animal)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int counter = _treesInHabitat.size() - 1;

        sb.append("HABITAT|")
                .append(_habitatId).append("|")
                .append(_Name).append("|")
                .append(_area).append("|");

        // Check if there are trees in the habitat
        if (_treesInHabitat.isEmpty()) {
            sb.append("0"); // If no trees
        } else {
            sb.append(_treesInHabitat.size()); // Number of trees

            // Convert each Tree to its string representation
            Collection<Tree> trees = getTrees();
            Iterator<Tree> iterator = trees.iterator();
            boolean first = true;

            while (iterator.hasNext()) {
                Tree tree = iterator.next();

                if (first) {
                    sb.append("\n"); // Add a newline only before the first tree
                    first = false;
                }

                sb.append(tree.toString());

                if (iterator.hasNext()) {
                    sb.append("\n"); // Add a newline only if there are more trees
                }
            }
        }

        return sb.toString();
    }

}
