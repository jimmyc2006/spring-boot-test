package springstudy.springboothive.util;

import java.util.Stack;

/**
 * @author shuwei
 * @version 创建时间：2018年6月12日 下午8:39:13 类说明
 */
public class TextFormatUtil {

  private static final String prefix = "  ";

  public static String format(String txt, char startSign, char endSign) {
    StringBuilder sb = new StringBuilder();
    // 层
    int floor = 0;
    char[] content = txt.toCharArray();
    for (int i = 0; i < content.length; i++) {
      if (content[i] == startSign) {
        sb.append("\n" + prefixStr(floor));
        floor++;
      } else if (content[i] == endSign) {
        sb.append("\n" + prefixStr(floor) + endSign);
        floor--;
      } else {
        sb.append(content[i]);
      }
    }
    return sb.toString();
  }

  private static String prefixStr(int floor) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < floor; i++) {
      sb.append(prefix);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String str =
        "(tok_query (tok_from (tok_subquery (tok_query (tok_from (tok_leftouterjoin (tok_tabref (tok_tabname dbx zpc)) (tok_tabref (tok_tabname dby def)))) (tok_insert (tok_destination (tok_dir tok_tmp_file)) (tok_select (tok_selexpr (. (tok_table_or_col zpc) aa)) (tok_selexpr (. (tok_table_or_col def) bb))))) d)) (tok_insert (tok_destination (tok_dir tok_tmp_file)) (tok_select (tok_selexpr (tok_table_or_col name))))) <eof>";
    System.out.println(format(str, '(', ')'));
  }
}
