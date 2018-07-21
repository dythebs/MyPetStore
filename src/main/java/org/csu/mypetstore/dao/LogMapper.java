package org.csu.mypetstore.dao;

import org.csu.mypetstore.domain.Record;

import java.util.List;

public interface LogMapper {
    void addRecord(Record record);

    List<Record> searchRecord(String username);

}
