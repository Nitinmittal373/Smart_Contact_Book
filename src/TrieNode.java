import java.util.HashMap;
import java.util.TreeMap;

public class TrieNode {
    private final TreeMap<Character,TrieNode> children=new TreeMap<>();

    private boolean isEndOfWord=false;

    public TreeMap<Character, TrieNode> getChildren(){
        return children;
    }

    public boolean isEndOfWord(){
        return isEndOfWord;
    }

    public void setEndOfWord(boolean isEndOfWord){
        this.isEndOfWord=isEndOfWord;
    }
}
