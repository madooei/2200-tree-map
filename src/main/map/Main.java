package map;

/** A demo of TreeMap: a website storing each username with its password. */
public final class Main {

  private Main() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    Map<String, String> passwords = new TreeMap<>();

    // The usernames arrive in sorted order, which would build a chain in a
    // plain binary search tree.
    System.out.println("register alice: " + passwords.put("alice", "wonderland"));
    System.out.println("register bob: " + passwords.put("bob", "builder123"));
    System.out.println("register carol: " + passwords.put("carol", "swift99"));
    System.out.println("register dave: " + passwords.put("dave", "diver42"));
    System.out.println("register erin: " + passwords.put("erin", "orbit7"));
    System.out.println("users registered: " + passwords.size());
    System.out.println("alice's password: " + passwords.get("alice"));
    System.out.println("bob changes the password: " + passwords.put("bob", "fixerUpper"));
    System.out.println("bob's password: " + passwords.get("bob"));
    System.out.println("users registered: " + passwords.size());
    System.out.println("frank's password: " + passwords.get("frank"));
    System.out.println("is carol registered? " + passwords.containsKey("carol"));
    System.out.println("carol deletes the account: " + passwords.remove("carol"));
    System.out.println("frank deletes the account: " + passwords.remove("frank"));
    System.out.print("all usernames, in sorted order:");
    for (String username : passwords) {
      System.out.print(" " + username);
    }
    System.out.println();
    System.out.println("all usernames with passwords:");
    for (Map.Entry<String, String> entry : passwords.entries()) {
      System.out.println("  " + entry.key() + " -> " + entry.value());
    }
  }
}
