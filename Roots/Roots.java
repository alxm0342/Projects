import java.lang.Math;
import java.util.Scanner;

/** Alexis Mendez
 *  CS 301 - Project 1 (Roots)
 *  10/24/11
 */
public class Roots {
    //functions a and b
    public static double func(double x, int choice){
        //function a
        if (choice==1){
            double y=0;
            //expression of function (a) from prompt, using math object class
            y = (2*Math.pow(x, 3)) - (11.7*Math.pow(x, 2)) + (17.7*x) - 5;
            return y;
        }
        //function b
        else{
            double y=0;
            //expression of function (b) from prompt, using math object class
            y = (Math.pow(Math.E, -x)) - x;
            return y;
        }
    }
    //derivatives of the functions a and b
    public static double funcPrime(double x, int choice){
        //function a'
        if (choice==1){
            double y=0;
            //expression of f'(a) from prompt, using math object class
            y = (6*Math.pow(x, 2)) - (23.4*x) + (17.7);
            return y;
        }
        //function b'
        else{
            double y=0;
            //expression of f'(b) from prompt, using math object class
            y = -(Math.pow(Math.E, -x)) - 1;
            return y;
        }
    }
    
    //bisection method for finding root
    public static void Bisection(double a, double b, int nmax, double err, int choice){
        double c = 0;
        double fc = 0;
        double fa = func(a, choice);
        double fb = func(b, choice);
        double calcError = 0;
        
        if ((fa/fb) > 0){
            System.out.println("f(" + a + ")= " + fa + " f(" + b + ")= " + fb);
            System.out.println("The function yields the same signs for (a) and (b)");
        }
        else{
            calcError = (b-a);
            System.out.println();
            System.out.println("n" + "\t\t" + "c" + "\t\t" + "fc" + "\t\t" + "calcError");
            for (int n=0; n <= nmax; n++){
                calcError = calcError/2;
                c = a + calcError;
                fc = func(c, choice);
                System.out.println(n + "\t\t" + c + "\t\t" + fc + "\t\t" + calcError);
                if (Math.abs(calcError) < (err/100)){
                    System.out.println("Convergence");
                    n = nmax;
                }
                if ((fa/fc) < 0){
                   b = c;
                   fb = fc;
                }
                else{
                    a = c;
                    fa = fc;
                }
            }
        }
    }
    
    public static void Newton(double x, int nmax, double err, double dx, int choice){
        double d = 0;
        double fx = func(x, choice);
        double fxp = 0;
        
        System.out.println();
        System.out.println("n" + "\t\t" + "x" + "\t\t" + "fx");
        System.out.println(0 + "\t\t" + x + "\t\t" + fx);
        for(int n=1; n <=nmax; n++){
            fxp = funcPrime(x, choice);
            if (Math.abs(fxp) < dx){
                System.out.println("Small Derivative");
                n = nmax;
            }
            else if (fxp == 0){
                System.out.println("Unable to continue: f'(" + x + ") = 0");
                n = nmax;
            }
            d = (fx/fxp);
            x = x-d;
            fx = func(x, choice);
            System.out.println(n + "\t\t" + x + "\t\t" + fx + "\t\t");
            if (Math.abs(d) < (err/100)){
                System.out.println("Convergence");
                n = nmax;
            }
        }
    }
    
    public static void Secant(double a, double b, int nmax, double err, int choice){
        double fa = func(a, choice);
        double fb = func(b, choice);
        double fc = 0;
        double d = 0;
        double c = 0;
        
        if(Math.abs(fa) > Math.abs(fb)){
            double temp = b;
            b = a;
            a = temp;
            temp = fb;
            fb = fa;
            fa = temp;
        }
        System.out.println("n" + "\t\t" + "x" + "\t\t" + "fx" + "\t\t");
        System.out.println(0 + "\t\t" + b + "\t\t" + fb);
        System.out.println(1 + "\t\t" + a + "\t\t" + fa);
        for (int n=2; n < nmax; n++){
            if(Math.abs(fa) > Math.abs(fb)){
                double temp = b;
                b = a;
                a = temp;
                temp = fb;
                fb = fa;
                fa = temp;
            }
            d = (b-a)/(fb - fa);
            c = b;
            fc = func(c, choice);
            b = a;
            fb = fa;
            d = d * fa;
            a = a-d;
            fa = func(a, choice);
            System.out.println(n + "\t\t" + a + "\t\t" + fa + "\t\t");
            
            if (Math.abs(d) < (err/100)){
                //error check for oscillations
                if (fa > .01)
                {
                    System.out.println("Error: Slow Convergence");
                    n = nmax;
                }
                System.out.println("Convergence");
                n = nmax;
            }
            //error-check for divergence
            if (n > 10 && (Math.abs(fa - fb) > (Math.abs(fb - fc)))){
                System.out.println("Warning: Divergence");
                n = nmax;
            }
        }
    }
    
        public static void ModSecant(double b, double db, int nmax, double err, int choice){
        double a = b + db;
        double fa = func(a, choice);
        double fb = func(b, choice);
        double fc = 0;
        double d = 0;
        double c = 0;
        
        if(Math.abs(fa) > Math.abs(fb)){
            double temp = b;
            b = a;
            a = temp;
            temp = fb;
            fb = fa;
            fa = temp;
        }
        System.out.println("n" + "\t\t" + "x" + "\t\t" + "fx" + "\t\t");
        System.out.println(0 + "\t\t" + b + "\t\t" + fb);
        System.out.println(1 + "\t\t" + a + "\t\t" + fa);
        for (int n=2; n < nmax; n++){
            if(Math.abs(fa) > Math.abs(fb)){
                double temp = b;
                b = a;
                a = temp;
                temp = fb;
                fb = fa;
                fa = temp;
            }
            d = (b-a)/(fb - fa);
            c = b;
            fc = func(c, choice);
            b = a;
            fb = fa;
            d = d * fa;
            a = a-d;
            fa = func(a, choice);
            System.out.println(n + "\t\t" + a + "\t\t" + fa + "\t\t");
            
            if (Math.abs(d) < (err/100)){
                //error check for oscillations
                if (fa > .01)
                {
                    System.out.println("Error: Slow Convergence");
                    n = nmax;
                }
                System.out.println("Convergence");
                n = nmax;
            }
            //error-check for divergence
            if (n > 10 && (Math.abs(fa - fb) > (Math.abs(fb - fc)))){
                System.out.println("Warning: Divergence");
                n = nmax;
            }
        }
    }
        
    public static void FalsePos(double a, double b, int nmax, double err, int choice){
        double c = 0;
        double fa = func(a, choice);
        double fb = func(b, choice);
        double fc = 0;
        double previous = 0;
        
        //determine that fa and fb are of opposite signs
        if ((fa/fb) > 0){
            System.out.println("f(" + a + ")= " + fa + " f(" + b + ")= " + fb);
            System.out.println("The function yields the same signs for (a) and (b)");
        }
        else{
            //ensure b is x-upper
            if (b < a){
                double temp = b;
                b = a;
                a = temp;
                temp = fb;
                fb = fa;
                fa = temp;
            }
            System.out.println("n" + "\t\t" + "c" + "\t\t\t" + "fc" + "\t\t");
            for (int n = 0; n < nmax; n++){
                previous = c;
                c = b - ((fb*(a-b))/(fa - fb));
                fc = func(c, choice);
                if (fc < 0){
                    a = c;
                    fa = fc;
                    System.out.println(n + "\t\t" + c + "\t\t" + fc + "\t\t");
                    //check if new and previous x-lower are close enough for convergence
                    if ((Math.abs(a-previous)/Math.abs(a)) < (err/100)){
                    System.out.println("Convergence");
                    n = nmax;
                    }
                }
                else if (fc > 0){
                    b = c;
                    fb = fc;
                    System.out.println(n + "\t\t" + c + "\t\t" + fc + "\t\t");
                    //check if new and previous x-upper are close enough for convergence
                    if ((Math.abs(b-previous)/Math.abs(b)) < (err/100)){
                    System.out.println("Convergence");
                    n = nmax;
                    }
                }
                else{
                    System.out.println(n + "\t\t" + c + "\t\t" + fc + "\t\t");
                    System.out.println("Convergence");
                    n = nmax;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner (System.in);
        int func = 0;
        int meth = 0;
        int nmax = 0;
        double err = 0;
        double a = 0;
        double b = 0;
        double dx = 0;
        
        //request desired function from user
        System.out.println("The following functions are available:");
        System.out.println("--------------------------------------");
        System.out.println("Function 1 = 2x^3 – 11.7x^2 + 17.7x – 5");
        System.out.println("Function 2 = e^(-x) -x");
        System.out.println();
        System.out.println("Choice?: ");
        func = keyboard.nextInt();
        
        //request desired method of finding root
        System.out.println();
        System.out.println("The following methods are available:");
        System.out.println("--------------------------------------");
        System.out.println("1)Bisection Method");
        System.out.println("2)Newton-Raphson Method");
        System.out.println("3)Secant Method");
        System.out.println("4)Modified-Secant Method");
        System.out.println("5)False-Position Method");
        System.out.println();
        System.out.println("Which Method?");
        meth = keyboard.nextInt();
                
        switch (meth){
            case 1:
                //determine variables for bisection
                System.out.println("Lower bound (a)?");
                a = keyboard.nextDouble();
                System.out.println("Upper bound (b)?");
                b = keyboard.nextDouble();
                System.out.println("Max Iterations (nmax)?");
                nmax = keyboard.nextInt();
                System.out.println("Max error % (e)?");
                err = keyboard.nextDouble();
                Bisection (a, b, nmax, err, func);
                break;
            case 2:
                //determine variable for Newton-Raphson Method
                System.out.println("What is X(initial)?");
                double x = keyboard.nextDouble();
                System.out.println("Max Iterations (nmax)?");
                nmax = keyboard.nextInt();
                System.out.println("Max error % (e)?");
                err = keyboard.nextDouble();
                System.out.println("What is the minimum derivative?");
                dx = keyboard.nextDouble();
                Newton(x, nmax, err, dx, func);
                break;
            case 3:
                //determine variables for secant method
                System.out.println("First approximation (x-0)?");
                b = keyboard.nextDouble();
                System.out.println("Second approximation (x-1)?");
                a = keyboard.nextDouble();
                System.out.println("Max Iterations (nmax)?");
                nmax = keyboard.nextInt();
                System.out.println("Max error % (e)?");
                err = keyboard.nextDouble();
                Secant(a, b, nmax, err, func);
                break;
            case 4:
                //determine variables for secant method
                System.out.println("Approximation (x)?");
                b = keyboard.nextDouble();
                System.out.println("Delta (dx)?");
                dx = keyboard.nextDouble();
                System.out.println("Max Iterations (nmax)?");
                nmax = keyboard.nextInt();
                System.out.println("Max error % (e)?");
                err = keyboard.nextDouble();
                ModSecant(b, dx, nmax, err, func);
                break;
            case 5:
                //determine variable for false-position method;
                System.out.println("Lower bound (a)?");
                a = keyboard.nextDouble();
                System.out.println("Upper bound (b)?");
                b = keyboard.nextDouble();
                System.out.println("Max Iterations (nmax)?");
                nmax = keyboard.nextInt();
                System.out.println("Max error % (e)?");
                err = keyboard.nextDouble();
                FalsePos(a, b, nmax, err, func);
                break;
        }
    }
}
