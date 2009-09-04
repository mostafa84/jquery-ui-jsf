package org.jquery.ui;

/**
 * A case converter for creating variable names.
 */
public class CaseConverter {
    /**
     * Converts camel case into human readable format.
     * @param c the camel case string to convert.
     * @return A human readable format.
     */
    public static String camelToHuman(String c) {
        StringBuilder v = new StringBuilder();
        char a[] = c.toCharArray();
        boolean first = true;
        for (char i:a) {
            if ('A' <= i && i <= 'Z') {
                v.append(' ').append(i);
            } else if (i=='_') {
                v.append(' ');
            } else if (first && Character.isLowerCase(i)) {
                v.append(Character.toUpperCase(i));
            } else {
                v.append(i);
            }
            first = false;
        }
        return v.toString();
    }

    /**
     * Converts a table name into human readable format.
     * @param t The table to convert.
     * @return A human readable format.
     */
    public static String tableToHuman(String t) {
        StringBuilder v = new StringBuilder();
        String s[] = t.split("\\_+");
        for (String i:s) {
            if (i.length()==1) {
                v.append(Character.toUpperCase(i.charAt(0))).append(' ');
            } else {
                v.append(Character.toUpperCase(i.charAt(0))).append(i.substring(1).toLowerCase()).append(' ');
            }
        }
        return v.toString();
    }

    /**
     * Converts a table into camel case.
     * @param t The table to convert.
     * @return Camel case format.
     */
    public static String tableToCamel(String t) {
        StringBuilder v = new StringBuilder();
        String s[] = t.split("\\_+");
        boolean first = true;
        for (String i:s) {
            if (first) {
                v.append(i.toLowerCase());
            } else if (i.length()==1) {
                v.append(Character.toUpperCase(i.charAt(0))).append(' ');
            } else {
                v.append(Character.toUpperCase(i.charAt(0))).append(i.substring(1).toLowerCase());
            }
            first = false;
        }
        return v.toString();
    }

    public static void main (String args[]) {
        CaseConverter c = new CaseConverter();
        System.out.println(c.camelToHuman("hiThereMarco"));
        System.out.println(c.camelToHuman("hiTThereMMarco"));
        System.out.println(c.tableToHuman("hey_there_how_are"));
        System.out.println(c.tableToCamel("hey_there_how_are"));
    }
    /*
        dataSource - the datasource.
        value - bypass the query, just use the value assigned
        query - the query.
        dialect - the dialect of this query, jdbc, hibernate, jpa.
        types - An array of types of the parameters.
        values - An array of parameter values.
        includes - which columns to include.
        excludes - which columns to exclude.
        first - the first row to display.
        rows - the amount of rows to display
     */
}
