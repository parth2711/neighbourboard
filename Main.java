import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Post> posts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String currentUser = "Anonymous";

    public static void main(String[] args) {
        System.out.println("Welcome to NeighbourBoard!");
        System.out.print("Enter your username to join the forum: ");
        currentUser = scanner.nextLine();

        seedData();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllPosts();
                    break;
                case "2":
                    createNewPost();
                    break;
                case "3":
                    interactWithPost();
                    break;
                case "4":
                    searchPosts();
                    break;
                case "5":
                    System.out.println("Goodbye, " + currentUser + "!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n<< NeighbourBoard Menu >>");
        System.out.println("1. View all posts");
        System.out.println("2. Create a new post");
        System.out.println("3. View/Interact with a post (Upvote/Comment)");
        System.out.println("4. Search threads");
        System.out.println("5. Exit");
    }

    private static void viewAllPosts() {
        if (posts.isEmpty()) {
            System.out.println("No posts available. Be the first to post!");
            return;
        }
        System.out.println("\n<< Recent Posts >>");
        for (Post post : posts) {
            post.displaySummary();
        }
    }

    private static void createNewPost() {
        System.out.print("Enter post title: ");
        String title = scanner.nextLine();
        System.out.print("Enter post content: ");
        String content = scanner.nextLine();
        
        posts.add(new Post(currentUser, title, content));
        System.out.println("Post created successfully!");
    }

    private static void interactWithPost() {
        System.out.print("Enter the ID of the post you want to view: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Post foundPost = null;
            for (Post p : posts) {
                if (p.getId() == id) {
                    foundPost = p;
                    break;
                }
            }

            if (foundPost == null) {
                System.out.println("Post not found.");
                return;
            }

            foundPost.displayFullPost();
            System.out.println("Options: [1] Upvote  [2] Downvote  [3] Add Comment  [4] Go Back");
            System.out.print("Choice: ");
            String action = scanner.nextLine();

            if (action.equals("1")) {
                foundPost.upvote();
                System.out.println("Upvoted!");
            } else if (action.equals("2")) {
                foundPost.downvote();
                System.out.println("Downvoted!");
            } else if (action.equals("3")) {
                System.out.print("Enter your comment: ");
                String commentText = scanner.nextLine();
                foundPost.addComment(currentUser, commentText);
                System.out.println("Comment added!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    private static void searchPosts() {
        System.out.print("Enter keyword to search in titles: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;

        System.out.println("\nSearch Results:");
        for (Post post : posts) {
            if (post.getTitle().toLowerCase().contains(keyword)) {
                post.displaySummary();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No posts found containing '" + keyword + "'.");
        }
    }

    private static void seedData() {
        Post p1 = new Post("Admin", "Water Supply Interruption", "The municipal corporation will be cutting water supply tomorrow from 10 AM to 2 PM.");
        p1.upvote();
        p1.addComment("Ravi", "Thanks for the heads up!");
        
        Post p2 = new Post("Anita", "Lost Cat near Park", "Has anyone seen an orange tabby cat? Answers to the name Garfield.");
        p2.upvote(); p2.upvote();
        
        posts.add(p1);
        posts.add(p2);
    }
}