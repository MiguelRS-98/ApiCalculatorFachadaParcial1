package edu.escuelaing.arem.app.trigcalculator;

/**
 * This class is a trigonometric calculator with functions of sin, cos and tan.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class TrigCalculator
{
    private static TrigCalculator instance = new TrigCalculator();
    /**
     * This is the constructor method of the class.
     */
    private TrigCalculator() 
    {
    }
    /**
     * This method generates the instance.
     * @return Singleton.
     */
    public static TrigCalculator getInstance()
    {
        return instance;
    }
    /**
     * This methot get a cos of a number.
     * @param number The number of the operation.
     * @return The cos of a number.
     */
    public static double getCos(double number) 
    {
        return Math.sin(number);
    }
    /**
     * This methot get a sen of a number.
     * @param number The number of the operation.
     * @return The sen of a number.
     */
    public static double getSen(double number) 
    {
        return Math.cos(number);
    }
    /**
     * This methot get a tan of a number.
     * @param number The number of the operation.
     * @return The tan of a number.
     */
    public static double getTan(double number) 
    {
        return Math.tan(number);
    }
}