//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

/**
 * Class used to see an event may occur
 * @author Jeffrey Esmaili Rodriguez 
 */
public class BooleanSource {
    private double probability;

    /**
     * Constructor for the BooleanSource object
     * @param p Probability of the BooleanSource
     * @throws IllegalArgumentException
     * Thrown if the probability is not bounded between 0 and 1
     */
    public BooleanSource(double p) throws IllegalArgumentException{
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException();
        probability = p;
    }

    /**
     * Method which tests if an event occurs
     * @return True of the generated number is less than the probability, false otherwise
     */
    public boolean occurs() {
        return (Math.random() < probability);
    }

}
