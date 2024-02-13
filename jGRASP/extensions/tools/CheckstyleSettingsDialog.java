
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.Arrays;
import java.util.EnumSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jgrasp.tool.ActionContext;
import jgrasp.tool.BasicToolUtil;
import jgrasp.tool.ComboFileChooser;
import jgrasp.tool.JGraspDialog;
import jgrasp.tool.URTextField;


/** Dialog for changing Checkstyle settings. **/
public class CheckstyleSettingsDialog {

   /** Checkstyle home selection combo. **/
   private ComboFileChooser homeCombo;
   
   /** Checks file selection combo. **/
   private ComboFileChooser checksCombo;
   
   /** Command line flags field. **/
   private JTextField flagsField;
   
   /** Jvm command line flags field. **/
   private JTextField javaFlagsField;
   
   /** Checkbox for controlling whether test source
    *  files are checked. **/
   private JCheckBox processTestCKB;

   /** Checkbox for controlling toolbar item visibility. **/
   private JCheckBox hideTBICKB;
   
   /** The current dialog. **/
   private JDialog dialog;
   
   /** The tool the tool for which this dialog is modifying
    *  the settings. **/
   private CheckstyleTool tool;
   
   /** The last selected checkstyle home directory. **/
   private File prevHome;
   
   /** The current simple default checks file, or null if there is none. **/
   private String simpleDefault;
   
   /** The current advanced default checks file, or null if there is none. **/
   private String advancedDefault;
   
   /** Simple checks selection label. **/ 
   private static String simpleChecksLabel = "<Simple Checks>";
   
   /** Advanced checks selection label. **/ 
   private static String advancedChecksLabel = "<Advanced Checks>";
 

   /** Creates a new CheckstyleSettingsDialog.
    *
    *  @param csTool the tool for which this dialog is modifying
    *  the settings. **/
   public CheckstyleSettingsDialog(final CheckstyleTool csTool) {
      tool = csTool;
   }


   /** Creates the configure dialog.
    *
    *  @param context action context.
    *
    *  @param settings the settings that the dialog will use
    *  and modify. **/
   public void showDialog(final ActionContext context,
         final CheckstyleSettings settings) {
      Frame parent = context.getDialogParent();
      String[] priorHomeHistory = null;
      String[] priorChecksHistory = null;
      if (dialog != null && parent != dialog.getParent()) {
         // Rebuild if parent is different.
         priorHomeHistory = homeCombo.getHistory();
         priorChecksHistory = checksCombo.getHistory();
         dialog.dispose();
         dialog = null;
      }
      if (dialog == null) {
         dialog = createDialog(parent);
         dialog.pack();
      }
   
      // Initialize.
      homeCombo.setFixedItems(settings.getCheckstyleHomes());
      File checkstyleHome = settings.getCheckstyleHome();
      if (checkstyleHome != null) {
         homeCombo.setSelectedFile(checkstyleHome.getAbsolutePath());
      }
      setPossibleChecks();
      File checksFile = settings.getChecksFile();
      if (checksFile != null) {
         checksCombo.setSelectedFile(checksFile.getAbsolutePath());
      }
   
      if (priorHomeHistory != null) {
         homeCombo.setHistory(priorHomeHistory);
      }
      if (priorChecksHistory != null) {
         checksCombo.setHistory(priorChecksHistory);
      }
      // In case the values are not in the fixed items or history.
      homeCombo.storeHistory();
      checksCombo.storeHistory();
   
      flagsField.setText(settings.getFlags());
      javaFlagsField.setText(settings.getJavaFlags());
      processTestCKB.setSelected(settings.getProcessTests());
      hideTBICKB.setSelected(settings.hideToolbarItems());
   
      dialog.setLocationRelativeTo(parent);
      dialog.setVisible(true);
   }
   


   /** Creates the dialog.
    *
    *  @param parent the dialog parent frame.
    *
    *  @return the newly created dialog. **/
   private JDialog createDialog(final Frame parent) {
      final JDialog dg = JGraspDialog.createDialog(parent, false);
      dg.setTitle("Checkstyle Tool Settings");
      final GridBagLayout layout = new GridBagLayout();
      final JLabel label = new JLabel("Checkstyle Home or Jar");
      final JLabel label2 = new JLabel("Checks File");
      String l = "Checkstyle Flags";
      final JLabel label3 = new JLabel(l);
      String l2 = "Java Flags";
      final JLabel label4 = new JLabel(l2);
      JPanel buttonPanel = new JPanel(false);
      JComponent contentPanel = 
         new JPanel(false) {
            public void doLayout() {
               int sp = BasicToolUtil.getSpacing();
               if (spacing != sp) {
                  spacing = sp;
                  Component[] lc = new Component[] { label, label2, label3, label4,
                        processTestCKB };
                  for (Component cmp : lc) {
                     GridBagConstraints c = layout.getConstraints(cmp);
                     c.insets.top = spacing;
                     c.insets.left = spacing;
                     c.insets.right = spacing;
                     layout.setConstraints(cmp, c);
                  }
                  
                  lc = new Component[] { homeCombo, checksCombo, flagsField, javaFlagsField };
                  for (Component cmp : lc) {
                     GridBagConstraints c = layout.getConstraints(cmp);
                     c.insets.top = spacing;
                     c.insets.right = spacing;
                     layout.setConstraints(cmp, c);
                  }
                  
                  lc = new Component[] { hideTBICKB, buttonPanel };
                  for (Component cmp : lc) {
                     GridBagConstraints c = layout.getConstraints(cmp);
                     c.insets.top = spacing;
                     c.insets.left = spacing;
                     c.insets.right = spacing;
                     c.insets.bottom = spacing;
                     layout.setConstraints(cmp, c);
                  }
               }
               super.doLayout();
            }
         };
      dg.setContentPane(contentPanel);
      
      GridBagConstraints constraints = new GridBagConstraints();
      Insets insets = constraints.insets;
      int spacing = BasicToolUtil.getSpacing();
      contentPanel.setLayout(layout);
      contentPanel.add(label);
      constraints.weightx = 1.0;
      constraints.weighty = 1.0;
      constraints.fill = GridBagConstraints.HORIZONTAL;
      insets.top = spacing;
      insets.left = spacing;
      insets.right = spacing;
      insets.bottom = 0;
      layout.setConstraints(label, constraints);
   
      homeCombo = new ComboFileChooser("Checkstyle Home or Jar",
            null,
            EnumSet.of(ComboFileChooser.Flags.FILE_OR_DIRECTORY),
            Arrays.asList(new String[] { "jar" }));
      homeCombo.addActionListener(
         (e)-> {
            if (!e.getActionCommand().equals("comboBoxChanged")) {
               return;
            }
            setPossibleChecks();
         });
   
      contentPanel.add(homeCombo);   
      constraints.weightx = 1000.0;
      constraints.gridwidth = GridBagConstraints.REMAINDER;
      insets.left = 0;
      layout.setConstraints(homeCombo, constraints);
      
      contentPanel.add(label2);
      constraints.weightx = 1.0;
      constraints.gridwidth = 1;
      insets.left = spacing;
      layout.setConstraints(label2, constraints);
   
      checksCombo = new ComboFileChooser("Checks File",
            null,
            EnumSet.of(ComboFileChooser.Flags.FILE),
            Arrays.asList(new String[] { "xml" }));
      contentPanel.add(checksCombo);
   
      insets.left = spacing;
      insets.top = spacing;
   
      constraints.gridwidth = GridBagConstraints.REMAINDER;
      constraints.weightx = 1000.0;
      insets.left = 0;
      layout.setConstraints(checksCombo, constraints);
   
      contentPanel.add(label3);
      constraints.weightx = 1.0;
      constraints.gridwidth = 1;
      insets.left = spacing;
      layout.setConstraints(label3, constraints);
   
      flagsField = new URTextField(l);
      contentPanel.add(flagsField);   
   
      constraints.weightx = 1000.0;
      constraints.gridwidth = GridBagConstraints.REMAINDER;
      insets.left = 0;
      layout.setConstraints(flagsField, constraints);
      
      contentPanel.add(label4);
      constraints.weightx = 1.0;
      constraints.gridwidth = 1;
      insets.left = spacing;
      layout.setConstraints(label4, constraints);
   
      javaFlagsField = new URTextField(l2);
      contentPanel.add(javaFlagsField);   
   
      constraints.weightx = 1000.0;
      constraints.gridwidth = GridBagConstraints.REMAINDER;
      insets.left = 0;
      layout.setConstraints(javaFlagsField, constraints);
      
      processTestCKB = new JCheckBox("Check Test Files");
      contentPanel.add(processTestCKB);
      insets.left = spacing;
      layout.setConstraints(processTestCKB, constraints);
   
      hideTBICKB = new JCheckBox("Hide Toolbar Items");
      contentPanel.add(hideTBICKB);
      insets.bottom = spacing;
      layout.setConstraints(hideTBICKB, constraints);
      
      buttonPanel.setLayout(new BorderLayout());
      contentPanel.add(buttonPanel);
      constraints.fill = GridBagConstraints.HORIZONTAL;
      layout.setConstraints(buttonPanel, constraints);
      
      JButton okButton = new JButton("OK");
      okButton.addActionListener((e)->accept());
      buttonPanel.add(okButton, "West");
   
      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener((e)->dg.setVisible(false));
      buttonPanel.add(cancelButton, "East");
      
      return dg;
   }
   
   
   /** Component spacing. **/
   private int spacing;
   
   
   /** Sets the possible checks based on the current home directory
    *  selection. **/
   private void setPossibleChecks() {
      String hs = homeCombo.getSelectedFile();
      File hf = null;
      if (hs.length() > 0) {
         hf = new File(hs);
      }
      String[] checks = CheckstyleSettings.findChecks(hf);
      String oldFile = checksCombo.getSelectedFile();
      boolean wasDefault = false;
      boolean wasAdvancedDefault = false;
      if (prevHome != null && hf != null
            && !prevHome.equals(hf)) {
         CheckstyleSettings oldSet = new CheckstyleSettings(
               prevHome, null, true, true, "", "");
         File oldDefault = oldSet.getChecksFile();
         if (oldDefault != null
               && oldDefault.getAbsolutePath().equals(oldFile)) {
            wasDefault = true;
         }
         else {
            File oldAdvancedDefault = oldSet.getAdvancedChecks();
            if (oldAdvancedDefault != null
                  && oldAdvancedDefault.getAbsolutePath().equals(oldFile)) {
               wasAdvancedDefault = true;
            }
         }
      }
      prevHome = hf;
   
      CheckstyleSettings newSet = new CheckstyleSettings(
            hf, null, true, true, "", "");
      File newDefaultFile = newSet.getChecksFile();
      File newAdvancedDefaultFile = newSet.getAdvancedChecks();
      if (newDefaultFile != null || newAdvancedDefaultFile != null) {
         String[] nchecks = new String[checks.length
               + ((newDefaultFile != null)? 1 : 0)
               + ((newAdvancedDefaultFile != null)? 1 : 0)];
         System.arraycopy(checks, 0, nchecks, nchecks.length - checks.length, checks.length);
         int ind = 0;
         if (newDefaultFile != null) {
            nchecks[ind++] = simpleChecksLabel;
            simpleDefault = newDefaultFile.getAbsolutePath();
         }
         if (newAdvancedDefaultFile != null) {
            nchecks[ind++] = advancedChecksLabel;
            advancedDefault = newAdvancedDefaultFile.getAbsolutePath();
         }
         checks = nchecks;
      }
               
      checksCombo.setFixedItems(checks);
      if (wasDefault && checks.length > 0) {
         checksCombo.setSelectedFile((newDefaultFile != null)
               ? newDefaultFile.getAbsolutePath() : oldFile);
      }
      else if (wasAdvancedDefault && checks.length > 0) {
         checksCombo.setSelectedFile((newAdvancedDefaultFile != null)
               ? newAdvancedDefaultFile.getAbsolutePath() : oldFile);
      }
      else {
         checksCombo.setSelectedFile(oldFile);
      }
   }
   
   
   /** Responds to the "OK" button. **/
   private void accept() {
      File home;
      File jar;
      String homeDir = homeCombo.getSelectedFile();
      if (homeDir.length() == 0) {
         home = null;
      }
      else {
         home = new File(homeDir);
         if (home.isDirectory()) {
            home = new File(homeDir);
            jar = CheckstyleSettings.getJarFromHome(home);
            if (jar == null) {
               JOptionPane.showMessageDialog(dialog, "Could not find "
                     + "checkstyle-all jar file in\n"
                     + "\"" + home.getAbsolutePath() + "\".",
                     "Error", JOptionPane.ERROR_MESSAGE);
               return;
            }
         }
         else {
            String homeName = home.getName();
            if (!CheckstyleSettings.isAllJar(homeName)) {
               if (JOptionPane.showOptionDialog(dialog, "Checkstyle jar file \""
                     + homeName + "\"\ndoes not match expected "
                     + "pattern for \"all\" jar file\n\""
                     + CheckstyleSettings.getJarMatchDescription() + "\".",
                     "Warning", JOptionPane.DEFAULT_OPTION,
                     JOptionPane.WARNING_MESSAGE, null,
                     new Object[] { "Use Anyway", "Cancel" }, "Cancel")
                     != 0) {
                  return;
               }
            }
         }
      }
      String checksString = checksCombo.getSelectedFile();
      if (checksString.equals(simpleChecksLabel)) {
         checksString = simpleDefault;
      }
      else if (checksString.equals(advancedChecksLabel)) {
         checksString = advancedDefault;
      }
      if (checksString == null) {
         checksString = "jGRASP_Checkstyle_Plugin_Internal_Error";
      }
      File checks = new File(checksString);
      String flags = flagsField.getText().replace('\n', ' ');
      String javaFlags = javaFlagsField.getText().replace('\n', ' ');
      CheckstyleSettings settings =
            new CheckstyleSettings(home, checks,
            processTestCKB.isSelected(), hideTBICKB.isSelected(),
            flags, javaFlags);
      tool.settingsChanged(settings);
      homeCombo.storeHistory();
      checksCombo.storeHistory();
      if (dialog != null) {
         dialog.setVisible(false);
      }
   }
}
