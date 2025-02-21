package experiment;

class Term {
    int coefficient;  
    int exponent;     
    Term next;        

    // Constructor  
    Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}

 
public class Polynomial {
    private Term head; 
    
     
    public Polynomial() {
        head = null;  
    }

     
    public Polynomial(Term head) {
        this.head = head;  
    }

    // Add polynomials
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Term t1 = p1.head;
        Term t2 = p2.head;
        Term dummy = new Term(0, 0);  
        Term current = dummy;

        while (t1 != null || t2 != null) {
            if (t1 == null || (t2 != null && t2.exponent < t1.exponent)) {
                current.next = new Term(t2.coefficient, t2.exponent);
                t2 = t2.next;
            } else if (t2 == null || (t1 != null && t1.exponent < t2.exponent)) {
                current.next = new Term(t1.coefficient, t1.exponent);
                t1 = t1.next;
            } else {
                int newCoeff = t1.coefficient + t2.coefficient;
                if (newCoeff != 0) {
                    current.next = new Term(newCoeff, t1.exponent);
                }
                t1 = t1.next;
                t2 = t2.next;
            }
            if (current.next != null) {
                current = current.next;
            }
        }
        return new Polynomial(dummy.next);
    }

    // Subtract polynomials
    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        Term t2 = p2.head;
        while (t2 != null) {
            t2.coefficient = -t2.coefficient; // Negate coefficients of p2
            t2 = t2.next;
        }
        return add(p1, p2); // Use the add method to compute subtraction
    }

    // Multiply polynomials
    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Term t1 = p1.head; t1 != null; t1 = t1.next) {
            Polynomial temp = new Polynomial();
            Term tempHead = null, tempCurrent = null;
            for (Term t2 = p2.head; t2 != null; t2 = t2.next) {
                Term newTerm = new Term(t1.coefficient * t2.coefficient, t1.exponent + t2.exponent);
                if (tempHead == null) {
                    tempHead = newTerm;
                    tempCurrent = newTerm;
                } else {
                    tempCurrent.next = newTerm;
                    tempCurrent = tempCurrent.next;
                }
            }
            temp.head = tempHead;
            result = add(result, temp);  
        }
        return result;
    }

    // Convert the polynomial to a string
    @Override
    public String toString() {
        if (head == null) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        Term current = head;
        while (current != null) {
            if (sb.length() > 0 && current.coefficient > 0) {
                sb.append(" + ");
            } else if (current.coefficient < 0) {
                sb.append(" - ");
            }
            sb.append(Math.abs(current.coefficient));
            if (current.exponent > 0) {
                sb.append("*x");
                if (current.exponent > 1) {
                    sb.append("^").append(current.exponent);
                }
            }
            current = current.next;
        }
        return sb.toString();
    }

    // Test Polynomial class
    public static void main(String[] args) {
         
        Term t1 = new Term(3, 0);
        t1.next = new Term(1, 1);
        t1.next.next = new Term(1, 3);

        Polynomial p1 = new Polynomial(t1);

        Term t2 = new Term(1, 2);
        t2.next = new Term(1000, 4);

        Polynomial p2 = new Polynomial(t2);

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);

        Polynomial sum = Polynomial.add(p1, p2);
        System.out.println("P1 + P2: " + sum);

        Polynomial diff = Polynomial.subtract(p1, p2);
        System.out.println("P1 - P2: " + diff);

        Polynomial prod = Polynomial.multiply(p1, p2);
        System.out.println("P1 * P2: " + prod);
    }
}
