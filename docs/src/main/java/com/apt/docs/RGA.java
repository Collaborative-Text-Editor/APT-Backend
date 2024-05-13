
package com.apt.docs;

import java.nio.charset.StandardCharsets;
import java.util.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.apt.docs.repository.document_repository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

public class RGA {
    private List<RGAElement> elements;
    private String siteId;
    private int clock;

    @JsonCreator
    public RGA(@JsonProperty("siteId") String siteId,
            @JsonProperty("elements") List<RGAElement> elements,
            @JsonProperty("clock") int clock) {
        this.elements = elements;
        this.siteId = siteId;
        this.clock = clock;
    }

    public String toByteArray() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        return json;
    }

    public String getIdAtIndex(int index) {
        System.out.println("document contentttttttttttttttttttt: ");

        // if (index < 0 ) {
        //     throw new IndexOutOfBoundsException();
        // }
        System.out.println("index: " + index );
        System.out.println("elements " + elements.size());
        System.out.println( "  " + elements.get(index-1).getId());
        return elements.get(index-1).getId();
    }

    public static RGA fromByteArray(byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        String json = new String(bytes, StandardCharsets.UTF_8);
        try {
            return mapper.readValue(json, RGA.class);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse JSON: " + e.getMessage());
            return null;
        }
    }

    public String toText() {
        StringBuilder sb = new StringBuilder();
        for (RGAElement element : elements) {
            if (!element.isDeleted()) {
                sb.append(element.getValue());
            }
        }
        return sb.toString();
    }

    public String addAfter(String id, char value, boolean bold, boolean italic) {
        // increment the logical clock
        clock++;
        // create a new element with a unique identifier

        System.out.println("document contentttttttttttttttttttt: ");
        RGAElement element = new RGAElement(siteId+clock, value, bold, italic, false);  //TODO: check if this is correct

        // find the index of the element with the given id
        int index = findIndexById(id);
        // insert the new element after it
        elements.add(index + 1, element);
        Operation operation = new Operation("add", element.getId(), element.getValue(), element.isBold(),
                element.isItalic());
        // sendOperation(operation);
        try {
            return toByteArray();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        ;
        //////////////////////////////////////////////////////
        /// ERROooooooooooooooooooooooooooooooRR//
        ////////////////////////////////////////////////////
        return null;
    }

    public String remove(String id) throws JsonProcessingException {
        // find the element with the given id
        int index = findIndexById(id);
        // if the element is not found, return the current state
        if (index == -1) {
            return toByteArray();
        }
        // mark the element as deleted
        elements.get(index).setDeleted(true);
        Operation operation = new Operation("remove", id, elements.get(index).getValue(), elements.get(index).isBold(),
                elements.get(index).isItalic());
        // sendOperation(operation);
        // return the new state
        return toByteArray();
    }

    private int findIndexById(String id) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1; // not found
    }

    // @SendTo("/topic/document")
    // private String sendOperation(Operation operation) {
    //     // convert the operation to a JSON string
    //     String json = "";
    //     ObjectMapper mapper = new ObjectMapper();
    //     try {
    //         json = mapper.writeValueAsString(operation);
    //     } catch (JsonProcessingException e) {

    //         e.printStackTrace();
    //     }
    //     // send the JSON string to all other replicas
    //     // template.convertAndSend("/topic/operations", json);
    //     return json;
    // }

    public void applyOperation(Operation operation) {
        if (operation.getType().equals("add")) {
            applyAddOperation(operation);
        } else if (operation.getType().equals("remove")) {
            applyRemoveOperation(operation);
        }
    }

    private void applyAddOperation(Operation operation) {
        // find the index of the element after which the new element should be added
        int index = findIndexById(operation.getId());
        // create a new element
        RGAElement element = new RGAElement(siteId,operation.getValue(), operation.isBold(),
                operation.isItalic(), false); //TODO: checkkkk
        // add the new element to the list of elements
        elements.add(index + 1, element);
    }

    private void applyRemoveOperation(Operation operation) {
        // find the index of the element to remove
        int index = findIndexById(operation.getId());
        // mark the element as deleted
        elements.get(index).setDeleted(true);
    }

    // Getters and Setters
    public List<RGAElement> getElements() {
        return elements;
    }

    public void setElements(List<RGAElement> elements) {
        this.elements = elements;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

}