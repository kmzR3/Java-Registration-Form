import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegistrationForm {

    private DefaultTableModel tableModel;
    private JTable dataTable;

    public RegistrationForm() {
        // Create the frame
        JFrame frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        frame.setLayout(new BorderLayout());

        // Panel for form fields
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Title Label
        JLabel titleLabel = new JLabel("Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(titleLabel, gbc);

        // Name Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Name: "), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(15);
        formPanel.add(nameField, gbc);

        // Mobile Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Mobile: "), gbc);
        gbc.gridx = 1;
        JTextField mobileField = new JTextField(15);
        formPanel.add(mobileField, gbc);

        // Gender Selection
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Gender: "), gbc);
        gbc.gridx = 1;
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        JPanel genderPanel = new JPanel();
        genderPanel.add(male);
        genderPanel.add(female);
        formPanel.add(genderPanel, gbc);

        // Date of Birth Selection
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("DOB: "), gbc);
        gbc.gridx = 1;
        String[] days = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30",
            "31",
        };
        String[] months = {
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
        };
        String[] years = {
            "2000",
            "2001",
            "2002",
            "2003",
            "2004",
            "2005",
            "2006",
            "2007",
            "2008",
            "2009",
            "2010",
            "2011",
            "2012",
        };
        JComboBox<String> dayBox = new JComboBox<>(days);
        JComboBox<String> monthBox = new JComboBox<>(months);
        JComboBox<String> yearBox = new JComboBox<>(years);
        JPanel dobPanel = new JPanel();
        dobPanel.add(dayBox);
        dobPanel.add(monthBox);
        dobPanel.add(yearBox);
        formPanel.add(dobPanel, gbc);

        // Address Field
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Address: "), gbc);
        gbc.gridx = 1;
        JTextArea addressArea = new JTextArea(3, 15);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        formPanel.add(addressScrollPane, gbc);

        // Contact Field
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Contact: "), gbc);
        gbc.gridx = 1;
        JTextArea contactArea = new JTextArea(3, 15);
        JScrollPane contactScrollPane = new JScrollPane(contactArea);
        formPanel.add(contactScrollPane, gbc);

        // Terms and Conditions Checkbox
        gbc.gridx = 1;
        gbc.gridy = 7;
        JCheckBox termsCheckBox = new JCheckBox("Accept Terms And Conditions.");
        formPanel.add(termsCheckBox, gbc);

        // Buttons Panel
        gbc.gridx = 1;
        gbc.gridy = 8;
        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        formPanel.add(buttonPanel, gbc);

        // Table to Display Submitted Data
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        tableModel.addColumn("Name");
        tableModel.addColumn("Mobile");
        tableModel.addColumn("Gender");
        tableModel.addColumn("DOB");
        tableModel.addColumn("Address");
        tableModel.addColumn("Contact");

        JScrollPane tableScrollPane = new JScrollPane(dataTable);

        // Split Pane (Form on Left, Table on Right)
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            formPanel,
            tableScrollPane
        );
        splitPane.setDividerLocation(400);

        // Add split pane to frame
        frame.add(splitPane, BorderLayout.CENTER);

        // Action Listener for Submit Button
        submitButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!termsCheckBox.isSelected()) {
                        JOptionPane.showMessageDialog(
                            frame,
                            "You must accept the Terms and Conditions!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    // Collect Data
                    String name = nameField.getText();
                    String mobile = mobileField.getText();
                    String gender = male.isSelected() ? "Male" : "Female";
                    String dob =
                        dayBox.getSelectedItem() +
                        " " +
                        monthBox.getSelectedItem() +
                        " " +
                        yearBox.getSelectedItem();
                    String address = addressArea.getText();
                    String contact = contactArea.getText();

                    // Add Data to Table
                    tableModel.addRow(
                        new Object[] {
                            name,
                            mobile,
                            gender,
                            dob,
                            address,
                            contact,
                        }
                    );

                    // Clear Fields
                    nameField.setText("");
                    mobileField.setText("");
                    genderGroup.clearSelection();
                    addressArea.setText("");
                    contactArea.setText("");
                    termsCheckBox.setSelected(false);
                }
            }
        );

        // Reset Button
        resetButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nameField.setText("");
                    mobileField.setText("");
                    genderGroup.clearSelection();
                    addressArea.setText("");
                    contactArea.setText("");
                    termsCheckBox.setSelected(false);
                }
            }
        );

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm());
    }
}
