package sc2002_scmb.hms.control.filerepository;

import java.util.Map;

import sc2002_scmb.hms.entity.AppointmentOutcomeRecordEntity;

/**
 * MedicalRecordRepository interface defines the contract for
 * loading and saving medical records to a repository.
 * This interface is implemented by classes that manage the persistence
 * of medical records (e.g., file-based repositories).
 */
public interface MedicalRecordRepository {

    /**
     * Prefix used for generating new medical record IDs.
     * Each record ID starts with this prefix followed by a unique number.
     */
    public static final String PREFIX = "OR";

    /**
     * Loads all medical records from the repository.
     * 
     * @return A map of medical records, where the key is the record ID
     *         and the value is the AppointmentOutcomeRecordEntity.
     */
    Map<String, AppointmentOutcomeRecordEntity> load();

    /**
     * Saves all medical records to the repository.
     * 
     * @param records A map of medical records to be saved, where the key
     *                is the record ID and the value is the
     *                AppointmentOutcomeRecordEntity.
     * @return true if the records were successfully saved, false otherwise.
     */
    boolean save(Map<String, AppointmentOutcomeRecordEntity> records);
}