package model;

public class Ranking {
    private Player root;
    public Ranking(Player root) {
        this.root = null;
    }

    public Ranking() {
        root = null;
    }

    public void insert(Player node){
        if(root==null){
            root = node;
        }else{
            insert(node, root);
        }
        return;
    }
    private void insert(Player node, Player current){
        if(node.getScore()<=current.getScore()){
            if(current.getLeft()==null){
                current.setLeft(node);
            } else {
                insert(node, current.getLeft());
            }
            return;
        }
        if(node.getScore()>current.getScore()){
            if(current.getRight()==null){
                current.setRight(node);
            } else {
                insert(node, current.getRight());
            }
            return;
        }
        return;
    }

    public void inorder() {
        inorder(root, 1);
    }
    private void inorder(Player node, int counter) {
        if (node != null) {
            if(counter<6){
                inorder(node.getRight(), counter);
                System.out.println("");
                System.out.println("Ranking: "+ counter + ". " + node.getName() + ": " + node.getScore());
                counter++;
                inorder(node.getLeft(), counter);
            }else{
                return;
            }
        }
        return;
    }


}