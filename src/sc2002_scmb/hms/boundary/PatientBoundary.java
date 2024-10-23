package sc2002_scmb.hms.boundary;

import sc2002_scmb.hms.control.PatientController;


public class PatientBoundary extends UserBoundary {

    private PatientController patientController;

    public PatientBoundary(PatientController patientController) {
        this.patientController = patientController;
    }

    @Override
    public void displayMenu() {

        
        throw new UnsupportedOperationException("Not supported yet.");
    }

}