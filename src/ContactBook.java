import java.util.*;

public class ContactBook {
    private final HashMap<String,Contact> map=new HashMap<>();
    private final Trie trie = new Trie();
    private final Deque<Action> undoStack=new ArrayDeque<>();
    private final Deque<Contact> recent=new ArrayDeque<>();


    public boolean addContact(Contact c) {
        String key = c.getName().toLowerCase();
        if (map.containsKey(key)) {
            return false;
        }
        map.put(key, c);
        trie.insert(c.getName());
        undoStack.push(new Action("ADD", c));
        return true;
    }

    public Contact findContact(String name){
        Contact c=map.get(name.toLowerCase());
        if(c==null){
            return null;
        }
        if(recent.contains(c)){
            recent.remove(c);
        }
        recent.addFirst(c);
        if(recent.size()>5){
            recent.removeLast();
        }
        return c;
    }

    public boolean deleteContact(String name) {
        Contact removed = map.remove(name.toLowerCase());
        if (removed==null) {
            return false;
        }
        trie.delete(name);
        undoStack.push(new Action("DELETE",removed));
        recent.remove(removed);
        return true;
    }

    public List<Contact> searchByPrefix(String prefix) {
        List<Contact> result = new ArrayList<>();
        List<String> names= trie.startsWith(prefix);
        for(String name:names){
            Contact c = map.get(name);
            if (c != null) {
                result.add(c);
            }
        }
        return result;
    }


    public boolean undo() {
        if (undoStack.isEmpty()) {
            return false;                 // nothing to undo
        }
        Action last = undoStack.pop();
        Contact c = last.getContact();
        String key = c.getName().toLowerCase();

        if (last.getType().equals("ADD")) {
            map.remove(key);
            trie.delete(key);
            recent.remove(c);
        } else {
            map.put(key,c);
            trie.insert(key);
        }
        return true;
    }

    public List<Contact> getRecentSearches(){
        return new ArrayList<>(recent);
    }

    public List<Contact> getAllSorted() {
        TreeMap<String, Contact> sorted = new TreeMap<>(map);
        return new ArrayList<>(sorted.values());
    }

    public boolean markFavourite(String name, int priority) {
        Contact c = map.get(name.toLowerCase());
        if(c==null){
            return false;
        }
        c.setPriority(priority);
        return true;
    }

    public List<Contact> getTopFavourites(int k) {
        PriorityQueue<Contact> pq = new PriorityQueue<>(
                (a, b) -> b.getPriority() - a.getPriority()
        );
        List<Contact> res=new ArrayList<>();
        for(Contact c:map.values()){
            if(c.getPriority()>0) {
                pq.add(c);
            }
        }
        while(!pq.isEmpty() && k>res.size()){
            res.add(pq.poll());
        }
        return res;
    }
    public void clearUndo() {
        undoStack.clear();
    }
}
