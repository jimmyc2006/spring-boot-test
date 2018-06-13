package cn.zhangbin.bean;

import cn.zhangbin.util.Check;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 生成的列的血缘关系
 */
public class ColLine {
    private String toNameParse; // 解析sql出来的列名称
    private String colCondition; // 带条件的源字段
    private Set<String> fromNameSet = new LinkedHashSet<String>(); //源字段
    private Set<String> conditionSet = new LinkedHashSet<String>(); //计算条件
    private Set<String> allConditionSet = new LinkedHashSet<String>();

    private String toTable; // 解析出来输出表
    private String toName; // 查询元数据出来的列名称

    private static final String CON_COLFUN = "COLFUN:";

    public ColLine(){}

    public ColLine(String toNameParse, String colCondition,
                   Set<String> fromNameSet, Set<String> conditionSet, String toTable,
                   String toName) {
        this.toNameParse = toNameParse;
        this.colCondition = colCondition;
        this.fromNameSet = fromNameSet;
        this.conditionSet = conditionSet;
        this.toTable = toTable;
        this.toName = toName;
    }

    public String getToNameParse() {
        return toNameParse;
    }
    public void setToNameParse(String toNameParse) {
        this.toNameParse = toNameParse;
    }
    public String getColCondition() {
        return colCondition;
    }
    public void setColCondition(String colCondition) {
        this.colCondition = colCondition;
    }
    public Set<String> getFromNameSet() {
        return fromNameSet;
    }
    public void setFromNameSet(Set<String> fromNameSet) {
        this.fromNameSet = fromNameSet;
    }
    public Set<String> getConditionSet() {
        return conditionSet;
    }
    public void setConditionSet(Set<String> conditionSet) {
        this.conditionSet = conditionSet;
    }
    public String getToTable() {
        return toTable;
    }
    public void setToTable(String toTable) {
        this.toTable = toTable;
    }
    public String getToName() {
        return toName;
    }
    public void setToName(String toName) {
        this.toName = toName;
    }

    public Set<String> getAllConditionSet(){
        allConditionSet.clear();
        if (needAdd()) {
            allConditionSet.add(CON_COLFUN + colCondition);
        }
        allConditionSet.addAll(conditionSet);
        return allConditionSet;
    }

    private boolean needAdd() {
        if (Check.notEmpty(colCondition)) {
            if (Check.isEmpty(fromNameSet)){ // 1+1 as num �����
                return true;
            }
            String[] split = colCondition.split("&");
            if (split.length > 0) {
                for (String string : split) {
                    if (Check.notEmpty(fromNameSet) && !fromNameSet.contains(string)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ColLine [toNameParse=" + toNameParse + ", colCondition="
                + colCondition + ", fromNameSet=" + fromNameSet
                + ", conditionSet=" + conditionSet + ", toTable=" + toTable
                + ", toName=" + toName + "]";
    }
}
