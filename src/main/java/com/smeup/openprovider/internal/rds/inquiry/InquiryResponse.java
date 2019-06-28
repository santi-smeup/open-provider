/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.openprovider.internal.rds.inquiry;

import it.smea.transformer.smeupobj.SmeupObject;
import it.smea.transformer.table.Column;
import it.smea.transformer.table.SmeupTable;
import it.smea.transformer.table.SmeupTableImpl;
import it.smea.transformer.tree.SmeupTreeNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InquiryResponse {
  private List<ColMetadata> metaData;
  private List<DataRow> data;
  private String inquiryId;
  private String inquiryDescr;
  private long globalSize;
  
  public InquiryResponse(){
    setData(new ArrayList<DataRow>());
    setMetaData(new ArrayList<ColMetadata>());
  }
  
  public SmeupTable toTable(){
    SmeupTable smeupTable = new SmeupTableImpl();
    Map<String, Column> mapMetaData = new LinkedHashMap<>();
    for(ColMetadata cm: getMetaData()){
      Column col = new Column(cm.getColId());
      col.setText(cm.getColDescr());
      mapMetaData.put(cm.getColId(), col);       
    }
    smeupTable.getColumnsMap().putAll(mapMetaData);
 
    for(DataRow dr: getData()){
      Map<String, SmeupObject> mapData = new LinkedHashMap<>();
      int i = 0;
      for(String val: dr.getRow()){
        boolean numeric = getMetaData().get(i).isNumeric();
        SmeupObject obj = new SmeupObject((numeric?"NR":"") + ";;" + val);
        mapData.put(getMetaData().get(i).getColId(), obj);
        i++;
      }
      smeupTable.addRow(mapData);      
    }
    return smeupTable;
  }

  
  public SmeupTreeNode toTree(){
    SmeupTreeNode tree = new SmeupTreeNode();
    tree.setContent(new SmeupObject());
    tree.setCodice(getInquiryId());
    tree.setTesto(getInquiryDescr());  
    
    for(DataRow dr: getData()){
      SmeupTreeNode child = new SmeupTreeNode();
      child.setContent(new SmeupObject());
      child.setCodice(dr.getRow().get(0));
      child.setTesto(dr.getRow().get(1));      
      tree.add(child);      
    }    
    return tree;
  }  

  public List<DataRow> getData() {
    return data;
  }

  public void setData(List<DataRow> data) {
    this.data = data;
  }

  public List<ColMetadata> getMetaData() {
    return metaData;
  }

  public void setMetaData(List<ColMetadata> metaData) {
    this.metaData = metaData;
  }

  public String getInquiryId() {
    return inquiryId;
  }

  public void setInquiryId(String inquiryId) {
    this.inquiryId = inquiryId;
  }

  public String getInquiryDescr() {
    return inquiryDescr;
  }

  public void setInquiryDescr(String inquiryDescr) {
    this.inquiryDescr = inquiryDescr;
  }

  public long getGlobalSize() {
    return globalSize;
  }

  public void setGlobalSize(long globalSize) {
    this.globalSize = globalSize;
  }
  
  public static class ColMetadata{
    private String colId;
    private String colDescr;
    private int colSize;  
    private boolean numeric;

    public String getColId() {
      return colId;
    }

    public void setColId(String colId) {
      this.colId = colId;
    }

    public String getColDescr() {
      return colDescr;
    }

    public void setColDescr(String colDescr) {
      this.colDescr = colDescr;
    }

    public int getColSize() {
      return colSize;
    }

    public void setColSize(int colSize) {
      this.colSize = colSize;
    }

    public boolean isNumeric() {
      return numeric;
    }

    public void setNumeric(boolean numeric) {
      this.numeric = numeric;
    }
  }
  
  public static class DataRow{
    private List<String> row;

    public DataRow(){
      setRow(new ArrayList<String>());
    }
    public List<String> getRow() {
      return row;
    }

    public void setRow(List<String> row) {
      this.row = row;
    }
  }
}
