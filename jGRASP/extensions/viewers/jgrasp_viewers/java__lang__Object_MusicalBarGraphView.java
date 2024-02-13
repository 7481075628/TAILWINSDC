package jgrasp_viewers;


import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Comparator;
import javax.sound.midi.Instrument;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerInitData;
import jgrasp.viewer.ViewerPriorityData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/** A bar graph viewer that plays musical notes when a value changes. **/
public class java__lang__Object_MusicalBarGraphView extends java__lang__Object_BarGraphView {

   /** Control mode. **/
   private enum Mode {
      
      /** Relative value. **/
      VALUE_INCREASING("Relative Value, Increasing"),
      
      /** Inverse relative value. **/
      VALUE_DECREASING("Relative Value, Decreasing"),
   
      /** Relative position. **/
      POSITION_INCREASING("Relative Position, Increasing"),
      
      /** Inverse relative position. **/
      POSITION_DECREASING("Relative Position, Decreasing"),
      
      /** Fixed value. Min and max should be forced the same, but if not it is halfway between. **/
      FIXED("Single Value");
      
      /** The display string for choosing a mode. **/
      final String label;
      
      
      /** Creates a new Mode.
       *
       * @param labelIn the display string. **/
      Mode(final String labelIn) {
         label = labelIn;
      }
      
      
      /** {@inheritDoc} **/
      @Override
      public String toString() {
         return label;
      }
   }
   
   
   /** Wrapper for Instrument with appropriate display string. **/
   private static class InstrumentWrapper {
   
      /** The instrument. **/
      public Instrument instrument;
      
      /** Index for instruments with the same name. **/
      public int index;
   
   
      /** Creates a new InstrumentWrapper.
       *
       * @param instrumentIn the instrument.
       *
       * @param indexIn index for instruments with the same name. **/
      public InstrumentWrapper(final Instrument instrumentIn, final int indexIn) {
         instrument = instrumentIn;
         index = indexIn;
      }
   
   
      /** {@inheritDoc} **/
      @Override
      public String toString() {
         return instrument.getName() + ((index > 0)? " (" + (index + 1) + ")" : "");
      }
   }
   

   /** User settings. **/
   private static class Settings {
      
      /** The chosen instrument toString() value, or null if the default is being used. **/
      private String chosenInstrument;
      
      /** The chosen instrument name, or null if the default is being used. **/
      private String chosenInstrumentName;
      
      /** The note mode. **/
      private Mode noteMode = Mode.VALUE_DECREASING;
      
      /** The velocity mode. **/
      private Mode velocityMode = Mode.FIXED;
      
      /** The duration mode. **/
      private Mode durationMode = Mode.FIXED;
      
      /** Lowest note to be used. **/
      private int minNote = 20;
      
      /** Highest note to be used. **/
      private int maxNote = 100;
      
      /** Lowest velocity to be used. **/
      private int minVelocity = 64;
      
      /** Highest velocity to be used. **/
      private int maxVelocity = 64;
      
      /** Minimum duration setting 0-1000 linear. **/
      private int minDuration = 250;
      
      /** Minimum duration setting 0-1000 linear. **/
      private int maxDuration = 250;
      
      
      /** Gets a note value from current position and value based on these settings.
       *
       * @param value value.
       *
       * @param min minimum value.
       *
       * @param max maximum value.
       *
       * @param pos position.
       *
       * @param minPos minimum position.
       *
       * @param maxPos maximum position.
       *
       * @return the note value. **/
      private int getNote(final int value, final int min, final int max, final int pos, final int minPos,
            final int maxPos) {
         return (int) (minNote + frac(noteMode, value, min, max, pos, minPos, maxPos) * (maxNote - minNote));
      }
      
      
      /** Gets a note velocity from current position and value based on these settings.
       *
       * @param value value.
       *
       * @param min minimum value.
       *
       * @param max maximum value.
       *
       * @param pos position.
       *
       * @param minPos minimum position.
       *
       * @param maxPos maximum position.
       *
       * @return the note velocity. **/
      private int getVelocity(final int value, final int min, final int max, final int pos, final int minPos,
            final int maxPos) {
         return (int) (minVelocity + frac(velocityMode, value, min, max, pos, minPos, maxPos)
               * (maxVelocity - minVelocity));
      }
      
      
      /** Gets a note duration from current position and value based on these settings.
       *
       * @param value value.
       *
       * @param min minimum value.
       *
       * @param max maximum value.
       *
       * @param pos position.
       *
       * @param minPos minimum position.
       *
       * @param maxPos maximum position.
       *
       * @return the note duration. **/
      private int getDuration(final int value, final int min, final int max, final int pos, final int minPos,
            final int maxPos) {
         double minD = Math.pow(10.0, minDuration / 500.0) * 100.0 - 100;
         double maxD = Math.pow(10.0, maxDuration / 500.0) * 100.0 - 100;
         return (int) (minD + frac(durationMode, value, min, max, pos, minPos, maxPos) * (maxD - minD));
      }
   }


   /** The synthesizer that is currently being used. **/
   private Synthesizer synth;

   /** Channel to be used. **/
   private MidiChannel channel;
   
   /** An off timer for each note being played. **/
   private final Timer[] timers = new Timer[127];

   /** True if MIDI initialization failed, false if it succeeded or was not yet attempted. **/
   private boolean synthInitFailed;
   
   /** The available MIDI instruments. **/
   private Instrument[] instruments;
   
   /** Reference settings. **/
   private static final Settings refSettings = new Settings();

   /** Current settings. **/
   private final Settings settings = new Settings();


   /** Creates a new bar graph viewer.
    *
    * @param vcd viewer creation data. **/
   public java__lang__Object_MusicalBarGraphView(final ViewerCreateData vcd) {
      super(vcd);
   }
   
   
   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Musical Bar Graph";
   }
   
   
   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      int result = super.getPriority(vpd);
      if (result > Integer.MIN_VALUE) {
         return result - 1;
      }
      return result;
   }

   
   /** {@inheritDoc} **/
   @Override
   public void changedValue(final int index, final int size, final int barHeight, final int barMin,
         final int barMax) {
      if (synthInitFailed) {
         return;
      }
      int note = settings.getNote(barHeight, barMin, barMax, index, 0, size);
      int velocity = settings.getVelocity(barHeight, barMin, barMax, index, 0, size);
      channel.noteOn(note, velocity);
      int duration = settings.getDuration(barHeight, barMin, barMax, index, 0, size);
      
      if (timers[note] == null) {
         timers[note] = new Timer(duration, 
            (e)->channel.noteOff(note));
         timers[note].setRepeats(false);
         timers[note].start();
      }
      else {
         timers[note].setDelay(duration);
         timers[note].setInitialDelay(duration);
         timers[note].restart();
      }
   }
   
   
   /** Gets the fractional value of a parameter based on mode.
    *
    * @param mode the mode.
    *
    * @param value the current value.
    *
    * @param minValue the lowest possible value.
    *
    * @param maxValue the highest possible value.
    *
    * @param pos the position within the structure.
    *
    * @param minPos the lowest possible position.
    *
    * @param maxPos the highest possible position.
    *
    * @return the fractional value corresponding to the specified inputs, ranges, and mode. **/
   private static double frac(final Mode mode, final int value, final int minValue, final int maxValue,
         final int pos, final int minPos, final int maxPos) {
      switch (mode) {
         case POSITION_INCREASING:
            return (pos - minPos) / (double) (maxPos - minPos);
         case POSITION_DECREASING:
            return (maxPos - pos) / (double) (maxPos - minPos);
         case VALUE_INCREASING:
            return (value - minValue) / (double) (maxValue - minValue);
         case VALUE_DECREASING:
            return (maxValue - value) / (double) (maxValue - minValue);
      }
      return 0.5;
   }
   
   
   /** {@inheritDoc} **/
   @Override
   public void endBarPaint() {
   }


   /** Performs MIDI initialization or reports previous failure of initialization.
    *
    * @return true on success, false on failure. **/
   private boolean synthInit() {
      if (synthInitFailed) {
         return false;
      }
      if (channel != null) {
         return true;
      }
      try {
         synth = MidiSystem.getSynthesizer(); 
         synth.open();
      }
      catch (MidiUnavailableException e) {
         synth = null;
         synthInitFailed = true;
         return false;
      }
      Soundbank sb = synth.getDefaultSoundbank();
      if (sb == null) {
         synth.close();
         synth = null;
         return false;
      }
      instruments = sb.getInstruments();
      Arrays.sort(instruments, Comparator.comparing(Instrument::getName).thenComparing(Instrument::toString));
      if (instruments.length == 0) {
         synth.close();
         synth = null;
         return false;
      }
      MidiChannel[] chs = synth.getChannels();
      for (MidiChannel ch : chs) {
         if (ch != null) {
            channel = ch;
            break;
         }
      }
      return channel != null;
   }


   /** {@inheritDoc} **/
   @Override
   public void buildGui(final JPanel main) {
      main.addAncestorListener(
         new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
            }
            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
            @Override
            public void ancestorRemoved(AncestorEvent e) {
               Synthesizer s = synth;
               if (s != null) {
                  s.close();
               }
            }
         });
      buildControls();
   }


   /** Builds the controls. **/
   private void buildControls() {
      if (!synthInit()) {
         return;
      }
      JPanel panel = new JPanel();
      JComboBox<InstrumentWrapper> instChoice = new JComboBox<>();
      int ind = 0;
      int selInd = -1;
      int selNameInd = -1;
      int selDefault = -1;
      int defaultProgram = channel.getProgram();
      String prevName = null;
      int sameNameCount = 0;
      for (Instrument inst : instruments) {
         if (!synth.isSoundbankSupported(inst.getSoundbank())) {
            continue;
         }
         String name = inst.getName();
         if (name.equals(prevName)) {
            sameNameCount++;
         }
         else {
            prevName = name;
            sameNameCount = 0;
         }
         InstrumentWrapper item = new InstrumentWrapper(inst, sameNameCount);
         instChoice.addItem(item);
         if (selInd == -1 && settings.chosenInstrument != null && inst.toString().equals(settings.chosenInstrument)) {
            selInd = ind;
         }
         // First with same name if no exact match on string.
         if (selNameInd == -1 && settings.chosenInstrumentName != null
               && inst.getName().equals(settings.chosenInstrumentName)) {
            selNameInd = ind;
         }
         // Identify the current (default) instrument.
         if (selDefault == -1 && defaultProgram == inst.getPatch().getProgram()) {
            selDefault = ind;
         }
         ind++;
      }
      int si = (selInd >= 0) ? selInd : (selNameInd >= 0) ? selNameInd : Math.max(selDefault, 0);
      instChoice.setSelectedIndex(si);
      setInstrument(instruments[si]);
      instChoice.addActionListener(
         (e)->{
            if (!e.getActionCommand().equals("comboBoxChanged")) {
               return;
            }
            setInstrument(instChoice.getItemAt(instChoice.getSelectedIndex()).instrument);
            getVIData().stateChanged();
         });
      GridBagLayout gbl = new GridBagLayout();
      GridBagConstraints constraints = new GridBagConstraints();
      Insets insets = constraints.insets;
      int spacing = 8;
      Font f = panel.getFont();
      if (f != null) {
         spacing = panel.getFontMetrics(f).getHeight() / 2;
      }
      panel.setLayout(gbl);
   
      constraints.weighty = 0.001;
      insets.top = spacing;
      insets.left = spacing;
      insets.bottom = 0;
   
      addLabel(panel, "Instrument", gbl, constraints);
      addElement(panel, instChoice, gbl, constraints, spacing, GridBagConstraints.NONE);
      
      addLabel(panel, "Note Mode", gbl, constraints);
      JComboBox<Mode> noteModeCtrl = createModeCtrl(settings.noteMode);
      final JSlider minNoteCtrl = new JSlider(0, 127, settings.minNote);
      final JSlider maxNoteCtrl = new JSlider(0, 127, settings.maxNote);
   
      noteModeCtrl.addActionListener(
         (e)->{
            if (!e.getActionCommand().equals("comboBoxChanged")) {
               return;
            }
            settings.noteMode = noteModeCtrl.getItemAt(noteModeCtrl.getSelectedIndex());
            if (settings.noteMode == Mode.FIXED && settings.minNote != settings.maxNote) {
               int mid = (settings.minNote + settings.maxNote) / 2;
               settings.minNote = mid;
               settings.maxNote = mid;
               minNoteCtrl.setValue(settings.maxNote);
               maxNoteCtrl.setValue(settings.maxNote);
            }
            getVIData().stateChanged();
         });
      addElement(panel, noteModeCtrl, gbl, constraints, spacing, GridBagConstraints.NONE);
   
      addLabel(panel, "Min Note", gbl, constraints);
      addElement(panel, minNoteCtrl, gbl, constraints, spacing, GridBagConstraints.HORIZONTAL);
      
      addLabel(panel, "Max Note", gbl, constraints);
      addElement(panel, maxNoteCtrl, gbl, constraints, spacing, GridBagConstraints.HORIZONTAL);
      
      minNoteCtrl.addChangeListener(
         (e)->{
            settings.minNote = minNoteCtrl.getValue();
            if (settings.maxNote < settings.minNote || settings.noteMode == Mode.FIXED) {
               settings.maxNote = settings.minNote;
               maxNoteCtrl.setValue(settings.maxNote);
            }
            getVIData().stateChanged();
         });
      
      maxNoteCtrl.addChangeListener(
         (e)->{
            settings.maxNote = maxNoteCtrl.getValue();
            if (settings.minNote > settings.maxNote || settings.noteMode == Mode.FIXED) {
               settings.minNote = settings.maxNote;
               minNoteCtrl.setValue(settings.minNote);
            }
            getVIData().stateChanged();
         });
      
      addLabel(panel, "Velocity Mode", gbl, constraints);
      JComboBox<Mode> velocityModeCtrl = createModeCtrl(settings.velocityMode);
      final JSlider minVelocityCtrl = new JSlider(0, 127, settings.minVelocity);
      final JSlider maxVelocityCtrl = new JSlider(0, 127, settings.maxVelocity);
   
      velocityModeCtrl.addActionListener(
         (e)->{
            if (!e.getActionCommand().equals("comboBoxChanged")) {
               return;
            }
            settings.velocityMode = velocityModeCtrl.getItemAt(velocityModeCtrl.getSelectedIndex());
            if (settings.velocityMode == Mode.FIXED && settings.minVelocity != settings.maxVelocity) {
               int mid = (settings.minVelocity + settings.maxVelocity) / 2;
               settings.minVelocity = mid;
               settings.maxVelocity = mid;
               minVelocityCtrl.setValue(settings.maxVelocity);
               maxVelocityCtrl.setValue(settings.maxVelocity);
            }
            getVIData().stateChanged();
         });
      addElement(panel, velocityModeCtrl, gbl, constraints, spacing, GridBagConstraints.NONE);
   
      addLabel(panel, "Min Velocity", gbl, constraints);
      addElement(panel, minVelocityCtrl, gbl, constraints, spacing, GridBagConstraints.HORIZONTAL);
      
      addLabel(panel, "Max Velocity", gbl, constraints);
      addElement(panel, maxVelocityCtrl, gbl, constraints, spacing, GridBagConstraints.HORIZONTAL);
      
      minVelocityCtrl.addChangeListener(
         (e)->{
            settings.minVelocity = minVelocityCtrl.getValue();
            if (settings.maxVelocity < settings.minVelocity || settings.velocityMode == Mode.FIXED) {
               settings.maxVelocity = settings.minVelocity;
               maxVelocityCtrl.setValue(settings.maxVelocity);
            }
            getVIData().stateChanged();
         });
      
      maxVelocityCtrl.addChangeListener(
         (e)->{
            settings.maxVelocity = maxVelocityCtrl.getValue();
            if (settings.minVelocity > settings.maxVelocity || settings.velocityMode == Mode.FIXED) {
               settings.minVelocity = settings.maxVelocity;
               minVelocityCtrl.setValue(settings.minVelocity);
            }
            getVIData().stateChanged();
         });
      
      addLabel(panel, "Duration Mode", gbl, constraints);
      JComboBox<Mode> durationModeCtrl = createModeCtrl(settings.durationMode);
      final JSlider minDurationCtrl = new JSlider(0, 1000, settings.minVelocity);
      final JSlider maxDurationCtrl = new JSlider(0, 1000, settings.maxVelocity);
      
      durationModeCtrl.addActionListener(
         (e)->{
            if (!e.getActionCommand().equals("comboBoxChanged")) {
               return;
            }
            settings.durationMode = durationModeCtrl.getItemAt(durationModeCtrl.getSelectedIndex());
            if (settings.durationMode == Mode.FIXED && settings.minDuration != settings.maxDuration) {
               int mid = (settings.minDuration + settings.maxDuration) / 2;
               settings.minDuration = mid;
               settings.maxDuration = mid;
               minDurationCtrl.setValue(settings.maxDuration);
               maxDurationCtrl.setValue(settings.maxDuration);
            }
            getVIData().stateChanged();
         });
      addElement(panel, durationModeCtrl, gbl, constraints, spacing, GridBagConstraints.NONE);
   
      addLabel(panel, "Min Duration", gbl, constraints);
      addElement(panel, minDurationCtrl, gbl, constraints, spacing, GridBagConstraints.HORIZONTAL);
      
      addLabel(panel, "Max Duration", gbl, constraints);
      addElement(panel, maxDurationCtrl, gbl, constraints, spacing, GridBagConstraints.HORIZONTAL);
      
      minDurationCtrl.addChangeListener(
         (e)->{
            settings.minDuration = minDurationCtrl.getValue();
            if (settings.maxDuration < settings.minDuration || settings.durationMode == Mode.FIXED) {
               settings.maxDuration = settings.minDuration;
               maxDurationCtrl.setValue(settings.maxDuration);
            }
            getVIData().stateChanged();
         });
      
      maxDurationCtrl.addChangeListener(
         (e)->{
            settings.maxDuration = maxDurationCtrl.getValue();
            if (settings.minDuration > settings.maxDuration || settings.durationMode == Mode.FIXED) {
               settings.minDuration = settings.maxDuration;
               minDurationCtrl.setValue(settings.minDuration);
            }
            getVIData().stateChanged();
         });
      
      getVIData().setControls(panel);
   }
   
   
   /** Creates a mode control.
    *
    * @param mode the initial mode.
    *
    * @return the newly created mode control. **/
   private static JComboBox<Mode> createModeCtrl(final Mode mode) {
      JComboBox<Mode> ctrl = new JComboBox<>();
      for (Mode m : Mode.class.getEnumConstants()) {
         ctrl.addItem(m);
      }
      ctrl.setSelectedItem(mode);
      return ctrl;
   }
   
   
   /** Adds a UI label for a label->control by-row grid bad layout.
    *
    * @param panel the panel to which the label will be added.
    *
    * @param text the label text.
    *
    * @param gbl the layout.
    *
    * @param constraints current constraints. **/ 
   private static void addLabel(final JPanel panel, final String text, final GridBagLayout gbl,
         final GridBagConstraints constraints) {
      constraints.insets.right = 0;
      constraints.weightx = 0.001;
      constraints.gridwidth = 1;
      constraints.anchor = GridBagConstraints.EAST;
      constraints.fill = GridBagConstraints.NONE;
      JLabel label = new JLabel(text);
      panel.add(label);
      gbl.setConstraints(label, constraints);
   }
   
   
   /** Adds a UI control for a label->control by-row grid bad layout.
    *
    * @param panel the panel to which the label will be added.
    *
    * @param element the control.
    *
    * @param gbl the layout.
    *
    * @param constraints current constraints.
    *
    * @param spacing the control-to-border spacing.
    *
    * @param fill the fill mode. **/ 
   private static void addElement(final JPanel panel, final JComponent element, final GridBagLayout gbl,
         final GridBagConstraints constraints, final int spacing, final int fill) {
      constraints.insets.right = spacing;
      constraints.weightx = 1;
      constraints.gridwidth = GridBagConstraints.REMAINDER;
      constraints.anchor = GridBagConstraints.WEST;
      constraints.fill = fill;
      panel.add(element);
      gbl.setConstraints(element, constraints);
   }
   
   
   /** Changes the chosen instrument.
    *
    * @param instrument the new instrument. **/
   private void setInstrument(final Instrument instrument) {
      synth.loadInstrument(instrument);
      channel.programChange(instrument.getPatch().getBank(), instrument.getPatch().getProgram());
      settings.chosenInstrument = instrument.toString();
      settings.chosenInstrumentName = instrument.getName();
   }


   /** {@inheritDoc} **/
   @Override
   public boolean toXML(final Document doc, final Element e) {
      Settings s = settings;
      Settings r = refSettings;
      if (s.noteMode == r.noteMode && s.velocityMode == r.velocityMode && s.durationMode == r.durationMode
            && s.minNote == r.minNote && s.maxNote == r.maxNote && s.minVelocity == r.minVelocity
            && s.minDuration == r.minDuration && s.maxDuration == r.maxDuration && s.chosenInstrument == null) {
         return false;
      }
      if (s.noteMode != r.noteMode) {
         addXMLString(doc, e, "NoteMode", s.noteMode.name());
      }
      if (s.velocityMode != r.velocityMode) {
         addXMLString(doc, e, "VelocityMode", s.velocityMode.name());
      }
      if (s.durationMode != r.durationMode) {
         addXMLString(doc, e, "DurationMode", s.durationMode.name());
      }
      if (s.minNote != r.minNote) {
         addXMLInt(doc, e, "MinNote", s.minNote);
      }
      if (s.maxNote != r.maxNote) {
         addXMLInt(doc, e, "MaxNote", s.maxNote);
      }
      if (s.minVelocity != r.minVelocity) {
         addXMLInt(doc, e, "MinVelocity", s.minVelocity);
      }
      if (s.maxVelocity != r.maxVelocity) {
         addXMLInt(doc, e, "MaxVelocity", s.maxVelocity);
      }
      if (s.minDuration != r.minDuration) {
         addXMLInt(doc, e, "MinDuration", s.minDuration);
      }
      if (s.maxDuration != r.maxDuration) {
         addXMLInt(doc, e, "MaxDuration", s.maxDuration);
      }
      if (s.chosenInstrument != null) {
         addXMLString(doc, e, "Instrument", s.chosenInstrument);
         addXMLString(doc, e, "InstrumentName", s.chosenInstrumentName);
      }
      return true;
   }


   /** {@inheritDoc} **/
   @Override
   public void build(final ViewerInitData vid, final Element initDataIn) {
      super.build(vid, initDataIn);
      if (initDataIn == null) {
         return;
      }
      settings.noteMode = getMode(initDataIn, "NoteMode", settings.noteMode);
      settings.velocityMode = getMode(initDataIn, "VelocityMode", settings.velocityMode);
      settings.durationMode = getMode(initDataIn, "DurationMode", settings.durationMode);
      settings.minNote = getInt(initDataIn, "MinNote", settings.minNote, 0, 127);
      settings.maxNote = getInt(initDataIn, "MaxNote", settings.maxNote, 0, 127);
      settings.minVelocity = getInt(initDataIn, "MinVelocity", settings.minVelocity, 0, 127);
      settings.maxVelocity = getInt(initDataIn, "MaxVelocity", settings.maxVelocity, 0, 127);
      settings.minDuration = getInt(initDataIn, "MinDuration", settings.minDuration, 0, 1000);
      settings.maxDuration = getInt(initDataIn, "MaxDuration", settings.maxDuration, 0, 1000);
      settings.chosenInstrument = getString(initDataIn, "Instrument");
      settings.chosenInstrumentName = getString(initDataIn, "InstrumentName");
   }
   
   
   /** Adds a child element containing a single text node to an element.
    *
    * @param doc the document.
    *
    * @param parent the element to which the child will be added.
    *
    * @param name the child element name.
    *
    * @param data the data for the text node that will be added to the child. If this is null, nothing will be
    * added. **/
   public static void addXMLString(final Document doc, final Node parent, final String name, final String data) {
      Element c = doc.createElement(name);
      c.appendChild(doc.createTextNode(data));
      parent.appendChild(c);
   }


   /** Adds a child element containing a single text node that contains an integer value.
    *
    * @param doc the document.
    *
    * @param parent the element to which the child will be added.
    *
    * @param name the child element name.
    *
    * @param v the int value. **/
   public static void addXMLInt(final Document doc, final Node parent, final String name, final int v) {
      Element c = doc.createElement(name);
      c.appendChild(doc.createTextNode(Integer.toString(v)));
      parent.appendChild(c);
   }


   /** Gets XML sub-node string content.
    *
    * @param el the parent element.
    *
    * @param name the sub element name.
    *
    * @return the string value of the requested element, or null if there are no such elements with a string
    * value. **/
   public static String getString(final Element el, final String name) {
      Node n = el.getElementsByTagName(name).item(0);
      if (n == null) {
         return null;
      }
      return n.getTextContent();
   }
      
      
   /** Gets an XML mode value.
    *
    * @param el the parent element.
    *
    * @param name the sub element name.
    *
    * @param def the default value to be used if the requested element is not found or does not have a meaningful
    * value.
    *
    * @return the requested element, or default if there are no such elements. **/
   public static Mode getMode(final Element el, final String name, final Mode def) {
      String v = getString(el, name);
      if (v == null) {
         return def;
      }
      try {
         return Enum.valueOf(Mode.class, v);
      }
      catch (IllegalArgumentException e) {
         return def;
      }
   }


   /** Gets an XML int value.
    *
    * @param el the parent element.
    *
    * @param name the sub element name.
    *
    * @param def the default value to be used if the requested element is not found or does not have a meaningful
    * value.
    *
    * @param min the minimum acceptable value. The result will be clipped to this value if appropriate.
    *
    * @param max the maximum acceptable value. The result will be clipped to this value if appropriate.
    *
    * @return the requested element, or default if there are no such elements. **/
   public static int getInt(final Element el, final String name, final int def, final int min, final int max) {
      String v = getString(el, name);
      if (v == null) {
         return def;
      }
      try {
         return Math.max(min, Math.min(max, Integer.valueOf(v)));
      }
      catch (NumberFormatException e) {
         return def;
      }
   }
}
