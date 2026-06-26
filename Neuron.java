import java.util.Random;

public class Neuron {
    private double[] weights;
    private double bias;

    // Constructor for giving neurons random numbers (replicating beggining processes)
    // Subtract Random by 0.5 to have negative numbers as well
    // numInputs = amout of data pieces given - therefore, amount of connections for hidden layer neurons
    public Neuron(int numInputs, Random rng) {
        weights = new double[numInputs];

        for (int i = 0; i < numInputs; i++) {
            weights[i] = rng.nextDouble() - 0.5;
        }

        bias = rng.nextDouble() - 0.5; 
        
    }

    // Write Setters and Getters for your instance variables (weights and bias)

    // Hint: Your weights instance variable is an array, 
    // so what do you need to pass into the setter and getter methods to access and individually modify weights?
    
    // setWeight


    // getWeight


    // setBias


    // getBias


     // left off here, let them do everything after line 41, so the for loop and everything else                                  
    public double calculateWeightedSum(double inputs[]) {
        double weightedSum = bias;
        double activationLevel = 0.0;

        for (int i = 0; i < inputs.length; i++) {
            weightedSum += (weights[i] * inputs[i]);
        }

        activationLevel = sigmoid(weightedSum);

        return activationLevel;
        //returns the every number times it's cressponding wieght, + bias
    }

    // Given
    private double sigmoid(double weightedSum) {
        double squishy = 1.0 / (1.0 + Math.exp(-weightedSum));

        return squishy;
    }
}
