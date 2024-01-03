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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

public class TaskManagerGUI {
    private JFrame frame;
    private JPanel menuPanel;
    private JPanel headerPanel;
    private Mediator mediator;
    private ArrayList<TaskWithDeadline> deadlineTaskList;
    private ArrayList<TaskWithPriority> priorityTaskList;
    private ArrayList<TaskWithCheckList> checklistTaskList;
    private DeadlineTaskBuilder deadlineTaskBuilder;
    private PriorityTaskBuilder priorityTaskBuilder;
    private CheckListTaskBuilder checkListTaskBuilder;

    public TaskManagerGUI(Mediator mediator) {
        this.mediator = mediator;
        deadlineTaskList = new ArrayList<>();
        priorityTaskList = new ArrayList<>();
        checklistTaskList = new ArrayList<>();
        deadlineTaskBuilder = new DeadlineTaskBuilder();
        priorityTaskBuilder = new PriorityTaskBuilder();
        checkListTaskBuilder = new CheckListTaskBuilder();
        initialize();
    }
    private void initialize() {
        frame = new JFrame("Task Manager");
        frame.setSize(400, 300);
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
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recreateFrame(createNewButtonPanel());
            }
        });
        JButton showTasksButton = new JButton("Show Tasks");
        showTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton showHistoryButton = new JButton("Show History");

        buttonPanel.add(addTaskButton, gbc);
        buttonPanel.add(showTasksButton, gbc);
        buttonPanel.add(showHistoryButton, gbc);

        menuPanel.add(headerPanel, BorderLayout.NORTH);
        menuPanel.add(buttonPanel, BorderLayout.CENTER);


        //2. Wyświetlanie wszystkich zadań
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
        panel.add(deadlineTaskButton, gbc);
        panel.add(priorityTaskButton, gbc);
        panel.add(checklistTaskButton, gbc);

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
                deadlineTaskList.add(task);
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
                priorityTaskList.add(task);
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
            checklistTaskList.add(task);
            recreateFrame(menuPanel);
        });
        panel.add(instructionPanel, BorderLayout.NORTH);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);
        return panel;
    }




}
