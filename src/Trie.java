import java.util.ArrayList;
import java.util.List;


public class Trie {
    private final TrieNode root=new TrieNode();

    public void insert(String word){
        TrieNode current=root;
        for(char ch: word.toLowerCase().toCharArray()){
            if(!current.getChildren().containsKey(ch)){
                current.getChildren().put(ch,new TrieNode());
            }
            current=current.getChildren().get(ch);
        }
        current.setEndOfWord(true);
    }

    private TrieNode findNode(String prefix){
        TrieNode current=root;
        for(char ch:prefix.toLowerCase().toCharArray()) {
            if (!current.getChildren().containsKey(ch)) return null;
            current=current.getChildren().get(ch);
        }
        return current;
    }

    private void collect(TrieNode node, String soFar, List<String> results) {
        if(node.isEndOfWord()){
            results.add(soFar);
        }
        for(char ch:node.getChildren().keySet()){
            collect(node.getChildren().get(ch),soFar+ch,results);
        }
    }

    public List<String> startsWith(String prefix){
        List<String> results=new ArrayList<>();
        TrieNode node=findNode(prefix);
        if(node==null){
            return new ArrayList<>();
        }
        collect(node,prefix.toLowerCase(),results);
        return results;
    }

    public boolean delete(String word) {
        TrieNode node = findNode(word);
        if(node==null || !node.isEndOfWord()){
            return false;
        }

//        node.setEndOfWord(false);
        remove(root,word.toLowerCase(),0);
        return true;

    }

    private boolean remove(TrieNode node, String word, int index) {
        if (index == word.length()) {

            if(!node.isEndOfWord()) return false;
            node.setEndOfWord(false);
            return node.getChildren().isEmpty();
        }

        char ch = word.charAt(index);
        TrieNode child = node.getChildren().get(ch);
        if (child == null) return false;

        boolean removeChild = remove(child, word, index + 1);

        if (removeChild) {
            node.getChildren().remove(ch);
            return node.getChildren().isEmpty() && !node.isEndOfWord();
        }
        return false;
    }
}
