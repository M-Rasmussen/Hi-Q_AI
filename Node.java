import java.util.ArrayList;
import java.util.List;

//Code for Node file from https://github.com/c05mic/GenericN-aryTree/blob/develop/src/main/java/com/c05mic/generictree/Node.java
//Generic Tree Node, that was used for the storage 


public class Node<T> {
    private T data;
    private List<Node<T>> children;
    private Node<T> parent;

    public Node(T data) {
        this.data = data;
        this.children = new ArrayList<Node<T>>();
    }

    public Node(Node<T> node) {
        this.data = node.getData();
        children = new ArrayList<Node<T>>();
    }

    public void addChild(Node<T> child) {
        child.setParent(this);
        children.add(child);
    }

    public void addChildAt(int index, Node<T> child) {
        child.setParent(this);
        this.children.add(index, child);
    }

    public void setChildren(List<Node<T>> children) {
        for (Node<T> child : children)
            child.setParent(this);

        this.children = children;
    }

    public void removeChildren() {
        this.children.clear();
    }

    public Node<T> removeChildAt(int index) {
        return children.remove(index);
    }

    public boolean removeChild(Node<T> childToBeDeleted) {
        List<Node<T>> list = getChildren();
        return list.remove(childToBeDeleted);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return this.children;
    }

    public Node<T> getChildAt(int index) {
        return children.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;

        if (obj instanceof Node) {
            if (((Node<?>) obj).getData().equals(this.data))
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

}