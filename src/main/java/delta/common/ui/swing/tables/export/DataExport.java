package delta.common.ui.swing.tables.export;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JTable;

import delta.common.ui.swing.tables.GenericTableController;
import delta.common.ui.swing.tables.TableColumnController;
import delta.common.ui.swing.tables.TableColumnsManager;

/**
 * Table data exporter.
 * @author DAM
 * @param <POJO> Type of managed data.
 */
public class DataExport<POJO>
{
  private ExportDataOutput _output;

  /**
   * Constructor.
   * @param output Output.
   */
  public DataExport(ExportDataOutput output)
  {
    _output=output;
  }

  /**
   * Export table data.
   * @param table Table to export.
   */
  public void export(GenericTableController<POJO> table)
  {
    int nbRows=table.getNbFilteredItems();
    List<TableColumnController<POJO,?>> columns=getColumnsToUse(table);
    int nbColumns=columns.size();
    // Headers
    String[] headers=new String[nbColumns];
    for(int j=0;j<nbColumns;j++)
    {
      TableColumnController<POJO,?> column=columns.get(j);
      headers[j]=column.getHeader();
    }
    _output.writeData(headers);
    // Values
    String[] values=new String[nbColumns];
    for(int i=0;i<nbRows;i++)
    {
      POJO pojo=table.getAtViewIndex(i);
      for(int j=0;j<nbColumns;j++)
      {
        TableColumnController<POJO,?> column=columns.get(j);
        Object data=column.getValueProvider().getData(pojo);
        values[j]=toString(data);
      }
      _output.writeData(values);
    }
  }

  private List<TableColumnController<POJO,?>> getColumnsToUse(GenericTableController<POJO> table)
  {
    TableColumnsManager<POJO> columnsMgr=table.getColumnsManager();
    List<TableColumnController<POJO,?>> columns=columnsMgr.getSelectedColumns();
    JTable jtable=table.getTable();
    int nbColumns=jtable.getColumnCount();
    List<TableColumnController<POJO,?>> ret=new ArrayList<TableColumnController<POJO,?>>();
    for(int j=0;j<nbColumns;j++)
    {
      int columnIndex=jtable.convertColumnIndexToModel(j);
      TableColumnController<POJO,?> column=columns.get(columnIndex);
      if (useColumn(column))
      {
        ret.add(column);
      }
    }
    return ret;
  }

  private boolean useColumn(TableColumnController<POJO,?> column)
  {
    Class<?> dataType=column.getDataType();
    return (dataType!=Icon.class);
  }

  private String toString(Object data)
  {
    if (data==null)
    {
      return "";
    }
    return data.toString();
  }
}
