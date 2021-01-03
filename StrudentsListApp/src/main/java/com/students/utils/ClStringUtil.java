package com.students.utils;

import org.apache.commons.lang.StringUtils;

public class ClStringUtil {

	private ClStringUtil() {
	}
	
	public static boolean isBlank( String str ) {

		 

        if ( "null".equalsIgnoreCase( StringUtils.trimToNull( str ) ) ) {

 

            return true;
        }

 

        if ( "\"\"".equalsIgnoreCase( StringUtils.trimToNull( str ) ) ) {

 

            return true;
        }

 

        return StringUtils.isBlank( str );
    }

 


    /**
     * Checks if a String is not whitespace, empty (""), "null", "NULL" or null.
     * <pre>
     *  ClStringUtil.isNotBlank(null)      = false
     *  ClStringUtil.isNotBlank("null")    = false
     *  ClStringUtil.isNotBlank("NULL")    = false
     *  ClStringUtil.isNotBlank("")        = false
     *  ClStringUtil.isNotBlank(" ")       = false
     *  ClStringUtil.isNotBlank("bob")     = true
     *  ClStringUtil.isNotBlank("  bob  ") = true
     *  </pre>
     * @param str
     * @return
     * @author Ravi Palla (GB736)
     * @since Version: 10.0<br>Created On: Aug 29, 2019
     */
    public static boolean isNotBlank( String str ) {

 

        return !isBlank( str );
    }

}
