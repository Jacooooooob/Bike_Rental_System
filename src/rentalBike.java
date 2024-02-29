import java.util.Scanner;

class function {
    public static int Tot = 20;

    public static void service0 () {
        Scanner c = new Scanner(System.in);
        String C = c.next();
        if (C.equals("borrow")) {
            System.out.println("Please choose from 3 rental options: ");
            int opt_b = service1();
            System.out.println("You have chosen option "+opt_b);
            int num_b = service2();
            Tot = Borrow(opt_b, num_b);
        }
        else if (C.equals("return")) {
            System.out.println("Please tell me what kind of bikes you rented: ");
            int opt_r = service1();
            System.out.println("You have chosen option "+opt_r);
            int num_r = service2();
            Tot = Return(opt_r, num_r);
        }
        else {
            System.out.println("I don't know what you mean. Please enter 'borrow' or 'return'.");
        }
        service0();
    }

    public static int service1 () {
        System.out.println("1-RMB20 per Hour, 2-RMB100 per Day, 3-RMB400 per Week");
        Scanner s = new Scanner(System.in);
        String S = s.next();
        if (!S.equals("1") && !S.equals("2") && !S.equals("3")) {
            System.out.println("Sorry, I don't know what you mean. Please enter '1' or '2' or '3'.");
            return service1();
        }
        else {
            return Integer.parseInt(S);
        }
    }

    public static int service2 () {
        System.out.println("How many bikes do you want to rent or return?");
        Scanner s = new Scanner(System.in);
        String S = s.next();
        int i = 0;
        try {
            i = Integer.parseInt(S);
        }
        finally {
            if (i > Tot) {
                System.out.println("Sorry, I don't have enough bikes. Please tell me again.");
                return service2();
            } else if (i > 0) return i;
            else {
                System.out.println("Sorry, I don't know what you mean. Please enter a positive integer.");
                return service2();
            }
        }
    }

    public static int Borrow (int opt, int num) {
        int total = Tot - num;
        int bill = 0;
        if (opt == 1) {
            bill = 20 * num;
        }
        else if (opt == 2) {
            bill = 100 * num;
        }
        else if (opt == 3) {
            bill = 400 * num;
        }
        System.out.println("You have chosen to rent "+num+" bikes of option "+opt);
        System.out.println("Your total bill is "+bill+" RMB");
        System.out.println("We have "+total+" bikes available.");
        return total;
    }

    public static int Return (int opt, int num) {
        int total = Tot + num;
        int bill = 0;
        if (opt == 1) {
            bill = 20 * num;
        }
        else if (opt == 2) {
            bill = 100 * num;
        }
        else if (opt == 3) {
            bill = 400 * num;
        }
        System.out.println("You have chosen to return "+num+" bikes of option "+opt);
        System.out.println("Your total bill is "+bill+" RMB");
        System.out.println("We have "+total+" bikes available.");
        return total;
    }
}

public class rentalBike {
    public static void main (String[] args) {
        System.out.println("######### Welcome to My Bike Shop! #########");
        System.out.println("######### We have " + function.Tot + " bikes available. #########");
        System.out.println("######### Are you here to borrow or return bikes? #########");
        function.service0();
    }
}