package org.csu.mypetstore.domain;

public class Record {
    private String username;
    private String record;
    public Record(){

    }
    public Record(String username,String record){
        this.username = username;
        this.record = record;
    }
    public String getUsername(){
        return username;
    }
    public String getRecord(){
        return record;
    }
    public void setUsername(String username){
        this.username =username;
    }
    public void setRecord(String record){
        this.record = record;
    }
}
