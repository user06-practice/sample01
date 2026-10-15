import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemoApp extends JFrame {

    private JTextArea textArea;
    private JLabel statusLabel;
    private JLabel clockLabel;

    public MemoApp() {

        setTitle("メモ帳");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color buttonColor = new Color(135, 206, 250);

        // ===== 上部 =====
        JPanel topPanel = new JPanel(new BorderLayout());

        clockLabel = new JLabel("", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Meiryo", Font.BOLD, 32));
        clockLabel.setForeground(new Color(0, 102, 204));
        clockLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 10, 15, 10));

        topPanel.add(clockLabel, BorderLayout.NORTH);

        // ===== ボタン =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));

        JButton newButton = new JButton("📝 新規");
        JButton openButton = new JButton("📂 開く");
        JButton saveButton = new JButton("💾 保存");
        JButton deleteButton = new JButton("🗑 削除");

        Font buttonFont =
                new Font("Meiryo", Font.BOLD, 14);

        newButton.setFont(buttonFont);
        openButton.setFont(buttonFont);
        saveButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

        newButton.setBackground(buttonColor);
        openButton.setBackground(buttonColor);
        saveButton.setBackground(buttonColor);
        deleteButton.setBackground(
                new Color(255, 160, 160));

        newButton.addActionListener(
                e -> newFile());

        openButton.addActionListener(
                e -> openFile());

        saveButton.addActionListener(
                e -> saveFile());

        deleteButton.addActionListener(
                e -> deleteText());

        buttonPanel.add(newButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);

        topPanel.add(buttonPanel,
                BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // ===== メモ欄 =====
        textArea = new JTextArea();

        textArea.setFont(
                new Font("Meiryo",
                        Font.PLAIN,
                        18));

        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);

        add(new JScrollPane(textArea),
                BorderLayout.CENTER);

        // ===== ステータス =====
        statusLabel =
                new JLabel("メモを入力してください");

        statusLabel.setFont(
                new Font("Meiryo",
                        Font.PLAIN,
                        13));

        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        5, 10, 5, 10));

        add(statusLabel,
                BorderLayout.SOUTH);

        // ===== 時計 =====
        Timer timer =
                new Timer(1000, e -> {

                    LocalDateTime now =
                            LocalDateTime.now();

                    clockLabel.setText(
                            "📅 "
                                    + now.format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyy/MM/dd (E)"))
                                    + "      "
                                    + "🕒 "
                                    + now.format(
                                    DateTimeFormatter.ofPattern(
                                            "HH:mm:ss")));
                });

        timer.setInitialDelay(0);
        timer.start();

        setVisible(true);
    }

    // ===== 新規 =====
    private void newFile() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "新しいメモを作成しますか？",
                        "確認",
                        JOptionPane.YES_NO_OPTION);

        if (result ==
                JOptionPane.YES_OPTION) {

            textArea.setText("");

            statusLabel.setText(
                    "新しいメモを作成しました");

            setTitle("メモ帳");
        }
    }

    // ===== 開く =====
    private void openFile() {

        JFileChooser chooser =
                new JFileChooser();

        if (chooser.showOpenDialog(this)
                == JFileChooser.APPROVE_OPTION) {

            File file =
                    chooser.getSelectedFile();

            try (BufferedReader reader =
                         new BufferedReader(
                                 new FileReader(file))) {

                textArea.read(reader, null);

                setTitle(
                        "メモ帳 - "
                                + file.getName());

                statusLabel.setText(
                        "開いているファイル : "
                                + file.getAbsolutePath());

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "ファイルを開けません");
            }
        }
    }

    // ===== 保存 =====
    private void saveFile() {

        JFileChooser chooser =
                new JFileChooser();

        if (chooser.showSaveDialog(this)
                == JFileChooser.APPROVE_OPTION) {

            File file =
                    chooser.getSelectedFile();

            try (BufferedWriter writer =
                         new BufferedWriter(
                                 new FileWriter(file))) {

                textArea.write(writer);

                setTitle(
                        "メモ帳 - "
                                + file.getName());

                statusLabel.setText(
                        "保存先 : "
                                + file.getAbsolutePath());

                JOptionPane.showMessageDialog(
                        this,
                        "保存しました！");

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "保存できません");
            }
        }
    }

    // ===== 削除 =====
    private void deleteText() {

        if (textArea.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "削除する内容がありません");

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "メモをすべて削除しますか？",
                        "削除確認",
                        JOptionPane.YES_NO_OPTION);

        if (result ==
                JOptionPane.YES_OPTION) {

            textArea.setText("");

            statusLabel.setText(
                    "メモを削除しました");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                MemoApp::new);
    }
}