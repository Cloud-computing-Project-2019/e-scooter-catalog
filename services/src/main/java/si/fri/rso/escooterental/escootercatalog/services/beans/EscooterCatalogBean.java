package si.fri.rso.escooterental.escootercatalog.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.escooterental.escootercatalog.lib.EscooterCatalog;
import si.fri.escooterental.escootercatalog.models.converters.EscooterCatalogConverter;
import si.fri.escooterental.escootercatalog.models.entities.EscooterCatalogEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class EscooterCatalogBean {
    private Logger log = Logger.getLogger(EscooterCatalogBean.class.getName());

    @Inject
    private EntityManager em;

    private Client httpClient;

    private String baseUrl;

    /* Ako zelim da pozovem drugi service, da bi radilo, prije nego napravim service discovery*/
    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://localhost:8081"; // only for demonstration
    }

    /*Vraca ce mi sve skutere iz baze */
    public List<EscooterCatalog> getEscooterCatalog(){
        TypedQuery<EscooterCatalogEntity> query = em.createNamedQuery("EscooterCatalogEntity.getAll",EscooterCatalogEntity.class);

        return query.getResultList().stream().map(EscooterCatalogConverter::toDto).collect(Collectors.toList());
    }

    public List<EscooterCatalog> getEscooterCatalog(UriInfo uriInfo){
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();
        return JPAUtils.queryEntities(em,EscooterCatalogEntity.class,queryParameters).stream().map(EscooterCatalogConverter::toDto).collect(Collectors.toList());
    }

    public EscooterCatalog getEscooter(Integer id){
        EscooterCatalogEntity entity = em.find(EscooterCatalogEntity.class,id);
        if (entity == null){
            throw new NotFoundException();
        }

        EscooterCatalog escooter = EscooterCatalogConverter.toDto(entity);
        return escooter;
    }

    public EscooterCatalog createEscooterCatalog(EscooterCatalog escooter){
        EscooterCatalogEntity entity = EscooterCatalogConverter.toEntity(escooter);
        try {
            beginTx();
            em.persist(entity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        if (entity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return EscooterCatalogConverter.toDto(entity);
    }

    public EscooterCatalog putEscooterCatalog(Integer id, EscooterCatalog escooter) {

        EscooterCatalogEntity c = em.find(EscooterCatalogEntity.class, id);

        if (c == null) {
            return null;
        }

        EscooterCatalogEntity updatedEntity = new EscooterCatalogEntity();

        try {
            beginTx();
            updatedEntity.setId(c.getId());
            updatedEntity = em.merge(updatedEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return EscooterCatalogConverter.toDto(updatedEntity);
    }

    public boolean deleteEscooterCatalog(Integer id){
        EscooterCatalogEntity entity = em.find(EscooterCatalogEntity.class,id);
        if (entity != null) {
            try {
                beginTx();
                em.remove(entity);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }

















}
