package com.util.base.b_class;

/**
 * @author y15079
 * @create 2017-09-23 16:17
 * @desc
 * 将 FomattableFlags 传递给 Formattable.formatTo() 方法，并修改 Formattables 的输出格式。
 * Formattable 的实现负责解释和验证所有标志
 *
 * 	ALTERNATE  要求输出使用替换形式, 整型
 *
 * 	LEFT_JUSTIFY  将输出左对齐。 整型
 *
 *  UPPERCASE  根据创建 formatTo() 方法的 formatter 参数期间给出的 locale 规则，将输出转换为大写形式。 整型
 *
 * FomattableFlags 搭配 Formattable.formatTo()接口使用， 最后由继承Formattable接口的实现类再作为参数由Formatter.format()调用
 *
 **/
public class FormattableFlagsExample {
}
