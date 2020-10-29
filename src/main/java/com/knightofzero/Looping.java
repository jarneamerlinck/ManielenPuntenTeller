package com.knightofzero;

import org.w3c.dom.*;

import java.util.ArrayList;

public abstract class Looping {
    private int index;
    private ArrayList<Integer> indexs;
    private String[] player;

    public Looping(ArrayList<Integer> indexs) {
        this.indexs=indexs;
    }
    public Looping(int index) {
        this.index=index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<Integer> getIndexs() {
        return indexs;
    }

    public void setIndexs(ArrayList<Integer> indexs) {
        this.indexs = indexs;
    }

    public abstract String toRun(NodeList nodelist);//TODO return type mss verkeerd of beter anders
    public NodeList getChilds(NodeList nodeList){
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node p = nodeList.item(i);
            if (p.getNodeType()==Node.ELEMENT_NODE) {
                Element person = (Element) p;
                NodeList PersonUnderList = person.getChildNodes();
                    /*
                    if (PersonUnderList.getLength()!=0){
                        player[getIndex()] = toRun(PersonUnderList);
                    }
                    else{
                        System.out.println("Error");
                    }*/
                //TODO WIP
                return PersonUnderList;

            }
        }
        return null;
    }
    public NodeList getNodes(NodeList nodeList){
        NodeList toReturn=nodeList;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node p = nodeList.item(i);
            if (p.getNodeType()==Node.ELEMENT_NODE) {

            }
        }
        return toReturn;
    }
    public ArrayList<Element> getElements(NodeList nodeList){
        ArrayList<Element> elements=new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node p = nodeList.item(i);
            if (p.getNodeType()==Node.ELEMENT_NODE) {
                elements.add((Element) p);
            }
        }
        return elements;
    }
    public String printAll(NodeList nodeList){
        String print="";
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node p = nodeList.item(i);
            if (p.getNodeType()==Node.ELEMENT_NODE) {
                Element person = (Element) p;
                setIndex(getIndex()+1);
                NodeList PersonSubList = person.getChildNodes();
                print+=toRun(PersonSubList)+"\n";
            }
        }
        return print;
    }
}
