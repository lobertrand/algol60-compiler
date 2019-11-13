// $ANTLR 3.5.1 /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g 2019-11-13 16:03:58

package eu.telecomnancy;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Algol60Lexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int T__56=56;
	public static final int ARG_TYPE=4;
	public static final int ARITHMETIC_OPERATOR=5;
	public static final int BLOCK=6;
	public static final int COMMENT=7;
	public static final int CONDITION=8;
	public static final int DO=9;
	public static final int ELSE_DEF=10;
	public static final int FOR_CLAUSE=11;
	public static final int IDENTIFIER=12;
	public static final int ID_LIST=13;
	public static final int IF_DEF=14;
	public static final int IF_STATEMENT=15;
	public static final int INIT=16;
	public static final int INTEGER=17;
	public static final int LOGICAL_OPERATOR=18;
	public static final int LOGICAL_VALUE=19;
	public static final int PARAMS_DEC=20;
	public static final int PARAM_LIST=21;
	public static final int PARAM_PART=22;
	public static final int PROC_CALL=23;
	public static final int PROC_DEC=24;
	public static final int PROC_HEADING=25;
	public static final int REAL=26;
	public static final int RELATIONAL_OPERATOR=27;
	public static final int ROOT=28;
	public static final int SIGNED_INTEGER=29;
	public static final int SPEC_PART=30;
	public static final int STEP=31;
	public static final int STRING=32;
	public static final int THEN_DEF=33;
	public static final int TYPE=34;
	public static final int UNTIL=35;
	public static final int VALUE_PART=36;
	public static final int VAR_DEC=37;
	public static final int WHILE_CLAUSE=38;
	public static final int WS=39;

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
	@Override public String getGrammarFileName() { return "/home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g"; }

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:6:7: ( '(' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:6:9: '('
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
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:7:7: ( ')' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:7:9: ')'
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
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:8:7: ( ',' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:8:9: ','
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
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:9:7: ( ':=' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:9:9: ':='
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
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:10:7: ( ';' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:10:9: ';'
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
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:11:7: ( 'begin' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:11:9: 'begin'
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
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:12:7: ( 'do' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:12:9: 'do'
			{
			match("do"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:13:7: ( 'else' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:13:9: 'else'
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
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:14:7: ( 'end' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:14:9: 'end'
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
	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:15:7: ( 'for' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:15:9: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:16:7: ( 'if' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:16:9: 'if'
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
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:17:7: ( 'procedure' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:17:9: 'procedure'
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
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:18:7: ( 'step' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:18:9: 'step'
			{
			match("step"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:19:7: ( 'then' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:19:9: 'then'
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
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:20:7: ( 'until' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:20:9: 'until'
			{
			match("until"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:21:7: ( 'value' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:21:9: 'value'
			{
			match("value"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:22:7: ( 'while' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:22:9: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__56"

	// $ANTLR start "TYPE"
	public final void mTYPE() throws RecognitionException {
		try {
			int _type = TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:160:5: ( 'real' | 'integer' | 'boolean' | 'string' )
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
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:160:9: 'real'
					{
					match("real"); 

					}
					break;
				case 2 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:161:9: 'integer'
					{
					match("integer"); 

					}
					break;
				case 3 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:162:9: 'boolean'
					{
					match("boolean"); 

					}
					break;
				case 4 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:163:9: 'string'
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
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:167:5: ( 'comment' (~ ( ';' ) )* ';' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:167:9: 'comment' (~ ( ';' ) )* ';'
			{
			match("comment"); 

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:167:19: (~ ( ';' ) )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '\u0000' && LA2_0 <= ':')||(LA2_0 >= '<' && LA2_0 <= '\uFFFF')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
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
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:175:5: ( '\"' (~ ( '\"' | '\\r' | '\\n' ) )* '\"' )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:175:9: '\"' (~ ( '\"' | '\\r' | '\\n' ) )* '\"'
			{
			match('\"'); 
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:175:13: (~ ( '\"' | '\\r' | '\\n' ) )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\u0000' && LA3_0 <= '\t')||(LA3_0 >= '\u000B' && LA3_0 <= '\f')||(LA3_0 >= '\u000E' && LA3_0 <= '!')||(LA3_0 >= '#' && LA3_0 <= '\uFFFF')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
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

			match('\"'); 
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
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:192:5: ( 'true' | 'false' )
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
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:192:9: 'true'
					{
					match("true"); 

					}
					break;
				case 2 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:193:9: 'false'
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

	// $ANTLR start "SIGNED_INTEGER"
	public final void mSIGNED_INTEGER() throws RecognitionException {
		try {
			int _type = SIGNED_INTEGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:198:5: ( '+' INTEGER | '-' INTEGER )
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0=='+') ) {
				alt5=1;
			}
			else if ( (LA5_0=='-') ) {
				alt5=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:198:7: '+' INTEGER
					{
					match('+'); 
					mINTEGER(); 

					}
					break;
				case 2 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:199:7: '-' INTEGER
					{
					match('-'); 
					mINTEGER(); 

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
	// $ANTLR end "SIGNED_INTEGER"

	// $ANTLR start "INTEGER"
	public final void mINTEGER() throws RecognitionException {
		try {
			int _type = INTEGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:203:5: ( ( '1' .. '9' ) ( '0' .. '9' )* )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:203:9: ( '1' .. '9' ) ( '0' .. '9' )*
			{
			if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:203:19: ( '0' .. '9' )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
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
					break loop6;
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

	// $ANTLR start "REAL"
	public final void mREAL() throws RecognitionException {
		try {
			int _type = REAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:206:5: ( ( '0' .. '9' )* '.' ( '0' .. '9' )* )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:206:9: ( '0' .. '9' )* '.' ( '0' .. '9' )*
			{
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:206:9: ( '0' .. '9' )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
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
					break loop7;
				}
			}

			match('.'); 
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:206:23: ( '0' .. '9' )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
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
					break loop8;
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
	// $ANTLR end "REAL"

	// $ANTLR start "IDENTIFIER"
	public final void mIDENTIFIER() throws RecognitionException {
		try {
			int _type = IDENTIFIER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:210:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:210:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:210:9: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= '0' && LA9_0 <= '9')||(LA9_0 >= 'A' && LA9_0 <= 'Z')||LA9_0=='_'||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
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
					break loop9;
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
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:214:5: ( '+' | '-' | '*' | '/' | '//' | '**' )
			int alt10=6;
			switch ( input.LA(1) ) {
			case '+':
				{
				alt10=1;
				}
				break;
			case '-':
				{
				alt10=2;
				}
				break;
			case '*':
				{
				int LA10_3 = input.LA(2);
				if ( (LA10_3=='*') ) {
					alt10=6;
				}

				else {
					alt10=3;
				}

				}
				break;
			case '/':
				{
				int LA10_4 = input.LA(2);
				if ( (LA10_4=='/') ) {
					alt10=5;
				}

				else {
					alt10=4;
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:214:9: '+'
					{
					match('+'); 
					}
					break;
				case 2 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:215:9: '-'
					{
					match('-'); 
					}
					break;
				case 3 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:216:9: '*'
					{
					match('*'); 
					}
					break;
				case 4 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:217:9: '/'
					{
					match('/'); 
					}
					break;
				case 5 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:218:9: '//'
					{
					match("//"); 

					}
					break;
				case 6 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:219:9: '**'
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
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:223:5: ( '<' | '<=' | '=' | '<>' | '>' | '>=' )
			int alt11=6;
			switch ( input.LA(1) ) {
			case '<':
				{
				switch ( input.LA(2) ) {
				case '=':
					{
					alt11=2;
					}
					break;
				case '>':
					{
					alt11=4;
					}
					break;
				default:
					alt11=1;
				}
				}
				break;
			case '=':
				{
				alt11=3;
				}
				break;
			case '>':
				{
				int LA11_3 = input.LA(2);
				if ( (LA11_3=='=') ) {
					alt11=6;
				}

				else {
					alt11=5;
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:223:9: '<'
					{
					match('<'); 
					}
					break;
				case 2 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:224:9: '<='
					{
					match("<="); 

					}
					break;
				case 3 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:225:9: '='
					{
					match('='); 
					}
					break;
				case 4 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:226:9: '<>'
					{
					match("<>"); 

					}
					break;
				case 5 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:227:9: '>'
					{
					match('>'); 
					}
					break;
				case 6 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:228:9: '>='
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
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:232:5: ( '<=>' | '=>' | '\\\\/' | '/\\\\' | '~' )
			int alt12=5;
			switch ( input.LA(1) ) {
			case '<':
				{
				alt12=1;
				}
				break;
			case '=':
				{
				alt12=2;
				}
				break;
			case '\\':
				{
				alt12=3;
				}
				break;
			case '/':
				{
				alt12=4;
				}
				break;
			case '~':
				{
				alt12=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}
			switch (alt12) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:232:9: '<=>'
					{
					match("<=>"); 

					}
					break;
				case 2 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:233:9: '=>'
					{
					match("=>"); 

					}
					break;
				case 3 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:234:9: '\\\\/'
					{
					match("\\/"); 

					}
					break;
				case 4 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:235:9: '/\\\\'
					{
					match("/\\"); 

					}
					break;
				case 5 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:236:9: '~'
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

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:241:5: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:241:9: ( ' ' | '\\t' | '\\r' | '\\n' )+
			{
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:241:9: ( ' ' | '\\t' | '\\r' | '\\n' )+
			int cnt13=0;
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( ((LA13_0 >= '\t' && LA13_0 <= '\n')||LA13_0=='\r'||LA13_0==' ') ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
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
					if ( cnt13 >= 1 ) break loop13;
					EarlyExitException eee = new EarlyExitException(13, input);
					throw eee;
				}
				cnt13++;
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
		// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:8: ( T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | TYPE | COMMENT | STRING | LOGICAL_VALUE | SIGNED_INTEGER | INTEGER | REAL | IDENTIFIER | ARITHMETIC_OPERATOR | RELATIONAL_OPERATOR | LOGICAL_OPERATOR | WS )
		int alt14=29;
		alt14 = dfa14.predict(input);
		switch (alt14) {
			case 1 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:10: T__40
				{
				mT__40(); 

				}
				break;
			case 2 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:16: T__41
				{
				mT__41(); 

				}
				break;
			case 3 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:22: T__42
				{
				mT__42(); 

				}
				break;
			case 4 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:28: T__43
				{
				mT__43(); 

				}
				break;
			case 5 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:34: T__44
				{
				mT__44(); 

				}
				break;
			case 6 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:40: T__45
				{
				mT__45(); 

				}
				break;
			case 7 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:46: T__46
				{
				mT__46(); 

				}
				break;
			case 8 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:52: T__47
				{
				mT__47(); 

				}
				break;
			case 9 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:58: T__48
				{
				mT__48(); 

				}
				break;
			case 10 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:64: T__49
				{
				mT__49(); 

				}
				break;
			case 11 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:70: T__50
				{
				mT__50(); 

				}
				break;
			case 12 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:76: T__51
				{
				mT__51(); 

				}
				break;
			case 13 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:82: T__52
				{
				mT__52(); 

				}
				break;
			case 14 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:88: T__53
				{
				mT__53(); 

				}
				break;
			case 15 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:94: T__54
				{
				mT__54(); 

				}
				break;
			case 16 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:100: T__55
				{
				mT__55(); 

				}
				break;
			case 17 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:106: T__56
				{
				mT__56(); 

				}
				break;
			case 18 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:112: TYPE
				{
				mTYPE(); 

				}
				break;
			case 19 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:117: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 20 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:125: STRING
				{
				mSTRING(); 

				}
				break;
			case 21 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:132: LOGICAL_VALUE
				{
				mLOGICAL_VALUE(); 

				}
				break;
			case 22 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:146: SIGNED_INTEGER
				{
				mSIGNED_INTEGER(); 

				}
				break;
			case 23 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:161: INTEGER
				{
				mINTEGER(); 

				}
				break;
			case 24 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:169: REAL
				{
				mREAL(); 

				}
				break;
			case 25 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:174: IDENTIFIER
				{
				mIDENTIFIER(); 

				}
				break;
			case 26 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:185: ARITHMETIC_OPERATOR
				{
				mARITHMETIC_OPERATOR(); 

				}
				break;
			case 27 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:205: RELATIONAL_OPERATOR
				{
				mRELATIONAL_OPERATOR(); 

				}
				break;
			case 28 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:225: LOGICAL_OPERATOR
				{
				mLOGICAL_OPERATOR(); 

				}
				break;
			case 29 :
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:1:242: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA14 dfa14 = new DFA14(this);
	static final String DFA14_eotS =
		"\1\31\5\uffff\15\31\1\uffff\2\32\1\65\1\31\3\uffff\1\32\2\36\3\uffff\2"+
		"\31\1\71\4\31\1\76\12\31\1\uffff\1\65\1\uffff\1\36\2\31\1\uffff\1\31\1"+
		"\115\1\116\1\31\1\uffff\15\31\1\135\2\uffff\3\31\1\141\1\31\1\143\1\144"+
		"\3\31\1\150\1\31\1\152\1\31\1\uffff\1\144\2\31\1\uffff\1\31\2\uffff\1"+
		"\157\1\160\1\161\1\uffff\1\31\1\uffff\3\31\1\150\3\uffff\1\31\2\150\4"+
		"\31\1\uffff\1\173\1\uffff";
	static final String DFA14_eofS =
		"\174\uffff";
	static final String DFA14_minS =
		"\1\11\5\uffff\1\145\1\157\1\154\1\141\1\146\1\162\1\164\1\150\1\156\1"+
		"\141\1\150\1\145\1\157\1\uffff\2\61\2\56\3\uffff\1\134\1\75\1\76\3\uffff"+
		"\1\147\1\157\1\60\1\163\1\144\1\162\1\154\1\60\1\164\1\157\2\145\1\165"+
		"\1\164\1\154\1\151\1\141\1\155\1\uffff\1\56\1\uffff\1\76\1\151\1\154\1"+
		"\uffff\1\145\2\60\1\163\1\uffff\1\145\1\143\1\160\1\151\1\156\1\145\1"+
		"\151\1\165\2\154\1\155\1\156\1\145\1\60\2\uffff\1\145\1\147\1\145\1\60"+
		"\1\156\2\60\1\154\2\145\1\60\1\145\1\60\1\141\1\uffff\1\60\1\145\1\144"+
		"\1\uffff\1\147\2\uffff\3\60\1\uffff\1\156\1\uffff\1\156\1\162\1\165\1"+
		"\60\3\uffff\1\164\2\60\1\162\1\0\1\145\1\0\1\uffff\1\60\1\uffff";
	static final String DFA14_maxS =
		"\1\176\5\uffff\2\157\1\156\1\157\1\156\1\162\1\164\1\162\1\156\1\141\1"+
		"\150\1\145\1\157\1\uffff\2\71\1\172\1\71\3\uffff\1\134\1\75\1\76\3\uffff"+
		"\1\147\1\157\1\172\1\163\1\144\1\162\1\154\1\172\1\164\1\157\1\162\1\145"+
		"\1\165\1\164\1\154\1\151\1\141\1\155\1\uffff\1\172\1\uffff\1\76\1\151"+
		"\1\154\1\uffff\1\145\2\172\1\163\1\uffff\1\145\1\143\1\160\1\151\1\156"+
		"\1\145\1\151\1\165\2\154\1\155\1\156\1\145\1\172\2\uffff\1\145\1\147\1"+
		"\145\1\172\1\156\2\172\1\154\2\145\1\172\1\145\1\172\1\141\1\uffff\1\172"+
		"\1\145\1\144\1\uffff\1\147\2\uffff\3\172\1\uffff\1\156\1\uffff\1\156\1"+
		"\162\1\165\1\172\3\uffff\1\164\2\172\1\162\1\uffff\1\145\1\uffff\1\uffff"+
		"\1\172\1\uffff";
	static final String DFA14_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\15\uffff\1\24\4\uffff\1\30\1\31\1\32\3\uffff"+
		"\1\33\1\34\1\35\22\uffff\1\26\1\uffff\1\27\3\uffff\1\7\4\uffff\1\13\16"+
		"\uffff\1\11\1\12\16\uffff\1\10\3\uffff\1\15\1\uffff\1\16\1\25\3\uffff"+
		"\1\22\1\uffff\1\6\4\uffff\1\17\1\20\1\21\7\uffff\1\23\1\uffff\1\14";
	static final String DFA14_specialS =
		"\166\uffff\1\0\1\uffff\1\1\3\uffff}>";
	static final String[] DFA14_transitionS = {
			"\2\40\2\uffff\1\40\22\uffff\1\40\1\uffff\1\23\5\uffff\1\1\1\2\1\32\1"+
			"\24\1\3\1\25\1\30\1\33\1\27\11\26\1\4\1\5\1\34\1\35\1\36\35\uffff\1\37"+
			"\5\uffff\1\6\1\22\1\7\1\10\1\11\2\uffff\1\12\6\uffff\1\13\1\uffff\1\21"+
			"\1\14\1\15\1\16\1\17\1\20\6\uffff\1\37",
			"",
			"",
			"",
			"",
			"",
			"\1\41\11\uffff\1\42",
			"\1\43",
			"\1\44\1\uffff\1\45",
			"\1\47\15\uffff\1\46",
			"\1\50\7\uffff\1\51",
			"\1\52",
			"\1\53",
			"\1\54\11\uffff\1\55",
			"\1\56",
			"\1\57",
			"\1\60",
			"\1\61",
			"\1\62",
			"",
			"\11\63",
			"\11\63",
			"\1\30\1\uffff\12\64\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\30\1\uffff\12\27",
			"",
			"",
			"",
			"\1\37",
			"\1\66",
			"\1\37",
			"",
			"",
			"",
			"\1\67",
			"\1\70",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\72",
			"\1\73",
			"\1\74",
			"\1\75",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\77",
			"\1\100",
			"\1\101\14\uffff\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"",
			"\1\30\1\uffff\12\64\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"\1\37",
			"\1\112",
			"\1\113",
			"",
			"\1\114",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\117",
			"",
			"\1\120",
			"\1\121",
			"\1\122",
			"\1\123",
			"\1\124",
			"\1\125",
			"\1\126",
			"\1\127",
			"\1\130",
			"\1\131",
			"\1\132",
			"\1\133",
			"\1\134",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"",
			"\1\136",
			"\1\137",
			"\1\140",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\142",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\145",
			"\1\146",
			"\1\147",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\151",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\153",
			"",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\154",
			"\1\155",
			"",
			"\1\156",
			"",
			"",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"\1\162",
			"",
			"\1\163",
			"\1\164",
			"\1\165",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"",
			"",
			"",
			"\1\166",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			"\1\167",
			"\60\171\12\170\7\171\32\170\4\171\1\170\1\171\32\170\uff85\171",
			"\1\172",
			"\60\171\12\170\7\171\32\170\4\171\1\170\1\171\32\170\uff85\171",
			"",
			"\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
			""
	};

	static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
	static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
	static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
	static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
	static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
	static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
	static final short[][] DFA14_transition;

	static {
		int numStates = DFA14_transitionS.length;
		DFA14_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
		}
	}

	protected class DFA14 extends DFA {

		public DFA14(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 14;
			this.eot = DFA14_eot;
			this.eof = DFA14_eof;
			this.min = DFA14_min;
			this.max = DFA14_max;
			this.accept = DFA14_accept;
			this.special = DFA14_special;
			this.transition = DFA14_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | TYPE | COMMENT | STRING | LOGICAL_VALUE | SIGNED_INTEGER | INTEGER | REAL | IDENTIFIER | ARITHMETIC_OPERATOR | RELATIONAL_OPERATOR | LOGICAL_OPERATOR | WS );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA14_118 = input.LA(1);
						s = -1;
						if ( ((LA14_118 >= '0' && LA14_118 <= '9')||(LA14_118 >= 'A' && LA14_118 <= 'Z')||LA14_118=='_'||(LA14_118 >= 'a' && LA14_118 <= 'z')) ) {s = 120;}
						else if ( ((LA14_118 >= '\u0000' && LA14_118 <= '/')||(LA14_118 >= ':' && LA14_118 <= '@')||(LA14_118 >= '[' && LA14_118 <= '^')||LA14_118=='`'||(LA14_118 >= '{' && LA14_118 <= '\uFFFF')) ) {s = 121;}
						else s = 25;
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA14_120 = input.LA(1);
						s = -1;
						if ( ((LA14_120 >= '\u0000' && LA14_120 <= '/')||(LA14_120 >= ':' && LA14_120 <= '@')||(LA14_120 >= '[' && LA14_120 <= '^')||LA14_120=='`'||(LA14_120 >= '{' && LA14_120 <= '\uFFFF')) ) {s = 121;}
						else if ( ((LA14_120 >= '0' && LA14_120 <= '9')||(LA14_120 >= 'A' && LA14_120 <= 'Z')||LA14_120=='_'||(LA14_120 >= 'a' && LA14_120 <= 'z')) ) {s = 120;}
						else s = 25;
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 14, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
