/**Name: Alexis Mendez
 * Class: CS 240 - Tang
 * Date: 2/7/11
 * Project 2: Singly-linked Set ADT (Test Program)
 */
public class SLSetTest {
    
    /**
     * @param args the command line arguments
     */
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {        
        //Test Add, Remove, Contain, ToString
        System.out.println("-----Testing Add, Remove, Contain, toString-----");
        SLSet A = new SLSet();
        A.addElement(1);
        A.addElement(3);
        A.addElement(5);
        A.addElement(7);
        A.remove(8);
        A.addElement(8);
        A.remove(8);
        System.out.println();
        //Expected set size = 4 - A={1, 3, 5, 7}
        System.out.println("Total set size: " + A.size());
        System.out.println("A = " + A.toString());
        
        //Test Subset
        System.out.println();
        System.out.println("---------------Testing Subset-------------------");
        SLSet B = new SLSet();
        SLSet D =  new SLSet();
        B.addElement(3);
        B.addElement(5);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("D = " + D.toString());
        //First Test: Valid Subset
        if (B.subsetOf(A)) {System.out.println("B is a subset of A");}
        else {System.out.println("B is not a subset of A");}
        //Second Test: Invalid sizes, not subset
        if (A.subsetOf(B)) {System.out.println("A is a subset of B");}
        else {System.out.println("A is not a subset of B");}
        //Third Test: Valid sizes, missing element
        B.addElement(4);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("D = " + D.toString());
        if (B.subsetOf(A)) {System.out.println("B is a subset of A");}
        else {System.out.println("B is not a subset of A");}
        //Fourth Test: Empty set, subset
        if (D.subsetOf(A)) {System.out.println("D is a subset of A");}
        else {System.out.println("D is not a subset of A");}
        
        //Test isEqual
        System.out.println();
        System.out.println("---------------Testing isEqual-------------------");
        //First Test: different sizes: should be false
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        if (B.isEqual(A)) {System.out.println("B equal to A");}
        else {System.out.println("B is not equal to A");}
        //Second Test: same size, different elements: should be false;
        B.addElement(1);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        if (B.isEqual(A)) {System.out.println("B equal to A");}
        else {System.out.println("B is not equal to A");}
        //Third Test: same size, same elements: should be true;
        B.addElement(7);
        B.remove(4);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        if (B.isEqual(A)) {System.out.println("B equal to A");}
        else {System.out.println("B is not equal to A");}
        
        SLSet C;
        
        //Test Union
        System.out.println();
        System.out.println("----------------Testing Union--------------------");
        System.out.println("Case 1 Equal but Distinct");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        B.addElement(1);
        C = A.union(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 2 Different Sizes");
        A = new SLSet();
        A.addElement(1);
        B = new SLSet();
        B.addElement(1);
        B.addElement(2);
        C = A.union(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 3 non-empty, different size, common elements");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        C = A.union(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 4 non-empty and nothing in common");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(4);
        B.addElement(5);
        C = A.union(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 5 one is empty and one is non-empty");
        A = new SLSet();
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        C = A.union(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        
        
        //Test Intersection
        System.out.println();
        System.out.println("-------------Testing Intersection----------------");
        System.out.println("Case 1 Equal but Distinct");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        B.addElement(1);
        C = A.intersection(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 2 Different Sizes");
        A = new SLSet();
        A.addElement(1);
        B = new SLSet();
        B.addElement(1);
        B.addElement(2);
        C = A.intersection(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 3 non-empty, different size, common elements");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        C = A.intersection(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 4 non-empty and nothing in common");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(4);
        B.addElement(5);
        C = A.intersection(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 5 one is empty and one is non-empty");
        A = new SLSet();
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        C = A.intersection(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        
        //Test Complement
        System.out.println();
        System.out.println("--------------Testing Complement-----------------");
        System.out.println("Case 1 Equal but Distinct");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        B.addElement(1);
        C = A.complement(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 2 Different Sizes");
        A = new SLSet();
        A.addElement(1);
        B = new SLSet();
        B.addElement(1);
        B.addElement(2);
        C = A.complement(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 3 non-empty, different size, common elements");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        C = A.complement(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 4 non-empty and nothing in common");
        A = new SLSet();
        A.addElement(1);
        A.addElement(2);
        A.addElement(3);
        B = new SLSet();
        B.addElement(4);
        B.addElement(5);
        C = A.complement(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
        System.out.println();
        System.out.println("Case 5 one is empty and one is non-empty");
        A = new SLSet();
        B = new SLSet();
        B.addElement(2);
        B.addElement(3);
        C = A.complement(B);
        System.out.println("A = " + A.toString());
        System.out.println("B = " + B.toString());
        System.out.println("C = " + C.toString());
    }
}
