package test;

import main.StringKata;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringKataTest {

    private static StringKata string_kata = new StringKata( );

    /**
     *  Step 1: Input is an empty string and returns 0
     */

    @Test
    public void EmptyStringReturnZero( ) throws Exception {
        assertThat( string_kata.Add( "" ) ).isEqualTo( 0 );
    }

    /**
     *  Step 2: Input is a string containing a number and return the number.
     */

    @Test
    public void Number1StringReturns1( ) throws Exception {
        assertThat( string_kata.Add( "1" ) ).isEqualTo( 1 );
    }

    @Test
    public void Number3StringReturns3( ) throws Exception {
        assertThat( string_kata.Add( "3" ) ).isEqualTo( 3 );
    }

    /**
     *  Step 3: Input is a string containing two numbers separated by a comma
     *  and return the sum of these numbers.
     */

    @Test
    public void InputString1And2Return3( ) throws Exception {
        assertThat( string_kata.Add( "1,2" ) ).isEqualTo( 3 );
    }

    @Test
    public void InputString3And5Returns8( ) throws Exception {
        assertThat( string_kata.Add( "3,5" ) ).isEqualTo( 8 );
    }

    /**
     *  Step 4: Input is a string containing multiple numbers separated by a comma
     *  and return the sum of these numbers.
     */
    @Test
    public void InputStringIsThreeNumsCommaReturnSum( ) throws Exception {
        assertThat( string_kata.Add( "1,2,3" ) ).isEqualTo( 6 );
    }

    @Test
    public void InputStringIsFourNumsCommaReturnSum( ) throws Exception {
        assertThat( string_kata.Add( "3,5,3,9" ) ).isEqualTo( 20 );
    }

    /**
     *  Step 5: Input is a string that has commas or newline characters in-between numbers
     *  and the method should still return sum.
     */
    @Test
    public void ThreeNumsOneNewlineOneCommaReturnSum( ) throws Exception {
        assertThat( string_kata.Add( "1,2\n3" ) ).isEqualTo( 6 );
    }

    @Test
    public void FourNumsTwoNewlineOneCommaReturnSum( ) throws Exception {
        assertThat( string_kata.Add( "3\n5\n3,9" ) ).isEqualTo( 20 );
    }

    @Test
    public void OneNumberOneCommaOneNewlineReturnNumber( ) throws Exception {
        assertThat( string_kata.Add( "1,\n" ) ).isEqualTo( 1 );
    }

    /**
     *  Step 6: Support different delimiters - Input is a string containing numbers and
     *  different delimiters.
     */
    @Test
    public void SemicolonDelimiterInInput( ) throws Exception {
        assertThat( string_kata.Add( "//;\n1;2" ) ).isEqualTo( 3 );
    }

    @Test
    public void BackslashRDelimiterInInput( ) throws Exception {
        assertThat( string_kata.Add( "//\r\r1,2\n5,8" ) ).isEqualTo( 16 );
    }

    /**
     *  Step 7: Throw exception when negatives are not allowed.
     */
    @Test( expected = StringKata.NegativeIntegerException.class )
    public void NegativeInputsThrowException( ) throws Exception {
        string_kata.Add( "-1,2,-3" );
    }

    @Test( expected = StringKata.NegativeIntegerException.class )
    public void NegativeInputThrowsException( ) throws Exception {
        string_kata.Add( "-1" );
    }

    /**
     *  Step 8: Numbers greater than 1000 should be ignored.
     */
    @Test
    public void InputGreaterThat1000IgnoreInSum( ) throws Exception {
        assertThat( string_kata.Add( "1000,1001,2" ) ).isEqualTo( 2 );
    }

    /**
     *  Step 9: Delimiters can be of any length of the format "//[delimiter]\n"
     */
    @Test
    public void OneDelimiterUnknownLength( ) throws Exception {
        assertThat( string_kata.Add("//[***]\n1***2***3" ) ).isEqualTo( 6 );
    }

    /**
     *  Step 10: Allow multiple delimiters "//[delim1][delim2]\n"
     */
    @Test
    public void MultipleDelimiters( ) throws Exception {
        assertThat( string_kata.Add( "//[*][%]\n1*2%3" ) ).isEqualTo( 6 );
    }


    /**
     * Step 11: Handle multiple delimiters with a length longer than one character.
     */
    @Test
    public void MultipleDelimitersLongerThanOneCharacter( ) throws Exception {
        assertThat( string_kata.Add( "//[***][#][%]\n1***2#3%4" ) ).isEqualTo( 10 );
    }

    /**
     *  Step 12: Handle delimiters that have numbers as part of them, where the
     *  number cannot be on the edge of a delimiter.
     */
    @Test
    public void DelimiterWithNumbers( ) throws Exception {
        assertThat( string_kata.Add( "//[*1*][%]\n1*1*2%3" ) ).isEqualTo( 6 );
    }

    @Test( expected = StringKata.InvalidDelimiterException.class )
    public void InvalidDelimiterWithNumbers( ) throws Exception {
        string_kata.Add( "//[1DD][%]\n11,2%3" );
    }

    /**
     * General number format exce
     * @throws Exception
     */
    @Test( expected = NumberFormatException.class )
    public void InvalidInputThrowsException( ) throws Exception {
        string_kata.Add( "1,2,d" );
    }
}
