package View;

import Controller.mediator.Mediator;
import Model.builders.CheckListTaskBuilder;
import Model.builders.DeadlineTaskBuilder;
import Model.builders.PriorityTaskBuilder;
import Model.tasks.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

public class TaskManagerGUI {
    private JFrame frame;
    private JPanel menuPanel;
    private JPanel headerPanel;
    private Mediator mediator;
    private Map<UUID, TaskWithDeadline> deadlineTaskMap;
    private Map<UUID, TaskWithPriority> priorityTaskMap;
    private Map<UUID, TaskWithCheckList> checklistTaskMap;
    private DeadlineTaskBuilder deadlineTaskBuilder;
    private PriorityTaskBuilder priorityTaskBuilder;
    private CheckListTaskBuilder checkListTaskBuilder;

    public TaskManagerGUI(Mediator mediator) {
        this.mediator = mediator;
        deadlineTaskMap = new HashMap<>();
        priorityTaskMap = new HashMap<>();
        checklistTaskMap = new HashMap<>();
        deadlineTaskBuilder = new DeadlineTaskBuilder();
        priorityTaskBuilder = new PriorityTaskBuilder();
        checkListTaskBuilder = new CheckListTaskBuilder();
        initialize();
    }
    private void initialize() {
        frame = new JFrame("Task Manager");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createMenuPanel());
        frame.setVisible(true);
    }

    private JPanel createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        //nagłówek- menu
        headerPanel = new JPanel();
        JLabel menuLabel = new JLabel("MENU");
        menuLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        headerPanel.add(menuLabel);

        // panel dla przycisków
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 0, 0, 0);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setPreferredSize(new Dimension(200, 50));
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recreateFrame(createNewButtonPanel());
            }
        });
        //2. Wyświetlanie wszystkich zadań
        JButton showTasksButton = new JButton("Show Tasks");
        showTasksButton.setPreferredSize(new Dimension(200, 50));
        showTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recreateFrame(displayTasks());
            }
        });
        JButton showCompletedTasks = new JButton("Show Completed Tasks");
        showCompletedTasks.setPreferredSize(new Dimension(200, 50));
        showCompletedTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recreateFrame(displayCompletedTasks());
            }
        });
        buttonPanel.add(addTaskButton, gbc);
        buttonPanel.add(showTasksButton, gbc);
        buttonPanel.add(showCompletedTasks, gbc);

        menuPanel.add(headerPanel, BorderLayout.NORTH);
        menuPanel.add(buttonPanel, BorderLayout.CENTER);



        //JButton completeTaskButton = new JButton("Complete Task");
        //3. Wyświetlanie historii

        return menuPanel;
    }
    private void recreateFrame(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void addTaskToMap(DefaultListModel<String> model, List<? extends Task> tasks) {
        for (Task task : tasks) {

            model.addElement(task.getName() + " (" + task.getId() + ")");
        }
    }
    private JPanel createNewButtonPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);
        //header:
        JPanel choicePanel = new JPanel();
        JLabel choiceLabel = new JLabel("Choose type of a new task");
        choiceLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        choicePanel.add(choiceLabel);

        JButton deadlineTaskButton = new JButton("Task with deadline");
        deadlineTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recreateFrame(createNewDeadlineTaskPanel());
            }
        });
        JButton priorityTaskButton = new JButton("Task with priority");
        priorityTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recreateFrame(createNewPriorityTaskPanel());
            }
        });
        JButton checklistTaskButton = new JButton("Task with checklist");
        checklistTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recreateFrame(createNewChecklistTaskPanel());
            }
        });
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> recreateFrame(menuPanel));

        panel.add(deadlineTaskButton, gbc);
        panel.add(priorityTaskButton, gbc);
        panel.add(checklistTaskButton, gbc);
        panel.add(returnButton, gbc);

        return panel;
    }

    private JPanel createNewDeadlineTaskPanel() {
        JPanel panel = new JPanel();

        JPanel instructionPanel = new JPanel();
        JLabel instructionLabel = new JLabel("Insert information about new task:");
        instructionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        instructionPanel.add(instructionLabel);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);
        //name
        infoPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(20);
        infoPanel.add(nameField, gbc);
        //description
        infoPanel.add(new JLabel("Description:"));
        JTextField descField = new JTextField(20);
        infoPanel.add(descField, gbc);
        //deadline
        infoPanel.add(new JLabel("Deadline (yyyy-mm-dd):"));
        JTextField deadlineField = new JTextField(20);
        infoPanel.add(deadlineField, gbc);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descField.getText();
                LocalDate deadline = null;
                try {
                    deadline = LocalDate.parse(deadlineField.getText());
                } catch (DateTimeParseException ex) {
                    System.out.println("Incorrect date format");
                }
                TaskWithDeadline task = deadlineTaskBuilder.resetTask()
                        .setName(name)
                        .setDescription(description)
                        .setDeadline(deadline)
                        .getResult();
                deadlineTaskMap.put(task.getId(), task);
                recreateFrame(menuPanel);
            }
        });
        panel.add(instructionPanel, BorderLayout.NORTH);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createNewPriorityTaskPanel(){
        JPanel panel = new JPanel();
        JPanel instructionPanel = new JPanel();
        JLabel instructionLabel = new JLabel("Insert information about new task:");
        instructionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        instructionPanel.add(instructionLabel);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);
        //name
        infoPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(20);
        infoPanel.add(nameField, gbc);
        //description
        infoPanel.add(new JLabel("Description:"));
        JTextField descField = new JTextField(20);
        infoPanel.add(descField, gbc);
        //deadline
        infoPanel.add(new JLabel("Priority"));
        JComboBox<Priority> priorityComboBox = new JComboBox<>(Priority.values());
        infoPanel.add(priorityComboBox, gbc);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descField.getText();
                Priority priority = (Priority) priorityComboBox.getSelectedItem();
                TaskWithPriority task = priorityTaskBuilder.resetTask()
                        .setName(name)
                        .setDescription(description)
                        .setPriority(priority)
                        .getResult();
                priorityTaskMap.put(task.getId(), task);
                recreateFrame(menuPanel);
            }
        });
        panel.add(instructionPanel, BorderLayout.NORTH);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createNewChecklistTaskPanel() {
        JPanel panel = new JPanel();
        JPanel instructionPanel = new JPanel();
        JLabel instructionLabel = new JLabel("Insert information about new task:");
        instructionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        instructionPanel.add(instructionLabel);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);
        //name
        infoPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(20);
        infoPanel.add(nameField, gbc);
        //description
        infoPanel.add(new JLabel("Description:"));
        JTextField descField = new JTextField(20);
        infoPanel.add(descField, gbc);
        //checklist
        infoPanel.add(new JLabel("Add Subtask: "));
        JTextField subtaskField = new JTextField(20);
        panel.add(subtaskField);

        DefaultListModel<String> checklistModel = new DefaultListModel<>();
        JList<String> checklist = new JList<>(checklistModel);
        JScrollPane checklistScrollPane = new JScrollPane(checklist);
        panel.add(checklistScrollPane);

        JButton addChecklistItemButton = new JButton("Add Item");
        addChecklistItemButton.addActionListener(e -> {
            String item = subtaskField.getText();
            if (!item.isEmpty()) {
                checklistModel.addElement(item);
                subtaskField.setText("");
            }
        });
        infoPanel.add(addChecklistItemButton);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String description = descField.getText();
            ArrayList<String> subtasks = Collections.list(checklistModel.elements());;
            TaskWithCheckList task = checkListTaskBuilder.resetTask()
                    .setName(name)
                    .setDescription(description)
                    .setChecklist(subtasks)
                    .getResult();
            checklistTaskMap.put(task.getId(), task);
            recreateFrame(menuPanel);
        });
        panel.add(instructionPanel, BorderLayout.NORTH);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel displayTasks(){
        JPanel panel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        JList<String> deadlineList = new JList<>(createListModel(deadlineTaskMap));
        tabbedPane.addTab("Deadline Tasks", new JScrollPane(deadlineList));

        JList<String> priorityList = new JList<>(createListModel(priorityTaskMap));
        tabbedPane.addTab("Priority Tasks", new JScrollPane(priorityList));

        JList<String> checklistList = new JList<>(createListModel(checklistTaskMap));
        tabbedPane.addTab("Checklist Tasks", new JScrollPane(checklistList));
        // selectionListenery

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveCopyButton = new JButton("Save Copy");
        JButton restoreCopyButton = new JButton("Restore Copy");
        JButton completeButton = new JButton("Mark as complete");
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> recreateFrame(menuPanel));
        buttonPanel.add(saveCopyButton);
        buttonPanel.add(restoreCopyButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(returnButton);
        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private DefaultListModel<String> createListModel(Map<UUID, ? extends Task> tasks) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Task task : tasks.values()) {
            model.addElement(task.getName() + " (" + task.getId() + ")");
        }
        return model;
    }

    private JPanel displayCompletedTasks(){
        JPanel panel = new JPanel(new BorderLayout());

        JPanel headerPanel = new JPanel();
        JLabel title = new JLabel("Completed Tasks");
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 15));
        headerPanel.add(title);
        panel.add(headerPanel, BorderLayout.NORTH);

        JTextArea completedTaskArea = new JTextArea(15, 30);
        completedTaskArea.setText(readCompletedFile());
        completedTaskArea.setEditable(false);
        panel.add(completedTaskArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> recreateFrame(menuPanel));
        buttonPanel.add(returnButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private String readCompletedFile() {
        StringBuilder completed = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("completedTasks.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                completed.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            return "There was a problem with reading file";
        }
        return completed.toString();
    }


}
