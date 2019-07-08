package com.code4ro.legalconsultation.models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
public class DocumentMetadata extends BaseEntity {

    @Column(name = "document_number", unique=true, nullable=false)
    private BigInteger documentNumber;

    @Column(name = "document_title", nullable=false)
    private String documentTitle;

    @Column(name = "document_initializer", nullable=false)
    private String documentInitializer;

    @Column(name = "document_type")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "date_of_development", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dateOfDevelopment;

    @Column(name ="date_of_receipt", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dateOfReceipt;

    public BigInteger getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(BigInteger documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocumentInitializer() {
        return documentInitializer;
    }

    public void setDocumentInitializer(String documentInitializer) {
        this.documentInitializer = documentInitializer;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Date getDateOfDevelopment() {
        return dateOfDevelopment;
    }

    public void setDateOfDevelopment(Date dateOfDevelopment) {
        this.dateOfDevelopment = dateOfDevelopment;
    }

    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(Date dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }
}
