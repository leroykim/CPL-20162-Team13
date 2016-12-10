
package com.example.android.bluetoothchat.observation_bp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Component {

    @SerializedName("code")
    @Expose
    private Code code;
    @SerializedName("valueQuantity")
    @Expose
    private ValueQuantity valueQuantity;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Component() {
    }

    /**
     * 
     * @param code
     * @param valueQuantity
     */
    public Component(Code code, ValueQuantity valueQuantity) {
        this.code = code;
        this.valueQuantity = valueQuantity;
    }

    /**
     * 
     * @return
     *     The code
     */
    public Code getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(Code code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The valueQuantity
     */
    public ValueQuantity getValueQuantity() {
        return valueQuantity;
    }

    /**
     * 
     * @param valueQuantity
     *     The valueQuantity
     */
    public void setValueQuantity(ValueQuantity valueQuantity) {
        this.valueQuantity = valueQuantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
