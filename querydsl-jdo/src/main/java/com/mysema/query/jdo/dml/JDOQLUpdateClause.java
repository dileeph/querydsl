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
package com.mysema.query.jdo.dml;

import java.util.List;

import com.mysema.query.DefaultQueryMetadata;
import com.mysema.query.QueryMetadata;
import com.mysema.query.dml.UpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.ExpressionUtils;
import com.mysema.query.types.NullExpression;
import com.mysema.query.types.Path;
import com.mysema.query.types.Predicate;

/**
 * UpdateClause implementation for JDO
 *
 * @author tiwe
 *
 */
public class JDOQLUpdateClause implements UpdateClause<JDOQLUpdateClause>{

    private final QueryMetadata metadata = new DefaultQueryMetadata();

//    private final PersistenceManager persistenceManager;
//
//    private final JDOQLTemplates templates;

//    public JDOQLUpdateClause(PersistenceManager persistenceManager, PEntity<?> entity) {
//        this(persistenceManager, entity, JDOQLTemplates.DEFAULT);
//    }
//
//    public JDOQLUpdateClause(PersistenceManager persistenceManager, PEntity<?> entity, JDOQLTemplates templates) {
//        this.persistenceManager = persistenceManager;
//        this.templates = templates;
//        metadata.addFrom(entity);
//    }

    @Override
    public long execute() {
        // TODO : implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @SuppressWarnings("unchecked")
    @Override
    public JDOQLUpdateClause set(List<? extends Path<?>> paths, List<?> values) {
        for (int i = 0; i < paths.size(); i++) {
            if (values.get(i) != null) {
                metadata.addProjection(ExpressionUtils.eqConst(((Expression)paths.get(i)), values.get(i)));
            } else {
                metadata.addProjection(ExpressionUtils.eq(((Expression)paths.get(i)), 
                        new NullExpression(paths.get(i).getType())));
            }
        }
        return this;
    }

    @Override
    public <T> JDOQLUpdateClause set(Path<T> path, T value) {
        if (value != null) {
            metadata.addProjection(ExpressionUtils.eqConst(path, value));
        } else {
            metadata.addProjection(ExpressionUtils.eq(path, new NullExpression<T>(path.getType())));
        }
        return this;
    }


    @Override
    public <T> JDOQLUpdateClause set(Path<T> path, Expression<? extends T> expression) {
        metadata.addProjection(ExpressionUtils.eq(path, expression));
        return this;
    }
    
    @Override
    public <T> JDOQLUpdateClause setNull(Path<T> path) {
        metadata.addProjection(ExpressionUtils.eq(path, new NullExpression<T>(path.getType())));
        return this;
    }
    
    @Override
    public JDOQLUpdateClause where(Predicate... o) {
        metadata.addWhere(o);
        return this;
    }


}
