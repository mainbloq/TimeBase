package deltix.util.jcg.scg;

import deltix.qsrv.hf.tickdb.lang.compiler.cg.QCGHelpers;
import deltix.util.jcg.*;

import java.io.IOException;
import java.util.*;

public class JTryStatementImpl extends JStatementImplBase implements JTryStatement {
    private final JCompoundStatement tryStmt;
    private JCompoundStatement finallyStmt = null;
    private final LinkedHashMap <Class <? extends Throwable>, CatchClause> catchMap =
        new LinkedHashMap <> ();

    public JTryStatementImpl (JContextImpl context) {
        super (context);
        
        tryStmt = new JCompStmtImpl (context);
    }
    
    @Override
    public void printElement(SourceCodePrinter out) throws IOException {
        if (finallyStmt == null && catchMap.isEmpty())
            throw new IllegalStateException("try statement has neither catch nor finally clause");

        out.print("try ", tryStmt);

        if (!catchMap.isEmpty())
            for (Map.Entry<Class<? extends Throwable>, CatchClause> entry : catchMap.entrySet()) {
                out.print("catch (", entry.getKey().getName(), " ", entry.getValue().var, ") ", entry.getValue().stmt);
            }

        if (finallyStmt != null)
            out.print("finally ", finallyStmt);
    }

    @Override
    public JCompoundStatement tryStmt() {
        return tryStmt;
    }

    @Override
    public JCompoundStatement addCatch(Class<? extends Throwable> t, String varName) {
        CatchClause clause = catchMap.get(t);
        if (clause != null)
            throw new IllegalStateException("addCatch is called twice for " + t.getName());
        else {
            clause = new CatchClause(varName);
            catchMap.put(t, clause);
            return clause.stmt;
        }
    }

    @Override
    public JLocalVariable catchVariable(Class<? extends Throwable> t) {
        final CatchClause clause = catchMap.get(t);
        if (clause == null)
            throw new IllegalStateException("addCatch was not called for " + t.getName());
        else
            return clause.var;
    }

    @Override
    public JCompoundStatement addFinally() {
        if (finallyStmt != null)
            throw new IllegalStateException("addFinally is called twice");
        else
            return (finallyStmt = new JCompStmtImpl (context));
    }

    private class CatchClause {
        private final JLocalVariable var;
        private final JCompoundStatement stmt;

        CatchClause(String varName) {
            var = new VarDeclImpl (context, 0, null, varName);
            stmt = new JCompStmtImpl (context);
        }
    }
}
