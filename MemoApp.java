import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MemoApp extends JFrame {

    private JTextArea textArea;

    public MemoApp() {
        setTitle("メモ帳");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("ファイル");

        JMenuItem openItem = new JMenuItem("開く");
        JMenuItem saveItem = new JMenuItem("保存");

        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader =
                    new BufferedReader(
                    new FileReader(chooser.getSelectedFile()))) {

                textArea.read(reader, null);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "ファイルを開けません");
            }
        }
    }

    private void saveFile() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer =
                    new BufferedWriter(
                    new FileWriter(chooser.getSelectedFile()))) {

                textArea.write(writer);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "保存できません");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemoApp::new);
    }
}