package org.csu.mypetstore.dao;


import org.csu.mypetstore.domain.Sequence;

public interface SequenceMapper {

  Sequence getSequence(Sequence sequence);
  void updateSequence(Sequence sequence);
}
