package org.codeworks.web.tools.dashboard.myspace.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Assert {

    /**
     * 是
     */
    yes((byte)1),
    /**
     * 否
     */
    no((byte)0);

    Assert(Byte value){this.value = value;}

    Byte value;

    @JsonCreator
    public static Assert parse(Byte value){
        for(Assert a: values())
            if(a.value.equals(value))
                return a;
        return null;
    }

    @JsonValue
    public Byte value(){return value;}

}
