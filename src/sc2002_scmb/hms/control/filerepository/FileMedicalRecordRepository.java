package sc2002_scmb.hms.control.filerepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

import sc2002_scmb.hms.entity.AppointmentOutcomeRecordEntity;
import sc2002_scmb.hms.entity.PrescriptionEntity;
import sc2002_scmb.hms.entity.PrescriptionStatus;
import sc2002_scmb.hms.entity.ServiceType;
import sc2002_scmb.hms.util.FileHandler;

/**
 * FileMedicalRecordRepository is a file-based implementation of the
 * MedicalRecordRepository interface. It provides methods to load and save
 * medical records from and to a text file.
 */
public class FileMedicalRecordRepository implements MedicalRecordRepository {

    private static final String FILE_NAME = "files/MedicalRecords.txt";
    private static final String SEPARATOR = "|";

    /**
     * Loads all medical records from the file.
     * 
     * @return A map of medical records where the key is the record ID and the value
     *         is the AppointmentOutcomeRecordEntity.
     */
    @Override
    public Map<String, AppointmentOutcomeRecordEntity> load() {
        Map<String, AppointmentOutcomeRecordEntity> records = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            List<String> lines = FileHandler.loadTextFile(FILE_NAME);
            for (String line : lines) {
                String[] args = line.split("\\" + SEPARATOR);
                String recordId = args[0];
                Date date = formatter.parse(args[1]);
                PrescriptionStatus prescriptionStatus = PrescriptionStatus.valueOf(args[2]);
                ServiceType serviceType = ServiceType.valueOf(args[3]);

                String[] medicineArgs = args[4].split(",");
                List<PrescriptionEntity> prescriptionEntities = new ArrayList<>();
                for (String medicineArg : medicineArgs) {
                    String[] medicine = medicineArg.split(":");
                    PrescriptionEntity prescription = new PrescriptionEntity(medicine[0],
                            Integer.parseInt(medicine[1]));
                    prescriptionEntities.add(prescription);
                }

                String consultationNotes = args[5];
                AppointmentOutcomeRecordEntity record = new AppointmentOutcomeRecordEntity(recordId, date, prescriptionStatus, serviceType,
                        prescriptionEntities, consultationNotes);
                records.put(record.getRecordId(), record);
            }

            System.out.println("Loaded Records: " + records.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    /**
     * Saves all medical records to the file.
     * 
     * @param records A map of medical records to be saved, where the key is the
     *                record ID and the value is the AppointmentOutcomeRecordEntity.
     * @return true if the records were successfully saved, false otherwise.
     */
    @Override
    public boolean save(Map<String, AppointmentOutcomeRecordEntity> records) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<String> lines = new ArrayList<>();

        try {
            for (AppointmentOutcomeRecordEntity record : records.values()) {
                StringBuilder line = new StringBuilder();

                line.append(record.getRecordId()).append(SEPARATOR);
                line.append(formatter.format(record.getDate())).append(SEPARATOR);
                line.append(record.getPrescriptionStatus().name()).append(SEPARATOR);
                line.append(record.getServiceType().name()).append(SEPARATOR);

                List<PrescriptionEntity> prescriptions = record.getPrescribedMedications();
                List<String> prescriptionStrings = new ArrayList<>();
                for (PrescriptionEntity prescription : prescriptions) {
                    prescriptionStrings.add(prescription.getName() + ":" + prescription.getQuantity());
                }
                line.append(String.join(",", prescriptionStrings)).append(SEPARATOR);
                line.append(record.getConsultationNotes());
                lines.add(line.toString());
            }

            FileHandler.saveTextFile(FILE_NAME, lines);

            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
