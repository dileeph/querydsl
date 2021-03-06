/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.ddl;

/**
 * @author tiwe
 *
 */
public class IndexData {

    private final String name;
    
    private final String[] columns;

    private boolean unique;
    
    public IndexData(String name, String[] columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public String[] getColumns() {
        return columns;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
    
    
}
