package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

public class Dokumenty {

    private Integer documentId;
    private Integer clientId;
    private String name;
    private byte[] content;
    private LocalDate created;
    private String info;
    private Integer type;

    public static Integer getTypeId(String typeString){
        if (typeString.equals("Smlouva s klientem")){
            return 1;
        }
        else if (typeString.equals("Interni dokumentace")){
            return 2;
        }
        else {
            return 3;
        }
    }

    public static RowMapper<Dokumenty> getDokumentyMapper(){
        return (rs, rowNum) -> {
            Dokumenty dokumenty = new Dokumenty();
            dokumenty.setClientId(rs.getInt("KLIENT_ID"));
            dokumenty.setDocumentId(rs.getInt("ID"));
            dokumenty.setName(rs.getString("NAZEV"));
            dokumenty.setCreated(rs.getDate("DATUM_VZNIKU").toLocalDate());
            dokumenty.setContent(rs.getBytes("OBSAH"));
            dokumenty.setInfo(rs.getString("POPIS"));
            return dokumenty;
        };
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
