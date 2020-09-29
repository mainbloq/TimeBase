package deltix.qsrv.hf.tickdb.lang.compiler.sx;

import deltix.qsrv.hf.pub.md.*;
import java.util.*;

/**
 *  The result of compiling a select statement.
 */
public class CompiledFilter extends CompiledQuery {
    public enum RunningFilter {
        NONE,
        FIRST_ONLY,
        DISTINCT
    }
    
    public final CompiledQuery                  source;
    public final CompiledExpression             condition;
    public final RunningFilter                  runningFilter;
    public final boolean                        aggregate;
    public final GroupBySpec                    groupBy;
    public final TupleConstructor               selector;
    public final TimestampLimits                tslimits;

    public CompiledFilter (
        CompiledQuery                   source,
        CompiledExpression              condition,
        RunningFilter                   runningFilter,
        boolean                         aggregate,
        GroupBySpec                     groupBy,
        TupleConstructor                selector,
        TimestampLimits                 tslimits
    )
    {
        super (
            selector == null ?
                source.type :
                new QueryDataType (false, (ClassDataType) selector.type)
        );
        
        this.source = source;
        this.condition = condition;
        this.runningFilter = runningFilter;
        this.aggregate = aggregate;
        this.groupBy = groupBy;
        this.selector = selector;
        this.tslimits = tslimits;
    }

    @Override
    public boolean                      isForward () {
        return (source.isForward ());
    }
    
    @Override
    public void                         getAllTypes (Set <ClassDescriptor> out) {
        source.getAllTypes (out);        
        out.add (selector.getClassDescriptor ());
    }
    
    @Override
    public boolean                      impliesAggregation () {
        return (false);
    }
    
    @Override
    protected void                      print (StringBuilder out) {
        out.append ("select");

        if (runningFilter != RunningFilter.NONE) {
            out.append (" ");
            out.append (runningFilter.name ());
        }

        if (aggregate)
            out.append (" aggregate");

        if (selector != null) {
            out.append (" ");
            selector.print (out);
        }

        out.append (" from ");
        source.print (out);

        if (condition != null) {
            out.append (" ");
            condition.print (out);
        }
        
        if (groupBy != null) {
            out.append (" ");
            out.append (groupBy);
        }
    }
}
