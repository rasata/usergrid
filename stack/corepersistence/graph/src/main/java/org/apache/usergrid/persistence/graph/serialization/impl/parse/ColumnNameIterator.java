package org.apache.usergrid.persistence.graph.serialization.impl.parse;


import java.util.Iterator;
import java.util.NoSuchElementException;

import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.Column;
import com.netflix.astyanax.query.RowQuery;


/**
 *
 * Simple iterator that wraps a Row query and will keep executing it's paging until there are no more
 * results to read from cassandra
 *
 *
 */
public class ColumnNameIterator<C, T> implements Iterable<T>, Iterator<T> {



    private final RowQuery<?, C> rowQuery;
    private final ColumnParser<C, T> parser;

    private Iterator<Column<C>> sourceIterator;



    public ColumnNameIterator( RowQuery<?, C> rowQuery, final ColumnParser<C, T> parser, final boolean skipFirst ) {
        this.rowQuery = rowQuery.autoPaginate( true );
        this.parser = parser;

        advanceIterator();

        //if we are to skip the first element, we need to advance the iterator
        if(skipFirst && sourceIterator.hasNext()){
            sourceIterator.next();
        }
    }



    @Override
    public Iterator<T> iterator() {
        return this;
    }


    @Override
    public boolean hasNext() {
        //if we've exhausted this iterator, try to advance to the next set
        if(sourceIterator.hasNext()){
           return true;
        }

        //advance the iterator, to the next page, there could be more
        advanceIterator();

        return sourceIterator.hasNext();
    }


    @Override
    public T next() {

        if(!hasNext()){
            throw new NoSuchElementException();
        }

        return parser.parseColumn(sourceIterator.next());
    }


    @Override
    public void remove() {
       sourceIterator.remove();
    }


    /**
     * Execute the query again and set the reuslts
     */
    private void advanceIterator(){
        try {
            sourceIterator = rowQuery.execute().getResult().iterator();
        }
        catch ( ConnectionException e ) {
            throw new RuntimeException("Unable to execute query", e);
        }
    }
}
