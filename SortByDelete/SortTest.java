public class SortTest {

	public static void main(String[] args) {
		SLList<Integer> intList = new SLList<Integer>();
		intList.add(3);
		intList.add(5);
		intList.add(1);
		intList.add(4);
		intList.add(2);
		System.out.println(intList.toString());
		intList.sortByDeletion();
		System.out.println(intList.toString());
	}

}
