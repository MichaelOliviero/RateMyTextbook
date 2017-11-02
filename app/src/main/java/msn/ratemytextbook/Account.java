package msn.ratemytextbook;

public class Account {

    protected String Username;
    protected String Password;
    protected String Email;
    protected int    Rating;

    public Account () {
        Username = "";
        Password = "";
        Email = "";
        Rating = 100;
    }

    public Account (String u, String p, String e, int r) {
        Username = u;
        Password = p;
        Email = e;
        Rating = r;
    }

    public String getUsername() { return Username; }
    public String getPassword() { return Password; }
    public String getEmail() { return Email; }
    public int getRating() { return Rating; }

    public void setUsername(String username) { Username = username; }
    public void setPassword(String password) { Password = password; }
    public void setEmail(String email) { Email = email; }
    public void setRating(int rating) { Rating = rating; }
}
