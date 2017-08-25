
public class Tester {
	public static void main(String[] args)
	{
	String[] words = {"One", "Two", "Three", "Four", "Five",
	"Six", "Seven", "Eight", "Nine", "Ten"};
	MyBST bst = new MyBST();

	for (String word : words)
	{
	System.out.println("Added: " + word + " " + bst.add(word));
	System.out.println("Contains: " + word + " " + bst.contains(word));
	if(bst.add(word))
	System.out.println("*** Added a duplicate value ***");
	System.out.println(bst);
	}

	 

	for (String word : words)
	{
	System.out.println("Removed: " + word + " " + bst.remove(word));
	if(bst.remove(word))
	System.out.println("*** Removed a non-existent value ***");
	System.out.println(bst);
	}
	}
}
