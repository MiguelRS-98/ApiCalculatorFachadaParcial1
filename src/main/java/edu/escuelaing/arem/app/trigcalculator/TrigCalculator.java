package edu.escuelaing.arem.app.trigcalculator;

/**
 * This class is a trigonometric calculator with functions of sin, cos and tan.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class TrigCalculator
{
    /**
     * This methot get a sin of a number.
     * @param number The number of the operation.
     * @return The sin of a number.
     */
    public static double getSin(double number) 
    {
        return Math.sin(number);
    }
    /**
     * This methot get a cos of a number.
     * @param number The number of the operation.
     * @return The cos of a number.
     */
    public static double getCos(double number) 
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