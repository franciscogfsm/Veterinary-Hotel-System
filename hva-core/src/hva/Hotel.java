package hva;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;

import java.util.HashSet;
import java.util.Set;

import hva.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Handler;

import hva.VaccinesRegist;

//import other Java classes
//import project classes

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;
    private Season _currentSeason = Season.PRIMAVERA;

    private Map<String, Species> _species = new HashMap<>();
    private Map<String, Habitat> _habitats = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Animal> _animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Tree> _trees = new HashMap<>();
    private Map<String, Keeper> _keepers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Veterinarian> _veterinarians = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Employees> _employees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private Map<String, Vaccines> _vaccines = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public Season getSeason() {
        return _currentSeason;
    }

    public Season setSeason(Season season) {
        return _currentSeason = season;
    }

    public void updateAges() {
        for (Tree tree : _trees.values()) {
            tree.CheckForAgeChange();
        }
    }

    public void NextSeasonAllExistingTrees() {
        for (Tree tree : _trees.values()) {
            tree.NextSeason();
        }
    }

    public int Advance_Season() {

        switch (_currentSeason) {
            case INVERNO:
                _currentSeason = Season.PRIMAVERA;
                break;
            case PRIMAVERA:
                _currentSeason = Season.VERAO;
                break;
            case VERAO:
                _currentSeason = Season.OUTONO;
                break;
            case OUTONO:
                _currentSeason = Season.INVERNO;
                break;
        }
        updateAges();
        NextSeasonAllExistingTrees();
        if (_currentSeason == Season.PRIMAVERA) {
            return 0;
        } else if (_currentSeason == Season.VERAO) {
            return 1;
        } else if (_currentSeason == Season.OUTONO) {
            return 2;
        } else {
            return 3;
        }
    }

    public String getAnimalSpecieId(String animalId) {
        return _animals.get(animalId).getSpeciesId();
    }

    /**
     * Read text input file and create domain entities.
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    void importFile(String filename) throws ImportFileException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String entryType = parts[0];
                switch (entryType) {
                    case "ANIMAL":
                        animalHandler(parts);
                        break;
                    case "ESPÉCIE":
                        speciesHandler(parts);
                        break;
                    case "HABITAT":
                        habitatHandler(parts);
                        break;
                    case "ÁRVORE":
                        treeHandler(parts);
                        break;
                    case "TRATADOR":
                        keeperHandler(parts);
                        break;
                    case "VETERINÁRIO":
                        veterinarianHandler(parts);
                        break;
                    case "VACINA":
                        vaccineHandler(parts);
                        break;
                    default:
                        throw new UnrecognizedEntryException(entryType);
                }
            }
        } catch (IOException | UnrecognizedEntryException
                | CoreDuplicateAnimalKeyException
                | CoreDuplicateHabitatKeyException
                | CoreDuplicateSpeciesKeyException
                | CoreDuplicateTreeKeyException
                | CoreDuplicateEmployeeKeyException
                | CoreDuplicateVaccineKeyException e) {
            throw new ImportFileException(filename, e);
        }
    }

    /**
     * Handles the process of registering an animal in the system by
     * parsing the necessary fields from the given array and invoking
     * the appropriate registration method.
     *
     * @param parts array of strings containing the details of the animal
     * @throws CoreDuplicateAnimalKeyException if an animal with the
     *                                         same ID already exists
     */
    private void animalHandler(String[] parts)
            throws CoreDuplicateAnimalKeyException {
        String animalId = parts[1];
        String animalName = parts[2];
        String speciesId = parts[3];
        String habitatId = parts[4];
        try {
            registerAnimal(animalId, animalName, speciesId, habitatId);
        } catch (CoreDuplicateAnimalKeyException e) {
            throw new CoreDuplicateAnimalKeyException(animalId);
            // Handle the exception
        } catch (CoreUnknownSpeciesKeyException e) {
        } catch (CoreUnknownHabitatKeyException e) {
        }
    }

    /**
     * Handles the process of registering a habitat in the system by
     * parsing the necessary fields from the given array and invoking
     * the appropriate registration method.
     *
     * @param parts array of strings containing the details of the
     *              habitat
     * @throws CoreDuplicateHabitatKeyException if a habitat with the
     *                                          same ID already exists
     */
    private void habitatHandler(String[] parts)
            throws CoreDuplicateHabitatKeyException {
        String habitatId = parts[1];
        String name = parts[2];
        int area = Integer.parseInt(parts[3]);

        // Check if treeIds are provided and handle them
        String[] treeIds = (parts.length > 4 && !parts[4].isEmpty()) ? parts[4].split(",") : new String[0];

        // Filter out empty or whitespace tree IDs
        treeIds = Arrays.stream(treeIds)
                .filter(treeId -> !treeId.trim().isEmpty())
                .toArray(String[]::new);

        try {
            // Register the habitat with valid treeIds
            registerHabitat(habitatId, name, area, treeIds);
        } catch (CoreDuplicateHabitatKeyException e) {
            throw new CoreDuplicateHabitatKeyException(habitatId);
        }
    }

    /**
     * Handles the process of registering a species in the system by
     * parsing the necessary fields from the given array and invoking
     * the appropriate registration method.
     *
     * @param parts array of strings containing the details of the species
     * @throws CoreDuplicateSpecieKeyException if a species with the
     *                                         same ID already exists
     */
    private void speciesHandler(String[] parts)
            throws CoreDuplicateSpeciesKeyException {
        String speciesId = parts[1];
        String name = parts[2];
        try {
            registerSpecies(speciesId, name);
        } catch (CoreDuplicateSpeciesKeyException e) {
            throw new CoreDuplicateSpeciesKeyException();
        }

    }

    /**
     * Handles the process of registering a tree in the system by
     * parsing the necessary fields from the given array and invoking
     * the appropriate registration method.
     *
     * @param parts array of strings containing the details of the tree
     * @throws CoreDuplicateTreeKeyException if a tree with the same
     *                                       ID already exists
     */
    private void treeHandler(String[] parts)
            throws CoreDuplicateTreeKeyException {
        String treeId = parts[1];
        String name = parts[2];
        int age = Integer.parseInt(parts[3]);
        int difficulty = Integer.parseInt(parts[4]);
        String type = parts[5];
        try {
            registryTree(treeId, name, age, difficulty, type, "");
        } catch (CoreDuplicateTreeKeyException e) {
            throw new CoreDuplicateTreeKeyException(treeId);
        }
    }

    /**
     * Handles the registration of a keeper (employee) in the system.
     * It extracts the necessary keeper information from the provided
     * parts array, including their ID, name, and associated habitats.
     *
     * @param parts an array of strings containing the details of the
     *              keeper
     * @throws CoreDuplicateEmployeeKeyException if a keeper with the
     *                                           same ID already exists
     */
    private void keeperHandler(String[] parts)
            throws CoreDuplicateEmployeeKeyException {
        String keeperId = parts[1];
        String name = parts[2];
        String[] habitatIds = parts.length > 3 ? parts[3].split(",") : new String[0];

        try {
            registerKeeper(keeperId, name, habitatIds);
        } catch (CoreDuplicateEmployeeKeyException e) {
            throw new CoreDuplicateEmployeeKeyException(keeperId);
        }
    }

    /**
     * Handles the registration of a veterinarian in the system.
     * It extracts the veterinarian's ID, name, and the list of species
     * they specialize in from the input array.
     *
     * @param parts an array of strings containing the details of the
     *              veterinarian
     * @throws CoreDuplicateEmployeeKeyException if a veterinarian with
     *                                           the same ID already exists
     */
    public void veterinarianHandler(String[] parts)
            throws CoreDuplicateEmployeeKeyException {
        String veterinarianId = parts[1];
        String name = parts[2];
        String[] speciesIds = parts.length > 3 ? parts[3].split(",") : new String[0];

        try {
            registerVeterinarian(veterinarianId, name, speciesIds);
        } catch (CoreDuplicateEmployeeKeyException e) {
            throw new CoreDuplicateEmployeeKeyException(veterinarianId);
        }
    }

    /**
     * Handles the registration of a vaccine in the system.
     * It extracts the vaccine's ID, name, and the list of species that
     * can receive the vaccine from the input array.
     *
     * @param parts an array of strings containing the details of the
     *              vaccine
     * @throws CoreDuplicateVaccineKeyException if a vaccine with the
     *                                          same ID already exists
     */
    public void vaccineHandler(String[] parts)
            throws CoreDuplicateVaccineKeyException {
        String vaccineId = parts[1];
        String name = parts[2];
        String[] speciesIds = parts.length > 3 ? parts[3].split(",") : new String[0];

        try {
            registerVaccine(vaccineId, name, speciesIds);
        } catch (CoreDuplicateVaccineKeyException e) {
            throw new CoreDuplicateVaccineKeyException(vaccineId);
        } catch (CoreUnknownSpeciesKeyException e) {
        }
    }

    /**
     * Registers a new animal in the system.
     *
     * @param animalId   the unique identifier for the animal
     * @param animalName the name of the animal
     * @param speciesId  the ID of the species the animal belongs to
     * @param habitatId  the ID of the habitat where the animal resides
     * @throws CoreDuplicateAnimalKeyException if an animal with the
     *                                         same ID already exists
     */
    public void registerAnimal(String animalId, String animalName,
            String speciesId, String habitatId)
            throws CoreDuplicateAnimalKeyException, CoreUnknownSpeciesKeyException, CoreUnknownHabitatKeyException {
        // Caso já exitir o animal
        Animal animal = _animals.get(animalId);
        if (animal != null) {
            throw new CoreDuplicateAnimalKeyException(animalId);
        }

        if (speciesId == null || speciesId.isEmpty() || _species.get(speciesId) == null) {
            throw new CoreUnknownSpeciesKeyException(speciesId);
        }
        if (habitatId == null || habitatId.isEmpty() || _habitats.get(habitatId) == null) {
            throw new CoreUnknownHabitatKeyException(habitatId);
        }

        animal = new Animal(animalId, animalName, speciesId, habitatId);
        _animals.put(animalId, animal);
        Species specie = _species.get(speciesId);
        specie.addAnimal(animal);

        Habitat habitat = _habitats.get(habitatId);
        habitat.addAnimal(animalId, animal);

    }

    /**
     * Registers a new species in the system.
     *
     * @param speciesId the unique identifier for the species
     * @param name      the name of the species
     * @throws CoreDuplicateSpecieKeyException if a species with the
     *                                         same ID already exists
     */
    public void registerSpecies(String speciesId, String name)
            throws CoreDuplicateSpeciesKeyException {
        // Caso já exitir a espécie
        Species species = _species.get(speciesId);
        if (species != null) {
            throw new CoreDuplicateSpeciesKeyException();

        }
        species = new Species(speciesId, name);
        _species.put(speciesId, species);
    }

    /**
     * Registers a new habitat in the system.
     * This method also allows associating trees with the habitat.
     *
     * @param habitatId the unique identifier for the habitat
     * @param name      the name of the habitat
     * @param area      the area of the habitat
     * @param treeIds   an array of tree IDs associated with the habitat
     * @throws CoreDuplicateHabitatKeyException if a habitat with the
     *                                          same ID already exists
     */
    public void registerHabitat(String habitatId, String name,
            int area, String[] treeIds)
            throws CoreDuplicateHabitatKeyException {
        Habitat habitat = _habitats.get(habitatId);
        if (habitat != null) {
            throw new CoreDuplicateHabitatKeyException(habitatId);
        }
        if (treeIds.length == 0) {
            habitat = new Habitat(habitatId, name, area);

        } else {
            habitat = new Habitat(habitatId, name, area);
            for (int i = 0; i < treeIds.length; i++) {
                habitat.addTrees(treeIds[i], _trees.get(treeIds[i]));
            }
        }
        _habitats.put(habitatId, habitat);
    }

    /**
     * Registers a new tree in the system.
     *
     * @param treeId     the unique identifier for the tree
     * @param name       the name of the tree
     * @param age        the age of the tree in years
     * @param difficulty the difficulty level of caring for the tree
     * @param type       the type of the tree
     * @throws CoreDuplicateTreeKeyException if a tree with the same
     *                                       ID already exists
     */
    public void registryTree(String treeId, String name, int age,
            int difficulty, String type, String biologicalCicle)
            throws CoreDuplicateTreeKeyException {
        Tree tree = _trees.get(treeId);
        if (tree != null) {
            throw new CoreDuplicateTreeKeyException(treeId);
        }
        TreeStateMachine stateMachine = new TreeStateMachine(_currentSeason);

        if (type.equals("PERENE")) {
            stateMachine.setTreeType(TreeType.PERENE);
        } else if (type.equals("CADUCA")) {
            stateMachine.setTreeType(TreeType.CADUCA);
        }

        if (type.equals("PERENE")) {
            tree = new Perene(treeId, name, age, difficulty, stateMachine);
        } else {
            tree = new Caduca(treeId, name, age, difficulty, stateMachine);

        }
        _trees.put(treeId, tree);
    }

    /**
     * Registers a new keeper in the system.
     * If no habitat IDs are provided, a default habitat ID of "TRT"
     * is assigned.
     *
     * @param keeperId   the unique identifier for the keeper
     * @param name       the name of the keeper
     * @param habitatIds an array of habitat IDs the keeper is
     *                   responsible for
     * @throws CoreDuplicateEmployeeKeyException if a keeper with the
     *                                           same ID already exists
     */
    public void registerKeeper(String keeperId, String name,
            String[] habitatIds)
            throws CoreDuplicateEmployeeKeyException {
        Keeper keeper = _keepers.get(keeperId);
        if (keeper != null) {
            throw new CoreDuplicateEmployeeKeyException(keeperId);
        }
        if (habitatIds.length == 0) {
            keeper = new Keeper(keeperId, name, "TRT");

        } else {
            keeper = new Keeper(keeperId, name, "TRT");
            for (int i = 0; i < habitatIds.length; i++) {
                keeper.addHabitat(habitatIds[i], _habitats.get(habitatIds[i]));
            }
        }
        _keepers.put(keeperId, keeper);
        _employees.put(keeperId, keeper);

    }

    /**
     * Registers a new veterinarian in the system.
     * If no species IDs are provided, a default species ID of "VET"
     * is assigned.
     *
     * @param veterinarianId the unique identifier for the veterinarian
     * @param name           the name of the veterinarian
     * @param speciesIds     an array of species IDs that the veterinarian
     *                       is responsible for
     * @throws CoreDuplicateEmployeeKeyException if a veterinarian with
     *                                           the same ID already exists
     */
    public void registerVeterinarian(String veterinarianId, String name,
            String[] speciesIds)
            throws CoreDuplicateEmployeeKeyException {

        Veterinarian veterinarian = _veterinarians.get(veterinarianId);
        if (veterinarian != null) {
            throw new CoreDuplicateEmployeeKeyException(veterinarianId);
        }
        if (speciesIds.length == 0) {
            veterinarian = new Veterinarian(veterinarianId, name, "VET");

        } else {
            veterinarian = new Veterinarian(veterinarianId, name, "VET");
            for (int i = 0; i < speciesIds.length; i++) {
                veterinarian.addSpecies(speciesIds[i], _species.get(speciesIds[i]));
            }
        }
        _veterinarians.put(veterinarianId, veterinarian);
        _employees.put(veterinarianId, veterinarian);
    }

    /**
     * Registers a new vaccine in the system.
     * If no species IDs are provided, the vaccine is registered
     * without any species association.
     *
     * @param vaccineId  the unique identifier for the vaccine
     * @param name       the name of the vaccine
     * @param speciesIds an array of species IDs that the vaccine is
     *                   applicable to
     * @throws CoreDuplicateVaccineKeyException if a vaccine with the
     *                                          same ID already exists
     */
    // Ver cena das maisculas e minusculas
    public void registerVaccine(String vaccineId, String name,
            String[] speciesIds)
            throws CoreDuplicateVaccineKeyException, CoreUnknownSpeciesKeyException {

        Vaccines vaccine = _vaccines.get(vaccineId);
        if (vaccine != null) {
            throw new CoreDuplicateVaccineKeyException(vaccineId);
        }
        if (speciesIds.length == 0) {
            vaccine = new Vaccines(vaccineId, name);
        } else {
            vaccine = new Vaccines(vaccineId, name);
            for (int i = 0; i < speciesIds.length; i++) {
                if (_species.get(speciesIds[i]) == null) {
                    throw new CoreUnknownSpeciesKeyException(speciesIds[i]);
                }
                vaccine.addSpecies(_species.get(speciesIds[i]));
            }
        }
        _vaccines.put(vaccineId, vaccine);
    }

    /**
     * Retrieves a collection of all habitats in the system.
     * 
     * @return a collection containing all Habitat objects.
     */
    public Collection<Habitat> habitats() {
        return _habitats.values();
    }

    /**
     * Retrieves a collection of all animals in the system.
     * 
     * @return a collection containing all Animal objects.
     */
    public Collection<Animal> animals() {
        return _animals.values();
    }

    public int animalsNumber() {
        return _animals.size();
    }

    /**
     * Retrieves a collection of all employees in the system.
     * 
     * @return a collection containing all Employee objects.
     */
    public Collection<Employees> employees() {
        return _employees.values();
    }

    /**
     * Retrieves a collection of all vaccines in the system.
     * 
     * @return a collection containing all Vaccine objects.
     */
    public Collection<Vaccines> vaccines() {
        return _vaccines.values();
    }

    public Collection<Tree> trees() {
        return _trees.values();
    }

    public String treesInHabitat(String habitatId) throws CoreUnknownHabitatKeyException {
        if (!_habitats.containsKey(habitatId)) {
            throw new CoreUnknownHabitatKeyException(habitatId);
        }

        Habitat habitat = _habitats.get(habitatId);
        Collection<Tree> trees = habitat.getTrees();

        // StringBuilder to build the output string
        StringBuilder result = new StringBuilder();

        for (Tree tree : trees) {
            result.append(tree.toString()).append("\n"); // Append each tree and a newline
        }

        return result.toString().trim(); // Trim to remove the trailing newline
    }

    public String getEmployeeType(String employeeId) {
        return _employees.get(employeeId).getType();

    }

    public void registerEmployee(String employeeId, String name, String type) throws CoreDuplicateEmployeeKeyException {

        if (type.equals("TRT")) {
            try {
                registerKeeper(employeeId, name, new String[0]);
            } catch (CoreDuplicateEmployeeKeyException e) {
                throw new CoreDuplicateEmployeeKeyException(employeeId);
            }
        }
        if (type.equals("VET")) {
            try {
                registerVeterinarian(employeeId, name, new String[0]);
            } catch (CoreDuplicateEmployeeKeyException e) {
                throw new CoreDuplicateEmployeeKeyException(employeeId);
            }
        }

    }

    public String AppRegistryTree(String habitatId, String treeId, String name, int age,
            int difficulty, String type)
            throws CoreDuplicateTreeKeyException, CoreUnknownHabitatKeyException {
        Tree tree = _trees.get(treeId);
        if (tree != null) {
            throw new CoreDuplicateTreeKeyException(treeId);
        }
        if (!_habitats.containsKey(habitatId)) {
            throw new CoreUnknownHabitatKeyException(habitatId);
        }
        TreeStateMachine stateMachine = new TreeStateMachine(_currentSeason);

        if (type.equals("PERENE")) {
            stateMachine.setTreeType(TreeType.PERENE);
        } else if (type.equals("CADUCA")) {
            stateMachine.setTreeType(TreeType.CADUCA);
        }

        if (type.equals("PERENE")) {
            tree = new Perene(treeId, name, age, difficulty, stateMachine);
        } else {
            tree = new Caduca(treeId, name, age, difficulty, stateMachine);
        }
        Habitat habitat = _habitats.get(habitatId);
        habitat.addTrees(treeId, tree);
        _trees.put(treeId, tree);
        return "Árvore|" + treeId + "|" + name + "|" + age + "|" + difficulty + "|" + type + "|"
                + stateMachine.getStateAction();
    }

    public void changeHabitatArea(String HabitatId, int area) throws CoreUnknownHabitatKeyException {
        Habitat habitat = _habitats.get(HabitatId);
        if (habitat == null) {
            // Lança a exceção se o habitat não for encontrado
            throw new CoreUnknownHabitatKeyException(HabitatId);
        }
        habitat.setHabitatArea(area);
    }

    // Bug: Ainda nao sei se isto tá bem depois temos de verificar
    public void changeHabitatInfluence(String HabitatId, String SpecieId, String influence)
            throws CoreUnknownHabitatKeyException, CoreUnknownSpeciesKeyException {
        if (!_habitats.containsKey(HabitatId)) {
            throw new CoreUnknownHabitatKeyException(HabitatId);
        }
        if (!_species.containsKey(SpecieId)) {
            throw new CoreUnknownSpeciesKeyException(HabitatId);
        }
        Habitat habitat = _habitats.get(HabitatId);
        habitat.setHabitatInfluence(SpecieId, influence);
    }

    public boolean ValidEmployeeType(String type) {
        if (type.equals("VET") || type.equals("TRT")) {
            return true;
        }
        return false;
    }

    public boolean ValidTreeType(String type) {
        if (type.equals("CADUCA") || type.equals("PERENE")) {
            return true;
        }
        return false;
    }

    public boolean ValidHabitatInfluence(String influence) {
        if (influence.equals("POS") || influence.equals("NEG") || influence.equals("NEU")) {
            return true;
        }
        return false;
    }

    // Se quando adiconas um animal també adicionas a sua especie a lista de
    // especies to habitat eu assumo que sim
    public void transferAnimalToHabitat(String animalId, String habitatId) throws CoreUnknownAnimalKeyException,
            CoreUnknownHabitatKeyException {
        if (!_animals.containsKey(animalId)) {
            throw new CoreUnknownAnimalKeyException(animalId);
        }
        if (!_habitats.containsKey(habitatId)) {
            throw new CoreUnknownHabitatKeyException(habitatId);
        }
        Animal animal = _animals.get(animalId);
        Habitat antigo = _habitats.get(animal.getHabitatId());
        antigo.removeAnimal(animalId);
        animal.setHabitatId(habitatId);
        Habitat habitat = _habitats.get(habitatId);
        habitat.addAnimal(animalId, animal);

    }

    public int CalculateSatisfaction(String animalId) throws CoreUnknownAnimalKeyException {
        double satisfaction = 0;
        if (!_animals.containsKey(animalId)) {
            throw new CoreUnknownAnimalKeyException(animalId);
        }

        Animal animal = _animals.get(animalId);
        if (animal.getHabitatId() == null) {
            return 20;
        }

        Habitat habitatOfAnimal = _habitats.get(animal.getHabitatId());
        int area = habitatOfAnimal.getHabitatArea();
        int population = habitatOfAnimal.getHabitatPopulation();

        Species species = _species.get(animal.getSpeciesId());

        satisfaction = 20
                + 3 * habitatOfAnimal.iguais(animal)
                - 2 * habitatOfAnimal.diferentes(animal)
                + (area / (double) population)
                + habitatOfAnimal.getAdequation(species);

        return (int) Math.round(satisfaction);
    }

    public void AtributeResponsibility(String employeeId, String id)
            throws CoreNoResponsibilityException, CoreUnknownEmployeeKeyException {
        if (!_employees.containsKey(employeeId)) {
            throw new CoreUnknownEmployeeKeyException(employeeId);
        }
        if (getEmployeeType(employeeId).equals("TRT")) {
            Keeper keeper = _keepers.get(employeeId);
            if (!_habitats.containsKey(id)) {
                throw new CoreNoResponsibilityException(employeeId, id);
            }
            keeper.addHabitat(id, _habitats.get(id));
        } else if (getEmployeeType(employeeId).equals("VET")) {
            Veterinarian veterinarian = _veterinarians.get(employeeId);
            if (!_species.containsKey(id)) {
                throw new CoreNoResponsibilityException(employeeId, id);
            }
            veterinarian.addSpecies(id, _species.get(id));
        }
    }

    public void RemoveResponsibility(String employeeId, String id) throws CoreNoResponsibilityException {
        if (getEmployeeType(employeeId).equals("TRT")) {
            Keeper keeper = _keepers.get(employeeId);
            if (!_habitats.containsKey(id) || !keeper.hasHabitat(id)) {
                throw new CoreNoResponsibilityException(employeeId, id);
            }
            keeper.removeHabitat(id);
        } else if (getEmployeeType(employeeId).equals("VET")) {
            Veterinarian veterinarian = _veterinarians.get(employeeId);
            if (!_species.containsKey(id) || !veterinarian.hasSpecies(id)) {
                throw new CoreNoResponsibilityException(employeeId, id);
            }
            veterinarian.removeSpecies(id);
        }

    }

    public boolean vaccinate(String VacineId, String VeterinarianId, String animalId)
            throws CoreUnknownVeterinarianKeyException, CoreVeterinarianNotAuthorizedException,
            CoreUnknownVaccineKeyException {

        if (_veterinarians.get(VeterinarianId) == null) {
            throw new CoreUnknownVeterinarianKeyException(VeterinarianId);
        }
        Veterinarian veterinarian = _veterinarians.get(VeterinarianId);
        Animal animal = _animals.get(animalId);
        Vaccines vacine = _vaccines.get(VacineId);

        if (!veterinarian.hasSpecies(animal.getSpeciesId())) {
            throw new CoreVeterinarianNotAuthorizedException(VeterinarianId, animal.getSpeciesId());
        }
        if (_vaccines.get(VacineId) == null) {
            throw new CoreUnknownVaccineKeyException(VacineId);
        }

        int finalDamage = Damage(_vaccines.get(VacineId), VeterinarianId, animal);

        if (finalDamage == 0) {
            if (veterinarian.hasSpecies(animal.getSpeciesId())
                    && vacine.hasSpecies(_species.get(animal.getSpeciesId()))) {
                animal.addToHealthState("NORMAL");
                VaccinesRegist regist = new VaccinesRegist(VeterinarianId, animalId);
                _vaccines.get(VacineId).addRegists(regist);
                return true;
            } else {
                animal.addToHealthState("CONFUSÃO");
                VaccinesRegist regist = new VaccinesRegist(VeterinarianId, animalId);
                _vaccines.get(VacineId).addRegists(regist);
                return false;
            }
        } else if (finalDamage >= 1 && finalDamage <= 4) {
            animal.addToHealthState("ACIDENTE");
            VaccinesRegist regist = new VaccinesRegist(VeterinarianId, animalId);
            _vaccines.get(VacineId).addRegists(regist);
            return false;

        } else if (finalDamage >= 5) {
            animal.addToHealthState("ERRO");
            VaccinesRegist regist = new VaccinesRegist(VeterinarianId, animalId);
            _vaccines.get(VacineId).addRegists(regist);
            return false;
        }
        // Add to vacination history

        return true;
    }

    public int Damage(Vaccines vaccine, String VeterinarianId, Animal animal) {
        int damage = 1000;
        Species species = _species.get(animal.getSpeciesId());
        String specie1 = species.getSpeciesName();

        if (vaccine.getSpecies().isEmpty()) {
            return 0;
        }

        for (Species specie : vaccine.getSpecies()) {
            String specie2 = specie.getSpeciesName();
            int tamanhoNomes = Math.max(specie1.length(), specie2.length());
            int caracteresComuns = countCommonCharacters(specie1, specie2);
            int currentDamage = tamanhoNomes - caracteresComuns;
            if (currentDamage < damage) {
                damage = currentDamage;
            }
        }
        return damage;
    }

    public int countCommonCharacters(String specie1, String specie2) {

        Set<Character> setSpecies1 = new HashSet<>();
        Set<Character> setSpecies2 = new HashSet<>();

        for (char c : specie1.toCharArray()) {
            setSpecies1.add(c);
        }
        for (char c : specie2.toCharArray()) {
            setSpecies2.add(c);
        }
        setSpecies1.retainAll(setSpecies2);

        return setSpecies1.size();
    }

    // REGISTO-VACINA|idVacina|idVeterinário|idEspécie
    public String VacinationHistory() {
        StringBuilder history = new StringBuilder();
        for (Vaccines vaccine : _vaccines.values()) {
            for (VaccinesRegist regist : vaccine.getVaccinesAplicationHistory()) {
                String formatted = "REGISTO-VACINA|" + vaccine.getVaccineId() + "|" + regist.getEmployeeId() + "|"
                        + _animals.get(regist.getAnimalId()).getSpeciesId();
                history.append(formatted).append("\n");
            }
        }
        // Tirar o ultimo \n
        if (history.length() > 0) {
            history.setLength(history.length() - 1);
        }
        return history.toString();
    }

    public String showAnimalsInHabitat(String habitatId) throws CoreUnknownHabitatKeyException {
        if (!_habitats.containsKey(habitatId)) {
            throw new CoreUnknownHabitatKeyException(habitatId);
        }
        StringBuilder animals = new StringBuilder();
        Habitat habitat = _habitats.get(habitatId);
        for (Animal animal : habitat.getAnimalsInHabitat()) {
            animals.append(animal.toString()).append("\n");
        }
        return animals.toString();
    }

    public String showNonVacinatedAnimals(String animalId){
        StringBuilder FormattedString = new StringBuilder();
        
        for (Animal animal : _animals.values()) {
            if (animal.getHealthState().equals("")) {
                FormattedString.append(animal.toString()).append("\n");
            }
        }
        return FormattedString.toString();
    }

    public String showHabitatsNonTrees(String habitatID) {
        StringBuilder FormattedString = new StringBuilder();
        for (Habitat habitat : _habitats.values()) {
            if (habitat.getNumberOfTrees() == 0) {
                FormattedString.append(habitat.toString()).append("\n");
            }
        }
        return FormattedString.toString();
    }

    public String ShowMedicalActsByVeterinarian(String vetId) throws CoreUnknownVeterinarianKeyException {
        if (!_veterinarians.containsKey(vetId)) {
            throw new CoreUnknownVeterinarianKeyException(vetId);
        }
        StringBuilder acts = new StringBuilder();
        for (Vaccines vaccine : _vaccines.values()) {
            for (VaccinesRegist regist : vaccine.getVaccinesAplicationHistory()) {
                if (regist.getEmployeeId().equals(vetId)) {
                    String formatted = "REGISTO-VACINA|" + vaccine.getVaccineId() + "|" + regist.getEmployeeId() + "|"
                            + _animals.get(regist.getAnimalId()).getSpeciesId();
                    acts.append(formatted).append("\n");
                }
            }
        }
        if (acts.length() > 0) {
            acts.setLength(acts.length() - 1);
        }
        return acts.toString();
    }

    public String ShowMedicalActsOnAnimal(String animalId) throws CoreUnknownAnimalKeyException {
        if (!_animals.containsKey(animalId)) {
            throw new CoreUnknownAnimalKeyException(animalId);
        }
        StringBuilder acts = new StringBuilder();
        for (Vaccines vaccine : _vaccines.values()) {
            for (VaccinesRegist regist : vaccine.getVaccinesAplicationHistory()) {
                if (regist.getAnimalId().equals(animalId)) {
                    String formatted = "REGISTO-VACINA|" + vaccine.getVaccineId() + "|" + regist.getEmployeeId() + "|"
                            + _animals.get(regist.getAnimalId()).getSpeciesId();
                    acts.append(formatted).append("\n");
                }
            }
        }
        if (acts.length() > 0) {
            acts.setLength(acts.length() - 1);
        }
        return acts.toString();
    }

    public String showWrongVaccinations() {
        StringBuilder wrongVaccinations = new StringBuilder();
        for (Vaccines vaccine : _vaccines.values()) {
            for (VaccinesRegist regist : vaccine.getVaccinesAplicationHistory()) {
                if (!_animals.get(regist.getAnimalId()).getHealthState().equals("NORMAL")) {
                    String formatted = "REGISTO-VACINA|" + vaccine.getVaccineId() + "|" + regist.getEmployeeId() + "|"
                            + _animals.get(regist.getAnimalId()).getSpeciesId();
                    wrongVaccinations.append(formatted).append("\n");
                }
            }
        }
        if (wrongVaccinations.length() > 0) {
            wrongVaccinations.setLength(wrongVaccinations.length() - 1);
        }
        return wrongVaccinations.toString();
    }

    public int calculateSatisfactionOfEmployee(String employeeId) throws CoreUnknownEmployeeKeyException {
        Employees employee = _employees.get(employeeId);

        if (employee == null) {
            throw new CoreUnknownEmployeeKeyException(employeeId);
        }

        if (employee instanceof Veterinarian) {
            Veterinarian vet = (Veterinarian) employee;
            Map<String, Species> _speciesVeterinarians = vet.getVetspecies();
            return calculateVeterinarianSatisfaction(vet, _speciesVeterinarians);
        } else if (employee instanceof Keeper) {
            Keeper keeper = (Keeper) employee;
            Map<String, Habitat> _habitatsKeepers = keeper.getKeeperHabitats();
            return calculateKeeperSatisfaction(keeper, _habitatsKeepers);
        }
        return 0;
    }

    private int calculateVeterinarianSatisfaction(Veterinarian vet, Map<String, Species> _speciesVeterinarians) {
        double totalWorkload = 0;
        int numOFVeterinarians = 0;
        int population = 0;// Populacao

        for (String speciesId : vet.getSpeciesIds().split(",")) {

            Species species = _speciesVeterinarians.get(speciesId);
            if (_speciesVeterinarians.size() > 0) {
                population += species.getPopulation();
            }
        }
        int numVeterinarians = getVeterinarianCountForSpecies(vet, _veterinarians);
        numOFVeterinarians += numVeterinarians;

        if (numOFVeterinarians != 0) {
            totalWorkload += (population / (double) numOFVeterinarians);

        } else {
            totalWorkload += population;
        }

        double satisfaction = 20 - totalWorkload;

        vet.setSatisfaction((int) Math.round(satisfaction));

        return vet.getSatisfaction();
    }

    public static int getVeterinarianCountForSpecies(Veterinarian vet, Map<String, Veterinarian> veterinarians) {
        int count = 0;
        Set<String> speciesIds = new HashSet<>(Arrays.asList(vet.getSpeciesIds().split(",")));

        for (Veterinarian vet1 : veterinarians.values()) {
            Set<String> vet1SpeciesIds = new HashSet<>(Arrays.asList(vet1.getSpeciesIds().split(",")));
            vet1SpeciesIds.retainAll(speciesIds);
            if (!vet1SpeciesIds.isEmpty()) {
                count++;
            }

        }
        return count;
    }

    public static int getKeeperCountForHabitats(Habitat habitat, Map<String, Keeper> keepers) {
        int count = 0;
        Set<String> habitat1 = new HashSet<>(Arrays.asList(habitat.getHabitatId()));

        for (Keeper keeper1 : keepers.values()) {
            Set<String> keeper1HabitatIds = new HashSet<>(Arrays.asList(keeper1.getHabitatIds().split(",")));
            habitat1.retainAll(keeper1HabitatIds);
            if (!habitat1.isEmpty()) {
                count++;
            }
            habitat1 = new HashSet<>(Arrays.asList(habitat.getHabitatId()));
        }
        return count;
    }

    private int calculateKeeperSatisfaction(Keeper keeper, Map<String, Habitat> _habitatsOfKeeper) {
        int area = 0;// Populacao
        int population = 0;
        int clean = 0;
        double Finalwork = 0;
        // Work in habitat

        for (Habitat habitat : keeper.getAssignedHabitats()) {
            int work = 0;
            area = habitat.getHabitatArea();
            population = 3 * habitat.getHabitatPopulation();
            for (Tree tree : habitat.getTrees()) {
                clean += cleanigEffort(tree);
            }
            int numKeepers = getKeeperCountForHabitats(habitat, _keepers);
            work = area + population + clean;
            Finalwork += work / numKeepers;
        }

        double satisfaction = 300 - Finalwork;

        return (int) Math.round(satisfaction);
    }

    // esforço_limpeza(a) = dificuldade_limpeza(a) * esforço_sazonal(a) *
    // log(idade(a) + 1)
    public double cleanigEffort(Tree tree) {
        return tree.getDifficulty() * tree.getCleaningEffort() * Math.log(tree.getTreeAge() + 1);
    }

    public int getGlobalSatisfaction() throws CoreUnknownEmployeeKeyException, CoreUnknownAnimalKeyException {
        int totalSatisfaction = 0;

        if (_employees.isEmpty() && _animals.isEmpty() && _habitats.isEmpty() && _species.isEmpty() && _trees.isEmpty()
                && _vaccines.isEmpty() && _keepers.isEmpty() && _veterinarians.isEmpty()) {
            return 0;
        }

        for (Employees employee : _employees.values()) {
            totalSatisfaction += calculateSatisfactionOfEmployee(employee.getEmployeeId());
        }
        for (Animal animals : _animals.values()) {
            totalSatisfaction += CalculateSatisfaction(animals.getAnimalId());
        }

        return totalSatisfaction;
    }

} // área(h) + 3 * população(h) + Σ esforço_limpeza(a)
