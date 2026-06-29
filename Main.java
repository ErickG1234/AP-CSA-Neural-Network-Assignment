public class Main {
    public static void main(String[] args) {
        // Create Training Data (2D array) Set for Neural Network
        int rowNum = /* specify number of rows here */;
        int colNum = /* specify number of columns here */;
        int epochs = /* specify number of epochs here */;
       
        double[] studentPassedTest = {1.0, 0.0, 1.0, 0.0};
        
        double[][] trainingDataSet = new double[rowNum][colNum];


        trainingDataSet[0][0] = 8.0;
        trainingDataSet[0][1] = 8.0;

        trainingDataSet[1][0] = 1.0;
        trainingDataSet[1][1] = 3.0;

        trainingDataSet[2][0] = 6.0;
        trainingDataSet[2][1] = 7.0;
        
        trainingDataSet[3][0] = 2.0;
        trainingDataSet[3][1] = 9.0;

        for (int i = 0; i < trainingDataSet.length; i++) {
            for (int j = 0; j < trainingDataSet[i].length; j++) {
                trainingDataSet[i][j] = trainingDataSet[i][j] / 10.0;
            }
        }

        NeuralNetwork myNeuralNetwork = new NeuralNetwork(0.1);

        // TODO:Call the train method to train the neural network with the training data set and the expected output
        
        
        for (int i = 0; i < trainingDataSet.length; i++) {
            // TODO: Call the predict method to get the prediction for each student in the training data set
            
            
            System.out.println("Student " + i + " Computer Prediction: " + /* Variable you stored the prediction in here */);
            System.out.println("Actual Student " + i + " Outcome: " + studentPassedTest[i]);
        }
    }
}