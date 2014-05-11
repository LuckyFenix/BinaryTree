import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JFrame implements ActionListener
{
    private int binaryTreeCount = 0;
    private JPanel globalPanel = new JPanel(new BorderLayout());
    private JPanel treesPanel = new JPanel();
    private ArrayList<BinaryTree> binaryTreesArray = new ArrayList<BinaryTree>();
    private ArrayList<JTextField> textFieldArray = new ArrayList<JTextField>();
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final JPanel btnPanel = new JPanel(new GridBagLayout());
    private JPanel binaryTreePanel;
    private final JButton addTreeBtn;
    private final JButton addNewNode;
    private final JButton deleteNode;
    private final JButton changesNode;
    private final JButton balancingTree;

    public Main()
    {
        setSize(800, 600);

        addTreeBtn = new JButton("Добавить дерево");
        addNewNode = new JButton("Добавить новый узел");
        deleteNode = new JButton("Удалить узел");
        changesNode = new JButton("Изменить узел");
        balancingTree = new JButton("Сбалансировать дерево");

        addNewNode.addActionListener(this);
        deleteNode.addActionListener(this);
        changesNode.addActionListener(this);
        balancingTree.addActionListener(this);

        addTreeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = JOptionPane.showInputDialog(getThis(), "Введите значение корня дерева:");
                    if (s != null) {
                        int n = Integer.parseInt(s);
                        binaryTreesArray.add(new BinaryTree(n));
                        textFieldArray.add(new JTextField(10));
                        binaryTreePanel = new JPanel(new GridBagLayout());
                        binaryTreePanel.add(textFieldArray.get(textFieldArray.size() - 1),
                                new GBC(0, 0).setInsets(5).setAnchor(GridBagConstraints.WEST));
                        binaryTreePanel.add(binaryTreesArray.get(binaryTreesArray.size() - 1),
                                new GBC(0, 1));
                        panel.add(binaryTreePanel, new GBC(0, binaryTreeCount).setWeight(1, 1));

                        revalidate();
                        repaint();

                        binaryTreeCount++;
                    }
                } catch (NumberFormatException e1)
                {
                    JOptionPane.showMessageDialog(getThis(), "Неверное значение");
                }
            }
        });

        treesPanel.add(new JScrollPane(panel));
        btnPanel.add(addTreeBtn, new GBC(0, 0).setInsets(5));
        btnPanel.add(addNewNode, new GBC(0, 1).setInsets(5));
        btnPanel.add(deleteNode, new GBC(0, 2).setInsets(5));
        btnPanel.add(changesNode, new GBC(0, 3).setInsets(5));
        btnPanel.add(balancingTree, new GBC(0, 4).setInsets(5));

        globalPanel.add(new JScrollPane(treesPanel), BorderLayout.CENTER);
        globalPanel.add(btnPanel, BorderLayout.EAST);
        add(globalPanel);
    }

    public JFrame getThis()
    {
        return this;
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Main main = new Main();
                main.setLocationRelativeTo(null);
                main.setVisible(true);
                main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        for (int i = 0; i < binaryTreesArray.size(); i++)
        {
            final int finalI = i;
            Runnable runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        String s = textFieldArray.get(finalI).getText();
                        if (e.getSource().equals(addNewNode))
                        {
                            int i1 = Integer.parseInt(s);
                            binaryTreesArray.get(finalI).addNewNode(i1);
                            getThis().revalidate();
                            textFieldArray.get(finalI).setText("");
                        }
                        if (e.getSource().equals(deleteNode))
                        {
                            int i1 = Integer.parseInt(s);
                            binaryTreesArray.get(finalI).deleteNode(i1);
                            getThis().revalidate();
                            textFieldArray.get(finalI).setText("");
                        }
                        if (e.getSource().equals(changesNode))
                        {
                            binaryTreesArray.get(finalI).
                                    changesNode(Integer.parseInt(s.split(" ")[0]),
                                            Integer.parseInt(s.split(" ")[1]));
                            getThis().revalidate();
                            textFieldArray.get(finalI).setText("");
                        }
                        if (e.getSource().equals(balancingTree))
                        {
                            binaryTreesArray.get(finalI).balancingTree();
                            getThis().revalidate();
                        }
                    } catch (Exception e1) {}
                }
            };
            runnable.run();
        }
    }
}
