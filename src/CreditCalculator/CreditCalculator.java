package CreditCalculator;

import java.lang.Math;

public class CreditCalculator {
    private double interest;
    private double payment;
    private double principal;
    private double periods;
    private String type;

    // Constructor to initialize the CreditCalculator with input parameters
    private CreditCalculator(String type, double principal, double periods, double interest, double payment) {
        this.interest = interest;
        this.payment = payment;
        this.principal = principal;
        this.periods = periods;
        this.type = type;
    }

    // Calculate monthly payments for annuity type loans
    public void calculateMonthlyPayments() {
        double i = interest / (12 * 100);
        double months = Math.ceil(Math.log(payment / (payment - i * principal)) / Math.log(1 + i));
        double years = (int) months / 12;
        double monthsRemaining = (int) (months % 12);

        if (monthsRemaining == 0) {
            System.out.println("You need " + years + " years to repay this credit!");
        } else if (years == 0) {
            System.out.println("You need " + monthsRemaining + " months to repay this credit!");
        } else {
            System.out.println("You need " + years + " years and " + monthsRemaining + " months to repay this credit!");
        }
    }

    // Calculate annuity monthly payment
    public void calculateAnnuityMonthlyPayment() {
        double i = interest / (12 * 100);
        double value = (i * Math.pow(1+i, periods))/(Math.pow(1+i, periods) - 1);
        double monthlyPayment = principal * value;
        monthlyPayment = Math.ceil(monthlyPayment);
        System.out.println("Your annuity payment = " + monthlyPayment + "!");
    }

    // Calculate the loan principal for annuity type loans
    public void calculateLoanPrincipal() {
        double i = interest / (12 * 100);
        double value = (i * Math.pow(1+i, periods))/(Math.pow(1+i, periods) - 1);
        double creditPrincipal = payment / value;
        System.out.println("Your loan principal = " + (int) creditPrincipal + "!");
    }

    // Calculate differentiated payments for diff type loans
    public void calculateDiffPayment() {
        double i = interest / (12 * 100);
        for (int j = 1; j < periods + 1; j++) {
            double diffAmount = Math.ceil((principal/periods) + i * (principal - (principal*(j-1)/periods)));
            System.out.println("Month: " + j + " paid out " + diffAmount);
        }
    }

    // Validate input parameters to ensure they are positive
    public void validateInput() {
        if ("annuity".equals(type)) {
            if (!(interest > 0 && payment > 0 && principal > 0) &&
                !(periods > 0 && principal > 0 && interest > 0) &&
                !(periods > 0 && payment > 0 && interest > 0)) {
                throw new IllegalArgumentException("Incorrect parameters");
            }
        } else if ("diff".equals(type)) {
            if (!(periods > 0 && principal > 0 && interest > 0)) {
                throw new IllegalArgumentException("Incorrect parameters");
            }
        } else {
            throw new IllegalArgumentException("Unsupported loan type: " + type);
        }
    }

    
    // Calculate payments based on the loan type and input parameters
    public void calculate() {
        try {
            validateInput();
            
            if ("annuity".equals(type)) {
                if (interest > 0 && payment > 0 && principal > 0) {
                    calculateMonthlyPayments();
                } else if (periods > 0 && principal > 0 && interest > 0) {
                    calculateAnnuityMonthlyPayment();
                } else if (periods > 0 && payment > 0 && interest > 0) {
                    calculateLoanPrincipal();
                } else {
                    System.out.println("Incorrect parameters");
                }
            } else if ("diff".equals(type)) {
                if (periods > 0 && principal > 0 && interest > 0) {
                    calculateDiffPayment();
                } else {
                    System.out.println("Incorrect parameters");
                }
            } else {
                System.out.println("Unsupported loan type: " + type);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        double interest = Double.parseDouble(System.getProperty("interest", "0.0"));
        double principal = Double.parseDouble(System.getProperty("principal", "0.0"));
        double payment = Double.parseDouble(System.getProperty("payment", "0.0"));
        double periods = Double.parseDouble(System.getProperty("periods", "0.0"));
        String type = System.getProperty("type");
        
        CreditCalculator creditCalculator = new CreditCalculator(type, principal, periods, interest, payment);
        creditCalculator.calculate();
    }
}
