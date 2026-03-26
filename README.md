# 🏘️ NeighbourBoard — Local Neighborhood Forum

A command-line community forum built in Java, where residents can post announcements, ask questions, report safety concerns, find lost items, and engage through comments and voting — all without needing the internet or a smartphone app.

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Data Structures Used](#data-structures-used)
- [Project Structure](#project-structure)
- [Setup & Running](#setup--running)
- [How to Use](#how-to-use)
- [Screenshots / Demo](#screenshots--demo)
- [Author](#author)

---

## About the Project

Many neighborhoods — especially in semi-urban and rural areas — lack a shared digital space where residents can communicate locally. WhatsApp groups get noisy, notice boards go unnoticed, and social media lacks locality context.

NeighbourBoard is a CLI-based neighborhood forum that:
- Lets residents register and log in securely
- Post under community-relevant tags (Announcement, Question, Safety, Lost & Found, Discussion)
- Vote posts and comments up or down (Reddit-style scoring)
- Search posts instantly using a prefix Trie
- Browse trending content via a max-heap Priority Queue
- Reply to comments in a nested thread tree
- Persists all data to local files between sessions

---

## Features

| Feature | Description |
|---|---|
| 🔐 Auth | Register / login with SHA-256 hashed passwords |
| 📝 Posts | Create, browse, delete posts with tags |
| 💬 Comments | Nested threaded comments with replies |
| 👍 Voting | Upvote/downvote posts and comments (one vote per user) |
| 🔍 Search | Instant prefix search on post titles (Trie) |
| 🔥 Trending | Top posts by score using a max-heap |
| 🏷️ Tags | Filter feed by tag: ANNOUNCEMENT, QUESTION, SAFETY, LOST_FOUND, DISCUSSION |
| 🏆 Leaderboard | Top community members by karma |
| 💾 Persistence | All data saved to `data/` folder as plain text files |
| 🧭 Navigation | Back-navigation history using a Stack |

---

## Data Structures Used

| Structure | Where Used | Why |
|---|---|---|
| `HashMap<String, User>` | User registry | O(1) username lookup |
| `LinkedHashMap<String, Post>` | Post store | Maintains insertion order for chronological feed |
| `PriorityQueue<Post>` | Trending posts | Max-heap by score; O(log n) insert, O(1) peek |
| `PriorityQueue<User>` | Leaderboard | Max-heap by karma |
| `Trie` (custom) | Post title search | O(L) prefix search where L = query length |
| `ArrayList<Comment>` | Comment tree (children) | Dynamic child list per node |
| `Queue<Comment>` (BFS) | Find comment by ID | Level-order traversal of comment tree |
| `Deque<String>` (as Stack) | Navigation history | O(1) push/pop for back navigation |
| `HashSet<String>` | Vote deduplication | O(1) vote-already-cast check |

---

## Project Structure

```
NeighbourBoard/
├── src/
│   └── forum/
│       ├── model/
│       │   ├── User.java          # User entity + karma + vote sets
│       │   ├── Post.java          # Post entity, Comparable by score
│       │   └── Comment.java       # Comment node (n-ary tree), Comparable
│       ├── service/
│       │   └── ForumService.java  # All business logic; owns all data structures
│       ├── util/
│       │   ├── SearchTrie.java    # Custom Trie for O(L) prefix search
│       │   ├── PasswordUtil.java  # SHA-256 hashing
│       │   └── FileStore.java     # Flat-file persistence (no external DB)
│       └── ui/
│           ├── ForumApp.java      # Main CLI entry point
│           └── Display.java       # Terminal rendering helpers (ANSI colors)
├── data/                          # Auto-created on first run
│   ├── users.txt
│   ├── posts.txt
│   └── comments.txt
└── README.md
```

---

## Setup & Running

### Prerequisites

- Java 17 or later (JDK)
- No external libraries required — pure Java standard library

### Compile

```bash
# From the project root
mkdir -p out
find src -name "*.java" | xargs javac -d out/
```

### Run

```bash
java -cp out forum.ui.ForumApp
```

### One-liner (compile + run)

```bash
mkdir -p out && find src -name "*.java" | xargs javac -d out/ && java -cp out forum.ui.ForumApp
```

---

## How to Use

### First Time

1. Run the app
2. Choose **1. Register** → enter a username and password
3. Choose **2. Login**
4. Start posting!

### Creating a Post

```
> 5  (Create Post)
Tag: ANNOUNCEMENT
Title: Water supply cut on Sunday 6am–10am
Body: The municipal corporation has informed...
END
✔ Post created! ID: a3f2b1c0
```

### Searching Posts

```
> 4  (Search)
Search keyword: water
```
Finds all posts with titles containing words starting with "water" — powered by the Trie.

### Voting

Open a post → press `u` to upvote, `d` to downvote.
Each user can vote once per post/comment. Votes affect the author's karma.

### Nested Comments

Open a post → `c` to comment → `r` to reply to a specific comment (enter its ID).

### Tags Available

| Tag | Use for |
|---|---|
| `ANNOUNCEMENT` | Official notices, utility cuts, events |
| `QUESTION` | Ask neighbors anything |
| `DISCUSSION` | Open conversations |
| `LOST_FOUND` | Lost pets, keys, items |
| `SAFETY` | Safety alerts, suspicious activity |

---

## Data Persistence

All data is stored in the `data/` folder (auto-created):
- `users.txt` — usernames, hashed passwords, karma
- `posts.txt` — all post metadata and body text
- `comments.txt` — all comments with parent linkage for tree reconstruction

Data is reloaded automatically on next launch. No database or external dependencies needed.

---

## Author

**[Your Name]**  
VIT — Programming in Java (BYOP Project)  
March 2026
