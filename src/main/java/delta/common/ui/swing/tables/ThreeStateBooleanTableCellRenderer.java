package delta.common.ui.swing.tables;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import delta.common.ui.swing.GuiFactory;

/**
 * Cell renderer Boolean values (true, false or null).
 * @author DAM
 */
public class ThreeStateBooleanTableCellRenderer extends DefaultTableCellRenderer
{
  private JCheckBox _checkbox;
  private JLabel _label;

  /**
   * Constructor.
   */
  public ThreeStateBooleanTableCellRenderer()
  {
    _checkbox=GuiFactory.buildCheckbox("");
    _checkbox.setHorizontalAlignment(SwingConstants.CENTER);
    _label=GuiFactory.buildLabel("");
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
  {
    if (value instanceof Boolean)
    {
      Boolean booleanValue=(Boolean)value;
      _checkbox.setSelected(booleanValue.booleanValue());
      return _checkbox;
    }
    return _label;
  }

  @Override
  public void setValue(Object value)
  {
    // Nothing!
  }
}
