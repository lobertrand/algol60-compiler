// $ANTLR null /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g 2019-11-06 17:34:42

package eu.telecomnancy;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Algol60Lexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int ARITHMETIC_OPERATOR=4;
	public static final int BLOCK=5;
	public static final int COMMENT=6;
	public static final int IDENTIFIER=7;
	public static final int INTEGER=8;
	public static final int LOGICAL_OPERATOR=9;
	public static final int LOGICAL_VALUE=10;
	public static final int RELATIONAL_OPERATOR=11;
	public static final int ROOT=12;
	public static final int STRING=13;
	public static final int TYPE=14;
	public static final int VAR_DEC=15;
	public static final int WS=16;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public Algol60Lexer() {} 
	public Algol60Lexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public Algol60Lexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g"; }

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:6:7: ( '(' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:6:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:7:7: ( ')' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:7:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:8:7: ( ',' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:8:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:9:7: ( ':=' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:9:9: ':='
			{
			match(":="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:10:7: ( ';' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:10:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:11:7: ( 'begin' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:11:9: 'begin'
			{
			match("begin"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:12:7: ( 'else' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:12:9: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:13:7: ( 'end' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:13:9: 'end'
			{
			match("end"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:14:7: ( 'if' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:14:9: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:15:7: ( 'procedure' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:15:9: 'procedure'
			{
			match("procedure"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:16:7: ( 'then' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:16:9: 'then'
			{
			match("then"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "TYPE"
	public final void mTYPE() throws RecognitionException {
		try {
			int _type = TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:127:5: ( 'real' | 'integer' | 'boolean' | 'string' )
			int alt1=4;
			switch ( input.LA(1) ) {
			case 'r':
				{
				alt1=1;
				}
				break;
			case 'i':
				{
				alt1=2;
				}
				break;
			case 'b':
				{
				alt1=3;
				}
				break;
			case 's':
				{
				alt1=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:127:9: 'real'
					{
					match("real"); 

					}
					break;
				case 2 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:128:9: 'integer'
					{
					match("integer"); 

					}
					break;
				case 3 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:129:9: 'boolean'
					{
					match("boolean"); 

					}
					break;
				case 4 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:130:9: 'string'
					{
					match("string"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TYPE"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:134:5: ( 'comment' (~ ( ';' ) )* ';' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:134:9: 'comment' (~ ( ';' ) )* ';'
			{
			match("comment"); 

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:134:19: (~ ( ';' ) )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '\u0000' && LA2_0 <= ':')||(LA2_0 >= '<' && LA2_0 <= '\uFFFF')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= ':')||(input.LA(1) >= '<' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop2;
				}
			}

			match(';'); 
			 _channel=HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:140:5: ( '\\'' (~ ( '`' | '\\r' | '\\n' ) )* '`' )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:140:9: '\\'' (~ ( '`' | '\\r' | '\\n' ) )* '`'
			{
			match('\''); 
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:140:14: (~ ( '`' | '\\r' | '\\n' ) )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\u0000' && LA3_0 <= '\t')||(LA3_0 >= '\u000B' && LA3_0 <= '\f')||(LA3_0 >= '\u000E' && LA3_0 <= '_')||(LA3_0 >= 'a' && LA3_0 <= '\uFFFF')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '_')||(input.LA(1) >= 'a' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			match('`'); 
			 setText(getText().substring(1, getText().length() - 1)); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "LOGICAL_VALUE"
	public final void mLOGICAL_VALUE() throws RecognitionException {
		try {
			int _type = LOGICAL_VALUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:157:5: ( 'true' | 'false' )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='t') ) {
				alt4=1;
			}
			else if ( (LA4_0=='f') ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:157:9: 'true'
					{
					match("true"); 

					}
					break;
				case 2 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:158:9: 'false'
					{
					match("false"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOGICAL_VALUE"

	// $ANTLR start "IDENTIFIER"
	public final void mIDENTIFIER() throws RecognitionException {
		try {
			int _type = IDENTIFIER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:163:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:163:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:163:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')||(LA5_0 >= 'A' && LA5_0 <= 'Z')||LA5_0=='_'||(LA5_0 >= 'a' && LA5_0 <= 'z')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop5;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENTIFIER"

	// $ANTLR start "ARITHMETIC_OPERATOR"
	public final void mARITHMETIC_OPERATOR() throws RecognitionException {
		try {
			int _type = ARITHMETIC_OPERATOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:167:5: ( '+' | '-' | '*' | '/' | '//' | '**' )
			int alt6=6;
			switch ( input.LA(1) ) {
			case '+':
				{
				alt6=1;
				}
				break;
			case '-':
				{
				alt6=2;
				}
				break;
			case '*':
				{
				int LA6_3 = input.LA(2);
				if ( (LA6_3=='*') ) {
					alt6=6;
				}

				else {
					alt6=3;
				}

				}
				break;
			case '/':
				{
				int LA6_4 = input.LA(2);
				if ( (LA6_4=='/') ) {
					alt6=5;
				}

				else {
					alt6=4;
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:167:9: '+'
					{
					match('+'); 
					}
					break;
				case 2 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:168:9: '-'
					{
					match('-'); 
					}
					break;
				case 3 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:169:9: '*'
					{
					match('*'); 
					}
					break;
				case 4 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:170:9: '/'
					{
					match('/'); 
					}
					break;
				case 5 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:171:9: '//'
					{
					match("//"); 

					}
					break;
				case 6 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:172:9: '**'
					{
					match("**"); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARITHMETIC_OPERATOR"

	// $ANTLR start "RELATIONAL_OPERATOR"
	public final void mRELATIONAL_OPERATOR() throws RecognitionException {
		try {
			int _type = RELATIONAL_OPERATOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:176:5: ( '<' | '<=' | '=' | '<>' | '>' | '>=' )
			int alt7=6;
			switch ( input.LA(1) ) {
			case '<':
				{
				switch ( input.LA(2) ) {
				case '=':
					{
					alt7=2;
					}
					break;
				case '>':
					{
					alt7=4;
					}
					break;
				default:
					alt7=1;
				}
				}
				break;
			case '=':
				{
				alt7=3;
				}
				break;
			case '>':
				{
				int LA7_3 = input.LA(2);
				if ( (LA7_3=='=') ) {
					alt7=6;
				}

				else {
					alt7=5;
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:176:9: '<'
					{
					match('<'); 
					}
					break;
				case 2 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:177:9: '<='
					{
					match("<="); 

					}
					break;
				case 3 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:178:9: '='
					{
					match('='); 
					}
					break;
				case 4 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:179:9: '<>'
					{
					match("<>"); 

					}
					break;
				case 5 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:180:9: '>'
					{
					match('>'); 
					}
					break;
				case 6 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:181:9: '>='
					{
					match(">="); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RELATIONAL_OPERATOR"

	// $ANTLR start "LOGICAL_OPERATOR"
	public final void mLOGICAL_OPERATOR() throws RecognitionException {
		try {
			int _type = LOGICAL_OPERATOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:185:5: ( '<=>' | '=>' | '\\\\/' | '/\\\\' | '~' )
			int alt8=5;
			switch ( input.LA(1) ) {
			case '<':
				{
				alt8=1;
				}
				break;
			case '=':
				{
				alt8=2;
				}
				break;
			case '\\':
				{
				alt8=3;
				}
				break;
			case '/':
				{
				alt8=4;
				}
				break;
			case '~':
				{
				alt8=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}
			switch (alt8) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:185:9: '<=>'
					{
					match("<=>"); 

					}
					break;
				case 2 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:186:9: '=>'
					{
					match("=>"); 

					}
					break;
				case 3 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:187:9: '\\\\/'
					{
					match("\\/"); 

					}
					break;
				case 4 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:188:9: '/\\\\'
					{
					match("/\\"); 

					}
					break;
				case 5 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:189:9: '~'
					{
					match('~'); 
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOGICAL_OPERATOR"

	// $ANTLR start "INTEGER"
	public final void mINTEGER() throws RecognitionException {
		try {
			int _type = INTEGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:193:5: ( ( '+' | '-' )? ( '1' .. '9' ) ( '0' .. '9' )* )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:193:9: ( '+' | '-' )? ( '1' .. '9' ) ( '0' .. '9' )*
			{
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:193:9: ( '+' | '-' )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0=='+'||LA9_0=='-') ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:193:29: ( '0' .. '9' )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop10;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:197:5: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:197:9: ( ' ' | '\\t' | '\\r' | '\\n' )+
			{
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:197:9: ( ' ' | '\\t' | '\\r' | '\\n' )+
			int cnt11=0;
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( ((LA11_0 >= '\t' && LA11_0 <= '\n')||LA11_0=='\r'||LA11_0==' ') ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt11 >= 1 ) break loop11;
					EarlyExitException eee = new EarlyExitException(11, input);
					throw eee;
				}
				cnt11++;
			}

			 _channel=HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:8: ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | TYPE | COMMENT | STRING | LOGICAL_VALUE | IDENTIFIER | ARITHMETIC_OPERATOR | RELATIONAL_OPERATOR | LOGICAL_OPERATOR | INTEGER | WS )
		int alt12=21;
		alt12 = dfa12.predict(input);
		switch (alt12) {
			case 1 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:10: T__17
				{
				mT__17(); 

				}
				break;
			case 2 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:16: T__18
				{
				mT__18(); 

				}
				break;
			case 3 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:22: T__19
				{
				mT__19(); 

				}
				break;
			case 4 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:28: T__20
				{
				mT__20(); 

				}
				break;
			case 5 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:34: T__21
				{
				mT__21(); 

				}
				break;
			case 6 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:40: T__22
				{
				mT__22(); 

				}
				break;
			case 7 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:46: T__23
				{
				mT__23(); 

				}
				break;
			case 8 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:52: T__24
				{
				mT__24(); 

				}
				break;
			case 9 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:58: T__25
				{
				mT__25(); 

				}
				break;
			case 10 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:64: T__26
				{
				mT__26(); 

				}
				break;
			case 11 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:70: T__27
				{
				mT__27(); 

				}
				break;
			case 12 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:76: TYPE
				{
				mTYPE(); 

				}
				break;
			case 13 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:81: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 14 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:89: STRING
				{
				mSTRING(); 

				}
				break;
			case 15 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:96: LOGICAL_VALUE
				{
				mLOGICAL_VALUE(); 

				}
				break;
			case 16 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:110: IDENTIFIER
				{
				mIDENTIFIER(); 

				}
				break;
			case 17 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:121: ARITHMETIC_OPERATOR
				{
				mARITHMETIC_OPERATOR(); 

				}
				break;
			case 18 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:141: RELATIONAL_OPERATOR
				{
				mRELATIONAL_OPERATOR(); 

				}
				break;
			case 19 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:161: LOGICAL_OPERATOR
				{
				mLOGICAL_OPERATOR(); 

				}
				break;
			case 20 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:178: INTEGER
				{
				mINTEGER(); 

				}
				break;
			case 21 :
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:186: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA12 dfa12 = new DFA12(this);
	static final String DFA12_eotS =
		"\1\21\5\uffff\10\21\1\uffff\2\21\1\uffff\2\24\1\uffff\1\24\2\30\3\uffff"+
		"\4\21\1\57\11\21\1\uffff\1\30\3\21\1\73\1\uffff\12\21\1\106\1\uffff\2"+
		"\21\1\111\1\112\1\113\3\21\1\117\1\21\1\uffff\2\21\3\uffff\2\21\1\112"+
		"\1\uffff\3\21\1\113\1\21\2\113\4\21\1\uffff\1\135\1\uffff";
	static final String DFA12_eofS =
		"\136\uffff";
	static final String DFA12_minS =
		"\1\11\5\uffff\1\145\1\154\1\146\1\162\1\150\1\145\1\164\1\157\1\uffff"+
		"\1\141\1\60\1\uffff\2\61\1\uffff\1\134\1\75\1\76\3\uffff\1\147\1\157\1"+
		"\163\1\144\1\60\1\164\1\157\1\145\1\165\1\141\1\162\1\155\1\154\1\60\1"+
		"\uffff\1\76\1\151\1\154\1\145\1\60\1\uffff\1\145\1\143\1\156\1\145\1\154"+
		"\1\151\1\155\1\163\1\156\1\145\1\60\1\uffff\1\147\1\145\3\60\1\156\2\145"+
		"\1\60\1\141\1\uffff\1\145\1\144\3\uffff\1\147\1\156\1\60\1\uffff\1\156"+
		"\1\162\1\165\1\60\1\164\2\60\1\162\1\0\1\145\1\0\1\uffff\1\60\1\uffff";
	static final String DFA12_maxS =
		"\1\176\5\uffff\1\157\2\156\2\162\1\145\1\164\1\157\1\uffff\1\141\1\71"+
		"\1\uffff\2\71\1\uffff\1\134\1\75\1\76\3\uffff\1\147\1\157\1\163\1\144"+
		"\1\172\1\164\1\157\1\145\1\165\1\141\1\162\1\155\1\154\1\71\1\uffff\1"+
		"\76\1\151\1\154\1\145\1\172\1\uffff\1\145\1\143\1\156\1\145\1\154\1\151"+
		"\1\155\1\163\1\156\1\145\1\172\1\uffff\1\147\1\145\3\172\1\156\2\145\1"+
		"\172\1\141\1\uffff\1\145\1\144\3\uffff\1\147\1\156\1\172\1\uffff\1\156"+
		"\1\162\1\165\1\172\1\164\2\172\1\162\1\uffff\1\145\1\uffff\1\uffff\1\172"+
		"\1\uffff";
	static final String DFA12_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\10\uffff\1\16\2\uffff\1\20\2\uffff\1\21\3"+
		"\uffff\1\22\1\23\1\25\16\uffff\1\24\5\uffff\1\11\13\uffff\1\10\12\uffff"+
		"\1\7\2\uffff\1\13\1\17\1\14\3\uffff\1\6\13\uffff\1\15\1\uffff\1\12";
	static final String DFA12_specialS =
		"\130\uffff\1\1\1\uffff\1\0\3\uffff}>";
	static final String[] DFA12_transitionS = {
			"\2\32\2\uffff\1\32\22\uffff\1\32\6\uffff\1\16\1\1\1\2\1\24\1\22\1\3\1"+
			"\23\1\uffff\1\25\1\uffff\11\20\1\4\1\5\1\26\1\27\1\30\35\uffff\1\31\5"+
			"\uffff\1\6\1\15\1\uffff\1\7\1\17\2\uffff\1\10\6\uffff\1\11\1\uffff\1"+
			"\13\1\14\1\12\11\uffff\1\31",
			"",
			"",
			"",
			"",
			"",
			"\1\33\11\uffff\1\34",
			"\1\35\1\uffff\1\36",
			"\1\37\7\uffff\1\40",
			"\1\41",
			"\1\42\11\uffff\1\43",
			"\1\44",
			"\1\45",
			"\1\46",
			"",
			"\1\47",
			"\12\50",
			"",
			"\11\51",
			"\11\51",
			"",
			"\1\31",
			"\1\52",
			"\1\31",
			"",
			"",
			"",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"\1\67",
			"\12\50",
			"",
			"\1\31",
			"\1\70",
			"\1\71",
			"\1\72",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"",
			"\1\74",
			"\1\75",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"",
			"\1\107",
			"\1\110",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"\1\114",
			"\1\115",
			"\1\116",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"\1\120",
			"",
			"\1\121",
			"\1\122",
			"",
			"",
			"",
			"\1\123",
			"\1\124",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"",
			"\1\125",
			"\1\126",
			"\1\127",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"\1\130",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			"\1\131",
			"\60\133\12\132\7\133\32\132\4\133\1\132\1\133\32\132\uff85\133",
			"\1\134",
			"\60\133\12\132\7\133\32\132\4\133\1\132\1\133\32\132\uff85\133",
			"",
			"\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
			""
	};

	static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
	static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
	static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
	static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
	static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
	static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
	static final short[][] DFA12_transition;

	static {
		int numStates = DFA12_transitionS.length;
		DFA12_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
		}
	}

	protected class DFA12 extends DFA {

		public DFA12(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 12;
			this.eot = DFA12_eot;
			this.eof = DFA12_eof;
			this.min = DFA12_min;
			this.max = DFA12_max;
			this.accept = DFA12_accept;
			this.special = DFA12_special;
			this.transition = DFA12_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | TYPE | COMMENT | STRING | LOGICAL_VALUE | IDENTIFIER | ARITHMETIC_OPERATOR | RELATIONAL_OPERATOR | LOGICAL_OPERATOR | INTEGER | WS );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA12_90 = input.LA(1);
						s = -1;
						if ( ((LA12_90 >= '\u0000' && LA12_90 <= '/')||(LA12_90 >= ':' && LA12_90 <= '@')||(LA12_90 >= '[' && LA12_90 <= '^')||LA12_90=='`'||(LA12_90 >= '{' && LA12_90 <= '\uFFFF')) ) {s = 91;}
						else if ( ((LA12_90 >= '0' && LA12_90 <= '9')||(LA12_90 >= 'A' && LA12_90 <= 'Z')||LA12_90=='_'||(LA12_90 >= 'a' && LA12_90 <= 'z')) ) {s = 90;}
						else s = 17;
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA12_88 = input.LA(1);
						s = -1;
						if ( ((LA12_88 >= '0' && LA12_88 <= '9')||(LA12_88 >= 'A' && LA12_88 <= 'Z')||LA12_88=='_'||(LA12_88 >= 'a' && LA12_88 <= 'z')) ) {s = 90;}
						else if ( ((LA12_88 >= '\u0000' && LA12_88 <= '/')||(LA12_88 >= ':' && LA12_88 <= '@')||(LA12_88 >= '[' && LA12_88 <= '^')||LA12_88=='`'||(LA12_88 >= '{' && LA12_88 <= '\uFFFF')) ) {s = 91;}
						else s = 17;
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 12, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
