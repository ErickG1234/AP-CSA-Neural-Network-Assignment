// Neural Network Visualization:

// Input   Hidden
// Level   Layer

//  ⚪----⚪
//   \    / \
//    \  /   \
//     \/    ⚪  --> Output
//     /\     |
//    /  \   /
//   /    \ /
// ⚪-----⚪    

import java.util.Random;
public class NeuralNetwork {
    private Neuron[] hiddenLayer;
    private Neuron output;
    private double learningRate;
    private double[] activationNums = new double[2];
 
    // 1) Random numbers for weights and bias - for when model actually works efficiently
    public NeuralNetwork(double userLearningRate) {
        learningRate = userLearningRate;
        Random randomNum = new Random();
        
        hiddenLayer = new Neuron[2];
        hiddenLayer[0] = new Neuron(2, randomNum);
        hiddenLayer[1] = new Neuron(2, randomNum);
        output = new Neuron(2, randomNum);
    }

    public void setlearningRate(double input) {
        learningRate = input;
    }

    public double getLearningRate() {
        return learningRate;
    }


    // TODO: Implement the predict method to make a prediction based on the input data
    // In this method, we are trying to find a activation level for our neurons, 
    // which is basically a guess or prediction based off our input
    public double predict(double[] inputs) {

        // Iterate through the Neurons in the Hidden Layer
        
        
            /* What instance variable should store te activation values?*/ 
            // find activation level by calling the caluclateWeightSum method on our current hidden neuron

            // To find the actual activation level,
            // call the calculateWeightedSum function on the output neuron and store that in a variable then return it
        
        
        double activationLevel = output.calculateWeightedSum(activationNums);
        
        return activationLevel;
    }

    // Start Train method here - "black box" math - given 
    // Looks at dataset various times (for multiple rounds (full loop), called Epochs)
    // make a prediction for each piece of data, 
    // caluclate how wrong the prediction was compared to the target
    // apply adjustemnt calculus

    public void train(double[][] trainingData, double[] targets, int epochs) {
        // Used to store the current student's information, the expected 
        // outcome (target), and the network's actual guess.
        double prediction = 0.0;
        double[] currentDataRow;
        double currentTarget;

        // Calculates how wrong the guess was and determines the "slope" 
        // (gradient) so the output neuron knows which way to adjust.
        double error = 0.0;
        double outputGradient = 0.0;

        // Temporarily stores the newly calculated adjustments for the 
        // final output neuron before applying them via setter methods.
        double newWeight1 = 0.0;
        double newWeight2 = 0.0;
        double newBias = 0.0;

        // Calculates how much of the output mistake was caused by 
        // each of the two neurons in the hidden layer.
        double hiddenNeuronGradient0 = 0.0;
        double hiddenNeuronGradient1 = 0.0;

        // Temporarily stores the new weights and bias for the FIRST 
        // neuron in the hidden layer.
        double updateHiddenNeuron0weight0 = 0.0;
        double updateHiddenNeuron0weight1 = 0.0;
        double newHLBias0 = 0.0;

        // Temporarily stores the new weights and bias for the SECOND 
        // neuron in the hidden layer.
        double updateHiddenNeuron1weight0 = 0.0;
        double updateHiddenNeuron1weight1 = 0.0;
        double newHLBias1 = 0.0;



        // One full epoch goes through all the data once (outer loop)
        for (int epochCounter = 0; epochCounter < epochs; epochCounter++) { // i
            for (int studentCounter = 0; studentCounter < trainingData.length ; studentCounter++) { // j
                currentDataRow = trainingData[studentCounter];

                 // 1) make a prediction for each piece of data, 
                prediction = predict(currentDataRow);

                // 2) Calculate Error (compare prediction and target)
                currentTarget = targets[studentCounter];
                error = currentTarget - prediction;

                // 3) BackPropagation - fixing prediction backwards
                // To turn the raw error into an actual adjustment, 
                // the network has to multiply the error by the 
                // "slope" (the derivative) of the activation function used in the neurons.
                // Gradient: multi-dimensional word for slope - tells you how steep the incline is

                // Output neuron knows how much "blame" it carries for the mistake
                outputGradient = error * sigmoidDerivative(prediction);

                // 4) Weight Adjustment
                // New Weight = Old Weight + (Learning Rate * Gradient * The Input that fed into this weight)
                // only "input" the output neuron ever receives is the activationNums (hideenLayer) from Phase 1
                // Update both weights!!
                newWeight1 = output.getWeight(0) + (learningRate * outputGradient * activationNums[0]);
                newWeight2 = output.getWeight(1)  + (learningRate * outputGradient * activationNums[1]);
                output.setWeight(0, newWeight1);
                output.setWeight(1, newWeight2);

                // Update Bias
                newBias = output.getBias() + (learningRate * outputGradient);
                output.setBias(newBias);

                // Training Hidden Layer
                // Hidden Gradient = Output Gradient * Connecting Weight * sigmoidDerivative(Hidden Activation)
                hiddenNeuronGradient0 = outputGradient * output.getWeight(0) * sigmoidDerivative(activationNums[0]);
                hiddenNeuronGradient1 = outputGradient * output.getWeight(1) * sigmoidDerivative(activationNums[1]);

                // Updating Weights in Hidden Layer
                // Remember that each neuron in the hidden layer has 2 weights
                // and there are 2 neurons 

                // Updating Neuron 0 weights
                updateHiddenNeuron0weight0 = hiddenLayer[0].getWeight(0) + (learningRate * hiddenNeuronGradient0 * currentDataRow[0]); 
                updateHiddenNeuron0weight1 = hiddenLayer[0].getWeight(1)  + (learningRate * hiddenNeuronGradient0 * currentDataRow[1]);

                // Updating Neuron 1 weights
                updateHiddenNeuron1weight0 = hiddenLayer[1].getWeight(0) + (learningRate * hiddenNeuronGradient1 * currentDataRow[0]); 
                updateHiddenNeuron1weight1 = hiddenLayer[1].getWeight(1)  + (learningRate * hiddenNeuronGradient1 * currentDataRow[1]);

                //Apply the setters
                hiddenLayer[0].setWeight(0, updateHiddenNeuron0weight0);
                hiddenLayer[0].setWeight(1, updateHiddenNeuron0weight1);

                hiddenLayer[1].setWeight(0, updateHiddenNeuron1weight0);
                hiddenLayer[1].setWeight(1, updateHiddenNeuron1weight1);
                
                
                //Update Biases --> HL = Hidden Layer (Slang btw)
                 newHLBias0 = hiddenLayer[0].getBias() + (learningRate * hiddenNeuronGradient0);
                 newHLBias1 = hiddenLayer[1].getBias() + (learningRate * hiddenNeuronGradient1);

                 // Set Biases
                 hiddenLayer[0].setBias(newHLBias0);
                 hiddenLayer[1].setBias(newHLBias1);
            }
        }
    }

    // Derivative of sigmoid function = y(1-y) -> y = prediction
    // Represents how sensitive the neuron is (based off shape of squishy curve)
    private double sigmoidDerivative(double prediction) {
        double squishyDerivative = prediction  * (1 - prediction);
        
        return squishyDerivative;
    }

}
