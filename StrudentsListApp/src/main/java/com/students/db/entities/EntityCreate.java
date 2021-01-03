package com.students.db.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.students.utils.ClStringUtil;


/**
 * 
 * 
 * @author Vignesh
 * @createdOn Aug 29, 2020
 */
public class EntityCreate {

	@SuppressWarnings("unused")
	private String dependencyString = "<dependency> <groupId>org.postgresql</groupId>  <artifactId>postgresql</artifactId> <version>42.2.11</version></dependency>";

	public static void main( String[] args ) {

		// String data = "select * from cl.multi_touch_outcomes";
		// getEntityData( data );
		String tableQry = "select * from information_schema.columns where table_name = 'users'";
		getColumnsData( tableQry );
	}

	/**
	 * 
	 * @author Vignesh
	 * @createdOn Aug 29, 2020
	 */
	private static void getColumnsData( String tableQry ) {

		try (Connection devqaCon = getConnectleader12Connection(); PreparedStatement ps = devqaCon.prepareStatement( tableQry ); ResultSet rSet = ps.executeQuery();) {

			while ( rSet.next() ) {

				String columnName = rSet.getString( "column_name" );

				String entityColumnName = titleCaseConversion( columnName );

				String dataType = rSet.getString( "data_type" );

				String columnDefault = rSet.getString( "column_default" );

				String characterMaximumLength = rSet.getString( "character_maximum_length" );

				String isNullable = rSet.getString( "is_nullable" );

				// @Column(name = "share_this_multi_touch_with", columnDefinition = "character varying(255)")

				if ( dataType.startsWith( "int" ) || dataType.startsWith( "serial" ) || dataType.startsWith( "bigint" ) ) {

					String entityColumn = "";

					entityColumn += "@Column(name = \"" + columnName + "\"";

					String columnDefinition = "";

					if ( ClStringUtil.isNotBlank( columnDefault ) )
						columnDefinition += "default " + columnDefault;

					if ( ClStringUtil.isNotBlank( characterMaximumLength ) )
						entityColumn += characterMaximumLength;

					if ( ClStringUtil.isNotBlank( columnDefinition ) )
						entityColumn += ", columnDefinition = \"" + columnDefinition + "\"";

					entityColumn += ")";

					if ( "no".equalsIgnoreCase( isNullable ) )
						System.out.println( "@NotNull(message=\"" + entityColumnName + " must not be null\")" );

					System.out.println( entityColumn );

					if(dataType.startsWith( "int" ))
					System.out.println( "private Integer " + entityColumnName + ";" );
					else
						System.out.println( "private Long " + entityColumnName + ";" );

				} else if ( dataType.startsWith( "bool" ) ) {

					String entityColumn = "";

					entityColumn += "@Column(name = \"" + columnName + "\"";

					String columnDefinition = "";

					if ( ClStringUtil.isNotBlank( columnDefault ) )
						columnDefinition += "default " + columnDefault;

					if ( ClStringUtil.isNotBlank( characterMaximumLength ) )
						entityColumn += characterMaximumLength;

					if ( ClStringUtil.isNotBlank( columnDefinition ) )
						entityColumn += ", columnDefinition = \"" + columnDefinition + "\"";

					entityColumn += ")";

					if ( "no".equalsIgnoreCase( isNullable ) )
						System.out.println( "@NotNull(message=\"" + entityColumnName + " must not be null\")" );

					System.out.println( entityColumn );

					System.out.println( "private Boolean " + entityColumnName + ";" );

				} else if ( dataType.startsWith( "time" ) ) {

					String entityColumn = "";

					entityColumn += "@Column(name = \"" + columnName + "\"";

					String columnDefinition = "";

					if ( ClStringUtil.isNotBlank( columnDefault ) )
						columnDefinition += "default " + columnDefault;

					if ( ClStringUtil.isNotBlank( characterMaximumLength ) )
						entityColumn += characterMaximumLength;

					if ( ClStringUtil.isNotBlank( columnDefinition ) )
						entityColumn += ", columnDefinition = \"" + columnDefinition + "\"";

					entityColumn += ")";

					if ( "no".equalsIgnoreCase( isNullable ) )
						System.out.println( "@NotNull(message=\"" + entityColumnName + " must not be null\")" );

					System.out.println( entityColumn );

					System.out.println( "@JsonFormat(pattern = \"yyyy-MM-dd'T'HH:mm:ss'Z'\") " );

					System.out.println( "private LocalDateTime " + entityColumnName + ";" );

				} else if ( dataType.startsWith( "date" ) ) {


					String entityColumn = "";

					entityColumn += "@Column(name = \"" + columnName + "\"";

					String columnDefinition = "";

					if ( ClStringUtil.isNotBlank( columnDefault ) )
						columnDefinition += "default " + columnDefault;

					if ( ClStringUtil.isNotBlank( characterMaximumLength ) )
						entityColumn += characterMaximumLength;

					if ( ClStringUtil.isNotBlank( columnDefinition ) )
						entityColumn += ", columnDefinition = \"" + columnDefinition + "\"";

					entityColumn += ")";

					if ( "no".equalsIgnoreCase( isNullable ) )
						System.out.println( "@NotNull(message=\"" + entityColumnName + " must not be null\")" );

					System.out.println( entityColumn );

					System.out.println( "@JsonFormat(pattern = \"yyyy-MM-dd'T'HH:mm:ss'Z'\")" );

					System.out.println( "private java.util.Date " + entityColumnName + ";" );

				} else if ( dataType.startsWith( "json" ) ) {

					String entityColumn = "";

					String columnDefinition = " columnDefinition = \"json\"";

					entityColumn += "@Column(name = \"" + columnName + "\"";


					if ( ClStringUtil.isNotBlank( columnDefault ) )
						columnDefinition += "default " + columnDefault;

					if ( ClStringUtil.isNotBlank( characterMaximumLength ) )
						entityColumn += ", length=" + characterMaximumLength;

					if ( ClStringUtil.isNotBlank( columnDefinition ) )
						entityColumn += ", " + columnDefinition;


					entityColumn += ")";

					if ( "no".equalsIgnoreCase( isNullable ) )
						System.out.println( "@NotNull(message=\"" + entityColumnName + " must not be null\")" );

					System.out.println( "@Type(type = \"json\")" );

					System.out.println( entityColumn );

					System.out.println( "private JsonNode " + entityColumnName + ";" );

				} else if ( dataType.startsWith( "varchar" ) || dataType.startsWith( "text" ) || dataType.startsWith( "character" ) || dataType.startsWith( "bpchar" ) ) {

					String entityColumn = "";

					entityColumn += "@Column(name = \"" + columnName + "\"";

					String columnDefinition = "";

					if ( ClStringUtil.isNotBlank( columnDefault ) )
						columnDefinition += "default " + columnDefault;

					if ( ClStringUtil.isNotBlank( characterMaximumLength ) )
						entityColumn += ", length=" + characterMaximumLength;

					if ( ClStringUtil.isNotBlank( columnDefinition ) )
						entityColumn += ", columnDefinition = \"" + columnDefinition + "\"";

					entityColumn += ")";

					if ( "no".equalsIgnoreCase( isNullable ) )
						System.out.println( "@NotNull(message=\"" + entityColumnName + " must not be null\")" );

					System.out.println( entityColumn );

					System.out.println( "private String " + entityColumnName + ";" );

				}


				System.out.println();
			}


		} catch ( SQLException e ) {
			e.printStackTrace();
		}

	}

	private static String titleCaseConversion( String inputString ) {

		if ( StringUtils.isBlank( inputString ) ) {
			return "";
		}

		if ( StringUtils.length( inputString ) == 1 || inputString.split( "_" ).length == 1 ) {
			return inputString;
		}


		StringBuffer resultPlaceHolder = new StringBuffer( inputString.length() );

		String[] data = inputString.split( "_" );

		int i = 0;
		for ( String stringPart : data ) {

			char[] charArray = stringPart.toLowerCase().toCharArray();

			if ( i != 0 )
				charArray[ 0 ] = Character.toUpperCase( charArray[ 0 ] );

			resultPlaceHolder.append( new String( charArray ) ).append( "" );

			i++;
		}

		return StringUtils.trim( resultPlaceHolder.toString() );
	}

	public static Connection getConnectleader12Connection() {

		String localurl = "jdbc:postgresql://localhost/Students DB", username = "postgres", password = "admin";

		Connection connection = null;

		try {

			Class.forName( "org.postgresql.Driver" );

			connection = DriverManager.getConnection( localurl, username, password );

		} catch ( Exception e ) {

			System.out.println( "DBConnection.getDevqaConnection() , Exception = " + ExceptionUtils.getStackTrace( e ) );

		}

		return connection;
	}
}
