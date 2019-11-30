package si.fri.escooterental.escootercatalog.models.converters;
import si.fri.rso.escooterental.escootercatalog.lib.EscooterCatalog;
import si.fri.escooterental.escootercatalog.models.entities.EscooterCatalogEntity;
public class EscooterCatalogConverter {
    /*Conver escootercatalog lib to dto(data transfer object) */
    public static EscooterCatalog toDto(EscooterCatalogEntity entity){
        EscooterCatalog dto = new EscooterCatalog();
        dto.setScooterId(entity.getId());
        dto.setScooterName(entity.getName());
        dto.setScooterStatus(entity.getStatus());
        dto.setScooterDescription(entity.getDescription());
        dto.setScooterPrice(entity.getPrice());
        dto.setPickupLocation(entity.getPickuplocation());
        dto.setLeaveLocation(entity.getLeavelocation());

        return dto;
    }

    public static EscooterCatalogEntity toEntity(EscooterCatalog dto){
        EscooterCatalogEntity entity = new EscooterCatalogEntity();
        entity.setId(dto.getScooterId());
        entity.setName(dto.getScooterName());
        entity.setStatus(dto.getScooterStatus());
        entity.setDescription(dto.getScooterDescription());
        entity.setPrice(dto.getScooterPrice());
        entity.setLeavelocation(dto.getLeaveLocation());
        entity.setPickuplocation(dto.getPickupLocation());

        return entity;
    }

}
