# Smart Contact Book

A small Java console app that I built to practice data structures and algorithms.
Each feature uses a different data structure.

## Features

| Feature | Data structure |
|---|---|
| Add, find, delete a contact | HashMap |
| Prefix search (type "na" to see Natik, Nandini) | Trie |
| Undo the last add or delete | Stack (Deque) |
| Last 5 searched contacts | Queue (Deque) |
| All contacts sorted by name | TreeMap |
| Top favourites by priority | PriorityQueue |
| Save and load contacts from a file | File I/O |

## How to run

1. Clone the repo
2. Open it in IntelliJ IDEA (JDK 17 or newer)
3. Run `Main.java`

## Project structure

- `Contact`: name, phone number and priority
- `ContactBook`: main logic, joins all the data structures
- `Trie`, `TrieNode`: prefix search and delete
- `Action`: one add or delete, used by undo
- `FileHelper`: saves and loads `contacts.txt`
- `Main`: console menu

## Things I learned

- Names are stored in lowercase, so "Nitin" and "nitin" are the same contact.
- A deleted contact must be removed from every structure (map, trie, recent list).

## Possible next steps

- Save favourites in the file
- Add JUnit tests
- Edit a contact
