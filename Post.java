import java.util.ArrayList;

public class Post {
    private static int idCounter = 1;
    private int id;
    private String author;
    private String title;
    private String body;
    private int upvotes;
    private int downvotes;
    private ArrayList<Comment> comments;

    public Post(String author, String title, String body) {
        this.id = idCounter++;
        this.author = author;
        this.title = title;
        this.body = body;
        this.upvotes = 0;
        this.downvotes = 0;
        this.comments = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }

    public void upvote() { upvotes++; }
    public void downvote() { downvotes++; }
    
    public void addComment(String author, String text) {
        comments.add(new Comment(author, text));
    }

    public void displaySummary() {
        System.out.println("[" + id + "] " + title + " (by " + author + ") | Upvotes: " + upvotes + " | Downvotes: " + downvotes);
    }

    public void displayFullPost() {
        System.out.println("\n=========================================");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Score: +" + upvotes + " / -" + downvotes);
        System.out.println("-----------------------------------------");
        System.out.println(body);
        System.out.println("-----------------------------------------");
        System.out.println("Comments (" + comments.size() + "):");
        for (Comment c : comments) {
            System.out.println(c.toString());
        }
        System.out.println("=========================================\n");
    }
}