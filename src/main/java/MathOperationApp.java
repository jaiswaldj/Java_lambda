
@FunctionalInterface
interface IMathFunction {
    int calculate(int a, int b);
    static void printResult(int a, int b, String function, IMathFunction fobj){
        System.out.println("Result of "+function+" is "+fobj.calculate(a, b));
    }
}

public class MathOperationApp {
    //Method
    /*public static int add(int a, int b){
        return  Math.addExact(a,b);
    }*/

    //Main Method
    public static void main(String [] args){
         //Using method Reference instead of lambda Expression
        //This expression implements 'IMathFunction' interface
        IMathFunction add = Integer::sum;

        // Lambda expression for multiplication & division for two parameters.
        // This expression implements 'IMathFunction'
        IMathFunction subtract = (x,y) -> x - y;
        IMathFunction multiply = (x, y) -> x * y;
        IMathFunction divide = (int x, int y) -> x / y;


        // Add & Multiply two numbers using lambda expression
        System.out.println("Addition is " + add.calculate(6, 3));
        System.out.println("Subtraction is " + subtract.calculate(6,3));
        System.out.println("Multiplication is" + multiply.calculate(6, 3));
        System.out.println("Division is" + divide.calculate(6, 3));

        // Passing lambda Expression as Function Parameter to Print Result using Static function
        IMathFunction.printResult(6,3, "Addition", add);
        IMathFunction.printResult(6,3, "Multiplication", multiply);
        IMathFunction.printResult(6,3,"Division", divide);


    }
}
