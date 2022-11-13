import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatBot extends JFrame implements ActionListener {
    private final Logger logger = LogManager.getRootLogger();
    private final String TITLE_OF_PROGRAM = "Виталик - ваш линчый чат-бот";
    private final int START_LOCATION = 300;
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 450;
    private final String BUTTON_ENTER = "Enter";

    JTextArea dialogue;
    JTextField message;
    Bot bot;

    public static void main(String[] args) {
        new ChatBot();

    }

    ChatBot() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        //area for dialog
        dialogue = new JTextArea();
        dialogue.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        // panel for checkbox, message field and button
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton(BUTTON_ENTER);
        enter.addActionListener(this);
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
        bot = new Bot();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String answer = bot.sayInReturn(message.getText());
        if (message.getText().trim().length() > 0) {
            dialogue.append(message.getText().concat("\n"));
            dialogue.append(TITLE_OF_PROGRAM.substring(0,8) +
                    answer + "\n");
        }
        if (bot.getAnswerForUnknownQuestion().equals(answer)){
            logger.info("Запрос: " + message.getText());
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}
