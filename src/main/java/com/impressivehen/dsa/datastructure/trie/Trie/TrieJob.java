package com.impressivehen.dsa.datastructure.trie.Trie;

import com.impressivehen.dsa.Job;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "job", havingValue = "TrieJob")
public class TrieJob implements Job {
    @Override
    public void execute() throws Exception {
        Trie trie = new Trie();
        trie.insert("apple");

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));

        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}

class TrieNode {
    public TrieNode[] children;
    public boolean isEndOfWord;

    public TrieNode() {
        // lowercase English letters
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * O(n) time, iterate every character in word
     * @param word
     */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.isEndOfWord = true;
    }

    /**
     * Check if a word exists in the Trie
     * Check if the word's last character node exists and isEndOfWord
     * O(n) time, iterate every character in word
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode node = findWordEndNode(word);

        return node != null && node.isEndOfWord;
    }

    /**
     * Returns word's last character node if exists, else return null
     * O(n) time
     * @return
     */
    private TrieNode findWordEndNode(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }

            node = node.children[index];
        }

        return node;
    }

    /**
     * Checks if a word exists in the Trie that starts with the prefix
     * O(n) time
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return findWordEndNode(prefix) != null;
    }
}