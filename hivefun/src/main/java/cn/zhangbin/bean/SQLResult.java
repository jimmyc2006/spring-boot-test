package cn.zhangbin.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SQLResult {
    Set<String> outputTables;
    Set<String> inputTables;
    List<ColLine> colLineList;
    public Set<String> getOutputTables() {
        return outputTables;
    }
    public void setOutputTables(Set<String> outputTables) {
        this.outputTables = outputTables;
    }
    public Set<String> getInputTables() {
        return inputTables;
    }
    public void setInputTables(Set<String> inputTables) {
        this.inputTables = inputTables;
    }
    public List<ColLine> getColLineList() {
        return colLineList;
    }
    public void setColLineList(List<ColLine> colLineList) {
        this.colLineList = colLineList;
    }
    public Set<String> getInputColumns() {
      Set<String> result = new HashSet<String>();
      for(ColLine c : this.getColLineList()) {
        result.addAll(c.getFromNameSet());
      }
      return result;
    }
}