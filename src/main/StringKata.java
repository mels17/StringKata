package main;

import java.util.Arrays;

public class StringKata {

    /**
     *   #---------------------------------------------#
     *   | EXCEPTION CLASS for negative integer inputs.|
     *   #---------------------------------------------#
     */
    public class NegativeIntegerException extends Exception {
        String message = "Negatives not allowed";


        public String toString(){
            return ( "NegativeIntegerException Occurred: "+ this.message ) ;
        }
    }

    /**
     *   #----------------------------------------------#
     *   | EXCEPTION CLASS for invalid delimiter inputs.|
     *   #----------------------------------------------#
     */

    public class InvalidDelimiterException extends Exception {
        String message = "Invalid Delimiter";


        public String toString(){
            return ( "InvalidDelimiterException Occurred: "+ this.message ) ;
        }
    }

    /**
     *   #----------------------------------------------------------#
     *   | STRING DELIMITER CLASS for optional delimiters in string.|
     *   #----------------------------------------------------------#
     */

    private class String_Delimiters {
        private String s;
        private String delimiters;

        public String_Delimiters( String s, String delimiters ) {
            this.s = s;
            this.delimiters = delimiters;
        }

        public String getDelimiters( ) {
            return "[" + delimiters + "]";
        }

        public String[ ] getNumberStringArray( ) {
            return this.s.split( this.getDelimiters( ) );
        }

        public String_Delimiters invoke( ) throws StringKata.InvalidDelimiterException {

            if ( s.length( ) >= 4 && s.substring( 0, 2 ).equals( "//" ) ) {

                if ( !s.substring( 2,3 ).equals( "[" ) ) {
                    oneOrTwoCharsDelimiters( );
                } else {
                    moreThan2CharDelimiters( );
                }
            }
            return this;
        }

        public boolean isInteger(String s) {
            try {
                Integer.parseInt(s);
            } catch(NumberFormatException e) {
                return false;
            } catch(NullPointerException e) {
                return false;
            }
            // only got here if we didn't return false
            return true;
        }



        /**
         *  Gets delimiters that are more than 2 characters long.
         */
        private void moreThan2CharDelimiters( ) throws StringKata.InvalidDelimiterException {
            // Get the index of the start and end of delimiters.
            int index_start = s.indexOf( '[' );
            int index_end = s.indexOf( ']' );

            // Replace all \n to ,
            s = s.replace( "\n", "," );

            do {
                replaceDelimiterWithCommaAndAlterString( index_start, index_end );

                // Search for more delimiters
                index_start = s.indexOf( '[' );
                index_end = s.indexOf( ']' );

            } while( index_start != -1 );

            // Removing the first delimiter
            s = s.substring( 1 );
        }

        private void replaceDelimiterWithCommaAndAlterString(int index_start, int index_end) throws
                InvalidDelimiterException {

            // Get the string delimiter
            String delim = s.substring( index_start + 1, index_end );

            if ( isInteger( delim.substring( 0, 1 ) ) ||
                    isInteger( delim.substring( delim.length() - 1 ) ) ) {

                throw new InvalidDelimiterException( );

            }
            // Omit out the delimiters.
            s = s.substring( index_end + 1 );

            s = s.replace( delim, "," );
        }


        /**
         * Works for a string containing one or two character delimiters.
         */
        private void oneOrTwoCharsDelimiters( ) {
            // Getting the one char delimiter
            String CharDelim = s.substring( 2, 3 );
            // Getting a possible two char delimiter
            String BackslashDelim = s.substring( 2, 4 );

            // Check if one character delimiter is "," and two char delimiter is "\n"
            if ( ! CharDelim.equals( "," ) && ! BackslashDelim.equals( "\n" ) ) {

                delimiters += CharDelim;
                s = s.substring( 4 );

            } else if ( s.length( ) >= 5 && CharDelim.equals( "\\" ) ) {
                // Check for backslash delimiters
                delimiters += BackslashDelim;
                s = s.substring( 5 );

            }

        }
    }


    /**
     * Converts string array into integer array and returns the sum of the integers.
     * @param nums_str
     * @return Sum all numbers if there are more than one number
     */
    private int multipleIntsReturnSum( String[ ] nums_str ) throws NegativeIntegerException {
        int[ ] nums = Arrays.stream( nums_str ).mapToInt( Integer::parseInt ).toArray( );

        negativeInputThrowException( nums );

        return Arrays.stream( nums ).filter(i -> i < 1000).sum( );
    }


    /**
     * Checks if there are negative integers in the array and throws exception.
     * @param nums
     * @throws NegativeIntegerException
     */
    private void negativeInputThrowException( int[ ] nums ) throws NegativeIntegerException {
        // Throws an exception when there are negative integers in the array.
        if ( Arrays.stream(nums).filter(i -> i < 0).toArray( ).length != 0 ) {
            throw new NegativeIntegerException( );
        }
    }


    /**
     * Sums all the numbers present in a string
     * @param s
     * @return sum
     */
    public int Add( String s ) throws NegativeIntegerException, StringKata.InvalidDelimiterException {
        // Return 0 if an empty string
        if ( s == "" ) {
            return 0;
        } else {
            // Check for additional delimiters.
            String_Delimiters string_Delimiters = new String_Delimiters( s, ",\n" ).invoke( );

            // Split between commas and newline to separate numbers.
            String[ ] nums_str = string_Delimiters.getNumberStringArray( ) ;

            if ( nums_str.length == 1 ) {
                // Parse string to integer if single number.
                int number = Integer.parseInt( nums_str[ 0 ] );
                if ( number < 0 ) {
                    throw new NegativeIntegerException( );
                } else if ( number >= 1000 ) {
                    return 0;
                }
                return number;
            } else {
                return multipleIntsReturnSum( nums_str );
            }
        }
    }


}
