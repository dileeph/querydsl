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
package com.mysema.query.types.expr;

import java.util.ArrayList;
import java.util.List;

import com.mysema.query.types.ConstantImpl;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Ops;
import com.mysema.query.types.Visitor;

/**
 * Coalesce defines a coalesce function invocation. The coalesce function
 * returns null if all arguments are null and the first non-null argument
 * otherwise
 *
 * @author tiwe
 *
 * @param <T> expression type
 */
@SuppressWarnings("unchecked")
public class Coalesce<T extends Comparable> extends ComparableExpression<T>{

    private static final long serialVersionUID = 445439522266250417L;

    private final List<Expression<? extends T>> exprs = new ArrayList<Expression<? extends T>>();

    public Coalesce(Class<? extends T> type, Expression...exprs){
        // NOTE : type parameters for the varargs, would result in compiler warnings
        super(type);
        for (Expression expr : exprs){
            add(expr);
        }
    }

    public Coalesce(Expression... exprs){
        // NOTE : type parameters for the varargs, would result in compiler warnings
        this((exprs.length > 0 ? exprs[0].getType() : Object.class), exprs);
    }

    @Override
    public <R,C> R accept(Visitor<R,C> v, C context) {
        return SimpleOperation.create(getType(), Ops.COALESCE, getExpressionList()).accept(v, context);
    }

    public final Coalesce<T> add(Expression<T> expr){
        this.exprs.add(expr);
        return this;
    }

    public final Coalesce<T> add(T constant){
        this.exprs.add(new ConstantImpl<T>(constant));
        return this;
    }

    public DateExpression<T> asDate(){
        return (DateExpression<T>) DateOperation.create(getType(), Ops.COALESCE, getExpressionList());
    }

    public DateTimeExpression<T> asDateTime(){
        return (DateTimeExpression<T>) DateTimeOperation.create(getType(), Ops.COALESCE, getExpressionList());
    }

    public NumberExpression<?> asNumber(){
        return NumberOperation.create((Class)getType(), Ops.COALESCE, getExpressionList());
    }

    public StringExpression asString(){
        return StringOperation.create(Ops.COALESCE, getExpressionList());
    }

    public TimeExpression<T> asTime(){
        return (TimeExpression<T>) TimeOperation.create(getType(), Ops.COALESCE, getExpressionList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Coalesce<?>) {
            Coalesce<?> c = (Coalesce<?>)o;
            return c.exprs.equals(exprs);
        } else {
            return false;
        }
    }

    private Expression<?> getExpressionList(){
        Expression<?> arg = exprs.get(0);
        for (int i = 1; i < exprs.size(); i++) {
            arg = SimpleOperation.create(List.class, Ops.LIST, arg, exprs.get(i));
        }
        return arg;
    }

    @Override
    public int hashCode(){
        return exprs.hashCode();
    }

}
