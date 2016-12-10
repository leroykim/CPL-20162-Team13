
package com.example.android.bluetoothchat.observation_bp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Code {

    @SerializedName("coding")
    @Expose
    private List<Coding> coding = new ArrayList<Coding>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Code() {
    }

    /**
     * 
     * @param coding
     */
    public Code(List<Coding> coding) {
        this.coding = coding;
    }

    /**
     * 
     * @return
     *     The coding
     */
    public List<Coding> getCoding() {
        return coding;
    }

    /**
     * 
     * @param coding
     *     The coding
     */
    public void setCoding(List<Coding> coding) {
        this.coding = coding;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
