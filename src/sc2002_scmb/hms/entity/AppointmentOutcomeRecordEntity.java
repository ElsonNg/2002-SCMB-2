package sc2002_scmb.hms.entity;

import java.util.Date;
import java.util.List;

public class AppointmentOutcomeRecordEntity {
    
    private String recordId;
    private Date date;
    private PrescriptionStatus prescriptionStatus;
    private ServiceType serviceType;
    private List<PrescriptionEntity> prescribedMedications;
    private String consultationNotes;


    public AppointmentOutcomeRecordEntity(String recordId, Date date, PrescriptionStatus prescriptionStatus, ServiceType serviceType, List<PrescriptionEntity> prescribedMedications, String consultationNotes) {
        this.recordId = recordId;
        this.date = date;
        this.prescriptionStatus = prescriptionStatus;
        this.serviceType = serviceType;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public List<PrescriptionEntity> getPrescribedMedications() {
        return prescribedMedications;
    }

    public void setPrescribedMedications(List<PrescriptionEntity> prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public PrescriptionStatus getPrescriptionStatus() {
        return this.prescriptionStatus;
    }

    public void setPrescriptionStatus(PrescriptionStatus status) {
        this.prescriptionStatus = status;
    }

    public String toString() {
        return "(" + recordId + ", " + date.toString() + ", " + serviceType + ", " + Integer.toString(prescribedMedications.size()) + ")";
    }
}