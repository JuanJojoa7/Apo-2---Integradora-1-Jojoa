package model;

public class Ranking {
    
    private Node root;
    
    private class Node {
        int key;
        Node left, right;
        
        public Node(int key) {
            this.key = key;
            left = right = null;
        }
    }
    
    public Ranking() {
        root = null;
    }
    
    public void insert(int key) { 
        root = insert(root, key);
    }
    
    private Node insert(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        }
        
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        
        return node;
    }
    
    public boolean search(int key) {
        return search(root, key);
    }
    
    private boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }
        
        if (key == node.key) {
            return true;
        }
        
        if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }
    
    public void inorder() {
        inorder(root);
    }
    
    private void inorder(Node node, int counter) {
        if (node != null) {
            inorder(node.right);
            System.out.print(counter + Player.getName + ": " + Player.getScore);
            counter++;
            inorder(node.left);
        }
    }
        public Node getMax(){
        return getMax(root);
    }

    public Node getMax(Node current){
        if(current==null){
            return null;
        }
        if(current.getRight()==null){
            return current;
        } else {
            getMax(current.getRight());
        }
        return null;
    }


    
}