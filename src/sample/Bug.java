package sample;

import javafx.beans.property.SimpleStringProperty;

public class Bug {

    private SimpleStringProperty id;
    private SimpleStringProperty dateEntered;
    private SimpleStringProperty project;
    private SimpleStringProperty version;
    private SimpleStringProperty company;
    private SimpleStringProperty lastUpdate;
    private SimpleStringProperty status;
    private SimpleStringProperty description;

    public Bug(){

    }

    public Bug(String id, String dateEntered, String project, String version, String company, String lastUpdate, String status, String description) {
        this.id = new SimpleStringProperty(id);
        this.dateEntered = new SimpleStringProperty(dateEntered);
        this.project = new SimpleStringProperty(project);
        this.version = new SimpleStringProperty(version);
        this.company = new SimpleStringProperty(company);
        this.lastUpdate = new SimpleStringProperty(lastUpdate);
        this.status = new SimpleStringProperty(status);
        this.description = new SimpleStringProperty(description);
    }


    public String getId() {
        return id.get();
    }

//    public SimpleStringProperty idProperty() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id.set(id);
//    }

    public String getDateEntered() {
        return dateEntered.get();
    }

//    public SimpleStringProperty dateEnteredProperty() {
//        return dateEntered;
//    }

//    public void setDateEntered(String dateEntered) {
//        this.dateEntered.set(dateEntered);
//    }

    public String getProject() {
        return project.get();
    }

//    public SimpleStringProperty projectProperty() {
//        return project;
//    }

//    public void setProject(String project) {
//        this.project.set(project);
//    }

    public String getVersion() {
        return version.get();
    }

//    public SimpleStringProperty versionProperty() {
//        return version;
//    }

//    public void setVersion(String version) {
//        this.version.set(version);
//    }

//    public String getCompany() {
//        return company.get();
//    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

//    public void setCompany(String company) {
//        this.company.set(company);
//    }

    public String getLastUpdate() {
        return lastUpdate.get();
    }

//    public SimpleStringProperty lastUpdateProperty() {
//        return lastUpdate;
//    }

//    public void setLastUpdate(String lastUpdate) {
//        this.lastUpdate.set(lastUpdate);
//    }

    public String getStatus() {
        return status.get();
    }

//    public SimpleStringProperty statusProperty() {
//        return status;
//    }

//    public void setStatus(String status) {
//        this.status.set(status);
//    }

    public String getDescription() {
        return description.get();
    }

//    public SimpleStringProperty descriptionProperty() {
//        return description;
//    }

//    public void setDescription(String description) {
//        this.description.set(description);
//    }

    @Override
    public String toString() {
        return "Bug{" +
                "id=" + id +
                ", dateEntered=" + dateEntered +
                ", project=" + project +
                ", version=" + version +
                ", company=" + company +
                ", lastUpdate=" + lastUpdate +
                ", status=" + status +
                ", description=" + description +
                '}';
    }
}
