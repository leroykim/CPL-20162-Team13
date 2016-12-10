package com.example.android.bluetoothchat;


import android.nfc.Tag;

import com.example.android.bluetoothchat.observation_bp.*;
import com.example.android.common.logger.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.lang.*;
import java.util.*;
import java.text.*;

import static com.example.android.common.activities.SampleActivityBase.TAG;

/**
 * Created by Pc on 2016-11-20.
 */

public class ObservationGson {
    public String onSerialReceived(String theString) {
        DomainObservationFP domainObservationFP = new DomainObservationFP();

        domainObservationFP.setResourceType("Observation");
        domainObservationFP.setId("Oxygen saturation in Arterial blood by Pulse oximetry");

        Identifier identifier = new Identifier();
        List<Identifier> identifierList = new ArrayList<Identifier>();
        identifier.setValue("960924-2895112");
        identifierList.add(identifier);
        domainObservationFP.setIdentifier(identifierList);

        Date dt = new Date();
        SimpleDateFormat formatter_one = new SimpleDateFormat ( "yyyy-MM-dd", Locale.ENGLISH );
        SimpleDateFormat formatter_two = new SimpleDateFormat ( "hh:mm:ss" , Locale.ENGLISH);
        String Time1 = formatter_one.format(dt);
        String Time2 = formatter_two.format(dt);
        domainObservationFP.setEffectiveDateTime(Time1 + "T" + Time2 + "+" + "09:00");

        List<Coding> codingBodysiteList = new ArrayList<Coding>();
        Coding codingBodysite = new Coding();
        codingBodysite.setCode("8885-6");
        codingBodysite.setDisplay("Right Index Finger");
        codingBodysite.setSystem("Heart rate measurement site");
        codingBodysiteList.add(codingBodysite);

        BodySite bodySite = new BodySite();
        bodySite.setCoding(codingBodysiteList);
        domainObservationFP.setBodySite(bodySite);

        List<Coding> codingComponentList = new ArrayList<Coding>();
        Coding codingComponent = new Coding();
        codingComponent.setSystem("BldA");
        codingComponent.setCode("59408-5");
        codingComponent.setDisplay("SaO2 % BldA PulseOx");
        codingComponentList.add(codingComponent);
        Code codeComponent = new Code();
        codeComponent.setCoding(codingComponentList);

        ValueQuantity valueQuantity = new ValueQuantity();
        Long longValue = new Long(Integer.parseInt(theString));
        valueQuantity.setValue(longValue);
        valueQuantity.setUnit("%");

        List<Component> ComponentList = new ArrayList<Component>();
        Component component = new Component();
        component.setCode(codeComponent);
        component.setValueQuantity(valueQuantity);
        ComponentList.add(component);
        domainObservationFP.setComponent(ComponentList);

        Gson gson = new GsonBuilder().create();
        String fhirString = gson.toJson(domainObservationFP);

        return fhirString;
    }
}
