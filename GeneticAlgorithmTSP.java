import java.util.*;

public class GeneticAlgorithmTSP {

    static class Tour {
        List<Integer> cities;
        double fitness;
        double distance;

        // Constructor: Create a tour from a list of cities
        public Tour(List<Integer> cities) {
            this.cities = new ArrayList<>(cities);
            calculateDistance();
            calculateFitness();
        }

        // Calculate total distance L(T) for the tour
        public void calculateDistance() {
            distance = 0;
            for (int i = 0; i < cities.size() - 1; i++) {
                distance += D[cities.get(i)][cities.get(i + 1)];
            }
            distance += D[cities.get(cities.size() - 1)][cities.get(0)]; // add return to start
        }

        // Calculate fitness f(T) = 1 / L(T)
        public void calculateFitness() {
            fitness = 1.0 / distance;
        }

        // Clone the tour (deep copy)
        public Tour clone() {
            return new Tour(new ArrayList<>(cities));
        }
    }

    static int n; // number of cities
    static double[][] D; // distance matrix D[i][j]
    static int populationSize = 100; // number of tours in population
    static double mutationRate = 0.02; // probability of mutation
    static int generations = 500; // number of generations
    static Random rand = new Random(); // random number generator

    public static void main(String[] args) {
        n = 5; // example: 5 cities
        D = generateDistanceMatrix(n); // generate random symmetric distances

        List<Tour> population = initializePopulation(); // initial random population

        // Main genetic algorithm loop
        for (int gen = 0; gen < generations; gen++) {
            population = evolvePopulation(population);
        }

        // Find and print the best tour
        Tour best = findBestTour(population);
        System.out.println("Best tour distance: " + best.distance);
        System.out.println("Tour order: " + best.cities);
    }

    // Create initial population of random tours
    static List<Tour> initializePopulation() {
        List<Tour> population = new ArrayList<>();
        List<Integer> baseTour = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            baseTour.add(i);
        }
        for (int i = 0; i < populationSize; i++) {
            Collections.shuffle(baseTour, rand);
            population.add(new Tour(baseTour));
        }
        return population;
    }

    // Generate next generation
    static List<Tour> evolvePopulation(List<Tour> oldPopulation) {
        List<Tour> newPopulation = new ArrayList<>();

        // Create new tours by selection, crossover, and mutation
        for (int i = 0; i < populationSize; i++) {
            Tour parent1 = selectParent(oldPopulation);
            Tour parent2 = selectParent(oldPopulation);

            Tour child = crossover(parent1, parent2); // crossover to create child
            mutate(child); // mutate child
            child.calculateDistance();
            child.calculateFitness();
            newPopulation.add(child);
        }

        return newPopulation;
    }

    // Select a parent tour using roulette wheel selection
    static Tour selectParent(List<Tour> population) {
        double sumFitness = 0;
        for (Tour t : population) {
            sumFitness += t.fitness;
        }

        double point = rand.nextDouble() * sumFitness;
        double runningSum = 0;
        for (Tour t : population) {
            runningSum += t.fitness;
            if (runningSum >= point) {
                return t;
            }
        }
        return population.get(population.size() - 1); // fallback
    }

    // Order Crossover (OX) between two parents
    static Tour crossover(Tour parent1, Tour parent2) {
        int start = rand.nextInt(n);
        int end = rand.nextInt(n);

        // Ensure start <= end
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        Set<Integer> selectedCities = new HashSet<>();
        List<Integer> childCities = new ArrayList<>(Collections.nCopies(n, -1));

        // Copy a slice from parent1
        for (int i = start; i <= end; i++) {
            int city = parent1.cities.get(i);
            childCities.set(i, city);
            selectedCities.add(city);
        }

        // Fill remaining cities from parent2 in order
        int currentIdx = (end + 1) % n;
        for (int i = 0; i < n; i++) {
            int city = parent2.cities.get((end + 1 + i) % n);
            if (!selectedCities.contains(city)) {
                childCities.set(currentIdx, city);
                currentIdx = (currentIdx + 1) % n;
            }
        }

        return new Tour(childCities);
    }

    // Mutate a tour by swapping two cities
    static void mutate(Tour tour) {
        if (rand.nextDouble() < mutationRate) {
            int i = rand.nextInt(n);
            int j = rand.nextInt(n);
            Collections.swap(tour.cities, i, j);
        }
    }

    // Find the best (shortest) tour in the population
    static Tour findBestTour(List<Tour> population) {
        return population.stream().min(Comparator.comparingDouble(t -> t.distance)).orElse(null);
    }

    // Generate a random symmetric distance matrix D[i][j]
    static double[][] generateDistanceMatrix(int size) {
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                matrix[i][j] = matrix[j][i] = 10 + rand.nextInt(90); // random distance between 10 and 100
            }
        }
        return matrix;
    }
}
