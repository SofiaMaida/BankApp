package ar.com.ada.maven.model.DTO;

public class DocumentationDTO {

    private Integer id;
    private String documentation_type;


    public DocumentationDTO(boolean b){

    }

    public DocumentationDTO(Integer id, String documentation_type){
        this.id = id;
        this.documentation_type = documentation_type;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    public String getDocumentation_type() {
        return documentation_type;
    }

    public void setDocumentation_type(String documentation_type) {
        this.documentation_type = documentation_type;
    }

    @Override
    public String toString() {
        return "Documentacion: [-id: " + id + " - Tipo de documento: " + documentation_type + "]";
    }

    @Override
    public int hashCode() {
        return -234 * id.hashCode() + documentation_type.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DocumentationDTO that = (DocumentationDTO) obj;
        return id.equals(that.id) && documentation_type.equals(that.documentation_type);
    }
}
