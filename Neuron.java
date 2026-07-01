import java.util.Random;

public class Neuron {
    //TODO: Create a double array instance variable called weights
    
    //TODO: Create a double instance variable called bias
   

    // Constructor for giving neurons random numbers (replicating beginning processes)
    // numInputs = amount of data pieces given - therefore, amount of connections for hidden layer neurons
    public Neuron(int numInputs, Random rng) {
        weights = new double[numInputs];

        for (int i = 0; i < numInputs; i++) {
            weights[i] = rng.nextDouble() - 0.5;
        }

        bias = rng.nextDouble() - 0.5; 
        
    }

    // TODO: Write Setters and Getters for your instance variables (weights and bias)

    // Hint: Your weights instance variable is an array, 
    // so what do you need to pass into the setter and getter methods to access and individually modify weights?
    
    // TODO: Implement setWeight method


    // TODO: Implement getWeight method


    // TODO: Implement setBias method


    // TODO: Implement getBias method


     
    // TODO: Implement the calculateWeightedSum method to calculate the weighted sum 
    // of the inputs and return the activation level                                
    public double calculateWeightedSum(double inputs[]) {

        // Iterate through the inputs


        // weighted sum = current weight x current input


        // Whatever variable you have stored after you finished the loop put it through the sigmoid

       
        /* variable = */ sigmoid(/* Variable here*/)

        return ;
    }

    // Given - function that takes a number and reduces to a number between 0 and 1
    private double sigmoid(double weightedSum) {
        double squishy = 1.0 / (1.0 + Math.exp(-weightedSum));

        return squishy;
    }
}
