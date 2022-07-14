package project.framework.gui;

public interface TableModelRowMapper<T> {

    Object[] mapToRowData(T t);

//    T mapFromObjectToData(Object object);

    String[] getColumns();

    int getUniqueIdIndex();

    int getDefaultValueIndex();
}
