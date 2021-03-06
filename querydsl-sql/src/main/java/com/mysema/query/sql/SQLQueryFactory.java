/*
 * Copyright 2011, Mysema Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mysema.query.sql;

import com.mysema.query.Query;
import com.mysema.query.QueryFactory;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLMergeClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;

/**
 * Factory interface for query and clause creation
 *
 * @author tiwe
 *
 * @param <Q> query type
 * @param <SQ> subquery type
 * @param <D> delete clause type
 * @param <U> update clause type
 * @param <I> insert clause type
 * @param <M> merge clause type
 */
public interface SQLQueryFactory<Q extends Query<?>, // extends AbstractSQLQuery<?>
    SQ extends AbstractSQLSubQuery<?>,
    D extends SQLDeleteClause,
    U extends SQLUpdateClause,
    I extends SQLInsertClause,
    M extends SQLMergeClause> extends QueryFactory<Q,SQ> {

    D delete(RelationalPath<?> path);

    Q from(Expression<?> from);

    I insert(RelationalPath<?> path);

    M merge(RelationalPath<?> path);

    U update(RelationalPath<?> path);

    Q query();

    SQ subQuery();

    SQ subQuery(Expression<?> from);

}
