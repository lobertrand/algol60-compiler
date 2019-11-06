// $ANTLR null /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g 2019-11-06 17:34:42

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
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARITHMETIC_OPERATOR", "BLOCK", 
		"COMMENT", "IDENTIFIER", "INTEGER", "LOGICAL_OPERATOR", "LOGICAL_VALUE", 
		"RELATIONAL_OPERATOR", "ROOT", "STRING", "TYPE", "VAR_DEC", "WS", "'('", 
		"')'", "','", "':='", "';'", "'begin'", "'else'", "'end'", "'if'", "'procedure'", 
		"'then'"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public static final String[] ruleNames = new String[] {
		"invalidRule", "variable_declaration", "procedure_body", "expression", 
		"formal_parameter_part", "if_clause", "block", "if_statement", "procedure_heading", 
		"procedure_declaration", "if_statement1", "super_expression", "statement", 
		"declaration", "logical_statement", "assignment", "formal_parameter_list", 
		"prog", "formal_parameter_list1", "boolean_operator"
	};

	public static final boolean[] decisionCanBacktrack = new boolean[] {
		false, // invalid decision
		false, false, false, false, false, false, false, false
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
	@Override public String getGrammarFileName() { return "/home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g"; }


	public static class prog_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "prog"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:31:1: prog : block -> ^( ROOT block ) ;
	public final Algol60Parser.prog_return prog() throws RecognitionException {
		Algol60Parser.prog_return retval = new Algol60Parser.prog_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope block1 =null;

		RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");

		try { dbg.enterRule(getGrammarFileName(), "prog");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(31, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:31:5: ( block -> ^( ROOT block ) )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:31:9: block
			{
			dbg.location(31,9);
			pushFollow(FOLLOW_block_in_prog116);
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
			// 31:15: -> ^( ROOT block )
			{
				dbg.location(31,18);
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:31:18: ^( ROOT block )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(31,20);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ROOT, "ROOT"), root_1);
				dbg.location(31,25);
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
		dbg.location(32, 4);

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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:34:1: block : 'begin' ( statement )* 'end' -> ^( BLOCK ( statement )* ) ;
	public final Algol60Parser.block_return block() throws RecognitionException {
		Algol60Parser.block_return retval = new Algol60Parser.block_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal2=null;
		Token string_literal4=null;
		ParserRuleReturnScope statement3 =null;

		Object string_literal2_tree=null;
		Object string_literal4_tree=null;
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");

		try { dbg.enterRule(getGrammarFileName(), "block");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(34, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:35:5: ( 'begin' ( statement )* 'end' -> ^( BLOCK ( statement )* ) )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:35:9: 'begin' ( statement )* 'end'
			{
			dbg.location(35,9);
			string_literal2=(Token)match(input,22,FOLLOW_22_in_block143);  
			stream_22.add(string_literal2);
			dbg.location(35,17);
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:35:17: ( statement )*
			try { dbg.enterSubRule(1);

			loop1:
			while (true) {
				int alt1=2;
				try { dbg.enterDecision(1, decisionCanBacktrack[1]);

				int LA1_0 = input.LA(1);
				if ( (LA1_0==IDENTIFIER||LA1_0==TYPE||(LA1_0 >= 25 && LA1_0 <= 26)) ) {
					alt1=1;
				}

				} finally {dbg.exitDecision(1);}

				switch (alt1) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:35:17: statement
					{
					dbg.location(35,17);
					pushFollow(FOLLOW_statement_in_block145);
					statement3=statement();
					state._fsp--;

					stream_statement.add(statement3.getTree());
					}
					break;

				default :
					break loop1;
				}
			}
			} finally {dbg.exitSubRule(1);}
			dbg.location(35,28);
			string_literal4=(Token)match(input,24,FOLLOW_24_in_block148);  
			stream_24.add(string_literal4);

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
			// 35:34: -> ^( BLOCK ( statement )* )
			{
				dbg.location(35,37);
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:35:37: ^( BLOCK ( statement )* )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(35,39);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);
				dbg.location(35,45);
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:35:45: ( statement )*
				while ( stream_statement.hasNext() ) {
					dbg.location(35,45);
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
		dbg.location(36, 4);

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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:38:1: statement : ( declaration | assignment | if_statement );
	public final Algol60Parser.statement_return statement() throws RecognitionException {
		Algol60Parser.statement_return retval = new Algol60Parser.statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope declaration5 =null;
		ParserRuleReturnScope assignment6 =null;
		ParserRuleReturnScope if_statement7 =null;


		try { dbg.enterRule(getGrammarFileName(), "statement");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(38, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:39:5: ( declaration | assignment | if_statement )
			int alt2=3;
			try { dbg.enterDecision(2, decisionCanBacktrack[2]);

			switch ( input.LA(1) ) {
			case TYPE:
			case 26:
				{
				alt2=1;
				}
				break;
			case IDENTIFIER:
				{
				alt2=2;
				}
				break;
			case 25:
				{
				alt2=3;
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

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:39:9: declaration
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(39,9);
					pushFollow(FOLLOW_declaration_in_statement176);
					declaration5=declaration();
					state._fsp--;

					adaptor.addChild(root_0, declaration5.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:40:9: assignment
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(40,9);
					pushFollow(FOLLOW_assignment_in_statement186);
					assignment6=assignment();
					state._fsp--;

					adaptor.addChild(root_0, assignment6.getTree());

					}
					break;
				case 3 :
					dbg.enterAlt(3);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:41:9: if_statement
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(41,9);
					pushFollow(FOLLOW_if_statement_in_statement196);
					if_statement7=if_statement();
					state._fsp--;

					adaptor.addChild(root_0, if_statement7.getTree());

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
		dbg.location(42, 4);

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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:46:1: declaration : ( variable_declaration | procedure_declaration );
	public final Algol60Parser.declaration_return declaration() throws RecognitionException {
		Algol60Parser.declaration_return retval = new Algol60Parser.declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope variable_declaration8 =null;
		ParserRuleReturnScope procedure_declaration9 =null;


		try { dbg.enterRule(getGrammarFileName(), "declaration");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(46, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:47:5: ( variable_declaration | procedure_declaration )
			int alt3=2;
			try { dbg.enterDecision(3, decisionCanBacktrack[3]);

			int LA3_0 = input.LA(1);
			if ( (LA3_0==TYPE) ) {
				alt3=1;
			}
			else if ( (LA3_0==26) ) {
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

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:47:9: variable_declaration
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(47,9);
					pushFollow(FOLLOW_variable_declaration_in_declaration217);
					variable_declaration8=variable_declaration();
					state._fsp--;

					adaptor.addChild(root_0, variable_declaration8.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:48:9: procedure_declaration
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(48,9);
					pushFollow(FOLLOW_procedure_declaration_in_declaration227);
					procedure_declaration9=procedure_declaration();
					state._fsp--;

					adaptor.addChild(root_0, procedure_declaration9.getTree());

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
		dbg.location(49, 4);

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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:51:1: variable_declaration : TYPE IDENTIFIER ';' -> ^( VAR_DEC TYPE IDENTIFIER ) ;
	public final Algol60Parser.variable_declaration_return variable_declaration() throws RecognitionException {
		Algol60Parser.variable_declaration_return retval = new Algol60Parser.variable_declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token TYPE10=null;
		Token IDENTIFIER11=null;
		Token char_literal12=null;

		Object TYPE10_tree=null;
		Object IDENTIFIER11_tree=null;
		Object char_literal12_tree=null;
		RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
		RewriteRuleTokenStream stream_TYPE=new RewriteRuleTokenStream(adaptor,"token TYPE");
		RewriteRuleTokenStream stream_21=new RewriteRuleTokenStream(adaptor,"token 21");

		try { dbg.enterRule(getGrammarFileName(), "variable_declaration");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(51, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:52:5: ( TYPE IDENTIFIER ';' -> ^( VAR_DEC TYPE IDENTIFIER ) )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:52:9: TYPE IDENTIFIER ';'
			{
			dbg.location(52,9);
			TYPE10=(Token)match(input,TYPE,FOLLOW_TYPE_in_variable_declaration246);  
			stream_TYPE.add(TYPE10);
			dbg.location(52,14);
			IDENTIFIER11=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variable_declaration248);  
			stream_IDENTIFIER.add(IDENTIFIER11);
			dbg.location(52,25);
			char_literal12=(Token)match(input,21,FOLLOW_21_in_variable_declaration250);  
			stream_21.add(char_literal12);

			// AST REWRITE
			// elements: IDENTIFIER, TYPE
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 52:29: -> ^( VAR_DEC TYPE IDENTIFIER )
			{
				dbg.location(52,32);
				// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:52:32: ^( VAR_DEC TYPE IDENTIFIER )
				{
				Object root_1 = (Object)adaptor.nil();
				dbg.location(52,34);
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VAR_DEC, "VAR_DEC"), root_1);
				dbg.location(52,42);
				adaptor.addChild(root_1, stream_TYPE.nextNode());dbg.location(52,47);
				adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());
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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:55:1: procedure_declaration : 'procedure' procedure_heading procedure_body ;
	public final Algol60Parser.procedure_declaration_return procedure_declaration() throws RecognitionException {
		Algol60Parser.procedure_declaration_return retval = new Algol60Parser.procedure_declaration_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal13=null;
		ParserRuleReturnScope procedure_heading14 =null;
		ParserRuleReturnScope procedure_body15 =null;

		Object string_literal13_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "procedure_declaration");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(55, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:56:5: ( 'procedure' procedure_heading procedure_body )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:56:9: 'procedure' procedure_heading procedure_body
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(56,9);
			string_literal13=(Token)match(input,26,FOLLOW_26_in_procedure_declaration279); 
			string_literal13_tree = (Object)adaptor.create(string_literal13);
			adaptor.addChild(root_0, string_literal13_tree);
			dbg.location(56,21);
			pushFollow(FOLLOW_procedure_heading_in_procedure_declaration281);
			procedure_heading14=procedure_heading();
			state._fsp--;

			adaptor.addChild(root_0, procedure_heading14.getTree());
			dbg.location(56,39);
			pushFollow(FOLLOW_procedure_body_in_procedure_declaration283);
			procedure_body15=procedure_body();
			state._fsp--;

			adaptor.addChild(root_0, procedure_body15.getTree());

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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:59:1: procedure_heading : IDENTIFIER formal_parameter_part ';' ;
	public final Algol60Parser.procedure_heading_return procedure_heading() throws RecognitionException {
		Algol60Parser.procedure_heading_return retval = new Algol60Parser.procedure_heading_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IDENTIFIER16=null;
		Token char_literal18=null;
		ParserRuleReturnScope formal_parameter_part17 =null;

		Object IDENTIFIER16_tree=null;
		Object char_literal18_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "procedure_heading");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(59, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:60:5: ( IDENTIFIER formal_parameter_part ';' )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:60:9: IDENTIFIER formal_parameter_part ';'
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(60,9);
			IDENTIFIER16=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_procedure_heading302); 
			IDENTIFIER16_tree = (Object)adaptor.create(IDENTIFIER16);
			adaptor.addChild(root_0, IDENTIFIER16_tree);
			dbg.location(60,20);
			pushFollow(FOLLOW_formal_parameter_part_in_procedure_heading304);
			formal_parameter_part17=formal_parameter_part();
			state._fsp--;

			adaptor.addChild(root_0, formal_parameter_part17.getTree());
			dbg.location(60,42);
			char_literal18=(Token)match(input,21,FOLLOW_21_in_procedure_heading306); 
			char_literal18_tree = (Object)adaptor.create(char_literal18);
			adaptor.addChild(root_0, char_literal18_tree);

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
		dbg.location(61, 4);

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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:63:1: formal_parameter_part : (| '(' formal_parameter_list ')' );
	public final Algol60Parser.formal_parameter_part_return formal_parameter_part() throws RecognitionException {
		Algol60Parser.formal_parameter_part_return retval = new Algol60Parser.formal_parameter_part_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal19=null;
		Token char_literal21=null;
		ParserRuleReturnScope formal_parameter_list20 =null;

		Object char_literal19_tree=null;
		Object char_literal21_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "formal_parameter_part");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(63, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:64:5: (| '(' formal_parameter_list ')' )
			int alt4=2;
			try { dbg.enterDecision(4, decisionCanBacktrack[4]);

			int LA4_0 = input.LA(1);
			if ( (LA4_0==21) ) {
				alt4=1;
			}
			else if ( (LA4_0==17) ) {
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

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:65:5: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:65:9: '(' formal_parameter_list ')'
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(65,9);
					char_literal19=(Token)match(input,17,FOLLOW_17_in_formal_parameter_part336); 
					char_literal19_tree = (Object)adaptor.create(char_literal19);
					adaptor.addChild(root_0, char_literal19_tree);
					dbg.location(65,13);
					pushFollow(FOLLOW_formal_parameter_list_in_formal_parameter_part338);
					formal_parameter_list20=formal_parameter_list();
					state._fsp--;

					adaptor.addChild(root_0, formal_parameter_list20.getTree());
					dbg.location(65,35);
					char_literal21=(Token)match(input,18,FOLLOW_18_in_formal_parameter_part340); 
					char_literal21_tree = (Object)adaptor.create(char_literal21);
					adaptor.addChild(root_0, char_literal21_tree);

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
			dbg.exitRule(getGrammarFileName(), "formal_parameter_part");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "formal_parameter_part"


	public static class formal_parameter_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "formal_parameter_list"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:68:1: formal_parameter_list : IDENTIFIER formal_parameter_list1 ;
	public final Algol60Parser.formal_parameter_list_return formal_parameter_list() throws RecognitionException {
		Algol60Parser.formal_parameter_list_return retval = new Algol60Parser.formal_parameter_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IDENTIFIER22=null;
		ParserRuleReturnScope formal_parameter_list123 =null;

		Object IDENTIFIER22_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "formal_parameter_list");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(68, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:69:5: ( IDENTIFIER formal_parameter_list1 )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:69:9: IDENTIFIER formal_parameter_list1
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(69,9);
			IDENTIFIER22=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_formal_parameter_list359); 
			IDENTIFIER22_tree = (Object)adaptor.create(IDENTIFIER22);
			adaptor.addChild(root_0, IDENTIFIER22_tree);
			dbg.location(69,21);
			pushFollow(FOLLOW_formal_parameter_list1_in_formal_parameter_list362);
			formal_parameter_list123=formal_parameter_list1();
			state._fsp--;

			adaptor.addChild(root_0, formal_parameter_list123.getTree());

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
		dbg.location(70, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "formal_parameter_list");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "formal_parameter_list"


	public static class formal_parameter_list1_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "formal_parameter_list1"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:72:1: formal_parameter_list1 : ( ',' IDENTIFIER formal_parameter_list1 |);
	public final Algol60Parser.formal_parameter_list1_return formal_parameter_list1() throws RecognitionException {
		Algol60Parser.formal_parameter_list1_return retval = new Algol60Parser.formal_parameter_list1_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal24=null;
		Token IDENTIFIER25=null;
		ParserRuleReturnScope formal_parameter_list126 =null;

		Object char_literal24_tree=null;
		Object IDENTIFIER25_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "formal_parameter_list1");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(72, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:73:5: ( ',' IDENTIFIER formal_parameter_list1 |)
			int alt5=2;
			try { dbg.enterDecision(5, decisionCanBacktrack[5]);

			int LA5_0 = input.LA(1);
			if ( (LA5_0==19) ) {
				alt5=1;
			}
			else if ( (LA5_0==18) ) {
				alt5=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(5);}

			switch (alt5) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:73:9: ',' IDENTIFIER formal_parameter_list1
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(73,9);
					char_literal24=(Token)match(input,19,FOLLOW_19_in_formal_parameter_list1381); 
					char_literal24_tree = (Object)adaptor.create(char_literal24);
					adaptor.addChild(root_0, char_literal24_tree);
					dbg.location(73,13);
					IDENTIFIER25=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_formal_parameter_list1383); 
					IDENTIFIER25_tree = (Object)adaptor.create(IDENTIFIER25);
					adaptor.addChild(root_0, IDENTIFIER25_tree);
					dbg.location(73,24);
					pushFollow(FOLLOW_formal_parameter_list1_in_formal_parameter_list1385);
					formal_parameter_list126=formal_parameter_list1();
					state._fsp--;

					adaptor.addChild(root_0, formal_parameter_list126.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:75:5: 
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
		dbg.location(75, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "formal_parameter_list1");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "formal_parameter_list1"


	public static class procedure_body_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "procedure_body"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:77:1: procedure_body : block ;
	public final Algol60Parser.procedure_body_return procedure_body() throws RecognitionException {
		Algol60Parser.procedure_body_return retval = new Algol60Parser.procedure_body_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope block27 =null;


		try { dbg.enterRule(getGrammarFileName(), "procedure_body");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(77, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:78:5: ( block )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:78:9: block
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(78,9);
			pushFollow(FOLLOW_block_in_procedure_body410);
			block27=block();
			state._fsp--;

			adaptor.addChild(root_0, block27.getTree());

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
		dbg.location(79, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "procedure_body");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "procedure_body"


	public static class assignment_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assignment"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:83:1: assignment : IDENTIFIER ':=' ^ expression ';' !;
	public final Algol60Parser.assignment_return assignment() throws RecognitionException {
		Algol60Parser.assignment_return retval = new Algol60Parser.assignment_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IDENTIFIER28=null;
		Token string_literal29=null;
		Token char_literal31=null;
		ParserRuleReturnScope expression30 =null;

		Object IDENTIFIER28_tree=null;
		Object string_literal29_tree=null;
		Object char_literal31_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "assignment");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(83, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:84:5: ( IDENTIFIER ':=' ^ expression ';' !)
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:84:9: IDENTIFIER ':=' ^ expression ';' !
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(84,9);
			IDENTIFIER28=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_assignment432); 
			IDENTIFIER28_tree = (Object)adaptor.create(IDENTIFIER28);
			adaptor.addChild(root_0, IDENTIFIER28_tree);
			dbg.location(84,24);
			string_literal29=(Token)match(input,20,FOLLOW_20_in_assignment434); 
			string_literal29_tree = (Object)adaptor.create(string_literal29);
			root_0 = (Object)adaptor.becomeRoot(string_literal29_tree, root_0);
			dbg.location(84,26);
			pushFollow(FOLLOW_expression_in_assignment437);
			expression30=expression();
			state._fsp--;

			adaptor.addChild(root_0, expression30.getTree());
			dbg.location(84,40);
			char_literal31=(Token)match(input,21,FOLLOW_21_in_assignment439); 
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
		dbg.location(85, 4);

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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:87:1: expression : ( INTEGER | STRING | IDENTIFIER );
	public final Algol60Parser.expression_return expression() throws RecognitionException {
		Algol60Parser.expression_return retval = new Algol60Parser.expression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set32=null;

		Object set32_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "expression");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(87, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:88:5: ( INTEGER | STRING | IDENTIFIER )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(88,5);
			set32=input.LT(1);
			if ( (input.LA(1) >= IDENTIFIER && input.LA(1) <= INTEGER)||input.LA(1)==STRING ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set32));
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
		dbg.location(91, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "expression");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "expression"


	public static class if_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "if_statement"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:93:1: if_statement : if_clause super_expression if_statement1 ;
	public final Algol60Parser.if_statement_return if_statement() throws RecognitionException {
		Algol60Parser.if_statement_return retval = new Algol60Parser.if_statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope if_clause33 =null;
		ParserRuleReturnScope super_expression34 =null;
		ParserRuleReturnScope if_statement135 =null;


		try { dbg.enterRule(getGrammarFileName(), "if_statement");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(93, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:94:5: ( if_clause super_expression if_statement1 )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:94:9: if_clause super_expression if_statement1
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(94,9);
			pushFollow(FOLLOW_if_clause_in_if_statement498);
			if_clause33=if_clause();
			state._fsp--;

			adaptor.addChild(root_0, if_clause33.getTree());
			dbg.location(94,19);
			pushFollow(FOLLOW_super_expression_in_if_statement500);
			super_expression34=super_expression();
			state._fsp--;

			adaptor.addChild(root_0, super_expression34.getTree());
			dbg.location(94,36);
			pushFollow(FOLLOW_if_statement1_in_if_statement502);
			if_statement135=if_statement1();
			state._fsp--;

			adaptor.addChild(root_0, if_statement135.getTree());

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
		dbg.location(95, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "if_statement");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "if_statement"


	public static class if_statement1_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "if_statement1"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:97:1: if_statement1 : ( 'else' super_expression |);
	public final Algol60Parser.if_statement1_return if_statement1() throws RecognitionException {
		Algol60Parser.if_statement1_return retval = new Algol60Parser.if_statement1_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal36=null;
		ParserRuleReturnScope super_expression37 =null;

		Object string_literal36_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "if_statement1");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(97, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:98:5: ( 'else' super_expression |)
			int alt6=2;
			try { dbg.enterDecision(6, decisionCanBacktrack[6]);

			int LA6_0 = input.LA(1);
			if ( (LA6_0==23) ) {
				alt6=1;
			}
			else if ( (LA6_0==IDENTIFIER||LA6_0==TYPE||(LA6_0 >= 24 && LA6_0 <= 26)) ) {
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

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:98:8: 'else' super_expression
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(98,8);
					string_literal36=(Token)match(input,23,FOLLOW_23_in_if_statement1520); 
					string_literal36_tree = (Object)adaptor.create(string_literal36);
					adaptor.addChild(root_0, string_literal36_tree);
					dbg.location(98,15);
					pushFollow(FOLLOW_super_expression_in_if_statement1522);
					super_expression37=super_expression();
					state._fsp--;

					adaptor.addChild(root_0, super_expression37.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:100:5: 
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
		dbg.location(100, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "if_statement1");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "if_statement1"


	public static class if_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "if_clause"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:102:1: if_clause : 'if' logical_statement 'then' ;
	public final Algol60Parser.if_clause_return if_clause() throws RecognitionException {
		Algol60Parser.if_clause_return retval = new Algol60Parser.if_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal38=null;
		Token string_literal40=null;
		ParserRuleReturnScope logical_statement39 =null;

		Object string_literal38_tree=null;
		Object string_literal40_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "if_clause");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(102, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:103:5: ( 'if' logical_statement 'then' )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:103:9: 'if' logical_statement 'then'
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(103,9);
			string_literal38=(Token)match(input,25,FOLLOW_25_in_if_clause547); 
			string_literal38_tree = (Object)adaptor.create(string_literal38);
			adaptor.addChild(root_0, string_literal38_tree);
			dbg.location(103,14);
			pushFollow(FOLLOW_logical_statement_in_if_clause549);
			logical_statement39=logical_statement();
			state._fsp--;

			adaptor.addChild(root_0, logical_statement39.getTree());
			dbg.location(103,32);
			string_literal40=(Token)match(input,27,FOLLOW_27_in_if_clause551); 
			string_literal40_tree = (Object)adaptor.create(string_literal40);
			adaptor.addChild(root_0, string_literal40_tree);

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
		dbg.location(104, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "if_clause");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "if_clause"


	public static class super_expression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "super_expression"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:108:1: super_expression : ( statement | block );
	public final Algol60Parser.super_expression_return super_expression() throws RecognitionException {
		Algol60Parser.super_expression_return retval = new Algol60Parser.super_expression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope statement41 =null;
		ParserRuleReturnScope block42 =null;


		try { dbg.enterRule(getGrammarFileName(), "super_expression");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(108, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:109:5: ( statement | block )
			int alt7=2;
			try { dbg.enterDecision(7, decisionCanBacktrack[7]);

			int LA7_0 = input.LA(1);
			if ( (LA7_0==IDENTIFIER||LA7_0==TYPE||(LA7_0 >= 25 && LA7_0 <= 26)) ) {
				alt7=1;
			}
			else if ( (LA7_0==22) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(7);}

			switch (alt7) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:109:9: statement
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(109,9);
					pushFollow(FOLLOW_statement_in_super_expression573);
					statement41=statement();
					state._fsp--;

					adaptor.addChild(root_0, statement41.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:110:9: block
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(110,9);
					pushFollow(FOLLOW_block_in_super_expression583);
					block42=block();
					state._fsp--;

					adaptor.addChild(root_0, block42.getTree());

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
		dbg.location(111, 4);

		}
		finally {
			dbg.exitRule(getGrammarFileName(), "super_expression");
			decRuleLevel();
			if ( getRuleLevel()==0 ) {dbg.terminate();}
		}

		return retval;
	}
	// $ANTLR end "super_expression"


	public static class logical_statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logical_statement"
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:115:1: logical_statement : ( expression boolean_operator expression | LOGICAL_VALUE );
	public final Algol60Parser.logical_statement_return logical_statement() throws RecognitionException {
		Algol60Parser.logical_statement_return retval = new Algol60Parser.logical_statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LOGICAL_VALUE46=null;
		ParserRuleReturnScope expression43 =null;
		ParserRuleReturnScope boolean_operator44 =null;
		ParserRuleReturnScope expression45 =null;

		Object LOGICAL_VALUE46_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "logical_statement");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(115, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:116:5: ( expression boolean_operator expression | LOGICAL_VALUE )
			int alt8=2;
			try { dbg.enterDecision(8, decisionCanBacktrack[8]);

			int LA8_0 = input.LA(1);
			if ( ((LA8_0 >= IDENTIFIER && LA8_0 <= INTEGER)||LA8_0==STRING) ) {
				alt8=1;
			}
			else if ( (LA8_0==LOGICAL_VALUE) ) {
				alt8=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				dbg.recognitionException(nvae);
				throw nvae;
			}

			} finally {dbg.exitDecision(8);}

			switch (alt8) {
				case 1 :
					dbg.enterAlt(1);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:116:9: expression boolean_operator expression
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(116,9);
					pushFollow(FOLLOW_expression_in_logical_statement604);
					expression43=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression43.getTree());
					dbg.location(116,20);
					pushFollow(FOLLOW_boolean_operator_in_logical_statement606);
					boolean_operator44=boolean_operator();
					state._fsp--;

					adaptor.addChild(root_0, boolean_operator44.getTree());
					dbg.location(116,37);
					pushFollow(FOLLOW_expression_in_logical_statement608);
					expression45=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression45.getTree());

					}
					break;
				case 2 :
					dbg.enterAlt(2);

					// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:117:9: LOGICAL_VALUE
					{
					root_0 = (Object)adaptor.nil();


					dbg.location(117,9);
					LOGICAL_VALUE46=(Token)match(input,LOGICAL_VALUE,FOLLOW_LOGICAL_VALUE_in_logical_statement618); 
					LOGICAL_VALUE46_tree = (Object)adaptor.create(LOGICAL_VALUE46);
					adaptor.addChild(root_0, LOGICAL_VALUE46_tree);

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
		dbg.location(118, 4);

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
	// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:120:1: boolean_operator : ( RELATIONAL_OPERATOR | LOGICAL_OPERATOR );
	public final Algol60Parser.boolean_operator_return boolean_operator() throws RecognitionException {
		Algol60Parser.boolean_operator_return retval = new Algol60Parser.boolean_operator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set47=null;

		Object set47_tree=null;

		try { dbg.enterRule(getGrammarFileName(), "boolean_operator");
		if ( getRuleLevel()==0 ) {dbg.commence();}
		incRuleLevel();
		dbg.location(120, 0);

		try {
			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:121:5: ( RELATIONAL_OPERATOR | LOGICAL_OPERATOR )
			dbg.enterAlt(1);

			// /home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/Algol60.g:
			{
			root_0 = (Object)adaptor.nil();


			dbg.location(121,5);
			set47=input.LT(1);
			if ( input.LA(1)==LOGICAL_OPERATOR||input.LA(1)==RELATIONAL_OPERATOR ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set47));
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
		dbg.location(123, 4);

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



	public static final BitSet FOLLOW_block_in_prog116 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_block143 = new BitSet(new long[]{0x0000000007004080L});
	public static final BitSet FOLLOW_statement_in_block145 = new BitSet(new long[]{0x0000000007004080L});
	public static final BitSet FOLLOW_24_in_block148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declaration_in_statement176 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignment_in_statement186 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_statement_in_statement196 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_declaration_in_declaration217 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_procedure_declaration_in_declaration227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_in_variable_declaration246 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_IDENTIFIER_in_variable_declaration248 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_variable_declaration250 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_procedure_declaration279 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_procedure_heading_in_procedure_declaration281 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_procedure_body_in_procedure_declaration283 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_procedure_heading302 = new BitSet(new long[]{0x0000000000220000L});
	public static final BitSet FOLLOW_formal_parameter_part_in_procedure_heading304 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_procedure_heading306 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_formal_parameter_part336 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_formal_parameter_list_in_formal_parameter_part338 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_formal_parameter_part340 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_formal_parameter_list359 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_formal_parameter_list1_in_formal_parameter_list362 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_formal_parameter_list1381 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_IDENTIFIER_in_formal_parameter_list1383 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_formal_parameter_list1_in_formal_parameter_list1385 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_block_in_procedure_body410 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_assignment432 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_assignment434 = new BitSet(new long[]{0x0000000000002180L});
	public static final BitSet FOLLOW_expression_in_assignment437 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_assignment439 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_clause_in_if_statement498 = new BitSet(new long[]{0x0000000006404080L});
	public static final BitSet FOLLOW_super_expression_in_if_statement500 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_if_statement1_in_if_statement502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_23_in_if_statement1520 = new BitSet(new long[]{0x0000000006404080L});
	public static final BitSet FOLLOW_super_expression_in_if_statement1522 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_if_clause547 = new BitSet(new long[]{0x0000000000002580L});
	public static final BitSet FOLLOW_logical_statement_in_if_clause549 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_if_clause551 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_super_expression573 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_block_in_super_expression583 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expression_in_logical_statement604 = new BitSet(new long[]{0x0000000000000A00L});
	public static final BitSet FOLLOW_boolean_operator_in_logical_statement606 = new BitSet(new long[]{0x0000000000002180L});
	public static final BitSet FOLLOW_expression_in_logical_statement608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOGICAL_VALUE_in_logical_statement618 = new BitSet(new long[]{0x0000000000000002L});
}
