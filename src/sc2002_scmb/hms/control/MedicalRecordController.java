package sc2002_scmb.hms.control;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import sc2002_scmb.hms.control.filerepository.FileMedicalRecordRepository;
import sc2002_scmb.hms.control.filerepository.MedicalRecordRepository;
import sc2002_scmb.hms.entity.AppointmentOutcomeRecordEntity;
import sc2002_scmb.hms.entity.PrescriptionEntity;
import sc2002_scmb.hms.entity.PrescriptionStatus;
import sc2002_scmb.hms.entity.ServiceType;

/**
 * MedicalRecordController class manages the medical records of patients,
 * including creating, updating, and retrieving medical records. This is used by PatientController, DoctorController and AdminController.
 */
public class MedicalRecordController {

    // The repository responsible for loading and saving medical records.
    private MedicalRecordRepository repository;
    
    // A map that holds the appointment records, keyed by their record ID.
    private Map<String, AppointmentOutcomeRecordEntity> records;

    public static void main(String[] args) {
        MedicalRecordController controller = new MedicalRecordController();
        System.out.println("MEDICAL RECORDS CONTROLLER MAIN ");
    }

    /**
     * Constructor for MedicalRecordController. It initializes the repository
     * and loads the medical records from the repository.
     */
    public MedicalRecordController() {
        this.repository = new FileMedicalRecordRepository();
        this.records = repository.load();

        // List<PrescriptionEntity> medicines = new ArrayList<PrescriptionEntity>();
        // medicines.add(new PrescriptionEntity("abc", 3));
        // medicines.add(new PrescriptionEntity("def", 5));
        // AppointmentOutcomeRecordEntity newRecord = createRecord(medicines);
        // newRecord.setConsultationNotes("TEST");
        // System.out.println(newRecord);
        // AppointmentOutcomeRecordEntity entity = new
        // AppointmentOutcomeRecordEntity("OR3", new Date(),
        // ServiceType.SERVICE_BLOOD_TEST, medicines, "test");
        // if(updateRecord(newRecord)) {
        // System.out.println("update entities");
        // }
    }

    /**
     * Retrieves a list of AppointmentOutcomeRecordEntity by their record IDs.
     * 
     * @param recordIds An array of record IDs for which the records are requested.
     * @return A list of AppointmentOutcomeRecordEntity that matches the given record IDs.
     */
    public List<AppointmentOutcomeRecordEntity> getRecordsById(String[] recordIds) {
        List<AppointmentOutcomeRecordEntity> records = new ArrayList<AppointmentOutcomeRecordEntity>();
        for (String recordId : recordIds) {
            records.add(this.records.get(recordId));
        }
        return records;
    }

    /**
     * Creates a new AppointmentOutcomeRecordEntity for a consultation service.
     * 
     * @param prescribedMedications A list of prescribed medications (PrescriptionEntity).
     * @return A new AppointmentOutcomeRecordEntity with a generated ID and consultation service type.
     */
    public AppointmentOutcomeRecordEntity createRecord(List<PrescriptionEntity> prescribedMedications) {
        Date date = new Date(); 
        String recordId = MedicalRecordRepository.PREFIX + Integer.toString(records.size() + 1); 
        return new AppointmentOutcomeRecordEntity(recordId, date, PrescriptionStatus.PRESCRIPTION_PENDING,
                ServiceType.SERVICE_CONSULTATION, prescribedMedications, "");
    }

    /**
     * Updates an existing record with new information and saves it to the repository.
     * 
     * @param record The AppointmentOutcomeRecordEntity to be updated.
     * @return True if the update and save operation were successful, false otherwise.
     */
    public boolean updateRecord(AppointmentOutcomeRecordEntity record) {
        String id = record.getRecordId();
        records.put(id, record);
        return repository.save(records);
    }

    /**
     * Updates the prescription status of a specific record and saves the change to the repository.
     * 
     * @param recordId The ID of the record whose prescription status is being updated.
     * @param status The new PrescriptionStatus to be set for the record.
     * @return True if the update and save operation were successful, false otherwise.
     */
    public boolean updatePrescriptionStatus(String recordId, PrescriptionStatus status) {
        // Stop if the record does not exist
        if (!records.containsKey(recordId))
            return false;
            
        // Update the prescription status
        records.get(recordId).setPrescriptionStatus(status);
        // Save the updated records
        return repository.save(records);
    }

}
