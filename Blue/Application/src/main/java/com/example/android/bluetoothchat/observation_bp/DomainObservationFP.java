
package com.example.android.bluetoothchat.observation_bp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class DomainObservationFP {

    @SerializedName("resourceType")
    @Expose
    private String resourceType;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("identifier")
    @Expose
    private List<Identifier> identifier = new ArrayList<Identifier>();
    @SerializedName("effectiveDateTime")
    @Expose
    private String effectiveDateTime;
    @SerializedName("bodySite")
    @Expose
    private BodySite bodySite;
    @SerializedName("component")
    @Expose
    private List<Component> component = new ArrayList<Component>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public DomainObservationFP() {
    }

    /**
     * 
     * @param id
     * @param effectiveDateTime
     * @param component
     * @param bodySite
     * @param identifier
     * @param resourceType
     */
    public DomainObservationFP(String resourceType, String id, List<Identifier> identifier, String effectiveDateTime, BodySite bodySite, List<Component> component) {
        this.resourceType = resourceType;
        this.id = id;
        this.identifier = identifier;
        this.effectiveDateTime = effectiveDateTime;
        this.bodySite = bodySite;
        this.component = component;
    }

    /**
     * 
     * @return
     *     The resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 
     * @param resourceType
     *     The resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The identifier
     */
    public List<Identifier> getIdentifier() {
        return identifier;
    }

    /**
     * 
     * @param identifier
     *     The identifier
     */
    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    /**
     * 
     * @return
     *     The effectiveDateTime
     */
    public String getEffectiveDateTime() {
        return effectiveDateTime;
    }

    /**
     * 
     * @param effectiveDateTime
     *     The effectiveDateTime
     */
    public void setEffectiveDateTime(String effectiveDateTime) {
        this.effectiveDateTime = effectiveDateTime;
    }

    /**
     * 
     * @return
     *     The bodySite
     */
    public BodySite getBodySite() {
        return bodySite;
    }

    /**
     * 
     * @param bodySite
     *     The bodySite
     */
    public void setBodySite(BodySite bodySite) {
        this.bodySite = bodySite;
    }

    /**
     * 
     * @return
     *     The component
     */
    public List<Component> getComponent() {
        return component;
    }

    /**
     * 
     * @param component
     *     The component
     */
    public void setComponent(List<Component> component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
