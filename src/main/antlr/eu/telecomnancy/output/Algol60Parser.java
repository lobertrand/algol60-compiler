// $ANTLR 3.5.1 /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g 2019-11-13 16:03:58

package eu.telecomnancy;
// import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;
import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class Algol60Parser extends DebugParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARG_TYPE", "ARITHMETIC_OPERATOR", 
		"BLOCK", "COMMENT", "CONDITION", "DO", "ELSE_DEF", "FOR_CLAUSE", "IDENTIFIER", 
		"ID_LIST", "IF_DEF", "IF_STATEMENT", "INIT", "INTEGER", "LOGICAL_OPERATOR", 
		"LOGICAL_VALUE", "PARAMS_DEC", "PARAM_LIST", "PARAM_PART", "PROC_CALL", 
		"PROC_DEC", "PROC_HEADING", "REAL", "RELATIONAL_OPERATOR", "ROOT", "SIGNED_INTEGER", 
		"SPEC_PART", "STEP", "STRING", "THEN_DEF", "TYPE", "UNTIL", "VALUE_PART", 
		"VAR_DEC", "WHILE_CLAUSE", "WS", "'('", "')'", "','", "':='", "';'", "'begin'", 
		"'do'", "'else'", "'end'", "'for'", "'if'", "'procedure'", "'step'", "'then'", 
		"'until'", "'value'", "'while'"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public static final String[] ruleNames = new String[] {
		"invalidRule", "logical_statement", "value_part", "specification_part", 
		"expression", "statement", "prog", "formal_parameter_part", "variable_declaration", 
		"for_clause", "procedure_heading", "block", "identifier_list", "procedure_call", 
		"declaration", "while_clause", "if_clause", "boolean_operator", "procedure_declaration", 
		"actual_parameter_list", "assignment", "procedure_body"
	};

	public static final boolean[] decisionCanBacktrack = new boolean[] {
		false, // invalid decision
		false, false, false, false, false, false, false, false, false, false
	};

 
	public int ruleLevel = 0;
	public int getRuleLevel() { return ruleLevel; }
	public void incRuleLevel() { ruleLevel++; }
	public void decRuleLevel() { ruleLevel--; }
	public Algol60Parser(TokenStream input) {
		this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
	}
	public Algol60Parser(TokenStream input, int port, RecognizerSharedState state) {
		super(input, state);
		DebugEventSocketProxy proxy =
			new DebugEventSocketProxy(this,port,adaptor);
		setDebugListener(proxy);
		setTokenStream(new DebugTokenStream(input,proxy));
		try {
			proxy.handshake();
		}
		catch (IOException ioe) {
			reportError(ioe);
		}
		TreeAdaptor adap = new CommonTreeAdaptor();
		setTreeAdaptor(adap);
		proxy.setTreeAdaptor(adap);
	}

	public Algol60Parser(TokenStream input, DebugEventListener dbg) {
		super(input, dbg);
		 
		TreeAdaptor adap = new CommonTreeAdaptor();
		setTreeAdaptor(adap);

	}

	protected boolean evalPredicate(boolean result, String predicate) {
		dbg.semanticPredicate(result, predicate);
		return result;
	}

		protected DebugTreeAdaptor adaptor;
		public void setTreeAdaptor(TreeAdaptor adaptor) {
			this.adaptor = new DebugTreeAdaptor(dbg,adaptor);
		}
		public TreeAdaptor getTreeAdaptor() {
			return adaptor;
		}
	@Override public String[] getTokenNames() { return Algol60Parser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g"; }


	public static class prog_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "prog"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:52:1: prog : block -> ^( ROOT block ) ;
	public final Algol60Parser.prog_return prog() throws RecognitionException {
		Algol60Parser.prog_return retval = new Algol60Parser.prog_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope block1 =null;

		RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");

		try { dbg.enterRule(getGrammarFileName(), "prog");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(52, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:52:5: ( block -> ^( ROOT block ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:52:9: block
			{
			dbg.location(52,9);
			pushFollow(FOLLOW_block_in_prog263);
			block1=block();
			state._fsp--;

			stream_block.add(block1.getTree());
			// AST REWRITE
			// elements: block
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 52:15: -> ^( ROOT block )
			{
				dbg.location(52,18);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:52:18: ^( ROOT block )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(52,20);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ROOT, "ROOT"), root_1);
				dbg.location(52,25);
				adaptor.addChild(root_1, stream_block.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(53, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "prog");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "prog"


	public static class block_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "block"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:55:1: block : 'begin' statement ( ';' statement )* 'end' -> ^( BLOCK ( statement )* ) ;
	public final Algol60Parser.block_return block() throws RecognitionException {
		Algol60Parser.block_return retval = new Algol60Parser.block_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal2=null;
		Token char_literal4=null;
		Token string_literal6=null;
		ParserRuleReturnScope statement3 =null;
		ParserRuleReturnScope statement5 =null;

		Object string_literal2_tree=null;
		Object char_literal4_tree=null;
		Object string_literal6_tree=null;
		RewriteRuleTokenStream stream_44=new RewriteRuleTokenStream(adaptor,"token 44");
		RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
		RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
		RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");

		try { dbg.enterRule(getGrammarFileName(), "block");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(55, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:56:5: ( 'begin' statement ( ';' statement )* 'end' -> ^( BLOCK ( statement )* ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:56:9: 'begin' statement ( ';' statement )* 'end'
			{
			dbg.location(56,9);
			string_literal2=(Token)match(input,45,FOLLOW_45_in_block291);  
			stream_45.add(string_literal2);
			dbg.location(56,17);
			pushFollow(FOLLOW_statement_in_block293);
			statement3=statement();
			state._fsp--;

			stream_statement.add(statement3.getTree());dbg.location(56,27);
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:56:27: ( ';' statement )*
			try { dbg.enterSubRule(1);

			loop1:
			while (true) {
				int alt1=2;
				try { dbg.enterDecision(1, decisionCanBacktrack[1]);

				int LA1_0 = input.LA(1);
				if ( (LA1_0==44) ) {
					alt1=1;
				}

				} finally {dbg.exitDecision(1);}

				switch (alt1) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:56:28: ';' statement
					{
					dbg.location(56,28);
					char_literal4=(Token)match(input,44,FOLLOW_44_in_block296);  
					stream_44.add(char_literal4);
					dbg.location(56,32);
					pushFollow(FOLLOW_statement_in_block298);
					statement5=statement();
					state._fsp--;

					stream_statement.add(statement5.getTree());
					}
					break;

				default :
					break loop1;
				}
			}
			} finally {dbg.exitSubRule(1);}
			dbg.location(56,44);
			string_literal6=(Token)match(input,48,FOLLOW_48_in_block302);  
			stream_48.add(string_literal6);

			// AST REWRITE
			// elements: statement
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 56:50: -> ^( BLOCK ( statement )* )
			{
				dbg.location(56,53);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:56:53: ^( BLOCK ( statement )* )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(56,55);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);
				dbg.location(56,61);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:56:61: ( statement )*
				while ( stream_statement.hasNext() ) {
					dbg.location(56,61);
					adaptor.addChild(root_1, stream_statement.nextTree());
				}
				stream_statement.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(57, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "block");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "block"


	public static class statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:59:1: statement : ( declaration | assignment | if_clause | for_clause | while_clause | block );
	public final Algol60Parser.statement_return statement() throws RecognitionException {
		Algol60Parser.statement_return retval = new Algol60Parser.statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope declaration7 =null;
		ParserRuleReturnScope assignment8 =null;
		ParserRuleReturnScope if_clause9 =null;
		ParserRuleReturnScope for_clause10 =null;
		ParserRuleReturnScope while_clause11 =null;
		ParserRuleReturnScope block12 =null;


		try { dbg.enterRule(getGrammarFileName(), "statement");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(59, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:60:5: ( declaration | assignment | if_clause | for_clause | while_clause | block )
			int alt2=6;
			try { dbg.enterDecision(2, decisionCanBacktrack[2]);

			switch ( input.LA(1) ) {
			case TYPE:
			case 51:
				{
				alt2=1;
				}
				break;
			case IDENTIFIER:
				{
				alt2=2;
				}
				break;
			case 50:
				{
				alt2=3;
				}
				break;
			case 49:
				{
				alt2=4;
				}
				break;
			case 56:
				{
				alt2=5;
				}
				break;
			case 45:
				{
				alt2=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}
			} finally {dbg.exitDecision(2);}

			switch (alt2) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:60:9: declaration
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(60,9);
					pushFollow(FOLLOW_declaration_in_statement330);
					declaration7=declaration();
					state._fsp--;

					adaptor.addChild(root_0, declaration7.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:61:9: assignment
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(61,9);
					pushFollow(FOLLOW_assignment_in_statement340);
					assignment8=assignment();
					state._fsp--;

					adaptor.addChild(root_0, assignment8.getTree());

					}
					break;
				case 3 :
					dbg.enterAlt(3);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:62:9: if_clause
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(62,9);
					pushFollow(FOLLOW_if_clause_in_statement350);
					if_clause9=if_clause();
					state._fsp--;

					adaptor.addChild(root_0, if_clause9.getTree());

					}
					break;
				case 4 :
					dbg.enterAlt(4);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:63:9: for_clause
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(63,9);
					pushFollow(FOLLOW_for_clause_in_statement360);
					for_clause10=for_clause();
					state._fsp--;

					adaptor.addChild(root_0, for_clause10.getTree());

					}
					break;
				case 5 :
					dbg.enterAlt(5);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:64:9: while_clause
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(64,9);
					pushFollow(FOLLOW_while_clause_in_statement370);
					while_clause11=while_clause();
					state._fsp--;

					adaptor.addChild(root_0, while_clause11.getTree());

					}
					break;
				case 6 :
					dbg.enterAlt(6);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:65:9: block
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(65,9);
					pushFollow(FOLLOW_block_in_statement380);
					block12=block();
					state._fsp--;

					adaptor.addChild(root_0, block12.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(66, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "statement");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "statement"


	public static class declaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "declaration"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:70:1: declaration : ( variable_declaration | procedure_declaration );
	public final Algol60Parser.declaration_return declaration() throws RecognitionException {
		Algol60Parser.declaration_return retval = new Algol60Parser.declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope variable_declaration13 =null;
		ParserRuleReturnScope procedure_declaration14 =null;


		try { dbg.enterRule(getGrammarFileName(), "declaration");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(70, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:71:5: ( variable_declaration | procedure_declaration )
			int alt3=2;
			try { dbg.enterDecision(3, decisionCanBacktrack[3]);

			int LA3_0 = input.LA(1);
			if ( (LA3_0==TYPE) ) {
				alt3=1;
			}
			else if ( (LA3_0==51) ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(3);}

			switch (alt3) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:71:9: variable_declaration
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(71,9);
					pushFollow(FOLLOW_variable_declaration_in_declaration401);
					variable_declaration13=variable_declaration();
					state._fsp--;

					adaptor.addChild(root_0, variable_declaration13.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:72:9: procedure_declaration
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(72,9);
					pushFollow(FOLLOW_procedure_declaration_in_declaration411);
					procedure_declaration14=procedure_declaration();
					state._fsp--;

					adaptor.addChild(root_0, procedure_declaration14.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(73, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "declaration");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "declaration"


	public static class variable_declaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "variable_declaration"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:75:1: variable_declaration : TYPE identifier_list -> ^( VAR_DEC TYPE identifier_list ) ;
	public final Algol60Parser.variable_declaration_return variable_declaration() throws RecognitionException {
		Algol60Parser.variable_declaration_return retval = new Algol60Parser.variable_declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token TYPE15=null;
		ParserRuleReturnScope identifier_list16 =null;

		Object TYPE15_tree=null;
		RewriteRuleTokenStream stream_TYPE=new RewriteRuleTokenStream(adaptor,"token TYPE");
		RewriteRuleSubtreeStream stream_identifier_list=new RewriteRuleSubtreeStream(adaptor,"rule identifier_list");

		try { dbg.enterRule(getGrammarFileName(), "variable_declaration");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(75, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:76:5: ( TYPE identifier_list -> ^( VAR_DEC TYPE identifier_list ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:76:9: TYPE identifier_list
			{
			dbg.location(76,9);
			TYPE15=(Token)match(input,TYPE,FOLLOW_TYPE_in_variable_declaration430);  
			stream_TYPE.add(TYPE15);
			dbg.location(76,14);
			pushFollow(FOLLOW_identifier_list_in_variable_declaration432);
			identifier_list16=identifier_list();
			state._fsp--;

			stream_identifier_list.add(identifier_list16.getTree());
			// AST REWRITE
			// elements: TYPE, identifier_list
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 76:30: -> ^( VAR_DEC TYPE identifier_list )
			{
				dbg.location(76,33);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:76:33: ^( VAR_DEC TYPE identifier_list )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(76,35);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VAR_DEC, "VAR_DEC"), root_1);
				dbg.location(76,43);
				adaptor.addChild(root_1, stream_TYPE.nextNode());dbg.location(76,48);
				adaptor.addChild(root_1, stream_identifier_list.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(77, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "variable_declaration");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "variable_declaration"


	public static class procedure_declaration_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "procedure_declaration"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:79:1: procedure_declaration : 'procedure' procedure_heading procedure_body -> ^( PROC_DEC procedure_heading procedure_body ) ;
	public final Algol60Parser.procedure_declaration_return procedure_declaration() throws RecognitionException {
		Algol60Parser.procedure_declaration_return retval = new Algol60Parser.procedure_declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal17=null;
		ParserRuleReturnScope procedure_heading18 =null;
		ParserRuleReturnScope procedure_body19 =null;

		Object string_literal17_tree=null;
		RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
		RewriteRuleSubtreeStream stream_procedure_heading=new RewriteRuleSubtreeStream(adaptor,"rule procedure_heading");
		RewriteRuleSubtreeStream stream_procedure_body=new RewriteRuleSubtreeStream(adaptor,"rule procedure_body");

		try { dbg.enterRule(getGrammarFileName(), "procedure_declaration");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(79, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:80:5: ( 'procedure' procedure_heading procedure_body -> ^( PROC_DEC procedure_heading procedure_body ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:80:9: 'procedure' procedure_heading procedure_body
			{
			dbg.location(80,9);
			string_literal17=(Token)match(input,51,FOLLOW_51_in_procedure_declaration461);  
			stream_51.add(string_literal17);
			dbg.location(80,21);
			pushFollow(FOLLOW_procedure_heading_in_procedure_declaration463);
			procedure_heading18=procedure_heading();
			state._fsp--;

			stream_procedure_heading.add(procedure_heading18.getTree());dbg.location(80,39);
			pushFollow(FOLLOW_procedure_body_in_procedure_declaration465);
			procedure_body19=procedure_body();
			state._fsp--;

			stream_procedure_body.add(procedure_body19.getTree());
			// AST REWRITE
			// elements: procedure_heading, procedure_body
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 81:9: -> ^( PROC_DEC procedure_heading procedure_body )
			{
				dbg.location(81,12);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:81:12: ^( PROC_DEC procedure_heading procedure_body )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(81,14);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROC_DEC, "PROC_DEC"), root_1);
				dbg.location(81,23);
				adaptor.addChild(root_1, stream_procedure_heading.nextTree());dbg.location(81,41);
				adaptor.addChild(root_1, stream_procedure_body.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(82, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "procedure_declaration");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "procedure_declaration"


	public static class procedure_heading_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "procedure_heading"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:84:1: procedure_heading : IDENTIFIER formal_parameter_part ';' value_part specification_part -> ^( PROC_HEADING ( formal_parameter_part )? ( value_part )? ( specification_part )? ) ;
	public final Algol60Parser.procedure_heading_return procedure_heading() throws RecognitionException {
		Algol60Parser.procedure_heading_return retval = new Algol60Parser.procedure_heading_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IDENTIFIER20=null;
		Token char_literal22=null;
		ParserRuleReturnScope formal_parameter_part21 =null;
		ParserRuleReturnScope value_part23 =null;
		ParserRuleReturnScope specification_part24 =null;

		Object IDENTIFIER20_tree=null;
		Object char_literal22_tree=null;
		RewriteRuleTokenStream stream_44=new RewriteRuleTokenStream(adaptor,"token 44");
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleSubtreeStream stream_formal_parameter_part=new RewriteRuleSubtreeStream(adaptor,"rule formal_parameter_part");
		RewriteRuleSubtreeStream stream_value_part=new RewriteRuleSubtreeStream(adaptor,"rule value_part");
		RewriteRuleSubtreeStream stream_specification_part=new RewriteRuleSubtreeStream(adaptor,"rule specification_part");

		try { dbg.enterRule(getGrammarFileName(), "procedure_heading");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(84, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:85:5: ( IDENTIFIER formal_parameter_part ';' value_part specification_part -> ^( PROC_HEADING ( formal_parameter_part )? ( value_part )? ( specification_part )? ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:85:9: IDENTIFIER formal_parameter_part ';' value_part specification_part
			{
			dbg.location(85,9);
			IDENTIFIER20=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_procedure_heading502);  
			stream_IDENTIFIER.add(IDENTIFIER20);
			dbg.location(85,20);
			pushFollow(FOLLOW_formal_parameter_part_in_procedure_heading504);
			formal_parameter_part21=formal_parameter_part();
			state._fsp--;

			stream_formal_parameter_part.add(formal_parameter_part21.getTree());dbg.location(85,42);
			char_literal22=(Token)match(input,44,FOLLOW_44_in_procedure_heading506);  
			stream_44.add(char_literal22);
			dbg.location(85,46);
			pushFollow(FOLLOW_value_part_in_procedure_heading508);
			value_part23=value_part();
			state._fsp--;

			stream_value_part.add(value_part23.getTree());dbg.location(85,57);
			pushFollow(FOLLOW_specification_part_in_procedure_heading510);
			specification_part24=specification_part();
			state._fsp--;

			stream_specification_part.add(specification_part24.getTree());
			// AST REWRITE
			// elements: specification_part, value_part, formal_parameter_part
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 86:9: -> ^( PROC_HEADING ( formal_parameter_part )? ( value_part )? ( specification_part )? )
			{
				dbg.location(86,12);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:86:12: ^( PROC_HEADING ( formal_parameter_part )? ( value_part )? ( specification_part )? )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(86,14);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROC_HEADING, "PROC_HEADING"), root_1);
				dbg.location(86,27);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:86:27: ( formal_parameter_part )?
				if ( stream_formal_parameter_part.hasNext() ) {
					dbg.location(86,27);
					adaptor.addChild(root_1, stream_formal_parameter_part.nextTree());
				}
				stream_formal_parameter_part.reset();
				dbg.location(86,50);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:86:50: ( value_part )?
				if ( stream_value_part.hasNext() ) {
					dbg.location(86,50);
					adaptor.addChild(root_1, stream_value_part.nextTree());
				}
				stream_value_part.reset();
				dbg.location(86,62);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:86:62: ( specification_part )?
				if ( stream_specification_part.hasNext() ) {
					dbg.location(86,62);
					adaptor.addChild(root_1, stream_specification_part.nextTree());
				}
				stream_specification_part.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(87, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "procedure_heading");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "procedure_heading"


	public static class formal_parameter_part_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "formal_parameter_part"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:89:1: formal_parameter_part : ( '(' identifier_list ')' -> ^( PARAM_PART identifier_list ) |);
	public final Algol60Parser.formal_parameter_part_return formal_parameter_part() throws RecognitionException {
		Algol60Parser.formal_parameter_part_return retval = new Algol60Parser.formal_parameter_part_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal25=null;
		Token char_literal27=null;
		ParserRuleReturnScope identifier_list26 =null;

		Object char_literal25_tree=null;
		Object char_literal27_tree=null;
		RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
		RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
		RewriteRuleSubtreeStream stream_identifier_list=new RewriteRuleSubtreeStream(adaptor,"rule identifier_list");

		try { dbg.enterRule(getGrammarFileName(), "formal_parameter_part");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(89, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:90:5: ( '(' identifier_list ')' -> ^( PARAM_PART identifier_list ) |)
			int alt4=2;
			try { dbg.enterDecision(4, decisionCanBacktrack[4]);

			int LA4_0 = input.LA(1);
			if ( (LA4_0==40) ) {
				alt4=1;
			}
			else if ( (LA4_0==44) ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(4);}

			switch (alt4) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:90:9: '(' identifier_list ')'
					{
					dbg.location(90,9);
					char_literal25=(Token)match(input,40,FOLLOW_40_in_formal_parameter_part553);  
					stream_40.add(char_literal25);
					dbg.location(90,13);
					pushFollow(FOLLOW_identifier_list_in_formal_parameter_part555);
					identifier_list26=identifier_list();
					state._fsp--;

					stream_identifier_list.add(identifier_list26.getTree());dbg.location(90,29);
					char_literal27=(Token)match(input,41,FOLLOW_41_in_formal_parameter_part557);  
					stream_41.add(char_literal27);

					// AST REWRITE
					// elements: identifier_list
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 90:33: -> ^( PARAM_PART identifier_list )
					{
						dbg.location(90,35);
						// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:90:35: ^( PARAM_PART identifier_list )
						{
						Object root_1 = (Object)adaptor.nil();
						dbg.location(90,37);
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARAM_PART, "PARAM_PART"), root_1);
						dbg.location(90,48);
						adaptor.addChild(root_1, stream_identifier_list.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:92:5: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(92, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "formal_parameter_part");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "formal_parameter_part"


	public static class identifier_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "identifier_list"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:94:1: identifier_list : IDENTIFIER ( ',' IDENTIFIER )* -> ^( ID_LIST ( IDENTIFIER )* ) ;
	public final Algol60Parser.identifier_list_return identifier_list() throws RecognitionException {
		Algol60Parser.identifier_list_return retval = new Algol60Parser.identifier_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IDENTIFIER28=null;
		Token char_literal29=null;
		Token IDENTIFIER30=null;

		Object IDENTIFIER28_tree=null;
		Object char_literal29_tree=null;
		Object IDENTIFIER30_tree=null;
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");

		try { dbg.enterRule(getGrammarFileName(), "identifier_list");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(94, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:95:5: ( IDENTIFIER ( ',' IDENTIFIER )* -> ^( ID_LIST ( IDENTIFIER )* ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:95:9: IDENTIFIER ( ',' IDENTIFIER )*
			{
			dbg.location(95,9);
			IDENTIFIER28=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifier_list593);  
			stream_IDENTIFIER.add(IDENTIFIER28);
			dbg.location(95,20);
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:95:20: ( ',' IDENTIFIER )*
			try { dbg.enterSubRule(5);

			loop5:
			while (true) {
				int alt5=2;
				try { dbg.enterDecision(5, decisionCanBacktrack[5]);

				int LA5_0 = input.LA(1);
				if ( (LA5_0==42) ) {
					alt5=1;
				}

				} finally {dbg.exitDecision(5);}

				switch (alt5) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:95:22: ',' IDENTIFIER
					{
					dbg.location(95,22);
					char_literal29=(Token)match(input,42,FOLLOW_42_in_identifier_list597);  
					stream_42.add(char_literal29);
					dbg.location(95,26);
					IDENTIFIER30=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifier_list599);  
					stream_IDENTIFIER.add(IDENTIFIER30);

					}
					break;

				default :
					break loop5;
				}
			}
			} finally {dbg.exitSubRule(5);}

			// AST REWRITE
			// elements: IDENTIFIER
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 95:40: -> ^( ID_LIST ( IDENTIFIER )* )
			{
				dbg.location(95,43);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:95:43: ^( ID_LIST ( IDENTIFIER )* )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(95,45);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ID_LIST, "ID_LIST"), root_1);
				dbg.location(95,53);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:95:53: ( IDENTIFIER )*
				while ( stream_IDENTIFIER.hasNext() ) {
					dbg.location(95,53);
					adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());
				}
				stream_IDENTIFIER.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(96, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "identifier_list");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "identifier_list"


	public static class value_part_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "value_part"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:98:1: value_part : ( 'value' identifier_list ';' -> ^( VALUE_PART identifier_list ) |);
	public final Algol60Parser.value_part_return value_part() throws RecognitionException {
		Algol60Parser.value_part_return retval = new Algol60Parser.value_part_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal31=null;
		Token char_literal33=null;
		ParserRuleReturnScope identifier_list32 =null;

		Object string_literal31_tree=null;
		Object char_literal33_tree=null;
		RewriteRuleTokenStream stream_44=new RewriteRuleTokenStream(adaptor,"token 44");
		RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
		RewriteRuleSubtreeStream stream_identifier_list=new RewriteRuleSubtreeStream(adaptor,"rule identifier_list");

		try { dbg.enterRule(getGrammarFileName(), "value_part");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(98, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:99:5: ( 'value' identifier_list ';' -> ^( VALUE_PART identifier_list ) |)
			int alt6=2;
			try { dbg.enterDecision(6, decisionCanBacktrack[6]);

			int LA6_0 = input.LA(1);
			if ( (LA6_0==55) ) {
				alt6=1;
			}
			else if ( (LA6_0==TYPE||LA6_0==45) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(6);}

			switch (alt6) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:99:9: 'value' identifier_list ';'
					{
					dbg.location(99,9);
					string_literal31=(Token)match(input,55,FOLLOW_55_in_value_part630);  
					stream_55.add(string_literal31);
					dbg.location(99,17);
					pushFollow(FOLLOW_identifier_list_in_value_part632);
					identifier_list32=identifier_list();
					state._fsp--;

					stream_identifier_list.add(identifier_list32.getTree());dbg.location(99,33);
					char_literal33=(Token)match(input,44,FOLLOW_44_in_value_part634);  
					stream_44.add(char_literal33);

					// AST REWRITE
					// elements: identifier_list
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 99:37: -> ^( VALUE_PART identifier_list )
					{
						dbg.location(99,40);
						// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:99:40: ^( VALUE_PART identifier_list )
						{
						Object root_1 = (Object)adaptor.nil();
						dbg.location(99,42);
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VALUE_PART, "VALUE_PART"), root_1);
						dbg.location(99,53);
						adaptor.addChild(root_1, stream_identifier_list.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:101:5: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(101, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "value_part");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "value_part"


	public static class specification_part_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "specification_part"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:103:1: specification_part : ( TYPE identifier_list ';' )* -> ^( SPEC_PART ( ^( ARG_TYPE TYPE identifier_list ) )* ) ;
	public final Algol60Parser.specification_part_return specification_part() throws RecognitionException {
		Algol60Parser.specification_part_return retval = new Algol60Parser.specification_part_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token TYPE34=null;
		Token char_literal36=null;
		ParserRuleReturnScope identifier_list35 =null;

		Object TYPE34_tree=null;
		Object char_literal36_tree=null;
		RewriteRuleTokenStream stream_44=new RewriteRuleTokenStream(adaptor,"token 44");
		RewriteRuleTokenStream stream_TYPE=new RewriteRuleTokenStream(adaptor,"token TYPE");
		RewriteRuleSubtreeStream stream_identifier_list=new RewriteRuleSubtreeStream(adaptor,"rule identifier_list");

		try { dbg.enterRule(getGrammarFileName(), "specification_part");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(103, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:104:5: ( ( TYPE identifier_list ';' )* -> ^( SPEC_PART ( ^( ARG_TYPE TYPE identifier_list ) )* ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:104:9: ( TYPE identifier_list ';' )*
			{
			dbg.location(104,9);
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:104:9: ( TYPE identifier_list ';' )*
			try { dbg.enterSubRule(7);

			loop7:
			while (true) {
				int alt7=2;
				try { dbg.enterDecision(7, decisionCanBacktrack[7]);

				int LA7_0 = input.LA(1);
				if ( (LA7_0==TYPE) ) {
					alt7=1;
				}

				} finally {dbg.exitDecision(7);}

				switch (alt7) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:104:11: TYPE identifier_list ';'
					{
					dbg.location(104,11);
					TYPE34=(Token)match(input,TYPE,FOLLOW_TYPE_in_specification_part669);  
					stream_TYPE.add(TYPE34);
					dbg.location(104,16);
					pushFollow(FOLLOW_identifier_list_in_specification_part671);
					identifier_list35=identifier_list();
					state._fsp--;

					stream_identifier_list.add(identifier_list35.getTree());dbg.location(104,32);
					char_literal36=(Token)match(input,44,FOLLOW_44_in_specification_part673);  
					stream_44.add(char_literal36);

					}
					break;

				default :
					break loop7;
				}
			}
			} finally {dbg.exitSubRule(7);}

			// AST REWRITE
			// elements: TYPE, identifier_list
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 104:39: -> ^( SPEC_PART ( ^( ARG_TYPE TYPE identifier_list ) )* )
			{
				dbg.location(104,41);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:104:41: ^( SPEC_PART ( ^( ARG_TYPE TYPE identifier_list ) )* )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(104,43);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SPEC_PART, "SPEC_PART"), root_1);
				dbg.location(104,53);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:104:53: ( ^( ARG_TYPE TYPE identifier_list ) )*
				while ( stream_TYPE.hasNext()||stream_identifier_list.hasNext() ) {
					dbg.location(104,53);
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:104:53: ^( ARG_TYPE TYPE identifier_list )
					{
					Object root_2 = (Object)adaptor.nil();
					dbg.location(104,55);
					root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARG_TYPE, "ARG_TYPE"), root_2);
					dbg.location(104,64);
					adaptor.addChild(root_2, stream_TYPE.nextNode());dbg.location(104,69);
					adaptor.addChild(root_2, stream_identifier_list.nextTree());
					adaptor.addChild(root_1, root_2);
					}

				}
				stream_TYPE.reset();
				stream_identifier_list.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(105, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "specification_part");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "specification_part"


	public static class procedure_body_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "procedure_body"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:107:1: procedure_body : block ;
	public final Algol60Parser.procedure_body_return procedure_body() throws RecognitionException {
		Algol60Parser.procedure_body_return retval = new Algol60Parser.procedure_body_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope block37 =null;


		try { dbg.enterRule(getGrammarFileName(), "procedure_body");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(107, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:108:5: ( block )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:108:9: block
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(108,9);
			pushFollow(FOLLOW_block_in_procedure_body709);
			block37=block();
			state._fsp--;

			adaptor.addChild(root_0, block37.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(109, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "procedure_body");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "procedure_body"


	public static class procedure_call_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "procedure_call"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:115:1: procedure_call : IDENTIFIER '(' actual_parameter_list ')' -> ^( PROC_CALL actual_parameter_list ) ;
	public final Algol60Parser.procedure_call_return procedure_call() throws RecognitionException {
		Algol60Parser.procedure_call_return retval = new Algol60Parser.procedure_call_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IDENTIFIER38=null;
		Token char_literal39=null;
		Token char_literal41=null;
		ParserRuleReturnScope actual_parameter_list40 =null;

		Object IDENTIFIER38_tree=null;
		Object char_literal39_tree=null;
		Object char_literal41_tree=null;
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
		RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
		RewriteRuleSubtreeStream stream_actual_parameter_list=new RewriteRuleSubtreeStream(adaptor,"rule actual_parameter_list");

		try { dbg.enterRule(getGrammarFileName(), "procedure_call");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(115, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:116:5: ( IDENTIFIER '(' actual_parameter_list ')' -> ^( PROC_CALL actual_parameter_list ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:116:9: IDENTIFIER '(' actual_parameter_list ')'
			{
			dbg.location(116,9);
			IDENTIFIER38=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_procedure_call732);  
			stream_IDENTIFIER.add(IDENTIFIER38);
			dbg.location(116,20);
			char_literal39=(Token)match(input,40,FOLLOW_40_in_procedure_call734);  
			stream_40.add(char_literal39);
			dbg.location(116,24);
			pushFollow(FOLLOW_actual_parameter_list_in_procedure_call736);
			actual_parameter_list40=actual_parameter_list();
			state._fsp--;

			stream_actual_parameter_list.add(actual_parameter_list40.getTree());dbg.location(116,46);
			char_literal41=(Token)match(input,41,FOLLOW_41_in_procedure_call738);  
			stream_41.add(char_literal41);

			// AST REWRITE
			// elements: actual_parameter_list
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 116:50: -> ^( PROC_CALL actual_parameter_list )
			{
				dbg.location(116,53);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:116:53: ^( PROC_CALL actual_parameter_list )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(116,55);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROC_CALL, "PROC_CALL"), root_1);
				dbg.location(116,65);
				adaptor.addChild(root_1, stream_actual_parameter_list.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(117, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "procedure_call");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "procedure_call"


	public static class actual_parameter_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "actual_parameter_list"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:119:1: actual_parameter_list : expression ( ',' expression )* -> ^( PARAM_LIST ( expression )* ) ;
	public final Algol60Parser.actual_parameter_list_return actual_parameter_list() throws RecognitionException {
		Algol60Parser.actual_parameter_list_return retval = new Algol60Parser.actual_parameter_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal43=null;
		ParserRuleReturnScope expression42 =null;
		ParserRuleReturnScope expression44 =null;

		Object char_literal43_tree=null;
		RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");

		try { dbg.enterRule(getGrammarFileName(), "actual_parameter_list");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(119, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:120:5: ( expression ( ',' expression )* -> ^( PARAM_LIST ( expression )* ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:120:9: expression ( ',' expression )*
			{
			dbg.location(120,9);
			pushFollow(FOLLOW_expression_in_actual_parameter_list765);
			expression42=expression();
			state._fsp--;

			stream_expression.add(expression42.getTree());dbg.location(120,20);
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:120:20: ( ',' expression )*
			try { dbg.enterSubRule(8);

			loop8:
			while (true) {
				int alt8=2;
				try { dbg.enterDecision(8, decisionCanBacktrack[8]);

				int LA8_0 = input.LA(1);
				if ( (LA8_0==42) ) {
					alt8=1;
				}

				} finally {dbg.exitDecision(8);}

				switch (alt8) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:120:22: ',' expression
					{
					dbg.location(120,22);
					char_literal43=(Token)match(input,42,FOLLOW_42_in_actual_parameter_list769);  
					stream_42.add(char_literal43);
					dbg.location(120,26);
					pushFollow(FOLLOW_expression_in_actual_parameter_list771);
					expression44=expression();
					state._fsp--;

					stream_expression.add(expression44.getTree());
					}
					break;

				default :
					break loop8;
				}
			}
			} finally {dbg.exitSubRule(8);}

			// AST REWRITE
			// elements: expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 120:40: -> ^( PARAM_LIST ( expression )* )
			{
				dbg.location(120,43);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:120:43: ^( PARAM_LIST ( expression )* )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(120,45);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARAM_LIST, "PARAM_LIST"), root_1);
				dbg.location(120,56);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:120:56: ( expression )*
				while ( stream_expression.hasNext() ) {
					dbg.location(120,56);
					adaptor.addChild(root_1, stream_expression.nextTree());
				}
				stream_expression.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(121, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "actual_parameter_list");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "actual_parameter_list"


	public static class assignment_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assignment"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:126:1: assignment : IDENTIFIER ':=' ^ expression ;
	public final Algol60Parser.assignment_return assignment() throws RecognitionException {
		Algol60Parser.assignment_return retval = new Algol60Parser.assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IDENTIFIER45=null;
		Token string_literal46=null;
		ParserRuleReturnScope expression47 =null;

		Object IDENTIFIER45_tree=null;
		Object string_literal46_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "assignment");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(126, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:127:5: ( IDENTIFIER ':=' ^ expression )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:127:9: IDENTIFIER ':=' ^ expression
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(127,9);
			IDENTIFIER45=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_assignment805); 
			IDENTIFIER45_tree = (Object)adaptor.create(IDENTIFIER45);
			adaptor.addChild(root_0, IDENTIFIER45_tree);
			dbg.location(127,24);
			string_literal46=(Token)match(input,43,FOLLOW_43_in_assignment807); 
			string_literal46_tree = (Object)adaptor.create(string_literal46);
			root_0 = (Object)adaptor.becomeRoot(string_literal46_tree, root_0);
			dbg.location(127,26);
			pushFollow(FOLLOW_expression_in_assignment810);
			expression47=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression47.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(128, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "assignment");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "assignment"


	public static class expression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:130:1: expression : ( INTEGER | STRING | IDENTIFIER );
	public final Algol60Parser.expression_return expression() throws RecognitionException {
		Algol60Parser.expression_return retval = new Algol60Parser.expression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set48=null;

		Object set48_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "expression");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(130, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:131:5: ( INTEGER | STRING | IDENTIFIER )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(131,5);
			set48=input.LT(1);
			if ( input.LA(1)==IDENTIFIER||input.LA(1)==INTEGER||input.LA(1)==STRING ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set48));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				dbg.recognitionException(mse);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(134, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "expression");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "expression"


	public static class if_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "if_clause"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:138:1: if_clause : 'if' logical_statement 'then' statement ( options {greedy=true; } : 'else' statement )? -> ^( IF_STATEMENT ^( IF_DEF logical_statement ) ^( THEN_DEF statement ) ( ^( ELSE_DEF statement ) )* ) ;
	public final Algol60Parser.if_clause_return if_clause() throws RecognitionException {
		Algol60Parser.if_clause_return retval = new Algol60Parser.if_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal49=null;
		Token string_literal51=null;
		Token string_literal53=null;
		ParserRuleReturnScope logical_statement50 =null;
		ParserRuleReturnScope statement52 =null;
		ParserRuleReturnScope statement54 =null;

		Object string_literal49_tree=null;
		Object string_literal51_tree=null;
		Object string_literal53_tree=null;
		RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
		RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
		RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
		RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
		RewriteRuleSubtreeStream stream_logical_statement=new RewriteRuleSubtreeStream(adaptor,"rule logical_statement");

		try { dbg.enterRule(getGrammarFileName(), "if_clause");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(138, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:5: ( 'if' logical_statement 'then' statement ( options {greedy=true; } : 'else' statement )? -> ^( IF_STATEMENT ^( IF_DEF logical_statement ) ^( THEN_DEF statement ) ( ^( ELSE_DEF statement ) )* ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:9: 'if' logical_statement 'then' statement ( options {greedy=true; } : 'else' statement )?
			{
			dbg.location(139,9);
			string_literal49=(Token)match(input,50,FOLLOW_50_in_if_clause870);  
			stream_50.add(string_literal49);
			dbg.location(139,14);
			pushFollow(FOLLOW_logical_statement_in_if_clause872);
			logical_statement50=logical_statement();
			state._fsp--;

			stream_logical_statement.add(logical_statement50.getTree());dbg.location(139,32);
			string_literal51=(Token)match(input,53,FOLLOW_53_in_if_clause874);  
			stream_53.add(string_literal51);
			dbg.location(139,39);
			pushFollow(FOLLOW_statement_in_if_clause876);
			statement52=statement();
			state._fsp--;

			stream_statement.add(statement52.getTree());dbg.location(139,49);
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:49: ( options {greedy=true; } : 'else' statement )?
			int alt9=2;
			try { dbg.enterSubRule(9);
			try { dbg.enterDecision(9, decisionCanBacktrack[9]);

			int LA9_0 = input.LA(1);
			if ( (LA9_0==47) ) {
				alt9=1;
			}
			} finally {dbg.exitDecision(9);}

			switch (alt9) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:72: 'else' statement
					{
					dbg.location(139,72);
					string_literal53=(Token)match(input,47,FOLLOW_47_in_if_clause886);  
					stream_47.add(string_literal53);
					dbg.location(139,79);
					pushFollow(FOLLOW_statement_in_if_clause888);
					statement54=statement();
					state._fsp--;

					stream_statement.add(statement54.getTree());
					}
					break;

			}
			} finally {dbg.exitSubRule(9);}

			// AST REWRITE
			// elements: statement, statement, logical_statement
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 139:91: -> ^( IF_STATEMENT ^( IF_DEF logical_statement ) ^( THEN_DEF statement ) ( ^( ELSE_DEF statement ) )* )
			{
				dbg.location(139,93);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:93: ^( IF_STATEMENT ^( IF_DEF logical_statement ) ^( THEN_DEF statement ) ( ^( ELSE_DEF statement ) )* )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(139,95);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IF_STATEMENT, "IF_STATEMENT"), root_1);
				dbg.location(139,108);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:108: ^( IF_DEF logical_statement )
				{
				Object root_2 = (Object)adaptor.nil();
				dbg.location(139,110);
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(IF_DEF, "IF_DEF"), root_2);
				dbg.location(139,117);
				adaptor.addChild(root_2, stream_logical_statement.nextTree());
				adaptor.addChild(root_1, root_2);
				}
				dbg.location(139,136);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:136: ^( THEN_DEF statement )
				{
				Object root_2 = (Object)adaptor.nil();
				dbg.location(139,138);
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(THEN_DEF, "THEN_DEF"), root_2);
				dbg.location(139,147);
				adaptor.addChild(root_2, stream_statement.nextTree());
				adaptor.addChild(root_1, root_2);
				}
				dbg.location(139,158);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:158: ( ^( ELSE_DEF statement ) )*
				while ( stream_statement.hasNext() ) {
					dbg.location(139,158);
					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:139:158: ^( ELSE_DEF statement )
					{
					Object root_2 = (Object)adaptor.nil();
					dbg.location(139,160);
					root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ELSE_DEF, "ELSE_DEF"), root_2);
					dbg.location(139,169);
					adaptor.addChild(root_2, stream_statement.nextTree());
					adaptor.addChild(root_1, root_2);
					}

				}
				stream_statement.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(140, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "if_clause");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "if_clause"


	public static class for_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "for_clause"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:142:1: for_clause : 'for' assignment 'step' REAL 'until' expression 'do' statement -> ^( FOR_CLAUSE ^( INIT assignment ) ^( STEP REAL ) ^( UNTIL expression ) ^( DO statement ) ) ;
	public final Algol60Parser.for_clause_return for_clause() throws RecognitionException {
		Algol60Parser.for_clause_return retval = new Algol60Parser.for_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal55=null;
		Token string_literal57=null;
		Token REAL58=null;
		Token string_literal59=null;
		Token string_literal61=null;
		ParserRuleReturnScope assignment56 =null;
		ParserRuleReturnScope expression60 =null;
		ParserRuleReturnScope statement62 =null;

		Object string_literal55_tree=null;
		Object string_literal57_tree=null;
		Object REAL58_tree=null;
		Object string_literal59_tree=null;
		Object string_literal61_tree=null;
		RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
		RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
		RewriteRuleTokenStream stream_REAL=new RewriteRuleTokenStream(adaptor,"token REAL");
		RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
		RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_assignment=new RewriteRuleSubtreeStream(adaptor,"rule assignment");
		RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");

		try { dbg.enterRule(getGrammarFileName(), "for_clause");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(142, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:143:5: ( 'for' assignment 'step' REAL 'until' expression 'do' statement -> ^( FOR_CLAUSE ^( INIT assignment ) ^( STEP REAL ) ^( UNTIL expression ) ^( DO statement ) ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:143:7: 'for' assignment 'step' REAL 'until' expression 'do' statement
			{
			dbg.location(143,7);
			string_literal55=(Token)match(input,49,FOLLOW_49_in_for_clause931);  
			stream_49.add(string_literal55);
			dbg.location(143,13);
			pushFollow(FOLLOW_assignment_in_for_clause933);
			assignment56=assignment();
			state._fsp--;

			stream_assignment.add(assignment56.getTree());dbg.location(143,24);
			string_literal57=(Token)match(input,52,FOLLOW_52_in_for_clause935);  
			stream_52.add(string_literal57);
			dbg.location(143,31);
			REAL58=(Token)match(input,REAL,FOLLOW_REAL_in_for_clause937);  
			stream_REAL.add(REAL58);
			dbg.location(143,36);
			string_literal59=(Token)match(input,54,FOLLOW_54_in_for_clause939);  
			stream_54.add(string_literal59);
			dbg.location(143,44);
			pushFollow(FOLLOW_expression_in_for_clause941);
			expression60=expression();
			state._fsp--;

			stream_expression.add(expression60.getTree());dbg.location(143,55);
			string_literal61=(Token)match(input,46,FOLLOW_46_in_for_clause943);  
			stream_46.add(string_literal61);
			dbg.location(143,60);
			pushFollow(FOLLOW_statement_in_for_clause945);
			statement62=statement();
			state._fsp--;

			stream_statement.add(statement62.getTree());
			// AST REWRITE
			// elements: assignment, expression, statement, REAL
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 143:70: -> ^( FOR_CLAUSE ^( INIT assignment ) ^( STEP REAL ) ^( UNTIL expression ) ^( DO statement ) )
			{
				dbg.location(143,72);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:143:72: ^( FOR_CLAUSE ^( INIT assignment ) ^( STEP REAL ) ^( UNTIL expression ) ^( DO statement ) )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(143,74);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FOR_CLAUSE, "FOR_CLAUSE"), root_1);
				dbg.location(143,85);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:143:85: ^( INIT assignment )
				{
				Object root_2 = (Object)adaptor.nil();
				dbg.location(143,87);
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(INIT, "INIT"), root_2);
				dbg.location(143,92);
				adaptor.addChild(root_2, stream_assignment.nextTree());
				adaptor.addChild(root_1, root_2);
				}
				dbg.location(143,104);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:143:104: ^( STEP REAL )
				{
				Object root_2 = (Object)adaptor.nil();
				dbg.location(143,106);
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(STEP, "STEP"), root_2);
				dbg.location(143,111);
				adaptor.addChild(root_2, stream_REAL.nextNode());
				adaptor.addChild(root_1, root_2);
				}
				dbg.location(143,117);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:143:117: ^( UNTIL expression )
				{
				Object root_2 = (Object)adaptor.nil();
				dbg.location(143,119);
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(UNTIL, "UNTIL"), root_2);
				dbg.location(143,125);
				adaptor.addChild(root_2, stream_expression.nextTree());
				adaptor.addChild(root_1, root_2);
				}
				dbg.location(143,137);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:143:137: ^( DO statement )
				{
				Object root_2 = (Object)adaptor.nil();
				dbg.location(143,139);
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(DO, "DO"), root_2);
				dbg.location(143,142);
				adaptor.addChild(root_2, stream_statement.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(144, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "for_clause");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "for_clause"


	public static class while_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "while_clause"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:145:1: while_clause : 'while' logical_statement 'do' statement -> ^( WHILE_CLAUSE ^( CONDITION logical_statement ) ^( DO statement ) ) ;
	public final Algol60Parser.while_clause_return while_clause() throws RecognitionException {
		Algol60Parser.while_clause_return retval = new Algol60Parser.while_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal63=null;
		Token string_literal65=null;
		ParserRuleReturnScope logical_statement64 =null;
		ParserRuleReturnScope statement66 =null;

		Object string_literal63_tree=null;
		Object string_literal65_tree=null;
		RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
		RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
		RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
		RewriteRuleSubtreeStream stream_logical_statement=new RewriteRuleSubtreeStream(adaptor,"rule logical_statement");

		try { dbg.enterRule(getGrammarFileName(), "while_clause");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(145, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:146:3: ( 'while' logical_statement 'do' statement -> ^( WHILE_CLAUSE ^( CONDITION logical_statement ) ^( DO statement ) ) )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:146:3: 'while' logical_statement 'do' statement
			{
			dbg.location(146,3);
			string_literal63=(Token)match(input,56,FOLLOW_56_in_while_clause987);  
			stream_56.add(string_literal63);
			dbg.location(146,12);
			pushFollow(FOLLOW_logical_statement_in_while_clause990);
			logical_statement64=logical_statement();
			state._fsp--;

			stream_logical_statement.add(logical_statement64.getTree());dbg.location(146,30);
			string_literal65=(Token)match(input,46,FOLLOW_46_in_while_clause992);  
			stream_46.add(string_literal65);
			dbg.location(146,35);
			pushFollow(FOLLOW_statement_in_while_clause994);
			statement66=statement();
			state._fsp--;

			stream_statement.add(statement66.getTree());
			// AST REWRITE
			// elements: logical_statement, statement
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 146:45: -> ^( WHILE_CLAUSE ^( CONDITION logical_statement ) ^( DO statement ) )
			{
				dbg.location(146,47);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:146:47: ^( WHILE_CLAUSE ^( CONDITION logical_statement ) ^( DO statement ) )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(146,49);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(WHILE_CLAUSE, "WHILE_CLAUSE"), root_1);
				dbg.location(146,62);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:146:62: ^( CONDITION logical_statement )
				{
				Object root_2 = (Object)adaptor.nil();
				dbg.location(146,64);
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(CONDITION, "CONDITION"), root_2);
				dbg.location(146,74);
				adaptor.addChild(root_2, stream_logical_statement.nextTree());
				adaptor.addChild(root_1, root_2);
				}
				dbg.location(146,93);
				// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:146:93: ^( DO statement )
				{
				Object root_2 = (Object)adaptor.nil();
				dbg.location(146,95);
				root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(DO, "DO"), root_2);
				dbg.location(146,98);
				adaptor.addChild(root_2, stream_statement.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(147, 0);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "while_clause");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "while_clause"


	public static class logical_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logical_statement"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:148:1: logical_statement : ( expression boolean_operator expression | LOGICAL_VALUE );
	public final Algol60Parser.logical_statement_return logical_statement() throws RecognitionException {
		Algol60Parser.logical_statement_return retval = new Algol60Parser.logical_statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LOGICAL_VALUE70=null;
		ParserRuleReturnScope expression67 =null;
		ParserRuleReturnScope boolean_operator68 =null;
		ParserRuleReturnScope expression69 =null;

		Object LOGICAL_VALUE70_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "logical_statement");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(148, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:149:5: ( expression boolean_operator expression | LOGICAL_VALUE )
			int alt10=2;
			try { dbg.enterDecision(10, decisionCanBacktrack[10]);

			int LA10_0 = input.LA(1);
			if ( (LA10_0==IDENTIFIER||LA10_0==INTEGER||LA10_0==STRING) ) {
				alt10=1;
			}
			else if ( (LA10_0==LOGICAL_VALUE) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(10);}

			switch (alt10) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:149:9: expression boolean_operator expression
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(149,9);
					pushFollow(FOLLOW_expression_in_logical_statement1025);
					expression67=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression67.getTree());
					dbg.location(149,20);
					pushFollow(FOLLOW_boolean_operator_in_logical_statement1027);
					boolean_operator68=boolean_operator();
					state._fsp--;

					adaptor.addChild(root_0, boolean_operator68.getTree());
					dbg.location(149,37);
					pushFollow(FOLLOW_expression_in_logical_statement1029);
					expression69=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression69.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:150:9: LOGICAL_VALUE
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(150,9);
					LOGICAL_VALUE70=(Token)match(input,LOGICAL_VALUE,FOLLOW_LOGICAL_VALUE_in_logical_statement1039); 
					LOGICAL_VALUE70_tree = (Object)adaptor.create(LOGICAL_VALUE70);
					adaptor.addChild(root_0, LOGICAL_VALUE70_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(151, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "logical_statement");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "logical_statement"


	public static class boolean_operator_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "boolean_operator"
	// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:153:1: boolean_operator : ( RELATIONAL_OPERATOR | LOGICAL_OPERATOR );
	public final Algol60Parser.boolean_operator_return boolean_operator() throws RecognitionException {
		Algol60Parser.boolean_operator_return retval = new Algol60Parser.boolean_operator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set71=null;

		Object set71_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "boolean_operator");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(153, 0);

		try {
			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:154:5: ( RELATIONAL_OPERATOR | LOGICAL_OPERATOR )
			dbg.enterAlt(1);

			// /home/etudiants/zianieli1u/NAS_UL_ETUDIANTS/compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(154,5);
			set71=input.LT(1);
			if ( input.LA(1)==LOGICAL_OPERATOR||input.LA(1)==RELATIONAL_OPERATOR ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set71));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				dbg.recognitionException(mse);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		dbg.location(156, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "boolean_operator");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "boolean_operator"

	// Delegated rules



	public static final BitSet FOLLOW_block_in_prog263 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_45_in_block291 = new BitSet(new long[]{0x010E200400001000L});
	public static final BitSet FOLLOW_statement_in_block293 = new BitSet(new long[]{0x0001100000000000L});
	public static final BitSet FOLLOW_44_in_block296 = new BitSet(new long[]{0x010E200400001000L});
	public static final BitSet FOLLOW_statement_in_block298 = new BitSet(new long[]{0x0001100000000000L});
	public static final BitSet FOLLOW_48_in_block302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declaration_in_statement330 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement340 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_clause_in_statement350 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_for_clause_in_statement360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_while_clause_in_statement370 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_block_in_statement380 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_declaration_in_declaration401 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_procedure_declaration_in_declaration411 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_in_variable_declaration430 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_identifier_list_in_variable_declaration432 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_procedure_declaration461 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_procedure_heading_in_procedure_declaration463 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_procedure_body_in_procedure_declaration465 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_procedure_heading502 = new BitSet(new long[]{0x0000110000000000L});
	public static final BitSet FOLLOW_formal_parameter_part_in_procedure_heading504 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_44_in_procedure_heading506 = new BitSet(new long[]{0x0080000400000000L});
	public static final BitSet FOLLOW_value_part_in_procedure_heading508 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_specification_part_in_procedure_heading510 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_40_in_formal_parameter_part553 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_identifier_list_in_formal_parameter_part555 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_41_in_formal_parameter_part557 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifier_list593 = new BitSet(new long[]{0x0000040000000002L});
	public static final BitSet FOLLOW_42_in_identifier_list597 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifier_list599 = new BitSet(new long[]{0x0000040000000002L});
	public static final BitSet FOLLOW_55_in_value_part630 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_identifier_list_in_value_part632 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_44_in_value_part634 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_in_specification_part669 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_identifier_list_in_specification_part671 = new BitSet(new long[]{0x0000100000000000L});
	public static final BitSet FOLLOW_44_in_specification_part673 = new BitSet(new long[]{0x0000000400000002L});
	public static final BitSet FOLLOW_block_in_procedure_body709 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_procedure_call732 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_40_in_procedure_call734 = new BitSet(new long[]{0x0000000100021000L});
	public static final BitSet FOLLOW_actual_parameter_list_in_procedure_call736 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_41_in_procedure_call738 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_actual_parameter_list765 = new BitSet(new long[]{0x0000040000000002L});
	public static final BitSet FOLLOW_42_in_actual_parameter_list769 = new BitSet(new long[]{0x0000000100021000L});
	public static final BitSet FOLLOW_expression_in_actual_parameter_list771 = new BitSet(new long[]{0x0000040000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_assignment805 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_assignment807 = new BitSet(new long[]{0x0000000100021000L});
	public static final BitSet FOLLOW_expression_in_assignment810 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_if_clause870 = new BitSet(new long[]{0x00000001000A1000L});
	public static final BitSet FOLLOW_logical_statement_in_if_clause872 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_53_in_if_clause874 = new BitSet(new long[]{0x010E200400001000L});
	public static final BitSet FOLLOW_statement_in_if_clause876 = new BitSet(new long[]{0x0000800000000002L});
	public static final BitSet FOLLOW_47_in_if_clause886 = new BitSet(new long[]{0x010E200400001000L});
	public static final BitSet FOLLOW_statement_in_if_clause888 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_49_in_for_clause931 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_assignment_in_for_clause933 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_52_in_for_clause935 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_REAL_in_for_clause937 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_54_in_for_clause939 = new BitSet(new long[]{0x0000000100021000L});
	public static final BitSet FOLLOW_expression_in_for_clause941 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_46_in_for_clause943 = new BitSet(new long[]{0x010E200400001000L});
	public static final BitSet FOLLOW_statement_in_for_clause945 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_56_in_while_clause987 = new BitSet(new long[]{0x00000001000A1000L});
	public static final BitSet FOLLOW_logical_statement_in_while_clause990 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_46_in_while_clause992 = new BitSet(new long[]{0x010E200400001000L});
	public static final BitSet FOLLOW_statement_in_while_clause994 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_logical_statement1025 = new BitSet(new long[]{0x0000000008040000L});
	public static final BitSet FOLLOW_boolean_operator_in_logical_statement1027 = new BitSet(new long[]{0x0000000100021000L});
	public static final BitSet FOLLOW_expression_in_logical_statement1029 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOGICAL_VALUE_in_logical_statement1039 = new BitSet(new long[]{0x0000000000000002L});
}
