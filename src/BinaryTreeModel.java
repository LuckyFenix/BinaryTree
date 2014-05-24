import java.util.ArrayList;

public class BinaryTreeModel
{
    Node rootNode;
    public BinaryTreeModel(int value)
    {
        this.rootNode = new Node();
        this.rootNode.addNode(value);
    }

    public void addNode(int value)
    {
        rootNode.addNode(value);
        System.out.println("add");
        printNods();
    }

    public void printNods()
    {
        System.out.print(rootNode.print());
        System.out.println();
    }

    public Node search(ArrayList<String> link)
    {
        return rootNode.search(link);
    }

    public ArrayList<String> getLink(int value)
    {
        ArrayList<String> arrayListLink = new ArrayList<>();
        String[] arrayLink = rootNode.getLink(value).split(" ");
        for (String anArrayLink : arrayLink) {
            if (!anArrayLink.equals(""))
                arrayListLink.add(anArrayLink);
        }
        return arrayListLink;
    }

    public void deleteNode(int value)
    {
        rootNode.delete(value);
    }

    public void balancing()
    {
        while (!rootNode.balancing());
    }

    public int getHeight()
    {
        return rootNode.getHeight();
    }

    public void changesNode(int oldValue, int newWalue)
    {
        deleteNode(oldValue);
        addNode(newWalue);
    }
}
