package delta.common.ui.swing.tables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Columns manager for a table.
 * @param <POJO> Type of tabled data.
 * @author DAM
 */
public class TableColumnsManager<POJO>
{
  // Available columns
  private List<TableColumnController<POJO,?>> _availableColumns;
  // Column IDs
  private HashMap<String,TableColumnController<POJO,?>> _columnsById;
  // Selected columns
  private List<TableColumnController<POJO,?>> _selectedColumns;
  // Default columns
  private List<String> _defaultColumnsIds;

  /**
   * Constructor.
   */
  public TableColumnsManager()
  {
    _availableColumns=new ArrayList<TableColumnController<POJO,?>>();
    _columnsById=new HashMap<String,TableColumnController<POJO,?>>();
    _selectedColumns=new ArrayList<TableColumnController<POJO,?>>();
    _defaultColumnsIds=new ArrayList<String>();
  }

  /**
   * Get the available columns.
   * @return the available columns.
   */
  public List<TableColumnController<POJO,?>> getAvailableColumns()
  {
    return _availableColumns;
  }

  /**
   * Get the default column IDs.
   * @return the default column IDs.
   */
  public List<String> getDefaultColumnsIds()
  {
    return new ArrayList<String>(_defaultColumnsIds);
  }

  /**
   * Set the default column IDs.
   * @param defaultColumnsIds IDs to set.
   */
  public void setDefaultColumnsIds(List<String> defaultColumnsIds)
  {
    _defaultColumnsIds.clear();
    _defaultColumnsIds.addAll(defaultColumnsIds);
  }

  /**
   * Get the current column at the given index.
   * @param index An index, starting at 0.
   * @return A table column controller.
   */
  public TableColumnController<POJO,?> getAt(int index)
  {
    return _selectedColumns.get(index);
  }

  /**
   * Get a column controller using its identifier.
   * @param id Identifier to use.
   * @return A controller or <code>null</code> if not found.
   */
  public TableColumnController<POJO,?> getById(String id)
  {
    return _columnsById.get(id);
  }

  /**
   * Get the index of the targeted column.
   * @param id Identifier of the targeted column.
   * @return An index (starting at 0), or <code>null</code> if not found.
   */
  public Integer getColumnIndex(String id)
  {
    int index=0;
    for(TableColumnController<POJO,?> column : _selectedColumns)
    {
      if (id.equals(column.getId()))
      {
        return Integer.valueOf(index);
      }
      index++;
    }
    return null;
  }

  /**
   * Get the selected columns.
   * @return the selected columns.
   */
  public List<TableColumnController<POJO,?>> getSelectedColumns()
  {
    return _selectedColumns;
  }

  /**
   * Get the identifiers of the displayed columns.
   * @return A list of column identifiers.
   */
  public List<String> getSelectedColumnsIds()
  {
    List<String> columnIds=new ArrayList<String>();
    for(TableColumnController<POJO,?> column : _selectedColumns)
    {
      columnIds.add(column.getId());
    }
    return columnIds;
  }

  /**
   * Set the selected columns.
   * @param selectedColumns Selected columns.
   */
  public void setSelectedColumns(List<TableColumnController<POJO,?>> selectedColumns)
  {
    _selectedColumns.clear();
    _selectedColumns.addAll(selectedColumns);
  }

  /**
   * Select the columns to display.
   * @param ids Identifiers of the columns to display.
   */
  public void setColumns(List<String> ids)
  {
    _selectedColumns.clear();
    for(String id : ids)
    {
      TableColumnController<POJO,?> controller=getById(id);
      if (controller!=null)
      {
        _selectedColumns.add(controller);
      }
    }
  }

  /**
   * Get the number of selected columns.
   * @return A columns count.
   */
  public int getSelectedColumnsCount()
  {
    return _selectedColumns.size();
  }

  /**
   * Add a column controller.
   * @param controller Controller to add.
   * @param selected Add the controller to the selected ones or not.
   */
  public void addColumnController(TableColumnController<POJO,?> controller, boolean selected)
  {
    String id=controller.getId();
    TableColumnController<POJO,?> old=_columnsById.get(id);
    if (old==null)
    {
      _availableColumns.add(controller);
      _columnsById.put(id,controller);
    }
    if (selected)
    {
      if (!_selectedColumns.contains(controller))
      {
        _selectedColumns.add(controller);
      }
    }
  }

  /**
   * Remove a column.
   * @param controller Controller to remove.
   */
  public void removeColumn(TableColumnController<POJO,?> controller)
  {
    _availableColumns.remove(controller);
    _selectedColumns.remove(controller);
    _columnsById.remove(controller.getId());
  }

  /**
   * Remove a column from the selected ones.
   * @param controller Controller to remove.
   */
  public void removeSelectedColumn(TableColumnController<POJO,?> controller)
  {
    _selectedColumns.remove(controller);
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    if (_availableColumns!=null)
    {
      for(TableColumnController<POJO,?> column : _availableColumns)
      {
        column.dispose();
      }
      _availableColumns.clear();
      _availableColumns=null;
    }
    if (_selectedColumns!=null)
    {
      _selectedColumns.clear();
      _selectedColumns=null;
    }
    if (_columnsById!=null)
    {
      _columnsById.clear();
      _columnsById=null;
    }
  }
}
